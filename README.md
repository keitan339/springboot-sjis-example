# SpringMVCでのSJIS(MS932)データ送受信

内部のコードはUTF-8のまま、データ送受信をSJIS(MS932)で行う方法。

## 画面表示

metaタグでcharsetをms932に設定する。

```html
<head>
  <meta charset="ms932">
</head>
```

## データ送信（Post）

formタグでaccept-charsetをms932に設定する。

```html
<form action="/submit" method="post" accept-charset="ms932">
```

## データ受信

### spring-bootの設定

application.propertiesでserver.servlet.encoding.forceにfalseを設定する。

```properties
server.servlet.encoding.force=false
```

### CharacterEncodingの設定

ServletFilterでcharacterEncodingでMS932を設定する。

```java
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
```

```java
@Configuration
public class FilterConfig {

  @Value("${filter.ms932.url-patterns}")
  private String[] urlPatterns;

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
```
