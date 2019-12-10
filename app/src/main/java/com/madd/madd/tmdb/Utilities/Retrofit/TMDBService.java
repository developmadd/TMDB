package com.madd.madd.tmdb.Utilities.Retrofit;

import com.madd.madd.tmdb.Models.Cast;
import com.madd.madd.tmdb.Models.MovieList;
import com.madd.madd.tmdb.Models.Movie;

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
    Call<MovieList> getMoviePopularList(@Query("api_key") String apiKey,
                                        @Query("language") String language,
                                        @Query("page") int page);

    @GET("movie/upcoming")
    Call<MovieList> getMovieUpcomingList(@Query("api_key") String apiKey,
                                         @Query("language") String language,
                                         @Query("page") int page);

    @GET("movie/top_rated")
    Call<MovieList> getMovieTopRatedList(@Query("api_key") String apiKey,
                                         @Query("language") String language,
                                         @Query("page") int page);

    @GET("search/movie")
    Call<MovieList> getMovieListByQuery(@Query("api_key") String apiKey,
                                        @Query("language") String language,
                                        @Query("query") String query,
                                        @Query("page") int page);

    @GET("movie/{movie_id}/credits")
    Call<Cast> getMovieCast(@Path("movie_id") String movieId,
                            @Query("api_key") String apiKey);


}

