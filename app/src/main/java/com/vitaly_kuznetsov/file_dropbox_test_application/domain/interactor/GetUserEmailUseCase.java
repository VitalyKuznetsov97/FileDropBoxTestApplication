package com.vitaly_kuznetsov.file_dropbox_test_application.domain.interactor;

import com.vitaly_kuznetsov.file_dropbox_test_application.domain.executor.ExecutionThread;
import com.vitaly_kuznetsov.file_dropbox_test_application.domain.executor.PostExecutionThread;
import com.vitaly_kuznetsov.file_dropbox_test_application.domain.repository.IUserRepository;

import io.reactivex.Observable;

public class GetUserEmailUseCase extends UseCase<String, Void> {

    private IUserRepository authRepository;

    public GetUserEmailUseCase(ExecutionThread threadExecutor, PostExecutionThread postExecutionThread,
                               IUserRepository authRepository) {
        super(threadExecutor, postExecutionThread);
        this.authRepository = authRepository;
    }

    @Override
    Observable<String> buildUseCaseObservable(Void aVoid) {
        return Observable.just(0).map(o -> authRepository.getEmail());
    }
}
