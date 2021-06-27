public class Store{
    private int product;

    Store(){
        product = 0;
    }

    public synchronized void get() {
        while (product == 0) {
            try {
                wait();
            }
            catch (InterruptedException ignored) {
            }
        }
        System.out.println("Hen");

        product = 0;
        notify();
    }
    public synchronized void put() {
        while (product == 1) {
            try {
                wait();
            }
            catch (InterruptedException ignored) {
            }
        }
        System.out.println("Egg");
        product = 1;
        notify();
    }
}