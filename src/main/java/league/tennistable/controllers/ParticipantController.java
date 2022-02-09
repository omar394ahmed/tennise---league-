package league.tennistable.controllers;

import league.tennistable.domain.models.dto.CustomResponse;
import league.tennistable.domain.models.dto.Link;
import league.tennistable.domain.models.Participant;
import league.tennistable.service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/participants")
@CrossOrigin(value = "http://localhost:4200")
public class ParticipantController {


    @Autowired
    ParticipantService participantService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<CustomResponse> request_to_join(UriComponentsBuilder builder, HttpServletRequest request, @RequestBody(required = true) Participant participant) {


        Participant createdParticipant = participantService.create(participant);

        URI uri = builder.path(request.getRequestURI()).path("/" + Long.toString(createdParticipant.getId())).build().toUri();
        CustomResponse customResponse = new CustomResponse(createdParticipant, Arrays.asList(new Link(uri, "self")));

        return ResponseEntity.created(uri).body(customResponse);
    }

    @GetMapping(value = "")
    public ResponseEntity<List<CustomResponse>> getAllParticipants(UriComponentsBuilder builder, HttpServletRequest request) {

        List<Participant> allParticipants = participantService.getAll();

        List<CustomResponse> all = allParticipants.stream().map(participant -> {
            URI uri = builder.path(request.getRequestURI()).path("/" + Long.toString(participant.getId())).build().toUri();

            return new CustomResponse(participant, Arrays.asList(new Link(uri, "self")));
        }).collect(Collectors.toList());

        return ResponseEntity.ok().body(all);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Participant> getParticipant(@PathVariable Long id) {

        Participant participant = participantService.getParticipant(id);
        return ResponseEntity.ok(participant);
    }





}
