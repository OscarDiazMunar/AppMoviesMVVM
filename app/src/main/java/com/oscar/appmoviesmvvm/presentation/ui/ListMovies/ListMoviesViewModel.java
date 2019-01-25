package com.oscar.appmoviesmvvm.presentation.ui.ListMovies;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.oscar.appmoviesmvvm.domain.model.ResponseApi;
import com.oscar.appmoviesmvvm.domain.usecase.ListMovies.GetListMovies;
import com.oscar.appmoviesmvvm.domain.usecase.ListMovies.UseCaseListMovies;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class ListMoviesViewModel extends ViewModel {
    private final GetListMovies getListMovies;
    private final CompositeDisposable disposables = new CompositeDisposable();
    private final MutableLiveData<ResponseApi> responseApi = new MutableLiveData<>();

    public ListMoviesViewModel(GetListMovies getListMovies) {
        this.getListMovies = getListMovies;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposables.clear();
    }

    MutableLiveData<ResponseApi> getResponse(){
        return responseApi;
    }

    public void getMovies(String page){
        getListMoviesVM(getListMovies, page);
    }
    //private
    private void getListMoviesVM(UseCaseListMovies useCaseListMovies, String page){
        disposables.add(useCaseListMovies.executeGetPopularMovies(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(__ -> responseApi.setValue(ResponseApi.loading()))
                .subscribe(result -> responseApi.setValue(ResponseApi.success(result)),
                        throwable -> responseApi.setValue(ResponseApi.error(throwable))
                )
        );
    }
}
