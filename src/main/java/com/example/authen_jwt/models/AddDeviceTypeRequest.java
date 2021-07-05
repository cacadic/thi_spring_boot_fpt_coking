package com.example.authen_jwt.models;

import java.io.Serializable;

public class AddDeviceTypeRequest implements Serializable {
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
