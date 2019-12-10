package com.madd.madd.tmdb.Fragments.Movie;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.madd.madd.tmdb.Models.Lists.Actor.ActorList_;
import com.madd.madd.tmdb.HTTP.Models.Movie;
import com.madd.madd.tmdb.R;
import com.madd.madd.tmdb.Services.MovieService;
import com.madd.madd.tmdb.Utilities.InformativeDialog;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieDetail extends Fragment {

    private View v;
    private TextView textViewTitle;
    private TextView textViewYear;
    private TextView textViewGenre;
    private TextView textViewAverage;
    private ImageView imageViewStar;
    private ImageView imageViewPoster;
    private ImageView imageViewBackdrop;
    private TextView textViewOverview;
    private RecyclerView recyclerViewCast;

    private Movie movie;
    private String movieId;
    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    private OnMovieDetailClose onMovieDetailClose;
    public void setOnMovieDetailClose(OnMovieDetailClose onMovieDetailClose) {
        this.onMovieDetailClose = onMovieDetailClose;
    }

    public MovieDetail() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_movie_detail, container, false);
        bindUI();
        getMovie();
        return v;
    }



    private void getMovie(){

        MovieService.getMovie(movieId, new MovieService.GetMovie() {
            @Override
            public void onSuccess(Movie pMovie) {
                movie = pMovie;
                fillView();
            }

            @Override
            public void onError(String error) {
                InformativeDialog.getInstance(getContext()).setData(
                        error,
                        "Error");
                Objects.requireNonNull(getFragmentManager()).popBackStack();
                onMovieDetailClose.onClose();
            }
        });

        ActorList_ actorList = new ActorList_(getContext(), recyclerViewCast);
        actorList.getMovieCast(movieId);

    }

    private void fillView(){

        if( !movie.getPosterPath().isEmpty() ) {
            Glide.with(imageViewPoster)
                    .load(movie.getPosterPath())
                    .centerCrop()
                    .thumbnail(Glide.with(Objects.requireNonNull(getContext())).
                            load(R.drawable.gif_load))
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
                    .thumbnail(Glide.with(Objects.requireNonNull(getContext())).
                            load(R.drawable.gif_load))
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




    private void bindUI(){
        textViewTitle = v.findViewById(R.id.TV_Movie_Detail_Title);
        textViewYear = v.findViewById(R.id.TV_Movie_Detail_Year);
        textViewGenre = v.findViewById(R.id.TV_Movie_Detail_Genre);
        textViewAverage = v.findViewById(R.id.TV_Movie_Detail_Average);
        imageViewStar = v.findViewById(R.id.IV_Detail_Star);
        imageViewPoster = v.findViewById(R.id.IV_Movie_Detail_Poster);
        imageViewBackdrop = v.findViewById(R.id.IV_Movie_Detail_Backdrop);
        textViewOverview = v.findViewById(R.id.TV_Movie_Detail_Overview);
        recyclerViewCast = v.findViewById(R.id.CTNR_Movie_Detail_Cast);
    }


    public interface OnMovieDetailClose {
        void onClose();
    }

}
