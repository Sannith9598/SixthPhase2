package sixth.phasetwo.track_expns;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetRepository  extends JpaRepository<BudgetEntity, Integer>{
    
}
