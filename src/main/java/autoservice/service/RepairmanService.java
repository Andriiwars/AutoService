package autoservice.service;

import autoservice.model.Order;
import autoservice.model.Repairman;
import java.util.List;

public interface RepairmanService {

    Repairman getRepairmanById(Long id);

    Repairman createRepairman(Repairman repairman);

    Repairman addCompletedOrder(Long id, Order order);

    List<Order> getAllCompletedOrders(Long id);

    List<Repairman> getAllRepairmensByOrderId(Long id);
}
