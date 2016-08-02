package com.nexient.orgchart.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Assert;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by kskronek on 5/24/2016.
 */
@EnableWebMvc
@Configuration
@ComponentScan("com.nexient.orgchart.web")
public class WebTestConfig {

    @Autowired
    WebApplicationContext context;

    @Bean
    public MockMvc init(){
        return MockMvcBuilders.webAppContextSetup(context).build();
    }
}
