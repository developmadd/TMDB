package com.madd.madd.tmdb.ui.TVShowCatalog;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.madd.madd.tmdb.data.HTTP.TMDBApi;
import com.madd.madd.tmdb.data.entities.TVShow.Model.TVShowList;
import com.madd.madd.tmdb.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TVShowAdapter extends  RecyclerView.Adapter<TVShowAdapter.ViewHolder>{


    private List<TVShowList.TVShow> tvShowList;
    private TVShowEvents tvShowEvents;

    TVShowAdapter(List<TVShowList.TVShow> tvShowList,
                  TVShowEvents tvShowEvents) {
        this.tvShowList = tvShowList;
        this.tvShowEvents = tvShowEvents;
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
        viewHolder.bind(tvShowList.get(position));
    }

    @Override
    public int getItemCount() {
        return tvShowList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.IV_Section_Content_Poster) ImageView imageViewPoster;
        @BindView(R.id.TV_Section_Content_Title) TextView textViewTitle;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(TVShowList.TVShow tvShow){
            if ( !tvShow.getPosterPath().isEmpty() ) {
                Glide.with(imageViewPoster)
                        .load(tvShow.getPosterPath())
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

            textViewTitle.setText(tvShow.getName());

            itemView.setOnClickListener(view ->
                    tvShowEvents.onTVShowClick(tvShow)
            );

            int itemMinLimit = TMDBApi.TMDB_PAGINATE_STEP;
            if ( tvShowList.size() >= itemMinLimit
                    && getAdapterPosition() == tvShowList.size() - 5 ) {
                tvShowEvents.onRequestNextPage();
            }
        }
    }

    public interface TVShowEvents {
        void onTVShowClick(TVShowList.TVShow selectedTVShow);
        void onRequestNextPage();

    }
}


