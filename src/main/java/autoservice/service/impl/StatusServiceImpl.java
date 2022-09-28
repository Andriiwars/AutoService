package autoservice.service.impl;

import autoservice.dao.FavorDao;
import autoservice.model.Favor;
import autoservice.model.Order;
import autoservice.model.Repairman;
import autoservice.service.FavorService;
import autoservice.service.OrderService;
import autoservice.service.RepairmanService;
import autoservice.service.StatusService;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class StatusServiceImpl implements StatusService {
    private final FavorService favorService;

    private final OrderService orderService;
    private final FavorDao favorDao;

    private final RepairmanService repairmanService;

    public StatusServiceImpl(FavorService favorService,
                             FavorDao favorDao,
                             OrderService orderService,
                             RepairmanService repairmanService) {
        this.favorService = favorService;
        this.favorDao = favorDao;
        this.orderService = orderService;
        this.repairmanService = repairmanService;
    }

    @Override
    public Favor changeFavorStatus(Long id, Favor.Status status) {
        favorDao.updateFavorStatus(id, status);
        return favorService.getFavorById(id);
    }

    @Override
    public Order changeOrderStatus(Long id, Order.Status status) {
        Order order = orderService.getOrderById(id);
        order.setStatus(status);
        List<Repairman> repairmanList;
        if (status == Order.Status.COMPLETED) {
            repairmanList = repairmanService.getAllRepairmensByOrderId(id);
            saveData(order, repairmanList);
        }
        if (status == Order.Status.FAILURE) {
            repairmanList = changeToFailure(order);
            saveData(order, repairmanList);
        }
        return order;
    }

    private List<Repairman> changeToFailure(Order order) {
        int firstElementIndex = 0;
        Favor priceFavor = order.getFavors().get(firstElementIndex);
        priceFavor.setPrice(BigDecimal.valueOf(500));
        return List.of(priceFavor.getRepairman());
    }

    private void saveData(Order order, List<Repairman> repairmanList) {
        order.setEndDate(LocalDateTime.now());
        orderService.createOrder(order);
        repairmanList.stream().distinct().forEach(r -> repairmanService
                .createRepairman(repairmanService
                        .addCompletedOrder(r.getId(), order)));
    }
}
