package com.oscar.appmoviesmvvm.domain.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * The type Videos.
 */
public class Videos implements Serializable {
    @SerializedName("results")
    private List<ResultsVideos> results;

    /**
     * Gets results.
     *
     * @return the results
     */
    public List<ResultsVideos> getResults ()
    {
        return results;
    }

    /**
     * Sets results.
     *
     * @param results the results
     */
    public void setResults (List<ResultsVideos> results)
    {
        this.results = results;
    }

    @Override
    public String toString() {
        return "Videos{" +
                "results=" + results +
                '}';
    }
}
