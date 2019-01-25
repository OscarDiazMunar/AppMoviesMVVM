package com.oscar.appmoviesmvvm.domain.usecase.ListMovies;

import com.oscar.appmoviesmvvm.data.respositories.RepositoryListMovies;
import com.oscar.appmoviesmvvm.domain.model.ResponseMovies;

import io.reactivex.Observable;

public class GetListMovies implements UseCaseListMovies{
    private final RepositoryListMovies repository;

    public GetListMovies(RepositoryListMovies repository) {
        this.repository = repository;
    }


    @Override
    public Observable<ResponseMovies> executeGetPopularMovies(String page) {
        return repository.getListPopularMovie(page);
    }
}
