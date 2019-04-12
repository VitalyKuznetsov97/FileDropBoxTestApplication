package com.vitaly_kuznetsov.file_dropbox_test_application.domain.interactor;

import com.vitaly_kuznetsov.file_dropbox_test_application.domain.entity.File;
import com.vitaly_kuznetsov.file_dropbox_test_application.domain.executor.ExecutionThread;
import com.vitaly_kuznetsov.file_dropbox_test_application.domain.executor.PostExecutionThread;
import com.vitaly_kuznetsov.file_dropbox_test_application.domain.repository.IFileRepository;

import io.reactivex.Observable;

public class ShowLastFileUseCase extends UseCase<File, Void> {

    private IFileRepository repository;

    public ShowLastFileUseCase(ExecutionThread threadExecutor, PostExecutionThread postExecutionThread,
                               IFileRepository repository) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    Observable<File> buildUseCaseObservable(Void aVoid) {
        return Observable.just(0).map(o -> repository.getLastChosenFile());
    }
}
