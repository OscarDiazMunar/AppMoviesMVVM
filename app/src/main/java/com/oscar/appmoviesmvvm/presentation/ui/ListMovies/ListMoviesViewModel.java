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
    private final MutableLiveData<ResponseApi> responseApiPopular = new MutableLiveData<>();
    private final MutableLiveData<ResponseApi> responseApiTopRated = new MutableLiveData<>();
    private final MutableLiveData<ResponseApi> responseApiUpcoming = new MutableLiveData<>();

    public ListMoviesViewModel(GetListMovies getListMovies) {
        this.getListMovies = getListMovies;
    }

    @Override
    protected void onCleared() {
        //super.onCleared();
        disposables.clear();
    }

    MutableLiveData<ResponseApi> getResponsePopular(){
        return responseApiPopular;
    }
    MutableLiveData<ResponseApi> getResponseTopRated(){
        return responseApiTopRated;
    }
    MutableLiveData<ResponseApi> getResponseUpcoming(){
        return responseApiUpcoming;
    }

    public void getMoviesPopular(String page){
        getListMoviesPopularVM(getListMovies, page);
    }

    public void getMoviesTopRating(String page){
        getListMoviesTopRating(getListMovies, page);
    }

    public void getMoviesUpcoming(String page){
        getListMoviesUpcoming(getListMovies, page);
    }

    private void getListMoviesPopularVM(UseCaseListMovies useCaseListMovies, String page){
        disposables.add(useCaseListMovies.executeGetPopularMovies(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(__ -> responseApiPopular.setValue(ResponseApi.loading()))
                .subscribe(result -> responseApiPopular.setValue(ResponseApi.success(result)),
                        throwable -> responseApiPopular.setValue(ResponseApi.error(throwable))
                )
        );
    }

    private void getListMoviesTopRating(UseCaseListMovies useCaseListMovies, String page) {
        disposables.add(useCaseListMovies.executeGetTopRatingMovies(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(__ -> responseApiTopRated.setValue(ResponseApi.loading()))
                .subscribe(result -> responseApiTopRated.setValue(ResponseApi.success(result)),
                        throwable -> responseApiTopRated.setValue(ResponseApi.error(throwable))
                )
        );
    }

    private void getListMoviesUpcoming(UseCaseListMovies useCaseListMovies, String page) {
        disposables.add(useCaseListMovies.executeGetUpcomingMovies(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(__ -> responseApiUpcoming.setValue(ResponseApi.loading()))
                .subscribe(result -> responseApiUpcoming.setValue(ResponseApi.success(result)),
                        throwable -> responseApiUpcoming.setValue(ResponseApi.error(throwable))
                )
        );
    }
}
