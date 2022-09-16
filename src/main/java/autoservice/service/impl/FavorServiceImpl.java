package autoservice.service.impl;

import autoservice.dao.FavorDao;
import autoservice.model.Favor;
import autoservice.service.FavorService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class FavorServiceImpl implements FavorService {
    private final FavorDao favorDao;

    public FavorServiceImpl(FavorDao favorDao) {
        this.favorDao = favorDao;
    }

    @Override
    public Favor getFavorById(Long id) {
        return favorDao.findById(id).orElseThrow(() ->
                new RuntimeException("Can't get favor by id " + id));
    }

    @Override
    public Favor createFavor(Favor favor) {
        return favorDao.save(favor);
    }

    @Override
    public List<Favor> getFavorsByRepairmanIdAndOrderId(Long repairmanId, Long orderId) {
        return favorDao.getFavorsByRepairmanIdAndOrderId(repairmanId, orderId);
    }
}
