package com.vitaly_kuznetsov.file_dropbox_test_application.presentation.thread;

import com.vitaly_kuznetsov.file_dropbox_test_application.domain.executor.PostExecutionThread;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * MainThread (UI Thread) implementation based on a {@link Scheduler}
 * which will execute actions on the Android UI thread
 */
public class UIThread implements PostExecutionThread {

  @Override public Scheduler getScheduler() {
    return AndroidSchedulers.mainThread();
  }

}
