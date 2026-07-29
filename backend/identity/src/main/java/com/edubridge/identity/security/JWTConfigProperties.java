package com.edubridge.identity.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "jwt")
public class JWTConfigProperties {

    private String secret = "edubridge-secret-key-change-in-production";
    private long accessExpiration = 900;
    private long refreshExpiration = 604800;
    private String issuer = "edubridge-os";
    private String tokenPrefix = "Bearer ";
    private String header = "Authorization";

    public long getAccessExpirationMillis() {
        return accessExpiration * 1000;
    }

    public long getRefreshExpirationMillis() {
        return refreshExpiration * 1000;
    }
}
