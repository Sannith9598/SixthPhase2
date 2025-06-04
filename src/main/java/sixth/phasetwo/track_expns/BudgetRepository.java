package sixth.phasetwo.track_expns;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetRepository extends JpaRepository<BudgetEntity, Integer> {
    List<BudgetEntity> findByType(String type);
}
