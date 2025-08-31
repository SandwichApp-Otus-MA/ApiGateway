package com.sandwich.app.controller;

import static com.sandwich.app.utils.Constants.NOTIFICATOR_PREFIX;
import static com.sandwich.app.utils.Constants.V1;

import com.sandwich.app.configuration.ProxyProperties;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.cloud.gateway.mvc.ProxyExchange;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping(NOTIFICATOR_PREFIX + V1)
public class NotificatorController extends ProxyController {

    public NotificatorController(ProxyProperties properties, HttpServletRequest request) {
        super(NOTIFICATOR_PREFIX, properties.getNotificatorUrl(), request);
    }

    // todo: пока что всем
    @PreAuthorize("isAuthenticated")
    @GetMapping("/notificator/search")
    public ResponseEntity<byte[]> search(ProxyExchange<byte[]> proxy) {
        return proxyPost(proxy);
    }

}
