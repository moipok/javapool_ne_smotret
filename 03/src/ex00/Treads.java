public class Treads extends Thread {
    private int count;

    public Treads(String name, int count){
        super(name);
        this.count = count;
    }

    public void run(){
        for (int i = 0; i < count; i++)
        {
            try {
                Treads.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(this.getName());
        }
    }
}
