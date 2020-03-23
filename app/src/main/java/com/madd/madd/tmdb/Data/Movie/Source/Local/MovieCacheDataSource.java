package com.madd.madd.tmdb.Data.Movie.Source.Local;


import com.madd.madd.tmdb.Data.HTTP.TMDBApi;
import com.madd.madd.tmdb.Data.Movie.Model.MovieList;
import com.madd.madd.tmdb.Data.HTTP.HTTPModule;
import com.madd.madd.tmdb.Data.Movie.MovieDataSource;

import java.util.ArrayList;
import java.util.List;

public class MovieCacheDataSource implements MovieDataSource.Cache {


    private long lastTimeStampPopular, lastTimeStampUpcoming, lastTimeStampTopRated;
    private List<MovieList.Movie> popularMovieList = new ArrayList<>();
    private List<MovieList.Movie> upcomingMovieList = new ArrayList<>();
    private List<MovieList.Movie> topRatedMovieList = new ArrayList<>();

    private long CACHE_LIFETIME = 1000 * 60;


    @Override
    public void getMovie(String movieId, MovieDataSource.GetMovie getMovie) {

    }

    @Override
    public void getMoviePopularList(int page, MovieDataSource.GetMovieList movieList) {
        if (System.currentTimeMillis() - lastTimeStampPopular < CACHE_LIFETIME ){
            if( popularMovieList.size() >= TMDBApi.TMDB_PAGINATE_STEP * page ) {
                int nPage = popularMovieList.size() / TMDBApi.TMDB_PAGINATE_STEP;
                MovieList mList = new MovieList(nPage,popularMovieList);
                movieList.onSuccess(mList);
                return;
            }
        } else {
            popularMovieList.clear();
        }

        movieList.onError("EMPTY");
    }

    @Override
    public void getMovieUpcomingList(int page, MovieDataSource.GetMovieList movieList) {
        if (System.currentTimeMillis() - lastTimeStampUpcoming < CACHE_LIFETIME ){
            if( upcomingMovieList.size() >= TMDBApi.TMDB_PAGINATE_STEP * page ) {
                int nPage = upcomingMovieList.size() / TMDBApi.TMDB_PAGINATE_STEP;
                MovieList mList = new MovieList(nPage,upcomingMovieList);
                movieList.onSuccess(mList);
                return;
            }
        } else {
            upcomingMovieList.clear();
        }

        movieList.onError("EMPTY");
    }

    @Override
    public void getMovieTopRatedList(int page, MovieDataSource.GetMovieList movieList) {
        if (System.currentTimeMillis() - lastTimeStampTopRated < CACHE_LIFETIME ){
            if( topRatedMovieList.size() >= TMDBApi.TMDB_PAGINATE_STEP * page ) {
                int nPage = topRatedMovieList.size() / TMDBApi.TMDB_PAGINATE_STEP;
                MovieList mList = new MovieList(nPage,topRatedMovieList);
                movieList.onSuccess(mList);
                return;
            }
        } else {
            topRatedMovieList.clear();
        }

        movieList.onError("EMPTY");
    }


    @Override
    public void setMoviePopularList(List<MovieList.Movie> movieList) {
        popularMovieList.addAll(movieList);
        lastTimeStampPopular = System.currentTimeMillis();
    }

    @Override
    public void setMovieUpcomingList(List<MovieList.Movie> movieList) {
        upcomingMovieList.addAll(movieList);
        lastTimeStampUpcoming = System.currentTimeMillis();
    }

    @Override
    public void setMovieTopRatedList(List<MovieList.Movie> movieList) {
        topRatedMovieList.addAll(movieList);
        lastTimeStampTopRated = System.currentTimeMillis();
    }
}
