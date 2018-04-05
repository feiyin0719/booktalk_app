package com.iffly.booktalk_app.domain.executor;

import io.reactivex.Scheduler;


public interface PostExecutionThread {
  Scheduler getScheduler();
}
