package com.madd.madd.tmdb.ui.MovieDetail;

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
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.madd.madd.tmdb.di.App;
import com.madd.madd.tmdb.data.entities.Cast.Model.Cast;
import com.madd.madd.tmdb.data.entities.Movie.Model.Movie;
import com.madd.madd.tmdb.R;
import com.madd.madd.tmdb.utilities.InformativeDialog;


import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailActivity extends AppCompatActivity implements  MovieDetailContract.View {

    @BindView(R.id.TV_Movie_Detail_Title)     TextView textViewTitle;
    @BindView(R.id.TV_Movie_Detail_Year)      TextView textViewYear;
    @BindView(R.id.TV_Movie_Detail_Genre)     TextView textViewGenre;
    @BindView(R.id.TV_Movie_Detail_Average)   TextView textViewAverage;
    @BindView(R.id.IV_Detail_Star)            ImageView imageViewStar;
    @BindView(R.id.IV_Movie_Detail_Poster)    ImageView imageViewPoster;
    @BindView(R.id.IV_Movie_Detail_Backdrop)  ImageView imageViewBackdrop;
    @BindView(R.id.TV_Movie_Detail_Overview)  TextView textViewOverview;
    @BindView(R.id.CTNR_Movie_Detail_Cast)    RecyclerView recyclerView;
    @BindView(R.id.TV_Movie_Detail_Cast_Error)  TextView textViewCastError;
    @BindView(R.id.PB_Movie_Detail)           ProgressBar progressBar;

    @Inject
    MovieDetailContract.Presenter presenter;

    private ActorAdapter actorAdapter;

    private String movieId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);
        ((App) getApplication()).getComponent().inject(this);
        loadView();

        movieId = getIntent().getStringExtra("movieId");

        presenter.setView(this);
        presenter.getMovie();
        presenter.getCast();

    }



    private void loadView(){

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false);
        actorAdapter = new ActorAdapter();

        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(actorAdapter);

    }

    private void fillMovieInfo(Movie movie){

        if( !movie.getPosterPath().isEmpty() ) {
            Glide.with(imageViewPoster)
                    .load(movie.getPosterPath())
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imageViewPoster);
        } else {
            Glide.with(imageViewPoster)
                    .load(R.drawable.image_not_picture)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imageViewPoster);
        }

        if( !movie.getBackdropPath().isEmpty() ) {
            Glide.with(imageViewBackdrop)
                    .load(movie.getBackdropPath())
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imageViewBackdrop);
        } else {
            Glide.with(imageViewBackdrop)
                    .load(R.drawable.image_not_picture)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imageViewBackdrop);
        }


        imageViewStar.setVisibility(View.VISIBLE);
        textViewTitle.setText(movie.getTitle());
        textViewGenre.setText(movie.getGenre());
        textViewYear.setText(movie.getYear());
        textViewAverage.setText(movie.getVoteAverage());
        textViewOverview.setText(movie.getOverview().isEmpty() ?
                "Sin descripción disponible" : movie.getOverview() );

    }


    @Override
    public void showMovie(Movie movie) {
        fillMovieInfo(movie);
    }

    @Override
    public void showCast(Cast cast) {
        textViewCastError.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
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
    public void showMovieError() {
        InformativeDialog.getInstance(this).setData(
                "Ha ocurrido un problema al tratar de mostrar el contenido seleccionado",
                "Error");
        finish();
    }

    @Override
    public void showCastError() {
        textViewCastError.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }


    @Override
    public String getMovieId() {
        return movieId;
    }

}
