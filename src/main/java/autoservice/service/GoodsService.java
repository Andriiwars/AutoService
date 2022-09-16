package autoservice.service;

import autoservice.model.Goods;
import java.util.List;

public interface GoodsService {
    Goods getGoodsById(Long id);

    Goods createGoods(Goods goods);

    List<Goods> getAllGoods();

    void deleteGoodsById(Long id);
}
