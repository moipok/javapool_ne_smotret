public class SumTh extends Thread {
    private int[] arr;
    private int index;
    private int count;
    private boolean end;
    private long value;
    private static int singltone = 0;
    private int id;

    {
        id = singltone++;
    }

    SumTh(int[] arr, int index,int count, boolean end){
        this.arr = arr;
        this.end = end;
        this.index = index;
        this.count = count;
        this.value = 0;
    }

    public void run(){
        int index_end = index + count;
        if (end) {
            index_end = arr.length;
        }
        for (int i = index; i < index_end; i++) {
            value =  value + arr[i];
        }
        if (end) {
            index_end--;
        }
        System.out.println("Thread " + id + " : from " + index + " to " + (index_end - 1) + "  sum is " + value);
    }

    public long getValue()
    {
        return value;
    }
}
