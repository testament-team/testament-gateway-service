package dev.testament.gateway.security.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfiguration {

    @Value("${testament.gateway.security.jwt.uri}")
    private String uri = "/auth/login";

    @Value("${testament.gateway.security.jwt.header}")
    private String header = "Authorization";

    @Value("${testament.gateway.security.jwt.prefix}")
    private String prefix = "Bearer ";

    @Value("${testament.gateway.security.jwt.secret}")
    private String secret;

    @Value("${testament.gateway.security.jwt.expiration}")
    private int expiration = 86400;

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public int getExpiration() {
        return expiration;
    }

    public void setExpiration(int expiration) {
        this.expiration = expiration;
    }

}
