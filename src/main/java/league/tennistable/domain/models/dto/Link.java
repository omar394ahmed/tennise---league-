package league.tennistable.domain.models.dto;

import lombok.Data;

import java.io.Serializable;
import java.net.URI;

@Data
public class Link implements Serializable {

    URI url;
    String rel;

    public Link(URI url, String rel) {
        this.url = url;
        this.rel = rel;
    }
}
