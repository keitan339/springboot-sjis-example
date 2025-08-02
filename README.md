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

ServletFilterでcharacterEncodingでMS932を設定する。

```java
public class Ms932EncodingFilter implements Filter {

  private String encoding = "MS932";

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    request.setCharacterEncoding(this.encoding);
    chain.doFilter(request, response);
  }
}
```
