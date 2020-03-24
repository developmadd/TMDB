package com.madd.madd.tmdb.data.entities.TVShow.Source.Local;


import com.madd.madd.tmdb.data.HTTP.TMDBApi;
import com.madd.madd.tmdb.data.entities.TVShow.Model.TVShowList;
import com.madd.madd.tmdb.data.entities.TVShow.TVShowDataSource;

import java.util.ArrayList;
import java.util.List;

public class TVShowCacheDataSource implements TVShowDataSource.Cache {


    private long lastTimeStampPopular, lastTimeStampOnAir, lastTimeStampTopRated;
    private List<TVShowList.TVShow> popularTVShowList = new ArrayList<>();
    private List<TVShowList.TVShow> onAirTVShowList = new ArrayList<>();
    private List<TVShowList.TVShow> topRatedTVShowList = new ArrayList<>();

    private long CACHE_LIFETIME = 1000 * 60;








    @Override
    public void getTVShow(String tvShowId, TVShowDataSource.GetTVShow getTVShow) {

    }

    @Override
    public void getTVShowPopularList(int page, TVShowDataSource.GetTVShowList getTVShowList) {
        if (System.currentTimeMillis() - lastTimeStampPopular < CACHE_LIFETIME ){
            if( popularTVShowList.size() >= TMDBApi.TMDB_PAGINATE_STEP * page ) {
                int nPage = popularTVShowList.size() / TMDBApi.TMDB_PAGINATE_STEP;
                TVShowList mList = new TVShowList(nPage, popularTVShowList);
                getTVShowList.onSuccess(mList);
                return;
            }
        } else {
            popularTVShowList.clear();
        }

        getTVShowList.onError("EMPTY");
    }

    @Override
    public void getTVShowTopRatedList(int page, TVShowDataSource.GetTVShowList getTVShowList) {
        if (System.currentTimeMillis() - lastTimeStampTopRated < CACHE_LIFETIME ){
            if( topRatedTVShowList.size() >= TMDBApi.TMDB_PAGINATE_STEP * page ) {
                int nPage = topRatedTVShowList.size() / TMDBApi.TMDB_PAGINATE_STEP;
                TVShowList mList = new TVShowList(nPage, topRatedTVShowList);
                getTVShowList.onSuccess(mList);
                return;
            }
        } else {
            topRatedTVShowList.clear();
        }

        getTVShowList.onError("EMPTY");
    }

    @Override
    public void getTVShowOnAirList(int page, TVShowDataSource.GetTVShowList getTVShowList) {
        if (System.currentTimeMillis() - lastTimeStampOnAir < CACHE_LIFETIME ){
            if( onAirTVShowList.size() >= TMDBApi.TMDB_PAGINATE_STEP * page ) {
                int nPage = onAirTVShowList.size() / TMDBApi.TMDB_PAGINATE_STEP;
                TVShowList mList = new TVShowList(nPage, onAirTVShowList);
                getTVShowList.onSuccess(mList);
                return;
            }
        } else {
            onAirTVShowList.clear();
        }

        getTVShowList.onError("EMPTY");
    }








    @Override
    public void setTVShowPopularList(List<TVShowList.TVShow> tvShowList) {
        popularTVShowList.addAll(tvShowList);
        lastTimeStampPopular = System.currentTimeMillis();
    }

    @Override
    public void setTVShowTopRatedList(List<TVShowList.TVShow> tvShowList) {
        topRatedTVShowList.addAll(tvShowList);
        lastTimeStampTopRated = System.currentTimeMillis();
    }

    @Override
    public void setTVShowOnAirList(List<TVShowList.TVShow> tvShowList) {
        onAirTVShowList.addAll(tvShowList);
        lastTimeStampOnAir = System.currentTimeMillis();
    }

}
