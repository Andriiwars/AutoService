package autoservice.dto.request;

import java.math.BigDecimal;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FavorRequestDto {
    @NotNull
    private Long orderId;
    @NotNull
    private String favorName;
    @NotNull
    private Long repairmanId;
    @NotNull
    private BigDecimal price;
}
