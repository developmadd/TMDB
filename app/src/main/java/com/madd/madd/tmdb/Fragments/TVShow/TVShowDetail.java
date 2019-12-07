package com.madd.madd.tmdb.Fragments.TVShow;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.madd.madd.tmdb.Models.Lists.Actor.ActorList;
import com.madd.madd.tmdb.Models.TVShow;
import com.madd.madd.tmdb.R;
import com.madd.madd.tmdb.Utilities.InformativeDialog;
import com.madd.madd.tmdb.Utilities.References;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class TVShowDetail extends Fragment {

    private View v;
    private TextView textViewTitle;
    private TextView textViewYear;
    private TextView textViewGenre;
    private TextView textViewAverage;
    private TextView textViewSeasons;
    private TextView textViewEpisodes;
    private ImageView imageViewStar;
    private ImageView imageViewPoster;
    private ImageView imageViewBackdrop;
    private TextView textViewOverview;
    private RecyclerView recyclerViewCast;

    private TVShow tvShow;
    private String tvShowId;
    public void setTVShowId(String tvShowId) {
        this.tvShowId = tvShowId;
    }

    private OnTVShowDetailClose onTvShowDetailClose;
    public void setOnTvShowDetailClose(OnTVShowDetailClose onTvShowDetailClose) {
        this.onTvShowDetailClose = onTvShowDetailClose;
    }

    public TVShowDetail() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_tvshow_detail, container, false);
        bindUI();
        getMovie();
        return v;
    }



    private void getMovie(){
        tvShow = new TVShow();
        tvShow.getFromAPI(getContext(), tvShowId, message -> {
            if( message.equals(References.OK_MESSAGE) ){
                fillView();
            } else {

                if (message.equals(References.NOT_INTERNET_ERROR)) {
                    InformativeDialog.getInstance(getContext()).setData(
                            "Parece que no tienes una conexión a internet",
                            "Sin internet");
                } else if (message.equals(References.SERVER_ERROR)) {
                    InformativeDialog.getInstance(getContext()).setData(
                            "Parece que ha habido un problema con el servidor",
                            "Error en servidor");
                } else {
                    InformativeDialog.getInstance(getContext()).setData(
                            "No hemos podido obtener el detalle de ests serie :(",
                            "No hay serie");
                }
                Objects.requireNonNull(getFragmentManager()).popBackStack();
                onTvShowDetailClose.onClose();

            }
        });


        ActorList actorList = new ActorList(getContext(), recyclerViewCast);
        actorList.getTVShowCast(tvShowId);
    }

    private void fillView(){

        if ( !tvShow.getPosterPath().isEmpty() ) {
            Glide.with(imageViewPoster)
                    .load(tvShow.getPosterPath())
                    .centerCrop()
                    .thumbnail(Glide.with(imageViewPoster).load(R.drawable.gif_load))
                    .into(imageViewPoster);
        } else {
            Glide.with(imageViewBackdrop)
                    .load(R.drawable.image_not_picture)
                    .centerCrop()
                    .into(imageViewPoster);
        }

        if ( !tvShow.getBackdropPath().isEmpty()){
            Glide.with(imageViewBackdrop)
                    .load(tvShow.getBackdropPath())
                    .centerCrop()
                    .thumbnail(Glide.with(imageViewBackdrop).load(R.drawable.gif_load))
                    .into(imageViewBackdrop);
        } else {
            Glide.with(imageViewBackdrop)
                    .load(R.drawable.image_not_picture)
                    .centerCrop()
                    .into(imageViewBackdrop);
        }


        imageViewStar.setVisibility(View.VISIBLE);
        textViewTitle.setText(tvShow.getTitle());
        textViewGenre.setText(tvShow.getGenre());
        textViewYear.setText(tvShow.getYear());
        textViewEpisodes.setText("Episodios: " + tvShow.getEpisodesNumber());
        textViewSeasons.setText("Temporadas: " + tvShow.getSeasonsNumber());
        textViewAverage.setText(tvShow.getVoteAverage());
        textViewOverview.setText(tvShow.getOverview().isEmpty() ?
                        "Sin descripción disponible" : tvShow.getOverview() );

    }




    private void bindUI(){
        textViewTitle = v.findViewById(R.id.TV_TVShow_Detail_Title);
        textViewYear = v.findViewById(R.id.TV_TVShow_Detail_Year);
        textViewGenre = v.findViewById(R.id.TV_TVShow_Detail_Genre);
        textViewAverage = v.findViewById(R.id.TV_TVShow_Detail_Average);
        textViewSeasons = v.findViewById(R.id.TV_TVShow_Detail_Seasons);
        textViewEpisodes = v.findViewById(R.id.TV_TVShow_Detail_Episodes);
        imageViewStar = v.findViewById(R.id.IV_TVShow_Detail_Star);
        imageViewPoster = v.findViewById(R.id.IV_TVShow_Detail_Poster);
        imageViewBackdrop = v.findViewById(R.id.IV_TVShow_Detail_Backdrop);
        textViewOverview = v.findViewById(R.id.TV_TVShow_Detail_Overview);
        recyclerViewCast = v.findViewById(R.id.CTNR_TVShow_Detail_Cast);
    }


    public interface OnTVShowDetailClose {
        void onClose();
    }

}
