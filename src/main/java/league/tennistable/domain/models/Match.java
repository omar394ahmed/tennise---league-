package league.tennistable.domain.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
public class Match {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToMany
    Set<Participant> participants;
    @Nullable
    Boolean winner;
    @ManyToOne
    @JsonBackReference
    Round round;

    @OneToOne
    private LeagueGroup group;

    @Override
    public String toString() {
        return "";
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Match)) return false;
        Match match = (Match) o;
        return Objects.equals(getId(), match.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
