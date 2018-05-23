package com.example.ruben.privaliachallenge.data.datasource;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface TMDBApi {

    String API_KEY = "93aea0c77bc168d8bbce3918cefefa45";

    @GET("movie/popular?api_key" + API_KEY + "&language=en-US")
    Observable<List<TMDBResponse>> getMovies(@Query("page") int page);


}
