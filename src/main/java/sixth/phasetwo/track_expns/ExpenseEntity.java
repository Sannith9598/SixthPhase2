package sixth.phasetwo.track_expns;

public class ExpenseEntity {
    private int id;
    private String type;
    private int budetId;
    private double amount;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public int getBudetId() {
        return budetId;
    }
    public void setBudetId(int budetId) {
        this.budetId = budetId;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
}