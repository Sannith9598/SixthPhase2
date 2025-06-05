package sixth.phasetwo.track_expns;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {
    @Autowired
    private ExpenseService expenseService;

    @PostMapping("/new")
    public ResponseEntity<ExpenseEntity> createExpense(@Valid @RequestBody ExpenseEntity expense) {
        return ResponseEntity.ok(expenseService.createExpense(expense));
    }

    @GetMapping("/by-budget/{budgetId}")
    public List<ExpenseEntity> getExpensesByBudget(@PathVariable int budgetId) {
        return expenseService.getExpensesByBudgetId(budgetId);
    }

    @GetMapping("/view")
    public ResponseEntity<List<ExpenseEntity>> getAllExpenses() {
        return ResponseEntity.ok(expenseService.getAllExpenses());
    }
    @GetMapping("view/{id}")
    public ResponseEntity<ExpenseEntity> getExpenseById(@PathVariable int id) {
        return expenseService.getExpenseById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("update/{id}")
    public ResponseEntity<ExpenseEntity> updateExpense(@PathVariable int id, @Valid @RequestBody ExpenseEntity expense) {
        return ResponseEntity.ok(expenseService.updateExpense(id, expense));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteExpense(@PathVariable int id) {
        expenseService.deleteExpense(id);
        return ResponseEntity.ok("Expense deleted successfully.");
    }
}
