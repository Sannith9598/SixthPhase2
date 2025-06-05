package sixth.phasetwo.track_expns;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BudgetService {
    @Autowired
    private BudgetRepository repo;

    public BudgetEntity createBudget(BudgetEntity budget) {
        return repo.save(budget);
    }

    public List<BudgetEntity> getAllBudgets() {
        return repo.findAll();
    }

    public Optional<BudgetEntity> getBudgetById(int id) {
        return repo.findById(id);
    }

    public BudgetEntity updateBudget(int id, BudgetEntity budget) {
        BudgetEntity existing = repo.findById(id).orElseThrow(() -> new RuntimeException("Budget not found"));
        existing.setType(budget.getType());
        existing.setIncome(budget.getIncome());
        existing.setDate(budget.getDate());
        existing.setTotalExpenses(budget.getTotalExpenses());
        return repo.save(existing);
    }

    public void deleteBudget(int id) {
        repo.deleteById(id);
    }
}
