package com.example.springboot.sjis;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
class SprinbootSjisApplicationTests {

	@Test
	void mainメソッドが正常に実行される() {
		// mainメソッドのテスト
		assertDoesNotThrow(() -> {
			SprinbootSjisApplication.main(new String[] {});
		});
	}

}
