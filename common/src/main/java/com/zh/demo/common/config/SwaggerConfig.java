package com.zh.demo.common.config;

import com.zh.demo.common.ProjectUtils;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebFlux;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.lang.annotation.Annotation;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Configuration
@EnableSwagger2WebFlux
@EnableSwagger2WebMvc
//@EnableSwagger2
@ComponentScan(value = {"springfox.documentation"})
public class SwaggerConfig {

    @Value("${spring.profiles.active:dev}")
    private String active;
    @Value("${swagger.title: Title}")
    private String title;
    @Value("${swagger.version: 1.0.0}")
    private String version;
    @Value("${swagger.description: description}")
    private String description;


    //    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public Docket createRestApi() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2);
        ParameterBuilder tokenBuilder = new ParameterBuilder();
        List<Parameter> parameterList = new ArrayList();
        tokenBuilder.name("Authorization")
                .defaultValue("Basic cGFtaXI6cGFtaXI=")
                .description("令牌 token ")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false)
                .build();
        parameterList.add(tokenBuilder.build());
        docket = docket.forCodeGeneration(true);
        docket = docket.directModelSubstitute(LocalDate.class, String.class);
//        docket = docket.genericModelSubstitutes(ResponseEntity.class);

        docket = docket.apiInfo(this.apiInfo())
                .globalOperationParameters(parameterList)
                .enable(!ProjectUtils.isProd(active));
        ApiSelectorBuilder select = docket.select();
        select = select.apis(RequestHandlerSelectors.withClassAnnotation(Api.class));

        Class<? extends Annotation> methodAnnotation = getMethodAnnotation();
        if (Objects.nonNull(methodAnnotation)) {
            select = select.apis(RequestHandlerSelectors.withMethodAnnotation(methodAnnotation));
        }


        select = select.paths(PathSelectors.any());
        return select.build();
//        return new Docket(DocumentationType.SWAGGER_2).apiInfo(this.apiInfo())
//                .globalOperationParameters(parameterList)
//                .enable(!ProjectUtils.isDev(active))
//                .select()
//                .apis(RequestHandlerSelectors.any())
//                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
//                .paths(PathSelectors.any())
//                .build();
    }

    private ApiInfo apiInfo() {
        return (new ApiInfoBuilder())
                .title("Project Document")
                .description("clouad")
                .termsOfServiceUrl("demo")
                .contact(new Contact("demo", "http://localhost", "zh@emal.com"))
                .version("1.0.0")
                .build();
    }


    public Class<? extends Annotation> getMethodAnnotation() {
        if (!isImportSpringWeb()) {
            return null;
        }
        try {
            return (Class<? extends Annotation>) Class.forName("org.springframework.web.bind.annotation.RequestMapping");
        } catch (ClassNotFoundException e) {
            log.error("web 包未导入", e);
            return null;
        }

    }

    public boolean isImportSpringWeb() {
        return Package.getPackage("org.springframework.web") != null
                || Package.getPackage("org.springframework.web.reactive") != null;
    }


}
