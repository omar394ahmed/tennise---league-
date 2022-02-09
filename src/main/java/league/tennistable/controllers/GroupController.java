package league.tennistable.controllers;

import league.tennistable.domain.models.LeagueGroup;
import league.tennistable.domain.models.Participant;
import league.tennistable.domain.models.dto.GroupDto;
import league.tennistable.domain.models.dto.GroupNumeration;
import league.tennistable.service.GroupService;
import league.tennistable.service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/groups")
@CrossOrigin(value = "http://localhost:4200")
public class GroupController {


    @Autowired
    ParticipantService participantService;


    @Autowired
    GroupService groupService;


    @GetMapping("")
    public ResponseEntity<List<GroupDto>> createGroups() {

        Random random = new Random();

        List<Participant> allParticipants = participantService.getAll();



        List<LeagueGroup> allGroups = groupService.getAllGroups();
        // loop till all participants have join group
        for (int list_count = 0; allParticipants.size() > 0; list_count++) {
            LeagueGroup group;

            LeagueGroup groupNotComplete = groupService.findGroupNotComplete();
            if (groupNotComplete == null) {
                // creat new group and new
                group = new LeagueGroup();
                group.setColleges(new HashSet<>());
            } else {
                // use the not completed one
                group = groupNotComplete;
            }

            // loop till form group of 4 = group-size Or there is no more participants
            for (int count = 0; (group.getColleges().size() < 4 && !allParticipants.isEmpty()); count++) {

                Participant participant = allParticipants.get(random.nextInt(allParticipants.size()));
                allParticipants.remove(participant);
                if (participant.getLeagueGroup() != null) continue;
                group.getColleges().add(participant);

            }

            if (group.getColleges().isEmpty()) continue;


            switch (allGroups.size()) {
                case 1:
                    group.setEnumeration(GroupNumeration.A);
                    break;
                case 2:
                    group.setEnumeration(GroupNumeration.B);
                    break;
                case 3:
                    group.setEnumeration(GroupNumeration.c);
                    break;
                case 4:
                    group.setEnumeration(GroupNumeration.D);
                    break;


            }
            // save the new group
            LeagueGroup createdGroup = groupService.createGroup(group);

            // add participant to group (update the participant Entity status)
            createdGroup.getColleges().forEach(participant -> {
                if (participant.getLeagueGroup() == null) {
                    participant.setLeagueGroup(createdGroup);
                    Participant UpdatesParticipant = participantService.addToGroup(participant);
                }
            });
        }

        // now all participants join group

        // preparing JSON
        List<GroupDto> groupDtos = allGroups.stream().map(leagueGroup -> {
            GroupDto groupDto = new GroupDto();
            leagueGroup.getColleges().forEach(participant -> groupDto.setParticipants(participant.getName()));
            groupDto.setId(leagueGroup.getId());
            groupDto.setName(leagueGroup.getEnumeration());
            return groupDto;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(groupDtos);
    }


}
