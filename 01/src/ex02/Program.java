public class Program {

    public static void main(String[] args) {

        UsersArrayList base = new UsersArrayList();
        int i = 0;
        for (; i < 15; i++) {
            base.addUser(new User("user" + i));
        }
        for (i = 0; i < 15; i++) {
            try {
                base.getUserByIndex(i).printAll();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            base.getUserByID(1000).printAll();
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(base.NumberOfUsers());
    }
}
