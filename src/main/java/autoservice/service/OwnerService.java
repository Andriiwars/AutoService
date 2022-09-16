package autoservice.service;

import autoservice.model.Owner;
import java.util.List;

public interface OwnerService {
    Owner getOwnerById(Long id);

    Owner createOwner(Owner owner);

    List<Owner> getAllOwners();

    void deleteOwnerById(Long id);
}
