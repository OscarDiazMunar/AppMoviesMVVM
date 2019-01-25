package com.oscar.appmoviesmvvm.data.rest;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.oscar.appmoviesmvvm.data.respositories.RepositoryListMovies;
import com.oscar.appmoviesmvvm.domain.model.ResponseMovies;
import com.oscar.appmoviesmvvm.domain.model.Videos;
import com.oscar.appmoviesmvvm.utils.Constants;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MovieDbClient implements RepositoryListMovies {
    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    private MovieDbService service;
    private static MovieDbClient instance;

    private MovieDbClient() {
        final Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        OkHttpClient client = setOkHttpClient();
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.URL.LIST_MOVIES)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();

        service = retrofit.create(MovieDbService.class);
    }

    /**
     * Get instance movie db client.
     *
     * @return the movie db client
     */
    public static MovieDbClient getInstance(){
        if (instance == null){
            instance = new MovieDbClient();
        }

        return instance;
    }

    private static OkHttpClient setOkHttpClient() {
        try {
            X509TrustManager trustManagerX509 = new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

                }

                @Override
                public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            };
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{trustManagerX509};

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            httpClient.sslSocketFactory(sslSocketFactory, trustManagerX509);

            httpClient.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String s, SSLSession sslSession) {
                    //Log.e("hostnameVerifier", s);
                    return true;
                }
            });

            return httpClient.build();
        } catch (KeyManagementException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            //return null;
        }

        return null;
    }

    @Override
    public Observable<ResponseMovies> getListPopularMovie(String page) {
        return service.getListPopularMovies(Constants.KEYS.API_KEY, Constants.LANGUAGE.ENGLISH, page);
    }

    @Override
    public Observable<ResponseMovies> getListTopRatedMovie(String page) {
        return service.getListTopratedMovies(Constants.KEYS.API_KEY, Constants.LANGUAGE.ENGLISH, page);
    }

    @Override
    public Observable<ResponseMovies> getListUpcomingMovie(String page) {
        return service.getListUpcomingMovies(Constants.KEYS.API_KEY, Constants.LANGUAGE.ENGLISH, page);
    }

    @Override
    public Observable<Videos> getVideos(String idMovie) {
        return service.getVideos(idMovie,Constants.KEYS.API_KEY, Constants.LANGUAGE.ENGLISH);
    }
}
