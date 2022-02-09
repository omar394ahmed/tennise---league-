package league.tennistable.domain.repositories;

import league.tennistable.domain.models.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParticipantRepo extends JpaRepository<Participant , Long> {


    List<Participant> findAllByLeagueGroupIdAndMatchesIsNull(Long id);
}
