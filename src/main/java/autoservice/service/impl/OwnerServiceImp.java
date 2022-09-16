package autoservice.service.impl;

import autoservice.dao.OwnerDao;
import autoservice.model.Owner;
import autoservice.service.OwnerService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class OwnerServiceImp implements OwnerService {
    private final OwnerDao ownerDao;

    public OwnerServiceImp(OwnerDao ownerDao) {
        this.ownerDao = ownerDao;
    }

    @Override
    public Owner getOwnerById(Long id) {
        return ownerDao.findById(id).orElseThrow(() ->
                new RuntimeException("Can't get owner by id " + id));
    }

    @Override
    public Owner createOwner(Owner owner) {
        return ownerDao.save(owner);
    }

    @Override
    public List<Owner> getAllOwners() {
        return ownerDao.findAll();
    }

    @Override
    public void deleteOwnerById(Long id) {
        ownerDao.deleteById(id);
    }
}
