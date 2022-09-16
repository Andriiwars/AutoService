package autoservice.dto.response;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class GoodsResponseDto {
    private Long id;
    private String name;
    private BigDecimal price;
}
