package com.zh.demo.swagger;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
//@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addRedirectViewController("/api/v2/api-docs", "/v2/api-docs");
//        registry.addRedirectViewController("/api/swagger-resources/configuration/ui", "/swagger-resources/configuration/ui");
//        registry.addRedirectViewController("/api/swagger-resources/configuration/security", "/swagger-resources/configuration/security");
//        registry.addRedirectViewController("/api/swagger-resources", "/swagger-resources");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        Static resource filtering

//        swagger-ui.html
//        registry.addResourceHandler("swagger-ui.html")
//                .addResourceLocations("/","classpath:/META-INF/resources/","classpath:/resources/");
//                .addResourceLocations("/swagger-ui/swagger-ui.html");

//        Swagger Style
//        registry.addResourceHandler("/webjars/**")
////                .addResourceLocations("/webjars/","classpath:/META-INF/resources/webjars/");
//                .addResourceLocations("/webjars/");

//        由于gradle目录特殊
        registry.addResourceHandler("*.png","*.css","*.js","*.html","*.js.map","*.css.map")
                .addResourceLocations("/resources/swagger-ui/","classpath:../resources/swagger-ui/");
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("/resources/","classpath:../resources/swagger-ui/");
    }
}
