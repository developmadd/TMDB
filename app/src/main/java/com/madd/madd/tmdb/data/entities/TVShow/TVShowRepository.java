package com.madd.madd.tmdb.data.entities.TVShow;

import com.madd.madd.tmdb.data.entities.DataSource;
import com.madd.madd.tmdb.data.entities.TVShow.Model.TVShow;
import com.madd.madd.tmdb.data.entities.TVShow.Model.TVShowList;


public class TVShowRepository implements TVShowDataSource.Repository {

    private TVShowDataSource.Remote tvShowRemoteDataSource;
    private TVShowDataSource.Cache tvShowCacheDataSource;
    private int popularPage = 1,topRatedPage = 1, onAirPage = 1;

    TVShowRepository(
            TVShowDataSource.Remote tvShowRemoteDataSource,
            TVShowDataSource.Cache tvShowCacheDataSource) {
        this.tvShowRemoteDataSource = tvShowRemoteDataSource;
        this.tvShowCacheDataSource = tvShowCacheDataSource;
    }




    @Override
    public void getTVShow(String tvShowId, DataSource.GetEntity<TVShow> getTVShow) {
        tvShowCacheDataSource.getTVShow(tvShowId, new DataSource.GetEntity<TVShow>() {
            @Override
            public void onSuccess(TVShow tvShow) {
                getTVShow.onSuccess(tvShow);
            }

            @Override
            public void onError(String error) {
                tvShowRemoteDataSource.getTVShow(tvShowId, new DataSource.GetEntity<TVShow>() {
                    @Override
                    public void onSuccess(TVShow tvShow) {
                        getTVShow.onSuccess(tvShow);
                        tvShowCacheDataSource.setTVShow(tvShow);
                    }

                    @Override
                    public void onError(String error) {
                        getTVShow.onError(error);
                    }
                });
            }
        });

    }

    @Override
    public void refreshTVShowPopularList(DataSource.GetList<TVShowList.TVShow> getTvShowList) {
        popularPage = 1;
        requestNextTVShowPopularList(getTvShowList);
    }

    @Override
    public void refreshTVShowTopRatedList(DataSource.GetList<TVShowList.TVShow> getTvShowList) {
        topRatedPage = 1;
        requestNextTVShowTopRatedList(getTvShowList);
    }

    @Override
    public void refreshTVShowOnAirList(DataSource.GetList<TVShowList.TVShow> getTvShowList) {
        onAirPage = 1;
        requestNextTVShowOnAirList(getTvShowList);
    }

    @Override
    public void requestNextTVShowPopularList(DataSource.GetList<TVShowList.TVShow> getTvShowList) {
        tvShowCacheDataSource.getTVShowTopRatedList(popularPage, new DataSource.GetEntity<TVShowList>() {
            @Override
            public void onSuccess(TVShowList tvShowList) {
                getTvShowList.onSuccess(tvShowList.getTvShowList());
                popularPage++;
            }

            @Override
            public void onError(String error) {
                tvShowRemoteDataSource.getTVShowPopularList(popularPage, new DataSource.GetEntity<TVShowList>() {
                    @Override
                    public void onSuccess(TVShowList tvShowList) {
                        getTvShowList.onSuccess(tvShowList.getTvShowList());
                        tvShowCacheDataSource.setTVShowPopularList(tvShowList.getTvShowList());
                        popularPage++;
                    }

                    @Override
                    public void onError(String error) {
                        getTvShowList.onError(error);
                    }
                });
            }
        });
    }

    @Override
    public void requestNextTVShowTopRatedList(DataSource.GetList<TVShowList.TVShow> getTVShowList) {
        tvShowCacheDataSource.getTVShowTopRatedList(topRatedPage, new DataSource.GetEntity<TVShowList>() {
            @Override
            public void onSuccess(TVShowList tvShowList) {
                topRatedPage++;
                getTVShowList.onSuccess(tvShowList.getTvShowList());
            }

            @Override
            public void onError(String error) {
                tvShowRemoteDataSource.getTVShowTopRatedList(topRatedPage, new DataSource.GetEntity<TVShowList>() {
                    @Override
                    public void onSuccess(TVShowList tvShowList) {
                        topRatedPage++;
                        tvShowCacheDataSource.setTVShowTopRatedList(tvShowList.getTvShowList());
                        getTVShowList.onSuccess(tvShowList.getTvShowList());
                    }

                    @Override
                    public void onError(String error) {
                        getTVShowList.onError(error);
                    }
                });
            }
        });
    }

    @Override
    public void requestNextTVShowOnAirList(DataSource.GetList<TVShowList.TVShow> getTVShowList) {
        tvShowCacheDataSource.getTVShowOnAirList(onAirPage, new DataSource.GetEntity<TVShowList>() {
            @Override
            public void onSuccess(TVShowList tvShowList) {
                getTVShowList.onSuccess(tvShowList.getTvShowList());
                onAirPage++;
            }

            @Override
            public void onError(String error) {
                tvShowRemoteDataSource.getTVShowOnAirList(onAirPage, new DataSource.GetEntity<TVShowList>() {
                    @Override
                    public void onSuccess(TVShowList tvShowList) {
                        tvShowCacheDataSource.setTVShowOnAirList(tvShowList.getTvShowList());
                        getTVShowList.onSuccess(tvShowList.getTvShowList());
                        onAirPage++;
                    }

                    @Override
                    public void onError(String error) {
                        getTVShowList.onError(error);
                    }
                });
            }
        });
    }










    @Override
    public void getFilteredPopularList(String text, DataSource.GetList<TVShowList.TVShow> getTvShowList) {
        tvShowCacheDataSource.getFilteredPopularList(text,getTvShowList);
    }

    @Override
    public void getFilteredTopRatedList(String text, DataSource.GetList<TVShowList.TVShow> getTvShowList) {
        tvShowCacheDataSource.getFilteredTopRatedList(text,getTvShowList);
    }

    @Override
    public void getFilteredOnAirList(String text, DataSource.GetList<TVShowList.TVShow> getTvShowList) {
        tvShowCacheDataSource.getFilteredOnAirList(text,getTvShowList);
    }


}
