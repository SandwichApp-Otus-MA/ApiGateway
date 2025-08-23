package com.sandwich.app.controller;

import static com.sandwich.app.utils.Constants.USER_SERVICE_PREFIX;
import static com.sandwich.app.utils.Constants.V1;

import com.sandwich.app.configuration.ProxyProperties;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.cloud.gateway.mvc.ProxyExchange;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping(USER_SERVICE_PREFIX + V1 + "/user")
public class UserController extends ProxyController {

    public UserController(ProxyProperties properties, HttpServletRequest request) {
        super(USER_SERVICE_PREFIX, properties.getUserServiceUrl(), request);
    }

    @PreAuthorize("""
        @jwtUtils.isSameUser(authentication, #id)
        or hasAnyRole(T(com.sandwich.app.rbac.UserRole).ADMIN, T(com.sandwich.app.rbac.UserRole).MANAGER)""")
    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getById(@PathVariable UUID id, ProxyExchange<byte[]> proxy) {
        return proxyGet(proxy);
    }

    @PreAuthorize("hasAnyRole(T(com.sandwich.app.rbac.UserRole).ADMIN, T(com.sandwich.app.rbac.UserRole).MANAGER)")
    @GetMapping("/search")
    public ResponseEntity<byte[]> getAll(ProxyExchange<byte[]> proxy) {
        return proxyGet(proxy);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    public ResponseEntity<byte[]> create(ProxyExchange<byte[]> proxy) {
        return proxyPost(proxy);
    }

    @PreAuthorize("""
        @jwtUtils.isSameUser(authentication, #body.id)
        or hasAnyRole(T(com.sandwich.app.rbac.UserRole).ADMIN, T(com.sandwich.app.rbac.UserRole).MANAGER)""")
    @PutMapping("/edit")
    public ResponseEntity<byte[]> edit(@RequestBody Object body, ProxyExchange<byte[]> proxy) {
        return proxyPut(proxy);
    }

    @PreAuthorize("""
        @jwtUtils.isSameUser(authentication, #id)
        or hasAnyRole(T(com.sandwich.app.rbac.UserRole).ADMIN, T(com.sandwich.app.rbac.UserRole).MANAGER)""")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<byte[]> delete(@PathVariable UUID id, ProxyExchange<byte[]> proxy) {
        return proxyDelete(proxy);
    }
}
