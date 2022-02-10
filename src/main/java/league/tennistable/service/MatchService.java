package league.tennistable.service;


import league.tennistable.domain.models.LeagueGroup;
import league.tennistable.domain.models.Match;
import league.tennistable.domain.models.Round;
import league.tennistable.domain.models.dto.MatchDto;
import net.bytebuddy.agent.builder.AgentBuilder;

import java.util.List;

public interface MatchService {

    Match createMatch(Match match);

    List<Match> getAll();

    Match addToRound(Match match);




    List<MatchDto> createMatches(List<LeagueGroup> allGroups);
}
