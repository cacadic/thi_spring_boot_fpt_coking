package com.example.authen_jwt.models;

import java.io.Serializable;

public class AddDeviceRequest implements Serializable {
    private String name;
    private int cs;
    private int device_type_id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCs() {
        return cs;
    }

    public void setCs(int cs) {
        this.cs = cs;
    }

    public int getDevice_type_id() {
        return device_type_id;
    }

    public void setDevice_type_id(int device_type_id) {
        this.device_type_id = device_type_id;
    }
}
