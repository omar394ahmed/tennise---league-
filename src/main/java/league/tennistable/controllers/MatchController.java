package league.tennistable.controllers;


import league.tennistable.domain.models.LeagueGroup;
import league.tennistable.domain.models.dto.MatchDto;
import league.tennistable.service.GroupService;
import league.tennistable.service.MatchService;
import league.tennistable.service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/matches")
@CrossOrigin(value = "http://localhost:4200")
public class MatchController {


    @Autowired
    GroupService groupService;
    @Autowired
    MatchService matchService;

    @GetMapping("")
    public ResponseEntity<List<MatchDto>> createMatches() {
        // getting all participants with groups
        List<LeagueGroup> allGroups = groupService.getAllGroups();
        // create matches between group members
        List<MatchDto> matches = matchService.createMatches(allGroups);

        return ResponseEntity.ok(matches);
    }


}
