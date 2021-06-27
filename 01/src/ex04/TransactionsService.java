import java.util.UUID;

public class TransactionsService {
    private UsersList list;

    public TransactionsService() {
        list = new UsersArrayList();
    }
    
    public void addUser(String name, int balance)
    {
        User a = new User(name, balance);
        list.addUser(a);
        System.out.print("Add ");
        a.printAll();
    }

    public void addUser(User a)
    {
        list.addUser(a);
        System.out.print("Add ");
        a.printAll();
    }

    public void printAllUsers() throws UserNotFoundException {
        for (int i = 0; i < list.NumberOfUsers(); i++)
            list.getUserByIndex(i + 1).printAll();
    }

    public int getBalanceUser(int id) throws UserNotFoundException {
        return list.getUserByID(id).getBalance();
    }

    public void performTrans(int id_from, int id_to, int amound)
            throws UserNotFoundException, IllegalTransactionException {
        if (getBalanceUser(id_from) < amound)
            throw new IllegalTransactionException(id_from);
        UUID num = UUID.randomUUID();
        User us1 = list.getUserByID(id_from);
        User us2 = list.getUserByID(id_to);
        Transaction t1 = new Transaction(num,  us1, us2, Category.DEBIT, amound);
        Transaction t2 = new Transaction(num,  us2, us1, Category.CREDIT, -amound);
        newBalance(us1, -amound);
        newBalance(us2, amound);
        us1.getTran().addTransaction(t1);
        us2.getTran().addTransaction(t2);
    }

    private void newBalance(User user, int amound){
        user.setBalance(user.getBalance() + amound);
    }
    private int lenOfTrans(int id) throws UserNotFoundException {
        Transaction[] a = list.getUserByID(id).getTran().toArray();
        int len = 0;
        for (Transaction transaction : a) {
            if (transaction.getRecipient().getId() == id)
                len++;
        }
        return len;
    }

    public Transaction[] getTransUser(int id) throws UserNotFoundException {
        int len = lenOfTrans(id);
        Transaction[] arr = new Transaction[len];
        Transaction[] a = list.getUserByID(id).getTran().toArray();
        int j = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i].getRecipient().getId() == id) {
                arr[j] = a[i];
                j++;
            }
        }
        return arr;
    }

    public void removeTrans(int id, String uid) throws UserNotFoundException {
        list.getUserByID(id).getTran().removeTransactionByID(UUID.fromString(uid));
    }

    private boolean checkUni(Transaction[] tr, int it)
    {
        int i = 0;
        UUID a = tr[it].getId();

        while (i < tr.length)
        {
            if (i == it){
                i++;
                continue;
            }
            if (a.equals(tr[i].getId()))
                return true;
            i++;
        }
        return false;
    }

    public Transaction[] checkValidity() throws UserNotFoundException {
        TransactionsLinkedList all = new TransactionsLinkedList();
        Transaction[] tmp;
        for (int i = 0; i < list.NumberOfUsers(); i++) {
                tmp = list.getUserByIndex(i + 1).getTran().toArray();
                for (int j = 0; j < tmp.length; j++)
                    all.addTransaction(tmp[j]);
        }
        Transaction[] tr = all.toArray();
        TransactionsLinkedList val = new TransactionsLinkedList();
        for (int i = 0; i < tr.length; i++)
        {
            if (!checkUni(tr, i))
                val.addTransaction(tr[i]);
        }
        return val.toArray();
    }
}



class IllegalTransactionException extends RuntimeException{
    private int id;
    public IllegalTransactionException(int i)
    {
        this.id = i;
    }
    public void printStackTrace(){
        System.err.println("IllegalTransactionException - id = "+ id);
    }
}