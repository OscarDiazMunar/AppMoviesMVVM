package com.oscar.appmoviesmvvm.presentation.ui.ListMovies;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;

import com.oscar.appmoviesmvvm.domain.model.ResponseApi;
import com.oscar.appmoviesmvvm.domain.model.Results;
import com.oscar.appmoviesmvvm.domain.usecase.ListMovies.GetListMovies;
import com.oscar.appmoviesmvvm.managers.paging.ResponseMoviesDataSourceFactory;

import io.reactivex.disposables.CompositeDisposable;

/**
 * The type List movies view model.
 */
public class ListMoviesViewModel extends ViewModel {
    private final GetListMovies getListMovies;
    private final CompositeDisposable disposables = new CompositeDisposable();
    private final MutableLiveData<ResponseApi> responseApiPopular = new MutableLiveData<>();
    private final MutableLiveData<ResponseApi> responseApiTopRated = new MutableLiveData<>();
    private final MutableLiveData<ResponseApi> responseApiUpcoming = new MutableLiveData<>();

    private LiveData<PagedList<Results>> pagedListResults;
    private ResponseMoviesDataSourceFactory dataSourceFactory;
    private PagedList.Config pagedListConfig;

    /**
     * Instantiates a new List movies view model.
     *
     * @param getListMovies the get list movies
     */
    public ListMoviesViewModel(GetListMovies getListMovies) {
        this.getListMovies = getListMovies;
    }

    private void initializePaging() {
        pagedListConfig = new PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(10)
                .setPageSize(10)
                .build();
        pagedListResults = new LivePagedListBuilder<>(dataSourceFactory, pagedListConfig)
                .build();
    }

    /**
     * Gets paged list results.
     *
     * @param typeFragment the type fragment
     * @return the paged list results
     */
    public LiveData<PagedList<Results>> getPagedListResults(int typeFragment) {
        dataSourceFactory = new ResponseMoviesDataSourceFactory(getListMovies, disposables, typeFragment);
        initializePaging();
        return pagedListResults;
    }

    @Override
    protected void onCleared() {
        disposables.clear();
    }
}
