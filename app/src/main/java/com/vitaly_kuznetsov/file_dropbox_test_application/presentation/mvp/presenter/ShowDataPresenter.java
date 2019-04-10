package com.vitaly_kuznetsov.file_dropbox_test_application.presentation.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.vitaly_kuznetsov.file_dropbox_test_application.domain.entity.Entity;
import com.vitaly_kuznetsov.file_dropbox_test_application.domain.interactor.DefaultObserver;
import com.vitaly_kuznetsov.file_dropbox_test_application.presentation.mvp.model.DirectoryModel;
import com.vitaly_kuznetsov.file_dropbox_test_application.presentation.mvp.model.ErrorModel;
import com.vitaly_kuznetsov.file_dropbox_test_application.presentation.mvp.model.FileModel;
import com.vitaly_kuznetsov.file_dropbox_test_application.presentation.mvp.model.IModel;
import com.vitaly_kuznetsov.file_dropbox_test_application.presentation.mvp.view.IShowDataView;

import java.util.ArrayList;
import java.util.Date;

@InjectViewState
public class ShowDataPresenter extends BasePresenter<IShowDataView> implements IShowDataPresenter{

    private boolean isLoading;

    /**
     * MVP methods:
     */
    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        refreshData();
    }

    /**
     * IShowDataPresenter methods:
     * @param entities is later to be transformed into a correct data structure.
     */
    @Override
    public void showData(ArrayList<Entity> entities) {
        ArrayList<IModel> iModels = new ArrayList<>();
        //for (Entity entity : entities) iModels.add();
        for (int i = 0; i < 5; i++){
            FileModel fileModel = new FileModel();
            fileModel.setName("File" + i);
            fileModel.setUrl("Url" + i);
            fileModel.setSize("Size" + i);
            fileModel.setDate(new Date());
            iModels.add(fileModel);
        }
        for (int i = 0; i < 5; i++){
            DirectoryModel directoryModel = new DirectoryModel();
            directoryModel.setName("Directory" + i);
            directoryModel.setAmountOfFiles(i);
            iModels.add(directoryModel);
        }
        getViewState().showData(iModels);
    }

    @Override
    public void onLoadingFinished() {
        isLoading = false;
        getViewState().hideLoading();
    }

    @Override
    public void showError(Exception exception) {
        ErrorModel errorModel = new ErrorModel();
        errorModel.setMessage(exception.getMessage());
        getViewState().showError(errorModel);
    }

    /**
     * UseCase commands:
     */

    private final class LoadDataUseCaseObserver extends DefaultObserver<ArrayList<Entity>> {

        private IShowDataPresenter presenter;

        LoadDataUseCaseObserver(IShowDataPresenter presenter) {
            this.presenter = presenter;
        }

        @Override public void onComplete() {
            presenter.onLoadingFinished();
        }

        @Override public void onError(Throwable e) {
            presenter.onLoadingFinished();
            presenter.showError((Exception) e);
        }

        @Override public void onNext(ArrayList<Entity> entities) {
            presenter.showData(entities);
        }

    }

    /**
     * Other support methods:
     */
    private void refreshData(){
        if (isLoading) return;
        else isLoading = true;
        getViewState().showLoading();
        showData(null);
        onLoadingFinished();
    }
}
