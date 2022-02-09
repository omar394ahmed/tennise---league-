package league.tennistable.service;


import league.tennistable.domain.models.LeagueGroup;

import java.util.List;

public interface GroupService {

    LeagueGroup createGroup(LeagueGroup group);

    List<LeagueGroup> getAllGroups();

    LeagueGroup findGroupNotComplete();
}
