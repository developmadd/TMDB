package com.madd.madd.tmdb.UI.MovieCatalog;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.madd.madd.tmdb.Data.HTTP.TMDBApi;
import com.madd.madd.tmdb.Data.Movie.Model.MovieList;
import com.madd.madd.tmdb.Data.HTTP.HTTPModule;
import com.madd.madd.tmdb.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieAdapter extends  RecyclerView.Adapter<MovieAdapter.ViewHolder>{


    private List<MovieList.Movie> movieList = new ArrayList<>();
    private int page = 1;
    private MovieEvents movieEvents;

    MovieAdapter(MovieEvents movieEvents) {
        this.movieEvents = movieEvents;
    }

    public List<MovieList.Movie> getList() {
        return movieList;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.section_content,
                viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.bind(movieList.get(position));
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{


        @BindView(R.id.IV_Section_Content_Poster) ImageView imageViewPoster;
        @BindView(R.id.TV_Section_Content_Title) TextView textViewTitle;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(MovieList.Movie movie){
            if ( !movie.getPosterPath().isEmpty() ) {
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

            textViewTitle.setText(movie.getTitle());

            itemView.setOnClickListener(view ->
                    movieEvents.onMovieClick(movie)
            );

            int itemMinLimit = TMDBApi.TMDB_PAGINATE_STEP;
            if ( movieList.size() >= itemMinLimit
                    && getAdapterPosition() == movieList.size() - 5 ) {
                movieEvents.onRequestNextPage();
            }
        }
    }

    public interface MovieEvents {
        void onMovieClick(MovieList.Movie selectedMovie);
        void onRequestNextPage();

    }
}


