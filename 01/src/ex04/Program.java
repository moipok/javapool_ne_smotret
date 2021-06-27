import java.util.UUID;

public class Program {

    public static void main(String[] args) {

        TransactionsService service = new TransactionsService();

        service.addUser("petya", 500);
        User a = new User("bob", 200);
        service.addUser(a);
        try {
            service.printAllUsers();
            System.out.println(service.getBalanceUser(1));
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }


    }
}
