package com.vitaly_kuznetsov.file_dropbox_test_application.presentation.thread;

import com.vitaly_kuznetsov.file_dropbox_test_application.domain.executor.ExecutionThread;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public class UseCaseExecutionThread implements ExecutionThread {

    @Override public Scheduler getScheduler() {
        return Schedulers.computation();
    }

}