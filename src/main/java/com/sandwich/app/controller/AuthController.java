package com.sandwich.app.controller;

import static com.sandwich.app.utils.Constants.USER_SERVICE_PREFIX;

import com.sandwich.app.configuration.ProxyProperties;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.cloud.gateway.mvc.ProxyExchange;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class AuthController extends ProxyController {

    public AuthController(ProxyProperties properties, HttpServletRequest request) {
        super(USER_SERVICE_PREFIX, properties.getUserServiceUrl(), request);
    }

    @PostMapping(USER_SERVICE_PREFIX + "/login")
    public ResponseEntity<byte[]> login(ProxyExchange<byte[]> proxy) {
        return proxyPost(proxy);
    }
}
