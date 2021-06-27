import java.util.UUID;

public interface TransactionsList {
    void addTransaction(Transaction transaction);
    void removeTransactionByID(UUID id);
    Transaction[] toArray();
}
