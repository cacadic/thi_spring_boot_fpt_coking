package com.example.authen_jwt.controller;

import com.example.authen_jwt.custom_exeption.DeviceTypeNotFound;
import com.example.authen_jwt.entities.Device;
import com.example.authen_jwt.entities.DeviceType;
import com.example.authen_jwt.models.AddDeviceRequest;
import com.example.authen_jwt.models.AddDeviceResponse;
import com.example.authen_jwt.repositories.DeviceRepository;
import com.example.authen_jwt.repositories.DeviceTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

import static java.lang.Integer.parseInt;

@RestController
public class DeviceController {
    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private DeviceTypeRepository deviceTypeRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/devices")
    public ResponseEntity<?> ListAllDevice(){
        try{
            Iterable<Device> device = deviceRepository.findAll();
            ArrayList<Device> allDevice = (ArrayList<Device>) device;

            return ResponseEntity.status(200).body(allDevice);
        } catch (Exception err){
            return ResponseEntity.status(500).body(err.getMessage());
        }
    }
    @RequestMapping(method = RequestMethod.GET, value = "/devices/")
    public ResponseEntity<?> ListDevices(@RequestParam("type") Optional<Integer> deviceTypeId){
        try{
            if(!deviceTypeId.isPresent()){
                return ResponseEntity.status(500).body("Error");
            }

            Iterable<Device> devices = deviceRepository.findAll();

            ArrayList<Device> listDevice = new ArrayList<>();
            devices.forEach((device -> {
                if(device.getDeviceType().getId() == deviceTypeId.get()){
                    listDevice.add(device);
                }
            }));
            return ResponseEntity.status(200).body(listDevice);

        } catch (Exception err){
            return ResponseEntity.status(500).body(err.getMessage());
        }
    }

    @ExceptionHandler(DeviceTypeNotFound.class)
    @RequestMapping(method = RequestMethod.POST, value = "/devices")
    public ResponseEntity<AddDeviceResponse> AddNewDevice(@RequestBody AddDeviceRequest addDeviceRequest){
        try {
            Optional<DeviceType> deviceType = deviceTypeRepository.findById(addDeviceRequest.getDevice_type_id());
            if(!deviceType.isPresent()){
                throw new DeviceTypeNotFound("Device Type Not Found");
            }

            Device newDevice = new Device(addDeviceRequest.getName(), addDeviceRequest.getCs(), deviceTypeRepository.findDeviveTypeById(addDeviceRequest.getDevice_type_id()));
            deviceRepository.save(newDevice);

            AddDeviceResponse addDeviceResponse = new AddDeviceResponse("Add Device Success!!!");
            return ResponseEntity.status(200).body(addDeviceResponse);

        } catch (DeviceTypeNotFound err){
            AddDeviceResponse addDeviceResponse = new AddDeviceResponse(err.getMessage());
            return ResponseEntity.status(500).body(addDeviceResponse);
        }
    }
}
