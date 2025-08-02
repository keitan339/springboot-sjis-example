package com.example.springboot.sjis.config;

import com.example.springboot.sjis.filter.Ms932EncodingFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

/**
 * フィルター設定クラス
 */
@Configuration
public class FilterConfig {

  @Value("${filter.ms932.url-patterns}")
  private String[] urlPatterns;

  /**
   * MS932エンコーディングフィルターのBean定義
   *
   * @return MS932エンコーディングフィルターの登録Bean
   */
  @Bean
  public FilterRegistrationBean<Ms932EncodingFilter> ms932EncodingFilter() {
    FilterRegistrationBean<Ms932EncodingFilter> registrationBean = new FilterRegistrationBean<>();

    // Filterを適用
    registrationBean.setFilter(new Ms932EncodingFilter());

    // Filterを適用するURLを設定
    registrationBean.addUrlPatterns(urlPatterns);

    // 優先順位を最優先に設定
    registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);

    return registrationBean;
  }
}
