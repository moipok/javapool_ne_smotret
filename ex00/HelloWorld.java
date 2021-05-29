public class HelloWorld {
    public static void main(String args[]) {
        int number = 479598;
        System.out.println(countNumber(number));
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
}