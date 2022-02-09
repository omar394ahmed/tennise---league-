package league.tennistable.service;


import league.tennistable.domain.models.Participant;

import java.util.List;

public interface ParticipantService {


    Participant create(Participant participant);

    Participant addToGroup(Participant participant);

    List<Participant> getAll();


    Participant getParticipant(Long id);

    List<Participant> getNonMatchParticipant(Long id);
}
