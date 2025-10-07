package com.sandwich.app.controller;

import static com.sandwich.app.utils.Constants.DELIVERY_PREFIX;
import static com.sandwich.app.utils.Constants.V1;

import com.sandwich.app.configuration.ProxyProperties;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.cloud.gateway.mvc.ProxyExchange;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping(DELIVERY_PREFIX + V1)
public class DeliveryController extends ProxyController {

    public DeliveryController(ProxyProperties properties, HttpServletRequest request) {
        super(DELIVERY_PREFIX, properties.getDeliveryUrl(), request);
    }

    @PreAuthorize("""
        @jwtUtils.isSameUser(authentication, #id)
        or hasAnyRole(T(com.sandwich.app.rbac.UserRole).ADMIN, T(com.sandwich.app.rbac.UserRole).MANAGER)""")
    @PostMapping("/delivery/search")
    public ResponseEntity<byte[]> search(ProxyExchange<byte[]> proxy) {
        return proxyPost(proxy);
    }
}
