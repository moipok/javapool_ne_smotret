public class User {
    private Integer id;
    private String name;
    private Integer balance;
    private TransactionsList tran;

    private User() { }

    public User(String name)
    {
        this(name, 0);
    }

    public User(String name1, Integer balance)
    {
        this.id = UserIdsGenerator.getInstance().generateId();
        name = name1;
        this.balance = balance;
        tran = new TransactionsLinkedList();
        if (balance < 0)
            errorFun();
    }


    public String getName() {
        return name;
    }

    public void setName(String name1) {
        name = name1;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Integer getId() {
        return id;
    }

    public void printAll() {
        System.out.println("User " + this.id + ": name - " + this.name + ", balance = " + this.balance);
    }
    public static void errorFun() {
        System.err.println("User balance error");
        System.exit(-1);
    }

    public TransactionsList getTran() {
        return tran;
    }
}
