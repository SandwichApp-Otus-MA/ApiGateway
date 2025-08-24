package com.sandwich.app.controller;

import static com.sandwich.app.utils.Constants.BILLING_SERVICE_PREFIX;
import static com.sandwich.app.utils.Constants.V1;

import com.sandwich.app.configuration.ProxyProperties;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.cloud.gateway.mvc.ProxyExchange;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping(BILLING_SERVICE_PREFIX + V1)
public class BillingController extends ProxyController {

    public BillingController(ProxyProperties properties, HttpServletRequest request) {
        super(BILLING_SERVICE_PREFIX, properties.getBillingServiceUrl(), request);
    }

    @PreAuthorize("isAuthenticated")
    @PostMapping("/payment/check-status/{id}")
    public ResponseEntity<byte[]> checkStatus(@PathVariable String id, ProxyExchange<byte[]> proxy) {
        return proxyPost(proxy);
    }

    @PreAuthorize("isAuthenticated")
    @PostMapping("/user-account/deposit/{id}")
    public ResponseEntity<byte[]> deposit(@PathVariable String id, ProxyExchange<byte[]> proxy) {
        return proxyPost(proxy);
    }

    @PreAuthorize("isAuthenticated")
    @PostMapping("/user-account/withdraw/{id}")
    public ResponseEntity<byte[]> withdraw(@PathVariable String id, ProxyExchange<byte[]> proxy) {
        return proxyPost(proxy);
    }

}
