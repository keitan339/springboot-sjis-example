package com.example.springboot.sjis.config;

import com.example.springboot.sjis.filter.Ms932EncodingFilter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FilterConfigIntegrationTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void filterConfigBeanが正しくSpringコンテキストに登録される() {
        // FilterConfigがBeanとして存在することを確認
        assertTrue(applicationContext.containsBean("filterConfig"));
        
        // FilterRegistrationBeanが存在することを確認
        assertTrue(applicationContext.containsBean("ms932EncodingFilter"));
        
        // Beanを取得して検証
        FilterRegistrationBean<?> registrationBean = 
            applicationContext.getBean("ms932EncodingFilter", FilterRegistrationBean.class);
        
        assertNotNull(registrationBean);
        assertNotNull(registrationBean.getFilter());
        assertTrue(registrationBean.getFilter() instanceof Ms932EncodingFilter);
    }
}