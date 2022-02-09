package league.tennistable.domain.repositories;

import league.tennistable.domain.models.LeagueGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepo extends JpaRepository<LeagueGroup, Long> {



}
