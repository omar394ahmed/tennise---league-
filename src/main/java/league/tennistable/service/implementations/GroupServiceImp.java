package league.tennistable.service.implementations;

import league.tennistable.domain.models.LeagueGroup;
import league.tennistable.domain.models.Participant;
import league.tennistable.domain.models.dto.GroupDto;
import league.tennistable.domain.models.dto.GroupNumeration;
import league.tennistable.domain.repositories.GroupRepo;
import league.tennistable.service.GroupService;
import league.tennistable.service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class GroupServiceImp implements GroupService {

    @Autowired
    GroupRepo group_repo;

    @Autowired
    ParticipantService participantService;

    @Override
    public LeagueGroup createGroup(LeagueGroup group) {
        return group_repo.save(group);
    }

    @Override
    public List<LeagueGroup> getAllGroups() {
        return group_repo.findAll();
    }

    @Override
    public List<GroupDto> creatGroups(List<Participant> allParticipants) {

        Random random = new Random();

        List<LeagueGroup> allGroups = getAllGroups();

        // loop till all participants have join group
        for (int list_count = 0; allParticipants.size() > 0; list_count++) {

            // find the up next group to enroll Available Participant to it
            LeagueGroup upNextGroup = findUpNextGroup();

            // loop till form group of 4 = group-size Or there is no more participants
            for (int count = 0; (upNextGroup.getColleges().size() < 4 && !allParticipants.isEmpty()); count++) {

                Participant participant = allParticipants.get(random.nextInt(allParticipants.size()));
                allParticipants.remove(participant);
                if (participant.getLeagueGroup() != null) continue;
                upNextGroup.getColleges().add(participant);

            }
            // continue if there is no group created
            if (upNextGroup.getColleges().isEmpty()) continue;

            // give name tag as A , b , c ,d to new groups , if the group is not new  group do nothing
            if (upNextGroup.getEnumeration() == null) {
                upNextGroup = namingNewGroups(allGroups, upNextGroup);
            }

            // save the new group or update the old one
            LeagueGroup createdGroup = createGroup(upNextGroup);

            // add participant to group (update the participant Entity status)
            participantService.addParticipantsToTheirGroups(createdGroup);

        }

        // now all participants join group
        // preparing JSON
        return mappingGroupsToEnhancedDtos(allGroups);
    }

    @Override
    public LeagueGroup findUpNextGroup() {
        List<LeagueGroup> all = group_repo.findAll();
        List<LeagueGroup> NonCompletedGroups = all.stream().filter(leagueGroup -> {
            return leagueGroup.getColleges().size() < 4;
        }).collect(Collectors.toList());
        // note every time there will be just one group not completed

        return (!NonCompletedGroups.isEmpty()) ? NonCompletedGroups.get(0) : new LeagueGroup();
    }


    @Override
    public LeagueGroup namingNewGroups(List<LeagueGroup> allExistenceGroups, LeagueGroup newGroup) {

        switch (allExistenceGroups.size()) {
            case 1:
                newGroup.setEnumeration(GroupNumeration.A);
                break;
            case 2:
                newGroup.setEnumeration(GroupNumeration.B);
                break;
            case 3:
                newGroup.setEnumeration(GroupNumeration.c);
                break;
            case 4:
                newGroup.setEnumeration(GroupNumeration.D);
                break;
        }

        return newGroup;
    }

    @Override
    public List<GroupDto> mappingGroupsToEnhancedDtos(List<LeagueGroup> allGroups) {
        List<GroupDto> groupDtos = allGroups.stream().map(leagueGroup -> {
            GroupDto groupDto = new GroupDto();
            leagueGroup.getColleges().forEach(participant -> groupDto.setParticipants(participant.getName()));
            groupDto.setId(leagueGroup.getId());
            groupDto.setName(leagueGroup.getEnumeration());
            return groupDto;
        }).collect(Collectors.toList());
        return groupDtos;
    }
}
