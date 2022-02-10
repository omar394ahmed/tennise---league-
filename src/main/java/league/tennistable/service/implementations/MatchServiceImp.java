package league.tennistable.service.implementations;

import league.tennistable.domain.models.LeagueGroup;
import league.tennistable.domain.models.Match;
import league.tennistable.domain.models.Participant;
import league.tennistable.domain.models.dto.MatchDto;
import league.tennistable.domain.repositories.MatchRepo;
import league.tennistable.service.MatchService;
import league.tennistable.service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MatchServiceImp implements MatchService {

    @Autowired
    MatchRepo matchRepo;

    @Autowired
    ParticipantService participantService;

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
    public List<MatchDto> createMatches(List<LeagueGroup> allGroups) {
        allGroups.stream().forEach(leagueGroup -> {

            List<Participant> nonMatchParticipant = participantService.getNonMatchParticipant(leagueGroup.getId());

            // note the group of 1 will not have any match how come , because of this we have (1) as our constraint to this while loop   ?!!
            while (nonMatchParticipant.size() > 1) {


                List<Participant> MatchList = new LinkedList<>(nonMatchParticipant).subList(0, 2);

                Set<Participant> matchParticipants = new HashSet<Participant>(MatchList);
                Match match = new Match();
                match.setParticipants(matchParticipants);

                //System.out.println("match = " + match);
                match.setGroup(leagueGroup);
                createMatch(match);

                matchParticipants.forEach(participant -> {
                    participant.getMatches().add(match);
                    // update participant
                    participantService.create(participant);
                });

                nonMatchParticipant.removeAll(matchParticipants);
            }

        });
        // return all matches after updating 
        List<Match> allMatches = getAll();

        // manual mapping
        List<MatchDto> allmatchesDtos = allMatches.stream().map(match -> {
            MatchDto matchDto = new MatchDto();
            matchDto.setId(match.getId());
            match.getParticipants().forEach(participant -> matchDto.setParticipantNames(participant.getName()));
            return matchDto ;
        }).collect(Collectors.toList());

        return allmatchesDtos ;
    }


}
