package com.zh.demo.auth.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Consumer;

/**
 * @author m
 */
@Data
@Component
@ConfigurationProperties(prefix = "spring.security")
public class SecurityProperties {
    private List<String> ignore;

    public String[] getIgnore() {

        return null;
    }

    public void ignoreEach(Consumer<String> consumer) {
//        Optional.ofNullable(ignore).orElse(Collections.emptyList())
//                .forEach(consumer::accept);
    }
}