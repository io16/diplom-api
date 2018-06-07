package com.queue.core;

import io.reactivex.Single;

import java.util.List;

public interface GroupService {
  Single<List<Group>> getGroups();
}
