package com.madd.madd.tmdb.UI.Fragments.TVShowDetail;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.madd.madd.tmdb.DI.App;
import com.madd.madd.tmdb.HTTP.Models.Cast;
import com.madd.madd.tmdb.HTTP.Models.TVShow;
import com.madd.madd.tmdb.R;
import com.madd.madd.tmdb.Utilities.InformativeDialog;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class TVShowDetailFragment extends Fragment implements  TVShowDetailContract.View{

    @BindView(R.id.TV_TVShow_Detail_Title) TextView textViewTitle;
    @BindView(R.id.TV_TVShow_Detail_Year) TextView textViewYear;
    @BindView(R.id.TV_TVShow_Detail_Genre) TextView textViewGenre;
    @BindView(R.id.TV_TVShow_Detail_Average) TextView textViewAverage;
    @BindView(R.id.TV_TVShow_Detail_Seasons) TextView textViewSeasons;
    @BindView(R.id.TV_TVShow_Detail_Episodes) TextView textViewEpisodes;
    @BindView(R.id.IV_TVShow_Detail_Star) ImageView imageViewStar;
    @BindView(R.id.IV_TVShow_Detail_Poster) ImageView imageViewPoster;
    @BindView(R.id.IV_TVShow_Detail_Backdrop) ImageView imageViewBackdrop;
    @BindView(R.id.TV_TVShow_Detail_Overview) TextView textViewOverview;
    @BindView(R.id.CTNR_TVShow_Detail_Cast) RecyclerView recyclerView;
    @BindView(R.id.TV_TVShow_Detail_Cast_Error)  TextView textViewCastError;
    @BindView(R.id.PB_TVShow_Detail) ProgressBar progressBar;



    @Inject TVShowDetailContract.Presenter presenter;

    private ActorAdapter actorAdapter;

    private TVShow tvShow;
    private String tvShowId;
    private List<Cast.Actor> actorList = new ArrayList<>();
    private OnTVShowDetailClose onTvShowDetailClose;

    public void setTVShowId(String tvShowId) {
        this.tvShowId = tvShowId;
    }
    public void setOnTvShowDetailClose(OnTVShowDetailClose onTvShowDetailClose) {
        this.onTvShowDetailClose = onTvShowDetailClose;
    }

    public TVShowDetailFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tvshow_detail, container, false);
        ButterKnife.bind(this,view);
        ((App)getActivity().getApplication()).getComponent().inject(this);
        loadView();

        presenter.setView(this);
        presenter.getTVShow();
        presenter.getCast();
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        presenter.setView(this);
    }


    private void loadView(){

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false);
        actorAdapter = new ActorAdapter( actorList );

        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(actorAdapter);


    }






    private void fillTVShowInfo(){

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
        textViewTitle.setText(tvShow.getName());
        textViewGenre.setText(tvShow.getGenre());
        textViewYear.setText(tvShow.getYear());
        textViewEpisodes.setText("Episodios: " + tvShow.getEpisodesNumber());
        textViewSeasons.setText("Temporadas: " + tvShow.getSeasonsNumber());
        textViewAverage.setText(tvShow.getVoteAverage());
        textViewOverview.setText(tvShow.getOverview().isEmpty() ?
                        "Sin descripción disponible" : tvShow.getOverview() );

    }

    @Override
    public void showTVShow(TVShow tvShow) {
        this.tvShow = tvShow;
        fillTVShowInfo();
    }

    @Override
    public void showCast(Cast cast) {
        textViewCastError.setVisibility(View.GONE);
        actorList.addAll(cast.getActorList());
        actorAdapter.notifyDataSetChanged();
    }

    @Override
    public void showLoadingProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showTVShowError() {
        InformativeDialog.getInstance(getContext()).setData(
                "Ha ocurrido un problema al tratar de mostrar el contenido seleccionado",
                "Error");
        onTvShowDetailClose.onClose();
    }

    @Override
    public void showCastError() {
        textViewCastError.setVisibility(View.VISIBLE);

    }

    @Override
    public void closeDetail() {
        onTvShowDetailClose.onClose();
    }

    @Override
    public String getTVShowId() {
        return tvShowId;
    }









    public interface OnTVShowDetailClose {
        void onClose();
    }

}
