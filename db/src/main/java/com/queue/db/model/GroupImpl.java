package com.queue.db.model;

import com.queue.core.Group;

public class GroupImpl implements Group {
  private Integer id;
  private String name;

  public GroupImpl(Integer id, String name) {
    this.id = id;
    this.name = name;
  }

  @Override
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  @Override
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
