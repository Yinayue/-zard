package com.oclye.model;

import lombok.Data;

/**
 * @author ocly
 * @date 2018/2/3 17:18
 */
@Data
public class User {
  private String name;
  private String id;
  private boolean online;

  public String getName() {
    return name;
  }

  public String getId() {
    return id;
  }

  public boolean isOnline() {
    return online;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setOnline(boolean online) {
    this.online = online;
  }


}
