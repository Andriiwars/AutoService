package autoservice.service;

import autoservice.model.Favor;
import autoservice.model.Order;

public interface StatusService {
    Favor changeFavorStatus(Long id, Favor.Status status);

    Order changeOrderStatus(Long id, Order.Status status);
}
