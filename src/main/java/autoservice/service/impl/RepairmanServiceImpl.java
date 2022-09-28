package autoservice.service.impl;

import autoservice.dao.RepairmanDao;
import autoservice.model.Order;
import autoservice.model.Repairman;
import autoservice.service.RepairmanService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class RepairmanServiceImpl implements RepairmanService {
    private final RepairmanDao repairmanDao;

    public RepairmanServiceImpl(RepairmanDao repairmanDao) {
        this.repairmanDao = repairmanDao;
    }

    @Override
    public List<Repairman> getAllRepairmensByOrderId(Long id) {
        return repairmanDao.getRepairmans(id);
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
        return repairmanDao.getOrders(id);
    }
}
