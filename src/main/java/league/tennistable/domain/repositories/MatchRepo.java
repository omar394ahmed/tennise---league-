package league.tennistable.domain.repositories;

import league.tennistable.domain.models.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepo extends JpaRepository<Match, Long> {



}
