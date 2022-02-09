package league.tennistable.domain.models.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class GroupDto implements Serializable {

    private Long id;

    private Enum<GroupNumeration> name ;

    public Enum<GroupNumeration> getName() {
        return name;
    }

    public void setName(Enum<GroupNumeration> name) {
        this.name = name;
    }

    Set<String> participants = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<String> getParticipants() {
        return participants;
    }

    public void setParticipants(String participants) {
        this.participants.add(participants);
    }
}
