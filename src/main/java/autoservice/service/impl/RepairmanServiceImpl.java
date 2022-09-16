package autoservice.service.impl;

import autoservice.dao.RepairmanDao;
import autoservice.model.Favor;
import autoservice.model.Order;
import autoservice.model.Repairman;
import autoservice.service.OrderService;
import autoservice.service.RepairmanService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class RepairmanServiceImpl implements RepairmanService {
    private final RepairmanDao repairmanDao;
    private final OrderService orderService;

    public RepairmanServiceImpl(RepairmanDao repairmanDao,
                                OrderService orderService) {
        this.repairmanDao = repairmanDao;
        this.orderService = orderService;
    }

    @Override
    public List<Repairman> getAllRepairmensByOrderId(Long id) {
        return orderService.getOrderById(id)
                .getFavors()
                .stream()
                .map(Favor::getRepairman)
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public Repairman addCompletedOrder(Long id, Order order) {
        Repairman repairman = getRepairmanById(id);
        repairman.getCompletedOrders().add(order);
        return repairman;
    }

    @Override
    public Repairman getRepairmanById(Long id) {
        return repairmanDao.findById(id).orElseThrow(() ->
                new RuntimeException("Can't get repairman by id " + id));
    }

    @Override
    public Repairman createRepairman(Repairman repairman) {
        return repairmanDao.save(repairman);
    }

    @Override
    public List<Order> getAllCompletedOrders(Long id) {
        return getRepairmanById(id).getCompletedOrders();
    }
}
