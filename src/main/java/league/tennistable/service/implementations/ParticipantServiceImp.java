package league.tennistable.service.implementations;

import league.tennistable.domain.models.LeagueGroup;
import league.tennistable.domain.models.Participant;
import league.tennistable.domain.repositories.ParticipantRepo;
import league.tennistable.service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticipantServiceImp implements ParticipantService {

    @Autowired
    ParticipantRepo participantRepo;

    @Override
    public Participant getParticipant(Long id) {
        return participantRepo.findById(id).get();
    }

    @Override
    public List<Participant> getNonMatchParticipant(Long id) {
        return participantRepo.findAllByLeagueGroupIdAndMatchesIsNull(id);
    }

    @Override
    public Participant create(Participant participant) {
        Participant savedParticipant = participantRepo.save(participant);
        return savedParticipant;
    }

    @Override
    public List<Participant> getAll() {
        return participantRepo.findAll();
    }

    @Override
    public Participant addToGroup(Participant participant) {
        return participantRepo.save(participant);
    }


    @Override
    public void addParticipantsToTheirGroups(LeagueGroup createdGroup) {

        createdGroup.getColleges().forEach(participant -> {
            if (participant.getLeagueGroup() == null) {
                participant.setLeagueGroup(createdGroup);
                Participant UpdatesParticipant = addToGroup(participant);
            }
        });

    }


}
