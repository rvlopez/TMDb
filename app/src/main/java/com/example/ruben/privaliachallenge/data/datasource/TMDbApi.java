package com.example.ruben.privaliachallenge.data.datasource;

import com.example.ruben.privaliachallenge.data.datasource.dto.TMDbResponseDto;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface TMDbApi {

    String API_KEY = "93aea0c77bc168d8bbce3918cefefa45";

    @GET("movie/popular?api_key=" + API_KEY + "&language=en-US")
    Observable<TMDbResponseDto> getPopularMovies(@Query("page") int page);

    @GET("search/movie?api_key=" + API_KEY + "&language=en-US")
    Observable<TMDbResponseDto> searchMovie(@Query("query") String query, @Query("page") int page);
}
