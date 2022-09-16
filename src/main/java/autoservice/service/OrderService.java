package autoservice.service;

import autoservice.model.Order;
import java.math.BigDecimal;
import java.util.List;

public interface OrderService {
    Order getOrderById(Long id);

    Order createOrder(Order order);

    List<Order> getAllOrders();

    void deleteOrderById(Long id);

    BigDecimal getFavorsPrice(Long id, Integer bonusCartOrders);

    BigDecimal getGoodsPrice(Long id, Integer bonusCartOrders);
}
