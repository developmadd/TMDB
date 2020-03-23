package com.madd.madd.tmdb.Data.Movie;

import com.madd.madd.tmdb.Data.Movie.Model.MovieList;

public class MovieRepository implements MovieDataSource.Repository {

    private MovieDataSource.Remote movieRemoteDataSource;
    private MovieDataSource.Cache movieCacheDataSource;

    public MovieRepository(MovieDataSource.Remote movieRemoteDataSource,
                           MovieDataSource.Cache movieCacheDataSource) {
        this.movieRemoteDataSource = movieRemoteDataSource;
        this.movieCacheDataSource = movieCacheDataSource;
    }

    @Override
    public void getMovie(String movieId, MovieDataSource.GetMovie getMovie) {
        movieRemoteDataSource.getMovie(movieId,getMovie);
    }

    @Override
    public void getMoviePopularList(int page, MovieDataSource.GetMovieList getMovieList) {
        movieCacheDataSource.getMoviePopularList(page, new MovieDataSource.GetMovieList() {
            @Override
            public void onSuccess(MovieList movieList) {
                getMovieList.onSuccess(movieList);
            }

            @Override
            public void onError(String error) {
                movieRemoteDataSource.getMoviePopularList(page, new MovieDataSource.GetMovieList() {
                    @Override
                    public void onSuccess(MovieList movieList) {
                        movieCacheDataSource.setMoviePopularList(movieList.getMovieList());
                        getMovieList.onSuccess(movieList);
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
    public void getMovieUpcomingList(int page, MovieDataSource.GetMovieList getMovieList) {
        movieCacheDataSource.getMovieUpcomingList(page, new MovieDataSource.GetMovieList() {
            @Override
            public void onSuccess(MovieList movieList) {
                getMovieList.onSuccess(movieList);
            }

            @Override
            public void onError(String error) {
                movieRemoteDataSource.getMovieUpcomingList(page, new MovieDataSource.GetMovieList() {
                    @Override
                    public void onSuccess(MovieList movieList) {
                        movieCacheDataSource.setMovieUpcomingList(movieList.getMovieList());
                        getMovieList.onSuccess(movieList);
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
    public void getMovieTopRatedList(int page, MovieDataSource.GetMovieList getMovieList) {
        movieCacheDataSource.getMovieTopRatedList(page, new MovieDataSource.GetMovieList() {
            @Override
            public void onSuccess(MovieList movieList) {
                getMovieList.onSuccess(movieList);
            }

            @Override
            public void onError(String error) {
                movieRemoteDataSource.getMovieTopRatedList(page, new MovieDataSource.GetMovieList() {
                    @Override
                    public void onSuccess(MovieList movieList) {
                        movieCacheDataSource.setMovieTopRatedList(movieList.getMovieList());
                        getMovieList.onSuccess(movieList);
                    }

                    @Override
                    public void onError(String error) {
                        getMovieList.onError(error);
                    }
                });
            }
        });
    }







}
