package com.madd.madd.tmdb.data.entities.TVShow;

import com.madd.madd.tmdb.data.entities.TVShow.Model.TVShow;
import com.madd.madd.tmdb.data.entities.TVShow.Model.TVShowList;

import java.util.List;

public interface TVShowDataSource {

    interface Repository{
        void getTVShow(String tvShowId, GetTVShow getTVShow);

        void getTVShowPopularList(int page, GetTVShowList tvShowList);
        void getTVShowTopRatedList(int page, GetTVShowList tvShowList);
        void getTVShowOnAirList(int page, GetTVShowList tvShowList);
    }
    interface Remote{

        void getTVShow(String tvShowId, GetTVShow getTVShow);

        void getTVShowPopularList(int page, GetTVShowList tvShowList);
        void getTVShowTopRatedList(int page, GetTVShowList tvShowList);
        void getTVShowOnAirList(int page, GetTVShowList tvShowList);

    }
    interface Cache{

        void getTVShow(String tvShowId, GetTVShow getTVShow);

        void getTVShowPopularList(int page, GetTVShowList tvShowList);
        void getTVShowTopRatedList(int page, GetTVShowList tvShowList);
        void getTVShowOnAirList(int page, GetTVShowList tvShowList);

        void setTVShowPopularList( List<TVShowList.TVShow> tvShowList);
        void setTVShowTopRatedList(List<TVShowList.TVShow> tvShowList);
        void setTVShowOnAirList( List<TVShowList.TVShow> tvShowList);
    }



    interface GetTVShowList{
        void onSuccess(TVShowList tvShowList);
        void onError(String error);
    }

    interface GetTVShow{
        void onSuccess(TVShow tvShow);
        void onError(String error);
    }


}
