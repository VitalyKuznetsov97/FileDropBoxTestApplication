package com.vitaly_kuznetsov.file_dropbox_test_application.domain.interactor;

import com.vitaly_kuznetsov.file_dropbox_test_application.domain.entity.Entity;
import com.vitaly_kuznetsov.file_dropbox_test_application.domain.executor.ExecutionThread;
import com.vitaly_kuznetsov.file_dropbox_test_application.domain.executor.PostExecutionThread;
import com.vitaly_kuznetsov.file_dropbox_test_application.domain.repository.IFileRepository;

import java.util.ArrayList;

import io.reactivex.Observable;

public class ShowAllFilesFromFolderUseCase extends UseCase<ArrayList<Entity>, String> {

    private IFileRepository repository;

    public ShowAllFilesFromFolderUseCase(ExecutionThread threadExecutor, PostExecutionThread postExecutionThread,
                                         IFileRepository iFileRepository) {
        super(threadExecutor, postExecutionThread);
        this.repository = iFileRepository;
    }

    @Override
    Observable<ArrayList<Entity>> buildUseCaseObservable(String directory) {
        return Observable.just(directory).map(o -> repository.getAllFiles(directory));
    }
}