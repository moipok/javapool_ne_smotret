public class Program {

    public static void main(String[] args) {
        int count = getCount(args);

        Treads a = new Treads("Egg", count);
        Treads b = new Treads("Hen", count);
        a.start();
        b.start();
        try {
            a.join();
            b.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < count; i++)
            System.out.println("Human");

    }

    public static int getCount(String[] args)
    {
        if (args.length != 1)
            errorFun();
        if (args[0].indexOf("--count=") != 0 || args[0].length() <= 8 || args[0].lastIndexOf("=") != 7)
            errorFun();
        int count = 0;
        try {
            count = Integer.parseInt(args[0].split("=")[1]);
        } catch (NumberFormatException e) {
            errorFun();
        }
        if (count <= 0)
            errorFun();
        return count;
    }
    public static void errorFun(){
        System.err.println("Error args");
        System.exit(-1);
    }
}
