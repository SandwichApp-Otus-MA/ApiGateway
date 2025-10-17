package com.sandwich.app.controller;

import static com.sandwich.app.utils.Constants.ORDER_SERVICE_PREFIX;
import static com.sandwich.app.utils.Constants.V1;

import com.sandwich.app.configuration.ProxyProperties;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.cloud.gateway.mvc.ProxyExchange;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping(ORDER_SERVICE_PREFIX + V1)
public class OrderController extends ProxyController {

    public OrderController(ProxyProperties properties, HttpServletRequest request) {
        super(ORDER_SERVICE_PREFIX, properties.getOrderServiceUrl(), request);
    }

    @PreAuthorize("isAuthenticated")
    @GetMapping("/order/{id}")
    public ResponseEntity<byte[]> getById(@PathVariable UUID id, ProxyExchange<byte[]> proxy) {
        return proxyGet(proxy);
    }

    @PreAuthorize("isAuthenticated")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/order/create")
    public ResponseEntity<byte[]> create(ProxyExchange<byte[]> proxy) {
        return proxyPost(proxy);
    }

    @PreAuthorize("isAuthenticated")
    @PostMapping("/order/change-status/{id}")
    public ResponseEntity<byte[]> changeStatus(@PathVariable UUID id, @RequestParam MultiValueMap<String, String> params, ProxyExchange<byte[]> proxy) {
        return proxyPost(proxy);
    }
}
