package autoservice.service.impl;

import autoservice.dao.OrderDao;
import autoservice.model.Favor;
import autoservice.model.Goods;
import autoservice.model.Order;
import autoservice.service.OrderService;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    private static final double GOODS_DISCOUNT = 0.01;
    private static final double FAVOR_DISCOUNT = 0.02;
    private final OrderDao orderDao;

    public OrderServiceImpl(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public Order getOrderById(Long id) {
        return orderDao.findById(id).orElseThrow(() ->
                new RuntimeException("Can't get order by id " + id));
    }

    @Override
    public Order createOrder(Order order) {
        return orderDao.save(order);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderDao.findAll();
    }

    @Override
    public void deleteOrderById(Long id) {
        orderDao.deleteById(id);
    }

    @Override
    public BigDecimal getFavorsPrice(Long id, Integer bonusCartOrdersCount) {
        Order order = getOrderById(id);
        int ordersNumber = order.getCar().getOwner().getOrders().size() + bonusCartOrdersCount;
        order.getFavors().forEach(f -> f.setPrice(f.getPrice()
                .subtract(f.getPrice()
                        .multiply(BigDecimal.valueOf(ordersNumber * FAVOR_DISCOUNT)))));
        return order.getFavors().stream()
                .map(Favor::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public BigDecimal getGoodsPrice(Long id, Integer bonusCartOrdersCount) {
        Order order = getOrderById(id);
        int ordersNumber = order.getCar().getOwner().getOrders().size() + bonusCartOrdersCount;
        order.getGoods()
                .forEach(g -> g.setPrice(g.getPrice()
                        .subtract(g.getPrice()
                                .multiply(BigDecimal.valueOf(ordersNumber * GOODS_DISCOUNT)))));
        return order.getGoods().stream()
                .map(Goods::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
