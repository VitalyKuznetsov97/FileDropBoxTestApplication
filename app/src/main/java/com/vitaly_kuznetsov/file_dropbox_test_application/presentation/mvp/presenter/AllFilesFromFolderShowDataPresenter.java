package com.vitaly_kuznetsov.file_dropbox_test_application.presentation.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.vitaly_kuznetsov.file_dropbox_test_application.data.repository.FileRepository;
import com.vitaly_kuznetsov.file_dropbox_test_application.domain.entity.File;
import com.vitaly_kuznetsov.file_dropbox_test_application.domain.interactor.DefaultObserver;
import com.vitaly_kuznetsov.file_dropbox_test_application.domain.interactor.SaveFileUseCase;
import com.vitaly_kuznetsov.file_dropbox_test_application.domain.interactor.ShowAllFilesFromFolderUseCase;
import com.vitaly_kuznetsov.file_dropbox_test_application.presentation.thread.UIThread;
import com.vitaly_kuznetsov.file_dropbox_test_application.presentation.thread.UseCaseExecutionThread;

import java.util.ArrayList;

@InjectViewState
public class AllFilesFromFolderShowDataPresenter extends AbstractShowDataPresenter{

    private ShowAllFilesFromFolderUseCase showAllFilesFromFolder;
    private SaveFileUseCase saveFileUseCase;
    private ArrayList<String> path = new ArrayList<>();

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        showAllFilesFromFolder = new ShowAllFilesFromFolderUseCase(new UseCaseExecutionThread(), new UIThread(),
                new FileRepository());
        saveFileUseCase = new SaveFileUseCase(new UseCaseExecutionThread(), new UIThread(),
                new FileRepository());
        disposeOnDestroy(showAllFilesFromFolder);
        disposeOnDestroy(saveFileUseCase);

        setPath("");
        refreshData();
    }

    boolean pathIsEmpty(){ return path.size() > 1; }

    void removeLastPath(){ path.remove(path.size() - 1); }

    String getPath() { return path.get(path.size() - 1); }

    void setPath(String path) {
        this.path.add(path);
    }

    @Override
    void startShowDataUseCase() {
        showAllFilesFromFolder.execute(new LoadDataUseCaseObserver(this), getPath());
    }

    void startSaveFileUseCase(File file) {
        saveFileUseCase.execute(new SaveFileObserver(this), file);
    }

    final class SaveFileObserver extends DefaultObserver<Object> {

        private AllFilesFromFolderShowDataPresenter presenter;

        SaveFileObserver(AllFilesFromFolderShowDataPresenter presenter) {
            this.presenter = presenter;
        }

        @Override public void onComplete() {
            presenter.fileSaved();
        }
    }

    private void fileSaved(){
        getViewState().finishActivity();
    }
}