package sixth.phasetwo.track_expns;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class ExpenseService {
     @Autowired
    private ExpenseRepository expenseRepository;
    

    public ExpenseEntity createExpense(ExpenseEntity expense) {
        return expenseRepository.save(expense);
    }

    public List<ExpenseEntity> getAllExpenses() {
        return expenseRepository.findAll();
    }

    public Optional<ExpenseEntity> getExpenseById(int id) {
        return expenseRepository.findById(id);
    }

    public ExpenseEntity updateExpense(int id, ExpenseEntity updatedExpense) {
        updatedExpense.setId(id);
        return expenseRepository.save(updatedExpense);
    }

    public void deleteExpense(int id) {
        expenseRepository.deleteById(id);
    }
}
