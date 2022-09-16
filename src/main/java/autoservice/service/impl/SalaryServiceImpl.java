package autoservice.service.impl;

import autoservice.model.Favor;
import autoservice.service.FavorService;
import autoservice.service.SalaryService;
import autoservice.service.StatusService;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class SalaryServiceImpl implements SalaryService {
    private static final BigDecimal REPAIRMAN_DISCOUNT = BigDecimal.valueOf(0.4);
    private final FavorService favorService;

    private final StatusService statusService;

    public SalaryServiceImpl(FavorService favorService,
                             StatusService statusService) {
        this.favorService = favorService;
        this.statusService = statusService;
    }

    @Override
    public BigDecimal getSalaryByRepairmanIdAndOrderId(Long repairmanId, Long orderId) {
        List<Favor> favors = favorService.getFavorsByRepairmanIdAndOrderId(repairmanId, orderId);
        favors.forEach(f -> statusService.changeFavorStatus(f.getId(),Favor.Status.PAID));
        return favors.stream()
                .filter(f -> f.getRepairman()
                        .getId().equals(repairmanId))
                .map(f -> f.getPrice()
                        .multiply(REPAIRMAN_DISCOUNT))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
