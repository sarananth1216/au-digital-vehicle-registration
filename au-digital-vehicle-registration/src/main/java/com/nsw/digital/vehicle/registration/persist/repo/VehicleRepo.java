package com.nsw.digital.vehicle.registration.persist.repo;

import com.nsw.digital.vehicle.registration.persist.dto.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepo extends CrudRepository<VehicleEntity, Long> {

    public VehicleEntity findByRego(String rego);
}
