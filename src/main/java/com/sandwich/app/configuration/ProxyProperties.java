package com.sandwich.app.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "api-gateway.proxy")
public class ProxyProperties {
    private String userServiceUrl;
    private String orderServiceUrl;
    private String billingServiceUrl;
    private String notificatorUrl;
    private String deliveryUrl;
    private String restaurantUrl;
}
