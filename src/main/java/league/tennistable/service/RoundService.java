package league.tennistable.service;

import league.tennistable.domain.models.Round;

import java.util.List;

public interface RoundService {

    Round createRound(Round round);

    List<Round> getAllRounds();




}
