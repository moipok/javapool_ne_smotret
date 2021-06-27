public class Egg implements Runnable{
    Store store;
    private int count;

    Egg(Store store, int count){
        this.store = store;
        this.count = count;
    }
    public void run(){
        for (int i = 0; i < count; i++) {
            store.put();
        }
    }
}