package com.madd.madd.tmdb.Utilities.Retrofit;

import com.madd.madd.tmdb.Models.ContentList_;
import com.madd.madd.tmdb.Models.Movie;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TMDBService {


    @GET("movie/{movie_id}")
    Call<Movie> getMovie(@Path("movie_id") String movieId,
                         @Query("api_key") String apiKey,
                         @Query("language") String language);

    @GET("movie/popular")
    Call<ContentList_> getMoviePopularList(@Query("api_key") String apiKey,
                                           @Query("language") String language,
                                           @Query("page") int page);


}

