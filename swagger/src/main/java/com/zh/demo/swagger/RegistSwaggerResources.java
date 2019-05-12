package com.zh.demo.swagger;


import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

@Primary
@Component
public class RegistSwaggerResources implements SwaggerResourcesProvider {

    String swaggerVersion = "2.0";


    @Override
    public List<SwaggerResource> get() {
        List<SwaggerResource> swaggerResources = new ArrayList<SwaggerResource>(10);
        swaggerResources.add(get("config","localhost:8082"));

        return swaggerResources;
    }

    public SwaggerResource get(String name,String location){
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion(swaggerVersion);
        return swaggerResource;
    }


}
