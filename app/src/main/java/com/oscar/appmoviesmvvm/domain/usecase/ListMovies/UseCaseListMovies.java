package com.oscar.appmoviesmvvm.domain.usecase.ListMovies;

import com.oscar.appmoviesmvvm.domain.model.ResponseMovies;

import io.reactivex.Observable;

public interface UseCaseListMovies {
    Observable<ResponseMovies> executeGetPopularMovies(String page);
    Observable<ResponseMovies> executeGetTopRatingMovies(String page);
    Observable<ResponseMovies> executeGetUpcomingMovies(String page);

}
