package com.oscar.appmoviesmvvm.data.respositories;

import com.oscar.appmoviesmvvm.domain.model.ResponseMovies;
import com.oscar.appmoviesmvvm.domain.model.Videos;

import io.reactivex.Observable;

public interface RepositoryListMovies {
    /**
     * Gets list popular movie.
     *
     * @param page the page
     * @return the list popular movie
     */
    Observable<ResponseMovies> getListPopularMovie(String page);

    /**
     * Gets list top rated movie.
     *
     * @param page the page
     * @return the list top rated movie
     */
    Observable<ResponseMovies> getListTopRatedMovie(String page);

    /**
     * Gets list upcoming movie.
     *
     * @param page the page
     * @return the list upcoming movie
     */
    Observable<ResponseMovies> getListUpcomingMovie(String page);

    /**
     * Gets videos.
     *
     * @param idMovie the id movie
     * @return the videos
     */
    Observable<Videos> getVideos(String idMovie);


}
