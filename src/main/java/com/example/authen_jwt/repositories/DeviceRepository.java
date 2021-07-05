package com.example.authen_jwt.repositories;

import com.example.authen_jwt.entities.Device;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface DeviceRepository extends CrudRepository<Device, Integer> {
    Iterable<Device> findAllByDeviceType(int id);
}
