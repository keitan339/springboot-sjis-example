package com.example.springboot.sjis.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

import com.example.springboot.sjis.filter.Ms932EncodingFilter;

@Configuration
public class FilterConfig {

    @Value("${filter.ms932.url-patterns}")
    private String[] urlPatterns;

    @Bean
    public FilterRegistrationBean<Ms932EncodingFilter> ms932EncodingFilter() {
        FilterRegistrationBean<Ms932EncodingFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new Ms932EncodingFilter());

        // application.propertiesから読み込んだURLパターンを設定
        registrationBean.addUrlPatterns(urlPatterns);

        registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);

        return registrationBean;
    }
}
