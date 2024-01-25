package com.ha.testing.config;

import com.ha.testing.client.CourseClient;
import com.ha.testing.client.UserCourseClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableFeignClients(clients = {UserCourseClient.class, CourseClient.class})
public class WebConfig implements WebMvcConfigurer {
}
