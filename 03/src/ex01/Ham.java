public class Ham implements Runnable{
    Store store;
    private int count;

    Ham(Store store, int count){
        this.store = store;
        this.count = count;
    }
    public void run(){
        for (int i = 0; i < count; i++) {
            store.get();
        }
    }
}
