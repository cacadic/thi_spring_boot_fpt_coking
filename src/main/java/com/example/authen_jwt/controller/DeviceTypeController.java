package com.example.authen_jwt.controller;

import com.example.authen_jwt.entities.DeviceType;
import com.example.authen_jwt.models.AddDeviceTypeRequest;
import com.example.authen_jwt.models.AddDeviceTypeResponse;
import com.example.authen_jwt.repositories.DeviceTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeviceTypeController {
    @Autowired
    private DeviceTypeRepository deviceTypeRepository;

    @RequestMapping(method = RequestMethod.POST, value = "/device_type")
    public ResponseEntity<AddDeviceTypeResponse> AddNewDeviceType(@RequestBody AddDeviceTypeRequest addDeviceTypeRequest){
        try {
            DeviceType newDeviceType = new DeviceType(addDeviceTypeRequest.getTitle());
            deviceTypeRepository.save(newDeviceType);
            AddDeviceTypeResponse response = new AddDeviceTypeResponse("Add device type success");
            return ResponseEntity.status(200).body(response);
        } catch (Exception err){
            AddDeviceTypeResponse response = new AddDeviceTypeResponse("Add device type failed");
            return ResponseEntity.status(500).body(response);
        }
    }
}
