package com.vitaly_kuznetsov.file_dropbox_test_application.domain.executor;

import io.reactivex.Scheduler;

/**
 * Thread abstraction created to change the execution context from any thread to any other thread.
 * Useful to encapsulate a Thread, some job will be done in background.
 */
public interface ExecutionThread {
    Scheduler getScheduler();
}
