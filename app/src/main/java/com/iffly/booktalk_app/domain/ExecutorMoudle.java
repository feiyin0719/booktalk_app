package com.iffly.booktalk_app.domain;

import com.iffly.booktalk_app.domain.executor.JobExecutor;
import com.iffly.booktalk_app.domain.executor.PostExecutionThread;
import com.iffly.booktalk_app.domain.executor.ThreadExecutor;
import com.iffly.booktalk_app.domain.executor.UIThread;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ExecutorMoudle {
    @Singleton
    @Provides
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides
    @Singleton
    PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }

}
