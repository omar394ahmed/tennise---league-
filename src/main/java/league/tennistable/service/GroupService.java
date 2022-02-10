package league.tennistable.service;


import league.tennistable.domain.models.LeagueGroup;
import league.tennistable.domain.models.Participant;
import league.tennistable.domain.models.dto.GroupDto;

import java.util.List;

public interface GroupService {

    LeagueGroup createGroup(LeagueGroup group);

    List<LeagueGroup> getAllGroups();

    List<GroupDto> creatGroups(List<Participant> allParticipants);

    LeagueGroup findUpNextGroup();

   List<GroupDto> mappingGroupsToEnhancedDtos(List<LeagueGroup> allGroups);

    LeagueGroup namingNewGroups(List<LeagueGroup> allExistenceGroups, LeagueGroup newGroup);


}
