package com.oscar.appmoviesmvvm.utils;

import android.support.v4.app.INotificationSideChannel;

/**
 * The type Constants.
 */
public class Constants {
    /**
     * The interface Url.
     */
    public interface URL{
        /**
         * The constant LIST_MOVIES.
         */
        String LIST_MOVIES = "https://api.themoviedb.org/3/movie/";
        /**
         * The constant PHOTO.
         */
        String PHOTO = "https://image.tmdb.org/t/p/w500";
        /**
         * The constant YOUTUBE.
         */
        String YOUTUBE = "https://www.youtube.com/watch?v=";
    }

    /**
     * The interface Language.
     */
    public interface LANGUAGE{
        /**
         * The constant ENGLISH.
         */
        String ENGLISH = "en-US";
    }

    /**
     * The interface Keys.
     */
    public interface KEYS{
        /**
         * The constant API_KEY.
         */
        String API_KEY = "01643e091b663b8a7d9dc90188c60cd0";
    }

    /**
     * The interface Args.
     */
    public interface ARGS{
        /**
         * The constant TYPE_FRAGMENT.
         */
        String TYPE_FRAGMENT = "typeFragment";
        /**
         * The constant ID_MOVIE.
         */
        String ID_MOVIE = " idMovie";
    }

    /**
     * The interface Data base.
     */
    public interface DATA_BASE{
        /**
         * The constant version.
         */
        int version = 1;
        /**
         * The constant name.
         */
        String name = "movies_user.sqlite";
    }

    /**
     * The interface Api status.
     */
    public interface API_STATUS{
        /**
         * The constant LOADING.
         */
        int LOADING = 0;
        /**
         * The constant SUCCESS.
         */
        int SUCCESS = 1;
        /**
         * The constant ERROR.
         */
        int ERROR = 2;
    }

    /**
     * The interface Type fragment.
     */
    public interface TYPE_FRAGMENT{
        /**
         * The constant POPULAR.
         */
        int POPULAR = 10;
        /**
         * The constant TOP_RATED.
         */
        int TOP_RATED = 11;
        /**
         * The constant UPCOMING.
         */
        int UPCOMING = 12;
    }
}
