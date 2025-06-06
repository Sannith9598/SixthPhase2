package sixth.phasetwo.track_expns;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BudgetService {
    @Autowired
    private BudgetRepository repo;

    @Autowired
    private ExpenseService expenseService;

    public BudgetEntity createBudget(BudgetEntity budget) {
        return repo.save(budget);
    }

    public List<BudgetEntity> getAllBudgets() {
        List<BudgetEntity> budgets = repo.findAll();

        for (BudgetEntity budget : budgets) {
            int id = budget.getId();
            List<ExpenseEntity> expenses = expenseService.getExpensesByBudgetId(id);
            double total = 0;
            for (ExpenseEntity e : expenses) {
                total += e.getAmount();
            }
            budget.setTotalExpenses(total);
            budget.setRemainingAmount(budget.getIncome() - total);

             repo.save(budget);
        }

        return budgets;
    }

    public Optional<BudgetEntity> getBudgetById(int id) {
        Optional<BudgetEntity> optionalBudget = repo.findById(id);
        if (optionalBudget.isPresent()) {
            BudgetEntity budget = optionalBudget.get();
            List<ExpenseEntity> expenses = expenseService.getExpensesByBudgetId(id);
            double total = 0;
            for (ExpenseEntity e : expenses) {
                total += e.getAmount();
            }
            budget.setTotalExpenses(total);
            budget.setRemainingAmount(budget.getIncome() - total);
             repo.save(budget);
        }
        return optionalBudget;
    }
    public BudgetEntity updateBudget(int id, BudgetEntity budget) {
        BudgetEntity existing = repo.findById(id).orElseThrow(() -> new RuntimeException("Budget not found"));
        existing.setType(budget.getType());
        existing.setIncome(budget.getIncome());
        existing.setDate(budget.getDate());
        List<ExpenseEntity> expenses = expenseService.getExpensesByBudgetId(id);
        double total = 0;
        for (ExpenseEntity e : expenses) {
            total += e.getAmount();
        }
        existing.setTotalExpenses(total);
        existing.setRemainingAmount(existing.getIncome() - total);
        return repo.save(existing);
    }

    public void deleteBudget(int id) {
        repo.deleteById(id);
    }

   public boolean isLowBudget(int id) {
        Optional<BudgetEntity> optional = repo.findById(id);
        return optional.isPresent() && optional.get().getRemainingAmount() < 0;
         }
         
    public Map<String, Object> getBudgetSummary(int id) {
        BudgetEntity budget = repo.findById(id)
                                .orElseThrow(() -> new RuntimeException("Budget not found"));

        List<ExpenseEntity> expenses = expenseService.getExpensesByBudgetId(id);
        double total = expenses.stream().mapToDouble(ExpenseEntity::getAmount).sum();

        double remaining = budget.getIncome() - total;
        String status = (remaining < 0) ? "Over Budget" : "Safe";

        Map<String, Object> summary = new LinkedHashMap<>();
        summary.put("budgetId", id);
        summary.put("income", budget.getIncome());
        summary.put("totalExpenses", total);
        summary.put("remainingAmount", remaining);
        summary.put("status", status);

        return summary;
    }
 
}
