package com.oscar.appmoviesmvvm.managers.paging;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;

import com.oscar.appmoviesmvvm.domain.model.Results;
import com.oscar.appmoviesmvvm.domain.usecase.ListMovies.GetListMovies;

import io.reactivex.disposables.CompositeDisposable;

/**
 * The type Response movies data source factory.
 */
public class ResponseMoviesDataSourceFactory extends DataSource.Factory<Integer, Results> {
    private MutableLiveData<ResponseMoviesDataSource> responseMoviesMutableLiveData;
    private GetListMovies getListMovies;
    private CompositeDisposable disposable;
    private int typeFragment;

    /**
     * Instantiates a new Response movies data source factory.
     *
     * @param getListMovies the get list movies
     * @param disposable    the disposable
     * @param typeFragment  the type fragment
     */
    public ResponseMoviesDataSourceFactory(GetListMovies getListMovies, CompositeDisposable disposable, int typeFragment) {
        this.getListMovies = getListMovies;
        this.disposable = disposable;
        this.typeFragment = typeFragment;
        responseMoviesMutableLiveData = new MutableLiveData<>();
    }

    /**
     * Gets response movies mutable live data.
     *
     * @return the response movies mutable live data
     */
    public MutableLiveData<ResponseMoviesDataSource> getResponseMoviesMutableLiveData() {
        return responseMoviesMutableLiveData;
    }

    @Override
    public DataSource<Integer, Results> create() {
        ResponseMoviesDataSource dataSource = new ResponseMoviesDataSource(getListMovies, disposable, typeFragment);
        responseMoviesMutableLiveData.postValue(dataSource);
        return dataSource;
    }
}
