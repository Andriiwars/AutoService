package autoservice.service.impl;

import autoservice.dao.GoodsDao;
import autoservice.model.Goods;
import autoservice.service.GoodsService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class GoodsServiceImpl implements GoodsService {
    private final GoodsDao goodsDao;

    public GoodsServiceImpl(GoodsDao goodsDao) {
        this.goodsDao = goodsDao;
    }

    @Override
    public Goods getGoodsById(Long id) {
        return goodsDao.findById(id).orElseThrow(() ->
                new RuntimeException("Can't get goods by id " + id));
    }

    @Override
    public Goods createGoods(Goods goods) {
        return goodsDao.save(goods);
    }

    @Override
    public List<Goods> getAllGoods() {
        return goodsDao.findAll();
    }

    @Override
    public void deleteGoodsById(Long id) {
        goodsDao.deleteById(id);
    }
}
