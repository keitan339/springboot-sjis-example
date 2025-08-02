package com.example.springboot.sjis.config;

import static org.junit.jupiter.api.Assertions.*;

import com.example.springboot.sjis.filter.Ms932EncodingFilter;
import org.junit.jupiter.api.Test;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.core.Ordered;
import org.springframework.test.util.ReflectionTestUtils;

class FilterConfigTest {

  @Test
  void ms932EncodingFilterBeanが正しく設定される() {
    // FilterConfigのインスタンス作成
    FilterConfig filterConfig = new FilterConfig();

    // @Valueフィールドを手動で設定
    ReflectionTestUtils.setField(filterConfig, "urlPatterns", new String[] {"/submit/*"});

    // Beanメソッドの実行
    FilterRegistrationBean<Ms932EncodingFilter> registrationBean =
        filterConfig.ms932EncodingFilter();

    // 検証
    assertNotNull(registrationBean);
    assertNotNull(registrationBean.getFilter());
    assertTrue(registrationBean.getFilter() instanceof Ms932EncodingFilter);
    assertEquals(Ordered.HIGHEST_PRECEDENCE, registrationBean.getOrder());

    // URLパターンの検証
    assertNotNull(registrationBean.getUrlPatterns());
    assertTrue(registrationBean.getUrlPatterns().contains("/submit/*"));
  }

  @Test
  void 複数のURLパターンが正しく設定される() {
    // FilterConfigのインスタンス作成
    FilterConfig filterConfig = new FilterConfig();

    // 複数のURLパターンを設定
    String[] patterns = {"/submit/*", "/api/*", "/test/*"};
    ReflectionTestUtils.setField(filterConfig, "urlPatterns", patterns);

    // Beanメソッドの実行
    FilterRegistrationBean<Ms932EncodingFilter> registrationBean =
        filterConfig.ms932EncodingFilter();

    // 検証
    assertNotNull(registrationBean.getUrlPatterns());
    assertEquals(3, registrationBean.getUrlPatterns().size());
    assertTrue(registrationBean.getUrlPatterns().contains("/submit/*"));
    assertTrue(registrationBean.getUrlPatterns().contains("/api/*"));
    assertTrue(registrationBean.getUrlPatterns().contains("/test/*"));
  }

  @Test
  void 空のURLパターン配列でも正常に動作する() {
    // FilterConfigのインスタンス作成
    FilterConfig filterConfig = new FilterConfig();

    // 空の配列を設定
    ReflectionTestUtils.setField(filterConfig, "urlPatterns", new String[] {});

    // Beanメソッドの実行
    FilterRegistrationBean<Ms932EncodingFilter> registrationBean =
        filterConfig.ms932EncodingFilter();

    // 検証
    assertNotNull(registrationBean);
    assertNotNull(registrationBean.getUrlPatterns());
    assertTrue(registrationBean.getUrlPatterns().isEmpty());
  }

  @Test
  void 単一のURLパターンでも正常に動作する() {
    // FilterConfigのインスタンス作成
    FilterConfig filterConfig = new FilterConfig();

    // 単一のパターンを設定
    ReflectionTestUtils.setField(filterConfig, "urlPatterns", new String[] {"/*"});

    // Beanメソッドの実行
    FilterRegistrationBean<Ms932EncodingFilter> registrationBean =
        filterConfig.ms932EncodingFilter();

    // 検証
    assertNotNull(registrationBean.getUrlPatterns());
    assertEquals(1, registrationBean.getUrlPatterns().size());
    assertTrue(registrationBean.getUrlPatterns().contains("/*"));
  }
}
