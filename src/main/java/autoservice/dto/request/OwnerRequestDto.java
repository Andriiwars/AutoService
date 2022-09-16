package autoservice.dto.request;

import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OwnerRequestDto {
    @NotNull
    private String lastName;
    @NotNull
    private String firstName;
}
