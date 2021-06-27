package edu.school21.printer.app;

import edu.school21.printer.logic.Recoder;

public class Main {

    public static void main(String[] args) {
        if (!checkArgs(args))
            errorFun();
        Recoder a = new Recoder(args[0].charAt(0), args[1].charAt(0));
        a.print();
    }

    public static boolean checkArgs(String[] args)
    {
        if (args.length != 2)
            return false;
        if (args[0].length() != 1 && args[1].length() != 1)
            return false;
        return true;
    }

    public static void errorFun()
    {
        System.err.println("Error args");
        System.exit(-1);
    }
}
