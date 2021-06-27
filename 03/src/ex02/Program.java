public class Program {

    public static void main(String[] args) {
        if (args.length != 2)
            errorFun();
        int len = getCount(args[0], "--arraySize=");
        int count = getCount(args[1], "--threadsCount=");


        int[] array = new int[len + 1];
        long sum_first = 0;
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) Math.round((Math.random() * 20) - 10);
            sum_first += array[i];
        }
        System.out.println("SUM = " + sum_first);


        int len_to_thread = (len / count);
        SumTh[] tr = new SumTh[count];
        int index = 0;
        for (int i = 0; i < count; i++)
        {
            boolean a = false;
            if (i == count - 1)
                a = true;
            tr[i] = new SumTh(array, index , len_to_thread, a);
            index = index + len_to_thread;
            tr[i].start();
        }


        long sum_tr = 0;
        for (int i = 0; i < count; i++)
        {
            try {
                tr[i].join();
                sum_tr += tr[i].getValue();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("SUM 2 = " + sum_tr);
    }

    public static int getCount(String args, String what)
    {
        if (args.isEmpty())
            errorFun();
        if (args.indexOf(what) != 0)
            errorFun();
        int count = 0;
        try {
            count = Integer.parseInt(args.split("=")[1]);
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
