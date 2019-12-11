package com.madd.madd.tmdb.ContentSearch;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.madd.madd.tmdb.HTTP.Models.ContentList;
import com.madd.madd.tmdb.HTTP.Models.MovieList;
import com.madd.madd.tmdb.R;
import com.madd.madd.tmdb.Utilities.References;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContentAdapter extends  RecyclerView.Adapter<ContentAdapter.ViewHolder>{


    private List<ContentList.Content> contentList;
    private ContentEvents contentEvents;

    ContentAdapter(List<ContentList.Content> contentList,
                   ContentEvents contentEvents) {
        this.contentList = contentList;
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

            int itemMinLimit = References.MOVIE_PAGINATE_STEP;
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


