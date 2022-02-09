package league.tennistable.service.implementations;

import league.tennistable.domain.models.Match;
import league.tennistable.domain.models.Round;
import league.tennistable.domain.repositories.MatchRepo;
import league.tennistable.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchServiceImp implements MatchService {

    @Autowired
    MatchRepo matchRepo;


    @Override
    public Match createMatch(Match match) {
        return matchRepo.save(match);
    }

    @Override
    public List<Match> getAll() {
        return matchRepo.findAll();
    }

    @Override
    public Match addToRound(Match match) {
        return matchRepo.save(match);
    }

    @Override
    public List<Match> getParticipantMatches(Long id) {
        return null;
    }
}
