package andrew.restaurant_k_p_o4.repos;

import andrew.restaurant_k_p_o4.domain.Session;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SessionRepository extends JpaRepository<Session, Integer> {
}
