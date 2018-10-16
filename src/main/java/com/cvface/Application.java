package com.cvface;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String [] args){
        SpringApplication.run(Application.class,args);
    }

    //这里是全局配置跨域
/*    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/products").allowedOrigins("http://localhost:8080");
            }
        };
    }*/
}
