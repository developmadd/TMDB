package com.madd.madd.tmdb.MovieDetail;


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
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.madd.madd.tmdb.DI.App;
import com.madd.madd.tmdb.HTTP.Models.Cast;
import com.madd.madd.tmdb.HTTP.Models.Movie;
import com.madd.madd.tmdb.R;
import com.madd.madd.tmdb.Utilities.InformativeDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieDetailFragment extends Fragment implements MovieDetailContract.View {


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
    @BindView(R.id.PB_Movie_Detail) ProgressBar progressBar;


    @Inject MovieDetailContract.Presenter presenter;

    private ActorAdapter actorAdapter;

    private Movie movie;
    private String movieId;
    private List<Cast.Actor> actorList = new ArrayList<>();
    private OnMovieDetailClose onMovieDetailClose;

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }
    public void setOnMovieDetailClose(OnMovieDetailClose onMovieDetailClose) {
        this.onMovieDetailClose = onMovieDetailClose;
    }

    public MovieDetailFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_movie_detail, container, false);
        ButterKnife.bind(this,view);
        ((App) Objects.requireNonNull(getActivity()).getApplication()).getComponent().inject(this);
        loadView();


        presenter.setView(this);
        presenter.getMovie();
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






    private void fillMovieInfo(){

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
        this.movie = movie;
        fillMovieInfo();
    }

    @Override
    public void showCast(Cast cast) {
        textViewCastError.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
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
    public void showMovieError() {
        InformativeDialog.getInstance(getContext()).setData(
                "Ha ocurrido un problema al tratar de mostrar el contenido seleccionado",
                "Error");
        onMovieDetailClose.onClose();
    }

    @Override
    public void showCastError() {
        textViewCastError.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void closeDetail() {
        onMovieDetailClose.onClose();
    }

    @Override
    public String getMovieId() {
        return movieId;
    }









    public interface OnMovieDetailClose {
        void onClose();
    }

}



