package sixth.phasetwo.track_expns;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BudgetService {
    @Autowired
    private BudgetRepository budgetRepository;

    public BudgetEntity createBudget(BudgetEntity budget) {
        return budgetRepository.save(budget);
    }

    public List<BudgetEntity> getAllBudgets() {
        return budgetRepository.findAll();
    }

    public Optional<BudgetEntity> getBudgetById(int id) {
        return budgetRepository.findById(id);
    }
}
