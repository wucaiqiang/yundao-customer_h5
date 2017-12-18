package com.yundao.customer.h5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ImportResource;

/**
 * @author 云道
 * @version 1.0.0
 * @blog http://blog.didispace.com
 */
@ServletComponentScan(basePackages = "com.yundao.customer.h5.filter")
@SpringBootApplication
@EnableCaching
@ImportResource(locations = "classpath:config/spring/springRootContext.xml")
public class Application extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

    }

}
