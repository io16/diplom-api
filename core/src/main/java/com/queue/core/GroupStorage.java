package com.queue.core;

import io.reactivex.Single;

import java.util.List;

public interface GroupStorage {
  Single<List<Group>> getGroups();
}
