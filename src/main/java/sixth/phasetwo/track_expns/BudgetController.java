package sixth.phasetwo.track_expns;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
@RequestMapping("/api/budgets")
public class BudgetController {
    @Autowired
    private BudgetService service;

    @PostMapping("/new")
    public ResponseEntity<BudgetEntity> create(@Valid @RequestBody BudgetEntity budget) {
        return ResponseEntity.ok(service.createBudget(budget));
    }

    @GetMapping("/view")
    public ResponseEntity<List<BudgetEntity>> getAll() {
        return ResponseEntity.ok(service.getAllBudgets());
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<BudgetEntity> getById(@PathVariable int id) {
        return service.getBudgetById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<BudgetEntity> update(@PathVariable int id, @Valid @RequestBody BudgetEntity budget) {
        return ResponseEntity.ok(service.updateBudget(id, budget));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        service.deleteBudget(id);
        return ResponseEntity.ok("Budget deleted.");
    }
    @GetMapping("/low-budget-alert")
    public ResponseEntity<List<Integer>> getLowBudgetIds() {
        List<BudgetEntity> allBudgets = service.getAllBudgets();
        List<Integer> lowBudgetIds = new ArrayList<>();

        for (BudgetEntity budget : allBudgets) {
            if (budget.getRemainingAmount() < 0) { // or <= 0 based on your logic
                lowBudgetIds.add(budget.getId());
            }
        }
            if (lowBudgetIds.isEmpty()) {
            return ResponseEntity.ok(lowBudgetIds);
        }
        return ResponseEntity.ok(lowBudgetIds);
    }
    @GetMapping("/summary/{id}")
    public ResponseEntity<Map<String, Object>> getSummary(@PathVariable int id) {
        return ResponseEntity.ok(service.getBudgetSummary(id));
    }
}
