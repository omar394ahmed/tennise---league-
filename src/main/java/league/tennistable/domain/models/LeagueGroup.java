package league.tennistable.domain.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import league.tennistable.domain.models.dto.GroupNumeration;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class LeagueGroup {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private Enum<GroupNumeration> enumeration ;

    @OneToMany(mappedBy = "leagueGroup")
    @JsonManagedReference
    Set<Participant> colleges;


    @Override
    public String toString() {
        return "";
    }



    public LeagueGroup() {
        this.colleges = new HashSet<>();
    }
}
