package com.tahometer.auth.main;

public class User {
  String email;
  String salt;
  String hash;

  public User(String email, String salt, String hash) {
    this.email = email;
    this.salt = salt;
    this.hash = hash;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getSalt() {
    return salt;
  }

  public void setSalt(String salt) {
    this.salt = salt;
  }

  public String getHash() {
    return hash;
  }

  public void setHash(String hash) {
    this.hash = hash;
  }
}
