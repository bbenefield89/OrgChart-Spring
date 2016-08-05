package com.nexient.orgchart.web.com.nexient.orgchart.web.config;

/**
 * Created by mrangel on 8/5/2016.
 */
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.nexient.security.authentication.AuthoritiesMap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Arrays;
import java.util.List;


@Configuration
@Import({SwaggerConfig.class})
@EnableWebMvc

public class WebConfig extends WebMvcConfigurerAdapter {


    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/**").addResourceLocations("classpath:/META-INF/resources/");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/**");
    }

    @Override
    public void configureMessageConverters(final List<HttpMessageConverter<?>> converters) {

        converters.add(this.jackson2HttpMessageConverter());
    }

    // This must be a bean so tests can access it.

    @Bean
    public MappingJackson2HttpMessageConverter jackson2HttpMessageConverter() {

        final MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        final Jackson2ObjectMapperBuilder builder = this.jacksonBuilder();
        converter.setObjectMapper(builder.build().enable(SerializationFeature.INDENT_OUTPUT));
        return converter;
    }

    private Jackson2ObjectMapperBuilder jacksonBuilder() {

        final Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.propertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
        return builder;
    }

}
