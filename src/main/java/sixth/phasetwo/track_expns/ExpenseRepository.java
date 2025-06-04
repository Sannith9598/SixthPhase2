package sixth.phasetwo.track_expns;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<ExpenseEntity, Integer>{
    List<ExpenseEntity> findByBudetId(int budetId);
    List<ExpenseEntity> findByType(String type);
}
