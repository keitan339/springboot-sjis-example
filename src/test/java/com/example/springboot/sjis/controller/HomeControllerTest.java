package com.example.springboot.sjis.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class HomeControllerTest {

  @Autowired private MockMvc mockMvc;

  @Test
  void homeメソッドがinputビューを返す() throws Exception {
    // GET / のテスト
    mockMvc.perform(get("/")).andExpect(status().isOk()).andExpect(view().name("input"));
  }

  @Test
  void submitメソッドがメッセージを受け取りresultビューを返す() throws Exception {
    // POST /submit のテスト
    String testMessage = "テストメッセージ\n改行あり";

    mockMvc
        .perform(post("/submit").param("message", testMessage))
        .andExpect(status().isOk())
        .andExpect(view().name("result"))
        .andExpect(model().attribute("message", testMessage));
  }

  @Test
  void submitメソッドが空のメッセージを処理する() throws Exception {
    // 空のメッセージのテスト
    mockMvc
        .perform(post("/submit").param("message", ""))
        .andExpect(status().isOk())
        .andExpect(view().name("result"))
        .andExpect(model().attribute("message", ""));
  }

  @Test
  void submitメソッドが特殊文字を含むメッセージを処理する() throws Exception {
    // 特殊文字を含むメッセージのテスト
    String specialMessage = "特殊文字テスト：①②③★☆♪";

    mockMvc
        .perform(post("/submit").param("message", specialMessage))
        .andExpect(status().isOk())
        .andExpect(view().name("result"))
        .andExpect(model().attribute("message", specialMessage));
  }
}
