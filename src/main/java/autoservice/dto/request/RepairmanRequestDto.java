package autoservice.dto.request;

import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RepairmanRequestDto {
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
}
