package autoservice.dto.request;

import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderRequestDto {
    @NotNull
    private Long carId;
    @NotNull
    private String problemDescription;
    @NotNull
    private LocalDateTime acceptanceDate;
}
