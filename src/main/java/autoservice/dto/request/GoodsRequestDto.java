package autoservice.dto.request;

import java.math.BigDecimal;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class GoodsRequestDto {
    @NotNull
    private String name;
    @NotNull
    private BigDecimal price;
}
