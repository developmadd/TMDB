package com.madd.madd.tmdb.HTTP;

import com.madd.madd.tmdb.HTTP.Models.Cast;
import com.madd.madd.tmdb.HTTP.Models.ContentList;
import com.madd.madd.tmdb.HTTP.Models.MovieList;
import com.madd.madd.tmdb.HTTP.Models.Movie;
import com.madd.madd.tmdb.HTTP.Models.TVShow;
import com.madd.madd.tmdb.HTTP.Models.TVShowList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TMDBApi {


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
    Call<ContentList> getMovieListByQuery(@Query("api_key") String apiKey,
                                          @Query("language") String language,
                                          @Query("query") String query,
                                          @Query("page") int page);




    @GET("movie/{movie_id}/credits")
    Call<Cast> getMovieCast(@Path("movie_id") String movieId,
                            @Query("api_key") String apiKey);




    @GET("tv/{tv_show_id}")
    Call<TVShow> getTVShow(@Path("tv_show_id") String tvShowId,
                           @Query("api_key") String apiKey,
                           @Query("language") String language);



    @GET("tv/popular")
    Call<TVShowList> getTVShowPopularList(@Query("api_key") String apiKey,
                                         @Query("language") String language,
                                         @Query("page") int page);



    @GET("tv/on_the_air")
    Call<TVShowList> getTVShowOnAirList(@Query("api_key") String apiKey,
                                         @Query("language") String language,
                                         @Query("page") int page);



    @GET("tv/top_rated")
    Call<TVShowList> getTVShowTopRatedList(@Query("api_key") String apiKey,
                                         @Query("language") String language,
                                         @Query("page") int page);



    @GET("search/tv")
    Call<ContentList> getTVShowListByQuery(@Query("api_key") String apiKey,
                                        @Query("language") String language,
                                        @Query("query") String query,
                                        @Query("page") int page);



    @GET("movie/{tv_show_id}/credits")
    Call<Cast> getTVShowCast(@Path("tv_show_id") String tvShowId,
                            @Query("api_key") String apiKey);

}

