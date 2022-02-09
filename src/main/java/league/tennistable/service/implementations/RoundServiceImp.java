package league.tennistable.service.implementations;

import league.tennistable.domain.models.Round;
import league.tennistable.domain.repositories.RoundRepo;
import league.tennistable.service.RoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoundServiceImp implements RoundService {


    @Autowired
    RoundRepo roundRepo ;
    @Override
    public Round createRound(Round round) {
        return roundRepo.save(round);
    }

    @Override
    public List<Round> getAllRounds() {
        return roundRepo.findAll();
    }


}
