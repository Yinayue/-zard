package com.example.demo.model;

import lombok.Data;

/**
 * @author ocly
 * @date 2018/2/2 15:39
 */
@Data
public class ChatMessage {

  /**
   * 发送者
   */
  private String name;
  /**
   * 内容
   */
  private String content;
  /**
   * 接收者
   */
  private String receiver;
  /**
   * 时间
   */
  private String date;

  public ChatMessage(String name, String content, String receiver, String date) {
    this.name = name;
    this.content = content;
    this.receiver = receiver;
    this.date = date;
  }

  public ChatMessage() {
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public void setReceiver(String receiver) {
    this.receiver = receiver;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getName() {
    return name;
  }

  public String getContent() {
    return content;
  }

  public String getReceiver() {
    return receiver;
  }

  public String getDate() {
    return date;
  }
}
