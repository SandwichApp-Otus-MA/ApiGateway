package com.sandwich.app.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.mvc.ProxyExchange;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Slf4j
@RequiredArgsConstructor
abstract class ProxyController {

    private final String prefix;
    private final String url;
    private final HttpServletRequest request;

    protected ResponseEntity<byte[]> proxyGet(ProxyExchange<byte[]> proxy) {
        var uri = getUri(proxy);
        return proxy.uri(uri).get();
    }

    protected ResponseEntity<byte[]> proxyPost(ProxyExchange<byte[]> proxy) {
        var uri = getUri(proxy);
        return proxy.uri(uri).post();
    }

    protected ResponseEntity<byte[]> proxyPut(ProxyExchange<byte[]> proxy) {
        var uri = getUri(proxy);
        return proxy.uri(uri)
            .put();
    }

    protected ResponseEntity<byte[]> proxyPatch(ProxyExchange<byte[]> proxy) {
        var uri = getUri(proxy);
        return proxy.uri(uri)
            .patch();
    }

    protected ResponseEntity<byte[]> proxyDelete(ProxyExchange<byte[]> proxy) {
        var uri = getUri(proxy);
        return proxy.uri(uri)
            .delete();
    }

    @NotNull
    private URI getUri(ProxyExchange<byte[]> proxy) {
        var uri = UriComponentsBuilder
            .fromUriString(url)
            .path(proxy.path(prefix))
            .query(request.getQueryString())
            .build(true)
            .toUri();

        log.debug("uri: {}", uri.toString());

        return uri;
    }
}
