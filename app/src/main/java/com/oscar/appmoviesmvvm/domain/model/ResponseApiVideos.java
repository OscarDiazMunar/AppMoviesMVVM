package com.oscar.appmoviesmvvm.domain.model;

import com.oscar.appmoviesmvvm.utils.Constants;

import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;

/**
 * The type Response api videos.
 */
public class ResponseApiVideos {
    private int status;

    /**
     * The Response videos.
     */
    @Nullable
    public final Videos responseVideos;
    /**
     * The Error rest.
     */
    @Nullable
    public final Throwable error;

    private ResponseApiVideos(int status, @Nullable Videos videos, @Nullable Throwable error) {
        this.status = status;
        this.responseVideos = videos;
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
     * Loading response api videos.
     *
     * @return the response api videos
     */
    public static ResponseApiVideos loading(){
        return new ResponseApiVideos(Constants.API_STATUS.LOADING, null, null);
    }


    /**
     * Success response api videos.
     *
     * @param responseVideos the response videos
     * @return the response api videos
     */
    public static ResponseApiVideos success(@NonNull Videos responseVideos){
        return new ResponseApiVideos(Constants.API_STATUS.SUCCESS, responseVideos, null);
    }


    /**
     * Error response api videos.
     *
     * @param error the error
     * @return the response api videos
     */
    public static ResponseApiVideos error(@NonNull Throwable error){
        return new ResponseApiVideos(Constants.API_STATUS.ERROR, null, error);
    }

}
