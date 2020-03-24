package com.madd.madd.tmdb.data.entities.Movie;

import com.madd.madd.tmdb.data.entities.DataSource;
import com.madd.madd.tmdb.data.entities.Movie.Model.Movie;
import com.madd.madd.tmdb.data.entities.Movie.Model.MovieList;

public class MovieRepository implements MovieDataSource.Repository {

    private MovieDataSource.Remote movieRemoteDataSource;
    private MovieDataSource.Cache movieCacheDataSource;
    private int popularPage = 1,topRatedPage = 1, upcomingPage = 1;


    MovieRepository(MovieDataSource.Remote movieRemoteDataSource,
                    MovieDataSource.Cache movieCacheDataSource) {
        this.movieRemoteDataSource = movieRemoteDataSource;
        this.movieCacheDataSource = movieCacheDataSource;
    }












    @Override
    public void getMovie(String movieId, DataSource.GetEntity<Movie> getMovie) {
        movieCacheDataSource.getMovie(movieId, new DataSource.GetEntity<Movie>()  {
            @Override
            public void onSuccess(Movie movie) {
                getMovie.onSuccess(movie);
            }

            @Override
            public void onError(String error) {
                movieRemoteDataSource.getMovie(movieId, new DataSource.GetEntity<Movie>() {
                    @Override
                    public void onSuccess(Movie movie) {
                        movieCacheDataSource.setMovie(movie);
                        getMovie.onSuccess(movie);
                    }

                    @Override
                    public void onError(String error) {
                        getMovie.onError(error);
                    }
                });
            }
        });
    }

    @Override
    public void refreshMoviePopularList(DataSource.GetList<MovieList.Movie> getMovieList) {
        popularPage = 1;
        requestNextMoviePopularList(getMovieList);
    }

    @Override
    public void refreshMovieUpcomingList(DataSource.GetList<MovieList.Movie> getMovieList) {
        upcomingPage = 1;
        requestNextMovieUpcomingList(getMovieList);
    }

    @Override
    public void refreshMovieTopRatedList(DataSource.GetList<MovieList.Movie> getMovieList) {
        topRatedPage = 1;
        requestNextMovieTopRatedList(getMovieList);
    }


    @Override
    public void requestNextMoviePopularList(DataSource.GetList<MovieList.Movie> getMovieList) {

        movieCacheDataSource.getMoviePopularList(popularPage, new DataSource.GetEntity<MovieList>() {
            @Override
            public void onSuccess(MovieList movieList) {
                popularPage++;
                getMovieList.onSuccess(movieList.getMovieList());
            }

            @Override
            public void onError(String error) {
                movieRemoteDataSource.getMoviePopularList(popularPage, new DataSource.GetEntity<MovieList>() {
                    @Override
                    public void onSuccess(MovieList movieList) {
                        popularPage++;
                        movieCacheDataSource.setMoviePopularList(movieList.getMovieList());
                        getMovieList.onSuccess(movieList.getMovieList());
                    }

                    @Override
                    public void onError(String error) {
                        getMovieList.onError(error);
                    }
                });
            }
        });
    }

    @Override
    public void requestNextMovieUpcomingList(DataSource.GetList<MovieList.Movie> getMovieList) {
        movieCacheDataSource.getMovieUpcomingList(upcomingPage, new DataSource.GetEntity<MovieList>() {
            @Override
            public void onSuccess(MovieList movieList) {
                upcomingPage++;
                getMovieList.onSuccess(movieList.getMovieList());
            }

            @Override
            public void onError(String error) {
                movieRemoteDataSource.getMovieUpcomingList(upcomingPage, new DataSource.GetEntity<MovieList>() {
                    @Override
                    public void onSuccess(MovieList movieList) {
                        upcomingPage++;
                        movieCacheDataSource.setMovieUpcomingList(movieList.getMovieList());
                        getMovieList.onSuccess(movieList.getMovieList());
                    }


                    @Override
                    public void onError(String error) {
                        getMovieList.onError(error);
                    }
                });
            }
        });
    }

    @Override
    public void requestNextMovieTopRatedList(DataSource.GetList<MovieList.Movie> getMovieList) {
        movieCacheDataSource.getMovieTopRatedList(topRatedPage, new DataSource.GetEntity<MovieList>() {
            @Override
            public void onSuccess(MovieList movieList) {
                topRatedPage++;
                getMovieList.onSuccess(movieList.getMovieList());
            }

            @Override
            public void onError(String error) {
                movieRemoteDataSource.getMovieTopRatedList(topRatedPage, new DataSource.GetEntity<MovieList>() {
                    @Override
                    public void onSuccess(MovieList movieList) {
                        topRatedPage++;
                        movieCacheDataSource.setMovieTopRatedList(movieList.getMovieList());
                        getMovieList.onSuccess(movieList.getMovieList());
                    }

                    @Override
                    public void onError(String error) {
                        getMovieList.onError(error);
                    }
                });
            }
        });
    }




    @Override
    public void getFilteredPopularList(String text, DataSource.GetList<MovieList.Movie> getMovieList) {
        movieCacheDataSource.getFilteredPopularList(text, getMovieList);
    }

    @Override
    public void getFilteredUpcomingList(String text, DataSource.GetList<MovieList.Movie> getMovieList) {
        movieCacheDataSource.getFilteredUpcomingList(text, getMovieList);
    }

    @Override
    public void getFilteredTopRatedList(String text, DataSource.GetList<MovieList.Movie> getMovieList) {
        movieCacheDataSource.getFilteredTopRatedList(text, getMovieList);
    }


}
