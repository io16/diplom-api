package com.queue.core;

public enum AdviceType {
  Student(1),
  Group(10);

  public final int id;

  AdviceType(int id) {
    this.id = id;
  }

  public static AdviceType getById(int id) {
    for (var type : values())
      if (type.id == id)
        return type;
    return null;
  }

}
