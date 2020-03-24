package com.madd.madd.tmdb.data.entities.Movie.Source.Local;


import com.madd.madd.tmdb.data.HTTP.TMDBApi;
import com.madd.madd.tmdb.data.entities.Movie.Model.Movie;
import com.madd.madd.tmdb.data.entities.Movie.Model.MovieList;
import com.madd.madd.tmdb.data.entities.Movie.MovieDataSource;
import com.madd.madd.tmdb.utilities.Utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MovieCacheDataSource implements MovieDataSource.Cache {


    private long lastTimeStampPopular, lastTimeStampUpcoming, lastTimeStampTopRated;
    private List<MovieList.Movie> popularMovieList = new ArrayList<>();
    private List<MovieList.Movie> upcomingMovieList = new ArrayList<>();
    private List<MovieList.Movie> topRatedMovieList = new ArrayList<>();
    private Map<String, Movie> movieMap = new HashMap<>();

    private long CACHE_LIFETIME = 1000 * 60;


    @Override
    public void getMovie(String movieId, MovieDataSource.GetMovie getMovie) {
        Movie movie = movieMap.get(movieId);
        if( movie != null ){
            if ( System.currentTimeMillis() - movie.getTampStamp() < CACHE_LIFETIME ) {
                getMovie.onSuccess(movie);
                return;
            } else {
                movieMap.remove(movieId);
            }
        }
        getMovie.onError("EMPTY");
    }

    @Override
    public void getMoviePopularList(int page, MovieDataSource.GetMovieList getMovieList) {
        if (System.currentTimeMillis() - lastTimeStampPopular < CACHE_LIFETIME ){
            if( popularMovieList.size() >= TMDBApi.TMDB_PAGINATE_STEP * page ) {
                int nPage = popularMovieList.size() / TMDBApi.TMDB_PAGINATE_STEP;
                MovieList mList = new MovieList(nPage,popularMovieList);
                getMovieList.onSuccess(mList);
                return;
            }
        } else {
            popularMovieList.clear();
        }

        getMovieList.onError("EMPTY");
    }

    @Override
    public void getMovieUpcomingList(int page, MovieDataSource.GetMovieList getMovieList) {
        if (System.currentTimeMillis() - lastTimeStampUpcoming < CACHE_LIFETIME ){
            if( upcomingMovieList.size() >= TMDBApi.TMDB_PAGINATE_STEP * page ) {
                int nPage = upcomingMovieList.size() / TMDBApi.TMDB_PAGINATE_STEP;
                MovieList mList = new MovieList(nPage,upcomingMovieList);
                getMovieList.onSuccess(mList);
                return;
            }
        } else {
            upcomingMovieList.clear();
        }

        getMovieList.onError("EMPTY");
    }

    @Override
    public void getMovieTopRatedList(int page, MovieDataSource.GetMovieList getMovieList) {
        if (System.currentTimeMillis() - lastTimeStampTopRated < CACHE_LIFETIME ){
            if( topRatedMovieList.size() >= TMDBApi.TMDB_PAGINATE_STEP * page ) {
                int nPage = topRatedMovieList.size() / TMDBApi.TMDB_PAGINATE_STEP;
                MovieList mList = new MovieList(nPage,topRatedMovieList);
                getMovieList.onSuccess(mList);
                return;
            }
        } else {
            topRatedMovieList.clear();
        }

        getMovieList.onError("EMPTY");
    }




    private void getFilteredMovieList( String text,
                                       List<MovieList.Movie> movieList,
                                       MovieDataSource.GetList<MovieList.Movie> getMovieList){
        if (!text.isEmpty()) {

            List<MovieList.Movie> filteredMovieList = new ArrayList<>();
            String compare = Utilities.cleanString(text);
            for (MovieList.Movie movie : movieList) {

                String original = Utilities.cleanString(movie.getTitle());
                if (original.startsWith(compare)) {
                    filteredMovieList.add(movie);
                }
            }
            for (MovieList.Movie movie : movieList) {

                String original = Utilities.cleanString(movie.getTitle());
                if ( !original.startsWith(compare) && original.contains(compare) ) {
                    filteredMovieList.add(movie);
                }
            }
            if(!filteredMovieList.isEmpty()){
                getMovieList.onSuccess(filteredMovieList);
            } else {
                getMovieList.onError("EMPTY");
            }

        } else {
            getMovieList.onSuccess(movieList);
        }
    }

    @Override
    public void getFilteredPopularList(String text, MovieDataSource.GetList<MovieList.Movie> getMovieList) {
        getFilteredMovieList(text,popularMovieList,getMovieList);
    }

    @Override
    public void getFilteredUpcomingList(String text, MovieDataSource.GetList<MovieList.Movie> getMovieList) {
        getFilteredMovieList(text,upcomingMovieList,getMovieList);
    }

    @Override
    public void getFilteredTopRatedList(String text, MovieDataSource.GetList<MovieList.Movie> getMovieList) {
        getFilteredMovieList(text,topRatedMovieList,getMovieList);
    }



    @Override
    public void setMovie(Movie movie) {
        movie.setTampStamp();
        movieMap.put(movie.getId(),movie);
    }


    @Override
    public void setMoviePopularList(List<MovieList.Movie> movieList) {
        if(popularMovieList.isEmpty()) {
            lastTimeStampPopular = System.currentTimeMillis();
        }
        popularMovieList.addAll(movieList);

    }

    @Override
    public void setMovieUpcomingList(List<MovieList.Movie> movieList) {
        if(upcomingMovieList.isEmpty()) {
            lastTimeStampUpcoming = System.currentTimeMillis();
        }
        upcomingMovieList.addAll(movieList);

    }

    @Override
    public void setMovieTopRatedList(List<MovieList.Movie> movieList) {
        if(topRatedMovieList.isEmpty()) {
            lastTimeStampTopRated = System.currentTimeMillis();
        }
        topRatedMovieList.addAll(movieList);

    }
}
