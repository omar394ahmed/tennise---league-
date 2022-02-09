package league.tennistable.controllers;


import league.tennistable.domain.models.LeagueGroup;
import league.tennistable.domain.models.Match;
import league.tennistable.domain.models.Participant;
import league.tennistable.domain.models.dto.MatchDto;
import league.tennistable.service.GroupService;
import league.tennistable.service.MatchService;
import league.tennistable.service.ParticipantService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/matches")
@CrossOrigin(value = "http://localhost:4200")
public class MatchController {


    @Autowired
    GroupService groupService;
    @Autowired
    MatchService matchService;

    @Autowired
    ParticipantService participantService;

    public static <T> List<T> randomSubList(List<T> list, int newSize) {
        list = new ArrayList<>(list);
        Collections.shuffle(list);
        HashSet hashSet = new HashSet();

        return list.subList(0, newSize);
    }

    @GetMapping("")
    public ResponseEntity<List<MatchDto>> createMatches() {

        List<LeagueGroup> allGroups = groupService.getAllGroups();

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
                matchService.createMatch(match);

                matchParticipants.forEach(participant -> {
                    participant.getMatches().add(match);
                    // update participant
                    participantService.create(participant);
                });

                nonMatchParticipant.removeAll(matchParticipants);
            }

        });

        List<Match> allMatches = matchService.getAll();
        // manual mapping
        List<MatchDto> allmatchesDtos = allMatches.stream().map(match -> {
            MatchDto matchDto = new MatchDto();
            matchDto.setId(match.getId());
            match.getParticipants().forEach(participant -> matchDto.setParticipantNames(participant.getName()));
            return matchDto ;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(allmatchesDtos);
    }


}
