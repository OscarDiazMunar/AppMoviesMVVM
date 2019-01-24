package com.oscar.appmoviesmvvm.domain.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * The type Results videos.
 */
public class ResultsVideos implements Serializable {
    @SerializedName("key")
    private String key;

    /**
     * Gets key.
     *
     * @return the key
     */
    public String getKey ()
    {
        return key;
    }

    /**
     * Sets key.
     *
     * @param key the key
     */
    public void setKey (String key)
    {
        this.key = key;
    }

    @Override
    public String toString() {
        return "ResultsVideos{" +
                "key='" + key + '\'' +
                '}';
    }
}
