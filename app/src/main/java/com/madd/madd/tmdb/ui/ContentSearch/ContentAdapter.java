package com.madd.madd.tmdb.ui.ContentSearch;

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
import com.madd.madd.tmdb.data.entities.ContentList.Model.ContentList;
import com.madd.madd.tmdb.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContentAdapter extends  RecyclerView.Adapter<ContentAdapter.ViewHolder>{


    private List<ContentList.Content> contentList = new ArrayList<>();
    private int moviePage = 1;
    private int tvShowPage = 1;
    private ContentEvents contentEvents;

    public List<ContentList.Content> getList() {
        return contentList;
    }

    public int getMoviePage() {
        return moviePage;
    }

    public void setMoviePage(int moviePage) {
        this.moviePage = moviePage;
    }

    public int getTvShowPage() {
        return tvShowPage;
    }

    public void setTvShowPage(int tvShowPage) {
        this.tvShowPage = tvShowPage;
    }



    ContentAdapter(ContentEvents contentEvents) {
        this.contentEvents = contentEvents;
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
        viewHolder.bind(contentList.get(position));
    }

    @Override
    public int getItemCount() {
        return contentList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{


        @BindView(R.id.IV_Section_Content_Poster) ImageView imageViewPoster;
        @BindView(R.id.TV_Section_Content_Title) TextView textViewTitle;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(ContentList.Content content){
            if ( !content.getPosterPath().isEmpty() ) {
                Glide.with(imageViewPoster)
                        .load(content.getPosterPath())
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

            textViewTitle.setText(content.getName());

            itemView.setOnClickListener(view ->
                    contentEvents.onContentClick(content)
            );

            int itemMinLimit = TMDBApi.TMDB_PAGINATE_STEP;
            if ( contentList.size() >= itemMinLimit
                    && getAdapterPosition() == contentList.size() - 5 ) {
                contentEvents.onRequestNextPage();
            }
        }
    }

    public interface ContentEvents {
        void onContentClick(ContentList.Content selectedContent);
        void onRequestNextPage();

    }
}


