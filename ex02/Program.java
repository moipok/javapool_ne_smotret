import java.util.Scanner;

import static java.lang.Math.sqrt;

public class Program {
    public static void main(String[]args) {
        Scanner scanner;
        scanner = new Scanner(System.in);
        int n = 0;
        int counter = 0;
        while ((n = scanner.nextInt()) != 42)
        {
            if (isEasy(countNumber(n))) {
                System.out.println("easy");
                counter++;
            }
        }
        System.out.print("Count of coffee-request – ");
        System.out.println(counter);

    }
    public static boolean isEasy(int n)
    {
        if (n % 2 == 0 && n != 2)
            return false;
        for (int i = 3; i < sqrt(n) + 1;) {
            if (n % i == 0)
                return false;
            i+=2;
        }
        return true;
    }

    public static int countNumber(int n)
    {
        int sum = 0;
        while (n != 0)
        {
            sum += n % 10;
            n /= 10;
        }
        return sum;
    }

    public static void printEnd(int counter, boolean status)
    {
        System.out.print(status);
        System.out.print(" ");
        System.out.println(counter);
    }
}