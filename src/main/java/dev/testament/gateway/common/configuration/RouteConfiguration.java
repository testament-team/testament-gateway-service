package dev.testament.gateway.common.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public final class RouteConfiguration {

    @Value("${zuul.routes.users.url}")
    private String userServiceHost;

    public String getUserServiceHost() {
        return userServiceHost;
    }

    public void setUserServiceHost(String userServiceHost) {
        this.userServiceHost = userServiceHost;
    }

}
