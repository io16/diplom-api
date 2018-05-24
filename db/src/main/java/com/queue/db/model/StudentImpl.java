package com.queue.db.model;

import com.queue.core.Student;

public class StudentImpl implements Student {
  private Integer id;
  private String firstName;
  private String lastName;
  private String email;
  private String hash;

  public StudentImpl(Integer id, String firstName, String lastName, String email, String hash) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.hash = hash;
  }

  @Override
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  @Override
  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  @Override
  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  @Override
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public String getHash() {
    return hash;
  }

  public void setHash(String hash) {
    this.hash = hash;
  }
}
