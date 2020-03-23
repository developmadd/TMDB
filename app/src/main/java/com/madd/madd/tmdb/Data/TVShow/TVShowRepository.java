package com.madd.madd.tmdb.Data.TVShow;

import com.madd.madd.tmdb.Data.TVShow.Model.TVShowList;

public class TVShowRepository implements TVShowDataSource.Repository {


    private TVShowDataSource.Remote tvShowRemoteDataSource;
    private TVShowDataSource.Cache tvShowCacheDataSource;

    public TVShowRepository(
            TVShowDataSource.Remote tvShowRemoteDataSource,
            TVShowDataSource.Cache tvShowCacheDataSource) {
        this.tvShowRemoteDataSource = tvShowRemoteDataSource;
        this.tvShowCacheDataSource = tvShowCacheDataSource;
    }

    @Override
    public void getTVShow(String tvShowId, TVShowDataSource.GetTVShow getTVShow) {
        tvShowRemoteDataSource.getTVShow(tvShowId,getTVShow);
    }

    @Override
    public void getTVShowPopularList(int page, TVShowDataSource.GetTVShowList getTVShowList) {
        tvShowCacheDataSource.getTVShowPopularList(page, new TVShowDataSource.GetTVShowList() {
            @Override
            public void onSuccess(TVShowList tvShowList) {
                getTVShowList.onSuccess(tvShowList);
            }

            @Override
            public void onError(String error) {
                tvShowRemoteDataSource.getTVShowPopularList(page, new TVShowDataSource.GetTVShowList() {
                    @Override
                    public void onSuccess(TVShowList tvShowList) {
                        tvShowCacheDataSource.setTVShowPopularList(tvShowList.getTvShowList());
                        getTVShowList.onSuccess(tvShowList);
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
    public void getTVShowTopRatedList(int page, TVShowDataSource.GetTVShowList getTVShowList    ) {
        tvShowCacheDataSource.getTVShowTopRatedList(page, new TVShowDataSource.GetTVShowList() {
            @Override
            public void onSuccess(TVShowList tvShowList) {
                getTVShowList.onSuccess(tvShowList);
            }

            @Override
            public void onError(String error) {
                tvShowRemoteDataSource.getTVShowTopRatedList(page, new TVShowDataSource.GetTVShowList() {
                    @Override
                    public void onSuccess(TVShowList tvShowList) {
                        tvShowCacheDataSource.setTVShowTopRatedList(tvShowList.getTvShowList());
                        getTVShowList.onSuccess(tvShowList);
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
    public void getTVShowOnAirList(int page, TVShowDataSource.GetTVShowList getTVShowList) {
        tvShowCacheDataSource.getTVShowOnAirList(page, new TVShowDataSource.GetTVShowList() {
            @Override
            public void onSuccess(TVShowList tvShowList) {
                getTVShowList.onSuccess(tvShowList);
            }

            @Override
            public void onError(String error) {
                tvShowRemoteDataSource.getTVShowOnAirList(page, new TVShowDataSource.GetTVShowList() {
                    @Override
                    public void onSuccess(TVShowList tvShowList) {
                        tvShowCacheDataSource.setTVShowOnAirList(tvShowList.getTvShowList());
                        getTVShowList.onSuccess(tvShowList);
                    }

                    @Override
                    public void onError(String error) {
                        getTVShowList.onError(error);
                    }
                });
            }
        });
    }
}
