package com.madd.madd.tmdb.ui.TVShowDetail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.madd.madd.tmdb.di.App;
import com.madd.madd.tmdb.data.entities.Cast.Model.Cast;
import com.madd.madd.tmdb.data.entities.TVShow.Model.TVShow;
import com.madd.madd.tmdb.R;
import com.madd.madd.tmdb.utilities.InformativeDialog;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TVShowDetailActivity extends AppCompatActivity implements TVShowDetailContract.View{

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

    @Inject
    TVShowDetailContract.Presenter presenter;

    private ActorAdapter actorAdapter;
    private String tvShowId;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tvshow_detail);

        ButterKnife.bind(this);
        ((App)getApplication()).getComponent().inject(this);
        loadView();

        tvShowId = getIntent().getStringExtra("tvShowId");

        presenter.setView(this);
        presenter.getTVShow();
        presenter.getCast();
    }




    @Override
    public void onResume() {
        super.onResume();
        presenter.setView(this);
    }


    private void loadView(){

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false);
        actorAdapter = new ActorAdapter(  );

        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(actorAdapter);


    }






    private void fillTVShowInfo(TVShow tvShow){

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
        fillTVShowInfo(tvShow);
    }

    @Override
    public void showCast(Cast cast) {
        textViewCastError.setVisibility(View.GONE);
        actorAdapter.getList().addAll(cast.getActorList());
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
        InformativeDialog.getInstance(this).setData(
                "Ha ocurrido un problema al tratar de mostrar el contenido seleccionado",
                "Error");
        finish();
    }

    @Override
    public void showCastError() {
        textViewCastError.setVisibility(View.VISIBLE);

    }



    @Override
    public String getTVShowId() {
        return tvShowId;
    }


}
