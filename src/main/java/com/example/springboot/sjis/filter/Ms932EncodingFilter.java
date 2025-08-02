package com.example.springboot.sjis.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import java.io.IOException;

/**
 * MS932（Windows-31J）エンコーディングを設定するサーブレットフィルター
 */
public class Ms932EncodingFilter implements Filter {

  private String encoding = "MS932";

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {

    // 文字コードを設定
    request.setCharacterEncoding(this.encoding);

    // 次のFilterを実行
    chain.doFilter(request, response);
  }
}
