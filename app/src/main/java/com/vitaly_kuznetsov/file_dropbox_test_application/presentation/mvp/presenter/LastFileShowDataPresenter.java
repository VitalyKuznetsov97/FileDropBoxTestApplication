package com.vitaly_kuznetsov.file_dropbox_test_application.presentation.mvp.presenter;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.vitaly_kuznetsov.file_dropbox_test_application.data.repository.DpbxRepository;
import com.vitaly_kuznetsov.file_dropbox_test_application.data.repository.FileRepository;
import com.vitaly_kuznetsov.file_dropbox_test_application.domain.entity.Entity;
import com.vitaly_kuznetsov.file_dropbox_test_application.domain.entity.File;
import com.vitaly_kuznetsov.file_dropbox_test_application.domain.interactor.DefaultObserver;
import com.vitaly_kuznetsov.file_dropbox_test_application.domain.interactor.ShowLastFileUseCase;
import com.vitaly_kuznetsov.file_dropbox_test_application.presentation.mvp.view.IShowDataView;
import com.vitaly_kuznetsov.file_dropbox_test_application.presentation.thread.UIThread;
import com.vitaly_kuznetsov.file_dropbox_test_application.presentation.thread.UseCaseExecutionThread;

import java.util.ArrayList;

@InjectViewState
public class LastFileShowDataPresenter extends AbstractShowDataPresenter{

    private ShowLastFileUseCase showLastFile;

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        showLastFile = new ShowLastFileUseCase(new UseCaseExecutionThread(), new UIThread(),
                new FileRepository());
        disposeOnDestroy(showLastFile);
        refreshData();
    }

    public void onResume(Context context){
        DpbxRepository.checkLogged(context);
        startShowDataUseCase();
    }

    @Override
    void startShowDataUseCase() {
        showLastFile.execute(new LoadFileUseCaseObserver(this), null);
    }

    final class LoadFileUseCaseObserver extends DefaultObserver<File> {

        private IShowDataPresenter presenter;

        LoadFileUseCaseObserver(IShowDataPresenter presenter) {
            this.presenter = presenter;
        }

        @Override public void onComplete() {
            presenter.onLoadingFinished();
        }

        @Override public void onError(Throwable e) {
            ArrayList<Entity> entities = new ArrayList<>();
            entities.add(null);
            presenter.showData(entities);
            presenter.onLoadingFinished();
        }

        @Override public void onNext(File file) {
            ArrayList<Entity> entities = new ArrayList<>();
            entities.add(file);
            presenter.showData(entities);
        }

    }

}