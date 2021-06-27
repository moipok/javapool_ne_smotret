import java.util.UUID;

public class TransactionsLinkedList implements TransactionsList{
    private NodeT head = null;
    @Override
    public void addTransaction(Transaction data) {
        NodeT n = new NodeT(data);
        n.next = head;
        n.prev = null;
        if(head != null)
            head.prev = n;
        head = n;
    }

    @Override
    public void removeTransactionByID(UUID id) {
        NodeT tmp = head;
        while (tmp != null)
        {
            if (tmp.data.getId().equals(id))
            {
                if (tmp.prev != null && tmp.next != null)
                {
                    tmp.prev.next = tmp.next;
                    tmp.next.prev = tmp.prev;
                }
                else if (tmp.prev == null && tmp.next == null)
                    head = null;
                else if (tmp.prev == null && tmp.next != null) {
                    tmp.next.prev = null;
                    head = tmp.next;
                }
                else if (tmp.prev != null && tmp.next == null){
                    tmp.prev.next = null;
                }
                break;
            }
            tmp = tmp.next;
        }
    }

    @Override
    public Transaction[] toArray() {
        int len = listLen();

        Transaction[] arr =  new Transaction[len];
        NodeT node = head;
        int i = 0;
        while (node != null)
        {
            arr[i] = node.data;
            i++;
            node=node.next;
        }
        return arr;
    }

    private int listLen()
    {
        NodeT tmp = head;
        int len = 0;
        while (tmp != null)
        {
            tmp = tmp.next;
            len++;
        }
        return len;
    }
}

class NodeT{
    Transaction data;
    NodeT prev;
    NodeT next;

    NodeT(Transaction d)
    {
        data = d;
    }
}