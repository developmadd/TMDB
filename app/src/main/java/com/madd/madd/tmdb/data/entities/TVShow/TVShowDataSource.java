package com.madd.madd.tmdb.data.entities.TVShow;

import com.madd.madd.tmdb.data.entities.DataSource;
import com.madd.madd.tmdb.data.entities.TVShow.Model.TVShow;
import com.madd.madd.tmdb.data.entities.TVShow.Model.TVShowList;

import java.util.List;

public interface TVShowDataSource {

    interface Repository{
        void getTVShow(String tvShowId, DataSource.GetEntity<TVShow> getTVShow);

        void refreshTVShowPopularList(DataSource.GetList<TVShowList.TVShow> getTvShowList);
        void refreshTVShowTopRatedList(DataSource.GetList<TVShowList.TVShow> getTvShowList);
        void refreshTVShowOnAirList(DataSource.GetList<TVShowList.TVShow> getTvShowList);

        void requestNextTVShowPopularList(DataSource.GetList<TVShowList.TVShow> getTvShowList);
        void requestNextTVShowTopRatedList(DataSource.GetList<TVShowList.TVShow> getTvShowList);
        void requestNextTVShowOnAirList(DataSource.GetList<TVShowList.TVShow> getTvShowList);

        void getFilteredPopularList(String text, DataSource.GetList<TVShowList.TVShow> getTvShowList);
        void getFilteredTopRatedList(String text, DataSource.GetList<TVShowList.TVShow> getTvShowList);
        void getFilteredOnAirList(String text, DataSource.GetList<TVShowList.TVShow> getTvShowList);
    }
    interface Remote{

        void getTVShow(String tvShowId, DataSource.GetEntity<TVShow> getTVShow);

        void getTVShowPopularList(int page, DataSource.GetEntity<TVShowList> getTvShowList);
        void getTVShowTopRatedList(int page, DataSource.GetEntity<TVShowList> getTvShowList);
        void getTVShowOnAirList(int page, DataSource.GetEntity<TVShowList> getTvShowList);
    }
    interface Cache{

        void getTVShow(String tvShowId, DataSource.GetEntity<TVShow> getTVShow);

        void getTVShowPopularList(int page, DataSource.GetEntity<TVShowList> getTvShowList);
        void getTVShowTopRatedList(int page, DataSource.GetEntity<TVShowList> getTvShowList);
        void getTVShowOnAirList(int page, DataSource.GetEntity<TVShowList> getTvShowList);

        void getFilteredPopularList(String text, DataSource.GetList<TVShowList.TVShow> getTvShowList);
        void getFilteredOnAirList(String text, DataSource.GetList<TVShowList.TVShow> getTvShowList);
        void getFilteredTopRatedList(String text, DataSource.GetList<TVShowList.TVShow> getTvShowList);

        void setTVShow(TVShow tvShow);
        void setTVShowPopularList( List<TVShowList.TVShow> tvShowList);
        void setTVShowTopRatedList(List<TVShowList.TVShow> tvShowList);
        void setTVShowOnAirList( List<TVShowList.TVShow> tvShowList);
    }





}
