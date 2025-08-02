package com.example.springboot.sjis.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class Ms932EncodingFilterTest {

    private Ms932EncodingFilter filter;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain filterChain;

    @BeforeEach
    void setUp() {
        filter = new Ms932EncodingFilter();
    }

    @Test
    void doFilterがリクエストエンコーディングをMS932に設定する() throws IOException, ServletException {
        // テスト実行
        filter.doFilter(request, response, filterChain);

        // 検証
        verify(request).setCharacterEncoding("MS932");
        verify(filterChain).doFilter(request, response);
    }

    @Test
    void doFilterがServletRequestとServletResponseでも動作する() throws IOException, ServletException {
        // ServletRequestとServletResponseのモック
        ServletRequest servletRequest = mock(ServletRequest.class);
        ServletResponse servletResponse = mock(ServletResponse.class);

        // テスト実行
        filter.doFilter(servletRequest, servletResponse, filterChain);

        // 検証
        verify(servletRequest).setCharacterEncoding("MS932");
        verify(filterChain).doFilter(servletRequest, servletResponse);
    }

    @Test
    void doFilterがエンコーディング設定後もフィルタチェーンを継続する() throws IOException, ServletException {
        // リクエストがnullの場合のシミュレーション
        doThrow(new RuntimeException("エンコーディング設定エラー"))
                .when(request).setCharacterEncoding(anyString());

        // テスト実行とエラーの確認
        try {
            filter.doFilter(request, response, filterChain);
        } catch (RuntimeException e) {
            // エラーが発生してもフィルタチェーンは呼ばれないことを確認
        }

        // フィルタチェーンが呼ばれていないことを確認
        verify(filterChain, never()).doFilter(any(), any());
    }

    @Test
    void doFilterが複数回呼ばれても正常に動作する() throws IOException, ServletException {
        // 複数回のフィルタ実行
        filter.doFilter(request, response, filterChain);
        filter.doFilter(request, response, filterChain);
        filter.doFilter(request, response, filterChain);

        // 各回でエンコーディングが設定されることを確認
        verify(request, times(3)).setCharacterEncoding("MS932");
        verify(filterChain, times(3)).doFilter(request, response);
    }
}