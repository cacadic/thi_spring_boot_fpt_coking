package com.example.authen_jwt.repositories;

import com.example.authen_jwt.entities.DeviceType;
import org.springframework.data.repository.CrudRepository;

public interface DeviceTypeRepository extends CrudRepository<DeviceType, Integer> {
    DeviceType findDeviveTypeById(int id);
}
