package sixth.phasetwo.track_expns;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ExpenseService {
    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired

    public ExpenseEntity createExpense(ExpenseEntity expense) {
        ExpenseEntity savedExpense = expenseRepository.save(expense);
        return savedExpense;
    }

    public List<ExpenseEntity> getAllExpenses() {
        return expenseRepository.findAll();
    }

    public Optional<ExpenseEntity> getExpenseById(int id) {
        return expenseRepository.findById(id);
    }
}
