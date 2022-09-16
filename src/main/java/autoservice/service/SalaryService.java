package autoservice.service;

import java.math.BigDecimal;

public interface SalaryService {
    BigDecimal getSalaryByRepairmanIdAndOrderId(Long repairmanId, Long orderId);
}
