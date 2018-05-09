//package com.jhmk.earlywaring.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
///**
// * Swagger2配置文件
// * @author  zzy on 2017/9/6.
// */
//@Configuration
//@EnableSwagger2
//public class Swagger2 {
//
//    @Bean
//    public Docket createRestApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(apiInfo())
//                .select()
//                 //指定扫描的包路径,将此包下的API生成文档
//                .apis(RequestHandlerSelectors.basePackage("com.jhmk.earlywaring.controller"))
//                .paths(PathSelectors.any())
//                .build();
//    }
//
//    /**
//     * 首页显示信息
//     * @return
//     */
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                .title("Anytxn系统使用Swagger2构建RESTful APIs")
//                .description("更多使用请看：https://swagger.io/docs/")
//                .termsOfServiceUrl("https://swagger.io/docs/")
//                .version("1.0")
//                .build();
//    }
//
//}