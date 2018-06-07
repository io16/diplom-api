package com.queue.core;

import io.reactivex.Single;

import javax.inject.Inject;
import java.util.List;

public class GroupServiceImpl implements GroupService {
  @Inject GroupStorage groupStorage;

  @Override
  public Single<List<Group>> getGroups() {
    return groupStorage.getGroups();
  }
}
