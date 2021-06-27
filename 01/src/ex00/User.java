public class User {
    private static int counter = 0;
    private Integer id;
    private String name;
    private Integer balance;

    {
        id = ++counter;
    }
    private User() { }

    public User(String name)
    {
        this(name, 0);
    }

    public User(String name1, Integer balance)
    {
        name = name1;
        this.balance = balance;
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
    public static void errorFun(){
        System.err.println("User balance error");
        System.exit(-1);
    }
}
