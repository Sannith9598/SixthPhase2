package sixth.phasetwo.track_expns;

import java.time.LocalDate;

public class BudgetEntity {
    private int id;
    private String type;
    private double income;
    private LocalDate date;
    private double totalExpenses;
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
    public double getIncome() {
        return income;
    }
    public void setIncome(double income) {
        this.income = income;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public double getTotalExpenses() {
        return totalExpenses;
    }
    public void setTotalExpenses(double totalExpenses) {
        this.totalExpenses = totalExpenses;
    }
    
}