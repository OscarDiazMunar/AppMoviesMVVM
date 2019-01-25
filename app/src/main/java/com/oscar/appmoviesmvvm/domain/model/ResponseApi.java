package com.oscar.appmoviesmvvm.domain.model;

import com.oscar.appmoviesmvvm.utils.Constants;

import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;

/**
 * The type Response api.
 */
public class ResponseApi {
    private int status;
    /**
     * The Response movies.
     */
    @Nullable
    public final ResponseMovies responseMovies;
    /**
     * The Error rest.
     */
    @Nullable
    public final Throwable error;

    private ResponseApi(int status, @Nullable ResponseMovies responseMovies, @Nullable Throwable error) {
        this.status = status;
        this.responseMovies = responseMovies;
        this.error = error;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * Loading response api.
     *
     * @return the response api
     */
    public static ResponseApi loading(){
        return new ResponseApi(Constants.API_STATUS.LOADING, null, null);
    }

    /**
     * Success response api.
     *
     * @param responseMovies the response movies
     * @return the response api
     */
    public static ResponseApi success(@NonNull ResponseMovies responseMovies){
        return new ResponseApi(Constants.API_STATUS.SUCCESS, responseMovies, null);
    }


    /**
     * Error response api.
     *
     * @param error the error
     * @return the response api
     */
    public static ResponseApi error(@NonNull Throwable error){
        return new ResponseApi(Constants.API_STATUS.ERROR, null, error);
    }

}
