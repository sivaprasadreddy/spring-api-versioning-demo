package dev.sivalabs.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ApiVersionConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
class WebMvcConfig implements WebMvcConfigurer {

    /*@Override
    public void configureApiVersioning(ApiVersionConfigurer configurer) {
        configurer
                .setDefaultVersion( "1.0")
                .addSupportedVersions("1.0", "1.1", "1.2", "2.0")
                .useRequestHeader( "Api-Version");
    }*/
}
