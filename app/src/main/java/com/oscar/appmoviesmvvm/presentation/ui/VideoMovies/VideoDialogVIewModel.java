package com.oscar.appmoviesmvvm.presentation.ui.VideoMovies;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.oscar.appmoviesmvvm.domain.model.ResponseApiVideos;
import com.oscar.appmoviesmvvm.domain.model.Videos;
import com.oscar.appmoviesmvvm.domain.usecase.ListMovies.GetListMovies;
import com.oscar.appmoviesmvvm.domain.usecase.ListMovies.UseCaseListMovies;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class VideoDialogVIewModel extends ViewModel {
    private final GetListMovies getListMovies;
    private final CompositeDisposable disposable = new CompositeDisposable();
    private final MutableLiveData<ResponseApiVideos> responseVideos = new MutableLiveData<>();


    public VideoDialogVIewModel(GetListMovies getListMovies) {
        this.getListMovies = getListMovies;
    }

    MutableLiveData<ResponseApiVideos> getResponseVideos(){
        return responseVideos;
    }

    public void getVideos(String idMovies){
        getVideosUseCase(getListMovies, idMovies);
    }

    private void getVideosUseCase(UseCaseListMovies useCaseListMovies, String idMovies){
        disposable.add(useCaseListMovies.executeGetVideos(idMovies)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe(__ -> responseVideos.setValue(ResponseApiVideos.loading()))
                    .subscribe(result -> responseVideos.setValue(ResponseApiVideos.success(result)),
                            throwable -> responseVideos.setValue(ResponseApiVideos.error(throwable)))
        );
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }


}
