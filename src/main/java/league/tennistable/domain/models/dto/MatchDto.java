package league.tennistable.domain.models.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class MatchDto implements Serializable {

    Set<String> participantNames = new HashSet<>();
    private Long id;
//
//    Boolean winner;
//
//
//    Round round;
//
//    private LeagueGroup group;


    public MatchDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<String> getParticipantNames() {
        return participantNames;
    }

    public void setParticipantNames(String participantName) {
        this.participantNames.add(participantName);
    }
}
