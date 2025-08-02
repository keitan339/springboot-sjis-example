package com.example.springboot.sjis.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * ホーム画面コントローラー
 */
@Slf4j
@Controller
public class HomeController {

  @GetMapping("/")
  public String home() {
    return "input";
  }

  /**
   * フォーム送信処理
   *
   * @param message 送信メッセージ
   * @param model ビューモデル
   * @return 結果表示ビュー名
   */
  @PostMapping(value = "/submit")
  public String submit(@RequestParam("message") String message, Model model) {
    log.info("受信メッセージ: {}", message);
    model.addAttribute("message", message);
    return "result";
  }
}
