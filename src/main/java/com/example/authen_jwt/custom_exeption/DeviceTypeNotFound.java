package com.example.authen_jwt.custom_exeption;

import org.springframework.security.core.AuthenticationException;

public class DeviceTypeNotFound extends AuthenticationException {
    public DeviceTypeNotFound(String msg) {
        super(msg);
    }
}
