package com.vitaly_kuznetsov.file_dropbox_test_application.domain.interactor;

import com.vitaly_kuznetsov.file_dropbox_test_application.domain.entity.File;
import com.vitaly_kuznetsov.file_dropbox_test_application.domain.executor.ExecutionThread;
import com.vitaly_kuznetsov.file_dropbox_test_application.domain.executor.PostExecutionThread;
import com.vitaly_kuznetsov.file_dropbox_test_application.domain.repository.IFileRepository;

import io.reactivex.Observable;

public class SaveFileUseCase extends UseCase<Object, File> {

    private IFileRepository repository;

    public SaveFileUseCase(ExecutionThread threadExecutor, PostExecutionThread postExecutionThread,
                               IFileRepository repository) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    Observable<Object> buildUseCaseObservable(File file) {
        return Observable.empty().doOnSubscribe(disposable -> repository.saveFile(file));
    }
}