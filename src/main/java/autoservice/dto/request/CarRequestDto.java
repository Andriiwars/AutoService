package autoservice.dto.request;

import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CarRequestDto {
    @NotNull
    private String brand;
    @NotNull
    private String model;
    @NotNull
    private Integer year;
    @NotNull
    private String serialNumber;
    @NotNull
    private Long ownerId;
}
