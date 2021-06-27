public class Program {

    public static void main(String[] args) {
        System.out.println("_________________________________________");
        User first = new User("first", 500);
        User second = new User("second", 500);
        Transaction t1 = new Transaction(first, second, Category.DEBIT, 100);
        t1.printTransaction();
        Transaction t2 = new Transaction(second, first, Category.CREDIT, -100);
        t2.printTransaction();
        second.setBalance(100);
        first.printAll();
        second.printAll();
        System.out.println("_________________________________________");
        User petya = new User("Petya");
        System.out.println(petya.getBalance());
        petya.setBalance(1000000);
        System.out.println(petya.getBalance());
        System.out.println("_________________________________________");
        User nepetya = new User("nepetya");
        System.out.println(nepetya.getBalance());
        nepetya.setBalance(-555);
        System.out.println(nepetya.getBalance());
    }
}
