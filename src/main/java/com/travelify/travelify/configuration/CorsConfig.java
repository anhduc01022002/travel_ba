package com.travelify.travelify.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true); // Cho phép credentials nếu cần
        corsConfiguration.addAllowedOrigin("http://localhost:3000"); // Thêm origin của React
        corsConfiguration.addAllowedHeader("*"); // Cho phép tất cả header
        corsConfiguration.addAllowedMethod("*"); // Cho phép tất cả method
        corsConfiguration.addAllowedMethod("OPTIONS"); // Đảm bảo xử lý preflight

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);

        return new CorsFilter(source);
    }
}