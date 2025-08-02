package com.example.springboot.sjis;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SprinbootSjisApplicationTests {

  @Test
  void mainメソッドが正常に実行される() {
    // mainメソッドのテスト
    assertDoesNotThrow(
        () -> {
          SprinbootSjisApplication.main(new String[] {});
        });
  }
}
