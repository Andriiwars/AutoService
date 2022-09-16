package autoservice.dto.response;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class FavorResponseDto {
    private Long id;
    private String favorName;
    private Long orderId;
    private Long repairmanId;
    private BigDecimal price;
    private String status;
}
