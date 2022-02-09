package league.tennistable.domain.models.dto;

import lombok.Data;

import java.util.List;

@Data
public class CustomResponse {


    public CustomResponse(Object object, List<Link> links) {
        this.object = object;
        this.links = links;
    }

    Object object;
    List<Link> links;
}
