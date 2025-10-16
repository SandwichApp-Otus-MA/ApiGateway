package com.sandwich.app.controller;

import static com.sandwich.app.utils.Constants.RESTAURANT_PREFIX;
import static com.sandwich.app.utils.Constants.V1;

import com.sandwich.app.configuration.ProxyProperties;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.cloud.gateway.mvc.ProxyExchange;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping(RESTAURANT_PREFIX + V1)
public class RestaurantController extends ProxyController {

    public RestaurantController(ProxyProperties properties, HttpServletRequest request) {
        super(RESTAURANT_PREFIX, properties.getRestaurantUrl(), request);
    }

    @GetMapping("/restaurant/{id}")
    public ResponseEntity<byte[]> getById(@PathVariable UUID id, ProxyExchange<byte[]> proxy) {
        return proxyGet(proxy);
    }

    @PostMapping("/restaurant/search")
    public ResponseEntity<byte[]> getAll(ProxyExchange<byte[]> proxy) {
        return proxyPost(proxy);
    }

    @PostMapping("/restaurant/create")
    public ResponseEntity<byte[]> create(ProxyExchange<byte[]> proxy) {
        return proxyPost(proxy);
    }

    @GetMapping("/menu/{id}")
    public ResponseEntity<byte[]> getMenuById(@PathVariable UUID id, ProxyExchange<byte[]> proxy) {
        return proxyGet(proxy);
    }

    @PostMapping("/menu/search")
    public ResponseEntity<byte[]> getMenuAll(ProxyExchange<byte[]> proxy) {
        return proxyPost(proxy);
    }

    @PostMapping("/menu/create")
    public ResponseEntity<byte[]> createMenu(ProxyExchange<byte[]> proxy) {
        return proxyPost(proxy);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<byte[]> getProductById(@PathVariable UUID id, ProxyExchange<byte[]> proxy) {
        return proxyGet(proxy);
    }

    @PostMapping("/product/search")
    public ResponseEntity<byte[]> getProductAll(ProxyExchange<byte[]> proxy) {
        return proxyPost(proxy);
    }

    @PostMapping("/product/create")
    public ResponseEntity<byte[]> createProduct(ProxyExchange<byte[]> proxy) {
        return proxyPost(proxy);
    }
}
