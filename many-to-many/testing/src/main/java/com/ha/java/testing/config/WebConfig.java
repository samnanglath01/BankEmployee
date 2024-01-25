package com.ha.java.testing.config;


import com.ha.java.testing.client.CourseClient;
import com.ha.java.testing.client.StudentClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableFeignClients(clients = {StudentClient.class, CourseClient.class})
public class WebConfig implements WebMvcConfigurer {
}
