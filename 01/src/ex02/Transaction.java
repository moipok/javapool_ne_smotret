import java.util.UUID;

public class Transaction {
    private UUID id;
    private User recipient;
    private User sender;
    private Category transfer;
    private Integer amount;

    private Transaction () { }

    public Transaction(User recipient, User sender, Category transfer, Integer amount)
    {
        this.id = UUID.randomUUID();
        this.recipient = recipient;
        this.sender = sender;
        this.transfer = transfer;
        this.amount = amount;
        if ((transfer == Category.DEBIT && amount < 0) || (transfer == Category.CREDIT && amount >= 0))
            errorFun();
    }

    public static void errorFun(){
        System.err.println("Transaction error");
        System.exit(-1);
    }

    public void printTransaction(){
        System.out.println(this.recipient.getName() + " -> " + this.sender.getName() + ", "
                + this.amount +", " + this.transfer + ", " + this.id);
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        if ((transfer == Category.DEBIT && amount < 0) || (transfer == Category.CREDIT && amount >= 0)) {
            errorFun();
        }
        else
            this.amount = amount;
    }

    public Category getTransfer() {
        return transfer;
    }

    public UUID getId() {
        return id;
    }

    public User getRecipient() {
        return recipient;
    }

    public User getSender() {
        return sender;
    }
}

enum Category{
    DEBIT,
    CREDIT
}