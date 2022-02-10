package league.tennistable.controllers;

import league.tennistable.domain.models.Participant;
import league.tennistable.domain.models.dto.GroupDto;
import league.tennistable.service.GroupService;
import league.tennistable.service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
        List<Participant> allParticipants = participantService.getAll();
        List<GroupDto> groupDtos = groupService.creatGroups(allParticipants);
        return ResponseEntity.ok(groupDtos);
    }


}
