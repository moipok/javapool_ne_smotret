public class Program {

    public static void main(String[] args) {
        User a = new User("a");
        User b = new User("b");
        User c = new User("c");


        Transaction t1 = new Transaction(a, b, Category.DEBIT, 10);
        Transaction t2 = new Transaction(b, a, Category.CREDIT, -10);
        Transaction t3 = new Transaction(a, c, Category.DEBIT, 11);
        Transaction t4 = new Transaction(c, a, Category.CREDIT, -11);

        a.getTran().addTransaction(t1);
        a.getTran().addTransaction(t2);
        a.getTran().addTransaction(t3);
        a.getTran().addTransaction(t4);

        a.getTran().removeTransactionByID(t1.getId());
        Transaction[] list = a.getTran().toArray();

        for (int i = 0; i < list.length; i++)
        {
            list[i].printTransaction();
        }

    }
}
