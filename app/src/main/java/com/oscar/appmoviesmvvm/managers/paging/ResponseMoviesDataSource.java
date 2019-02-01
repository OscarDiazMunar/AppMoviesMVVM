package com.oscar.appmoviesmvvm.managers.paging;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;

import com.oscar.appmoviesmvvm.domain.model.ResponseApi;
import com.oscar.appmoviesmvvm.domain.model.Results;
import com.oscar.appmoviesmvvm.domain.usecase.ListMovies.GetListMovies;
import com.oscar.appmoviesmvvm.utils.Constants;

import io.reactivex.disposables.CompositeDisposable;

/**
 * The type Response movies data source.
 */
public class ResponseMoviesDataSource extends PageKeyedDataSource<Integer, Results> {
    private GetListMovies getListMovies;
    private CompositeDisposable disposables;
    private final MutableLiveData<ResponseApi> responseApiPopular = new MutableLiveData<>();
    private int typeFragment;

    /**
     * Instantiates a new Response movies data source.
     *
     * @param getListMovies the get list movies
     * @param disposables   the disposables
     * @param typeFragment  the type fragment
     */
    public ResponseMoviesDataSource(GetListMovies getListMovies, CompositeDisposable disposables, int typeFragment) {
        this.getListMovies = getListMovies;
        this.disposables = disposables;
        this.typeFragment = typeFragment;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Results> callback) {
        switch (typeFragment){
            case Constants.TYPE_FRAGMENT.POPULAR:
                getListMovies.executeGetPopularMovies("1")
                        .doOnSubscribe( disposable -> {
                            disposables.add(disposable);
                            responseApiPopular.postValue(ResponseApi.loading());
                        })
                        .subscribe(result -> {
                                    responseApiPopular.postValue(ResponseApi.success(result));
                                    callback.onResult(result.getResults(), null, 2);
                                },
                                throwable -> responseApiPopular.postValue(ResponseApi.error(throwable))
                        );
                break;
            case Constants.TYPE_FRAGMENT.TOP_RATED:
                getListMovies.executeGetTopRatingMovies("1")
                        .doOnSubscribe( disposable -> {
                            disposables.add(disposable);
                            responseApiPopular.postValue(ResponseApi.loading());
                        })
                        .subscribe(result -> {
                                    responseApiPopular.postValue(ResponseApi.success(result));
                                    callback.onResult(result.getResults(), null, 2);
                                },
                                throwable -> responseApiPopular.postValue(ResponseApi.error(throwable))
                        );
                break;
            case Constants.TYPE_FRAGMENT.UPCOMING:
                getListMovies.executeGetUpcomingMovies("1")
                        .doOnSubscribe( disposable -> {
                            disposables.add(disposable);
                            responseApiPopular.postValue(ResponseApi.loading());
                        })
                        .subscribe(result -> {
                                    responseApiPopular.postValue(ResponseApi.success(result));
                                    callback.onResult(result.getResults(), null, 2);
                                },
                                throwable -> responseApiPopular.postValue(ResponseApi.error(throwable))
                        );
                break;
        }
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Results> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Results> callback) {
        switch (typeFragment){
            case Constants.TYPE_FRAGMENT.POPULAR:
                getListMovies.executeGetPopularMovies(String.valueOf(params.key))
                        .doOnSubscribe( disposable -> {
                            disposables.add(disposable);
                            responseApiPopular.postValue(ResponseApi.loading());
                        })
                        .subscribe(result -> {
                                    responseApiPopular.postValue(ResponseApi.success(result));
                                    int totalPages = Integer.valueOf(result.getTotal_pages());
                                    callback.onResult(result.getResults(),params.key == totalPages ? null : params.key+1);
                                },
                                throwable -> responseApiPopular.postValue(ResponseApi.error(throwable))
                        );
                break;
            case Constants.TYPE_FRAGMENT.TOP_RATED:
                getListMovies.executeGetTopRatingMovies(String.valueOf(params.key))
                        .doOnSubscribe( disposable -> {
                            disposables.add(disposable);
                            responseApiPopular.postValue(ResponseApi.loading());
                        })
                        .subscribe(result -> {
                                    responseApiPopular.postValue(ResponseApi.success(result));
                                    int totalPages = Integer.valueOf(result.getTotal_pages());
                                    callback.onResult(result.getResults(),params.key == totalPages ? null : params.key+1);
                                },
                                throwable -> responseApiPopular.postValue(ResponseApi.error(throwable))
                        );
                break;
            case Constants.TYPE_FRAGMENT.UPCOMING:
                getListMovies.executeGetUpcomingMovies(String.valueOf(params.key))
                        .doOnSubscribe( disposable -> {
                            disposables.add(disposable);
                            responseApiPopular.postValue(ResponseApi.loading());
                        })
                        .subscribe(result -> {
                                    responseApiPopular.postValue(ResponseApi.success(result));
                                    int totalPages = Integer.valueOf(result.getTotal_pages());
                                    callback.onResult(result.getResults(),params.key == totalPages ? null : params.key+1);
                                },
                                throwable -> responseApiPopular.postValue(ResponseApi.error(throwable))
                        );
                break;
        }
    }
}
