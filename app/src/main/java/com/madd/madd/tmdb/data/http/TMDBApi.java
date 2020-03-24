package com.madd.madd.tmdb.data.http;

import com.madd.madd.tmdb.data.entities.Cast.Model.Cast;
import com.madd.madd.tmdb.data.entities.ContentList.Model.ContentList;
import com.madd.madd.tmdb.data.entities.Movie.Model.MovieList;
import com.madd.madd.tmdb.data.entities.Movie.Model.Movie;
import com.madd.madd.tmdb.data.entities.TVShow.Model.TVShow;
import com.madd.madd.tmdb.data.entities.TVShow.Model.TVShowList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TMDBApi {

    String BASE_URL = "https://api.themoviedb.org/3/";
    String TMDB_API_KEY = "5312d9a66aaa268a0e2aab662d455498";
    int TMDB_PAGINATE_STEP = 20;
    String TMDB_LANGUAGE = "es-MX";

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



    @GET("tv/{tv_show_id}/credits")
    Call<Cast> getTVShowCast(@Path("tv_show_id") String tvShowId,
                            @Query("api_key") String apiKey);

}

