package com.sandwich.app.controller;

import static com.sandwich.app.utils.Constants.DELIVERY_PREFIX;
import static com.sandwich.app.utils.Constants.V1;

import com.sandwich.app.configuration.ProxyProperties;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.cloud.gateway.mvc.ProxyExchange;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping(DELIVERY_PREFIX + V1)
public class DeliveryController extends ProxyController {

    public DeliveryController(ProxyProperties properties, HttpServletRequest request) {
        super(DELIVERY_PREFIX, properties.getDeliveryUrl(), request);
    }

    @PostMapping("/courier/create")
    public ResponseEntity<byte[]> create(ProxyExchange<byte[]> proxy) {
        return proxyPost(proxy);
    }

    @PostMapping("/courier/change-status/{id}")
    public ResponseEntity<byte[]> changeStatus(@PathVariable UUID id, @RequestParam MultiValueMap<String, String> params, ProxyExchange<byte[]> proxy) {
        return proxyPost(proxy);
    }

    @PostMapping("/delivery/change-status/{id}")
    public ResponseEntity<byte[]> changeDeliveryStatus(@PathVariable UUID id, @RequestParam MultiValueMap<String, String> params, ProxyExchange<byte[]> proxy) {
        return proxyPost(proxy);
    }

    @PostMapping("/delivery/search")
    public ResponseEntity<byte[]> search(ProxyExchange<byte[]> proxy) {
        return proxyPost(proxy);
    }
}
