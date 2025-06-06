package sixth.phasetwo.track_expns;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TrackExpnsApplicationTests {

	@Mock
    private BudgetRepository repo;

    @Mock
    private ExpenseService expenseService;

    @InjectMocks
    private BudgetService budgetService;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);

	}
	
	@Test
    void testCreateBudget() {
        BudgetEntity budget = new BudgetEntity();
        budget.setId(1);
        budget.setType("Salary");
        budget.setIncome(10000);

        when(repo.save(budget)).thenReturn(budget);

        BudgetEntity saved = budgetService.createBudget(budget);
        assertEquals("Salary", saved.getType());
    }

    @Test
    void testGetBudgetByIdFound() {
        BudgetEntity budget = new BudgetEntity();
        budget.setId(1);
        budget.setIncome(5000);
        when(repo.findById(1)).thenReturn(Optional.of(budget));
        when(expenseService.getExpensesByBudgetId(1)).thenReturn(Arrays.asList());

        Optional<BudgetEntity> result = budgetService.getBudgetById(1);
        assertTrue(result.isPresent());
        assertEquals(5000, result.get().getIncome());
    }

    @Test
    void testGetBudgetByIdNotFound() {
        when(repo.findById(99)).thenReturn(Optional.empty());
        Optional<BudgetEntity> result = budgetService.getBudgetById(99);
        assertFalse(result.isPresent());
    }

    @Test
    void testUpdateBudget() {
        BudgetEntity existing = new BudgetEntity();
        existing.setId(1);
        existing.setIncome(10000);
        existing.setType("Old");

        BudgetEntity updated = new BudgetEntity();
        updated.setIncome(15000);
        updated.setType("Updated");

        when(repo.findById(1)).thenReturn(Optional.of(existing));
        when(expenseService.getExpensesByBudgetId(1)).thenReturn(Arrays.asList());
		when(repo.save(any(BudgetEntity.class))).thenReturn(updated);

        BudgetEntity result = budgetService.updateBudget(1, updated);
        assertEquals(15000, result.getIncome());
        assertEquals("Updated", result.getType());
    }

    @Test
    void testDeleteBudget() {
        doNothing().when(repo).deleteById(1);
        budgetService.deleteBudget(1);
        verify(repo, times(1)).deleteById(1);
    }

    @Test
    void testIsLowBudgetTrue() {
        BudgetEntity budget = new BudgetEntity();
        budget.setId(1);
        budget.setIncome(2000);
        budget.setRemainingAmount(-50);

        when(repo.findById(1)).thenReturn(Optional.of(budget));

        boolean result = budgetService.isLowBudget(1);
        assertTrue(result);
    }

    @Test
    void testIsLowBudgetFalse() {
        BudgetEntity budget = new BudgetEntity();
        budget.setId(1);
        budget.setIncome(2000);
        budget.setRemainingAmount(500);

        when(repo.findById(1)).thenReturn(Optional.of(budget));

        boolean result = budgetService.isLowBudget(1);
        assertFalse(result);
    }
}
