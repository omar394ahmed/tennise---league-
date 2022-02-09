package league.tennistable.service.implementations;

import league.tennistable.domain.models.LeagueGroup;
import league.tennistable.domain.repositories.GroupRepo;
import league.tennistable.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupServiceImp implements GroupService {

    @Autowired
    GroupRepo group_repo;


    @Override
    public List<LeagueGroup> getAllGroups() {
        return group_repo.findAll();
    }

    @Override
    public LeagueGroup findGroupNotComplete() {
        List<LeagueGroup> all = group_repo.findAll();
        List<LeagueGroup> collect = all.stream().filter(leagueGroup -> {
            return leagueGroup.getColleges().size() < 4;
        }).collect(Collectors.toList());
      // note every time there will be just one group not completed

       return (!collect.isEmpty())?collect.get(0) : null;
    }

    @Override
    public LeagueGroup createGroup(LeagueGroup group) {
        return group_repo.save(group);
    }
}
