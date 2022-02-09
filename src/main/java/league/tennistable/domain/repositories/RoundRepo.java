package league.tennistable.domain.repositories;

import league.tennistable.domain.models.Round;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoundRepo extends JpaRepository<Round , Long> {
}
