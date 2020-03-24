package com.madd.madd.tmdb.data.entities.TVShow.Source.Local;


import com.madd.madd.tmdb.data.entities.DataSource;
import com.madd.madd.tmdb.data.entities.TVShow.Model.TVShow;
import com.madd.madd.tmdb.data.http.TMDBApi;
import com.madd.madd.tmdb.data.entities.TVShow.Model.TVShowList;
import com.madd.madd.tmdb.data.entities.TVShow.TVShowDataSource;
import com.madd.madd.tmdb.utilities.Utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TVShowCacheDataSource implements TVShowDataSource.Cache {


    private long lastTimeStampPopular, lastTimeStampOnAir, lastTimeStampTopRated;
    private List<TVShowList.TVShow> popularTVShowList = new ArrayList<>();
    private List<TVShowList.TVShow> onAirTVShowList = new ArrayList<>();
    private List<TVShowList.TVShow> topRatedTVShowList = new ArrayList<>();
    private Map<String, TVShow> tvShowMap = new HashMap<>();

    private long CACHE_LIFETIME = 1000 * 60;





    @Override
    public void getTVShow(String tvShowId, DataSource.GetEntity<TVShow> getTVShow) {
        TVShow tvShow = tvShowMap.get(tvShowId);
        if( tvShow != null ){
            if ( System.currentTimeMillis() - tvShow.getTampStamp() < CACHE_LIFETIME ) {
                getTVShow.onSuccess(tvShow);
                return;
            } else {
                tvShowMap.remove(tvShowId);
            }
        }
        getTVShow.onError("EMPTY");
    }

    @Override
    public void getTVShowPopularList(int page, DataSource.GetEntity<TVShowList> getTVShowList) {
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
    public void getTVShowTopRatedList(int page, DataSource.GetEntity<TVShowList> getTVShowList) {
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
    public void getTVShowOnAirList(int page, DataSource.GetEntity<TVShowList> getTVShowList) {
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



    private void getFilteredTVShowList( String text,
                                       List<TVShowList.TVShow> tvShowList,
                                       DataSource.GetList<TVShowList.TVShow> getTVShowList){
        if (!text.isEmpty()) {

            List<TVShowList.TVShow> filteredTVShowList = new ArrayList<>();
            String compare = Utilities.cleanString(text);
            for (TVShowList.TVShow tvShow : tvShowList) {

                String original = Utilities.cleanString(tvShow.getName());
                if (original.startsWith(compare)) {
                    filteredTVShowList.add(tvShow);
                }
            }
            for (TVShowList.TVShow tvShow : tvShowList) {

                String original = Utilities.cleanString(tvShow.getName());
                if ( !original.startsWith(compare) && original.contains(compare) ) {
                    filteredTVShowList.add(tvShow);
                }
            }
            if(!filteredTVShowList.isEmpty()){
                getTVShowList.onSuccess(filteredTVShowList);
            } else {
                getTVShowList.onError("EMPTY");
            }

        } else {
            getTVShowList.onSuccess(tvShowList);
        }
    }

    @Override
    public void getFilteredPopularList(String text, DataSource.GetList<TVShowList.TVShow> getTvShowList) {
        getFilteredTVShowList(text, popularTVShowList, getTvShowList);
    }

    @Override
    public void getFilteredOnAirList(String text, DataSource.GetList<TVShowList.TVShow> getTvShowList) {
        getFilteredTVShowList(text, onAirTVShowList, getTvShowList);
    }

    @Override
    public void getFilteredTopRatedList(String text, DataSource.GetList<TVShowList.TVShow> getTvShowList) {
        getFilteredTVShowList(text, topRatedTVShowList, getTvShowList);
    }

    @Override
    public void setTVShow(TVShow tvShow) {
        tvShow.setTampStamp();
        tvShowMap.put(tvShow.getId(),tvShow);
    }

    @Override
    public void setTVShowPopularList(List<TVShowList.TVShow> tvShowList) {
        if( popularTVShowList.isEmpty() ) {
            popularTVShowList.addAll(tvShowList);
        }
        lastTimeStampPopular = System.currentTimeMillis();
    }

    @Override
    public void setTVShowTopRatedList(List<TVShowList.TVShow> tvShowList) {
        if( topRatedTVShowList.isEmpty() ) {
            topRatedTVShowList.addAll(tvShowList);
        }
        lastTimeStampTopRated = System.currentTimeMillis();
    }

    @Override
    public void setTVShowOnAirList(List<TVShowList.TVShow> tvShowList) {
        if( onAirTVShowList.isEmpty() ) {
            onAirTVShowList.addAll(tvShowList);
        }
        lastTimeStampOnAir = System.currentTimeMillis();
    }

}
