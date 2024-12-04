public class Budget {
    private double totalBudget;

    public Budget(double totalBudget) {
        this.totalBudget = totalBudget;
    }

    public double getTotalBudget() {
        return totalBudget;
    }

    public void setTotalBudget(double totalBudget) {
        this.totalBudget = totalBudget;
    }
    
    public boolean isOverBudget(double totalExpenses) {
        return totalExpenses > totalBudget;
    } 
}
