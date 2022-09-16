package autoservice.service;

import autoservice.model.Favor;
import java.util.List;

public interface FavorService {
    Favor getFavorById(Long id);

    Favor createFavor(Favor favor);

    List<Favor> getFavorsByRepairmanIdAndOrderId(Long repairmanId, Long orderId);
}
