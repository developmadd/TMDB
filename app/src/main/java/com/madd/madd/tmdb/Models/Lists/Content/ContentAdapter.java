package com.madd.madd.tmdb.Models.Lists.Content;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.madd.madd.tmdb.Models.ContentList_;
import com.madd.madd.tmdb.R;
import com.madd.madd.tmdb.Utilities.References;

import java.util.List;

public class ContentAdapter extends  RecyclerView.Adapter<ContentAdapter.ViewHolder>{

    private Context context;
    private List<ContentList_.Content> contentList;
    private ContentListener contentListener;

    private boolean singleContentType;


    ContentAdapter(List<ContentList_.Content> contentList, boolean singleContentType,
                   ContentListener contentListener) {
        this.contentList = contentList;
        this.contentListener = contentListener;
        this.singleContentType = singleContentType;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        context = viewGroup.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.section_content,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.bind(contentList.get(position), contentListener,position);
    }

    @Override
    public int getItemCount() {
        return contentList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private View itemView;
        private ImageView imageViewPoster;
        private TextView textViewTitle;
        private TextView textViewType;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            this.imageViewPoster = itemView.findViewById(R.id.IV_Section_Content_Poster);
            this.textViewTitle = itemView.findViewById(R.id.TV_Section_Content_Title);
            this.textViewType = itemView.findViewById(R.id.TV_Section_Content_Type);
        }

        void bind(ContentList_.Content content, ContentListener contentListener, int position){
            if ( !content.getPosterPath().isEmpty() ) {
                Glide.with(imageViewPoster)
                        .load(content.getPosterPath())
                        .centerCrop()
                        .thumbnail(Glide.with(context).load(R.drawable.gif_load))
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .into(imageViewPoster);
            } else {
                Glide.with(imageViewPoster)
                        .load(R.drawable.image_not_picture)
                        .centerCrop()
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .into(imageViewPoster);
            }

            textViewTitle.setText(content.getTitle());
            if ( singleContentType ) {
                textViewType.setVisibility(View.GONE);
            } else {
                textViewType.setVisibility(View.VISIBLE);
                textViewType.setText(content.getContentType() == References.TV_TYPE ?
                        "Serie" : "Película");
            }

            itemView.setOnClickListener(view ->
                    contentListener.onContentClick(content)
            );

            // Paginación no es posible si no se cumple este minimo de elementos
            // si es menor significa que es la ultima pagina
            int itemMinLimit = References.MOVIE_PAGINATE_STEP * ( singleContentType? 1 : 2 );
            if ( contentList.size() >= itemMinLimit
                    && position == contentList.size() - 5 ) {
                contentListener.onRequestNextPage();
            }
        }
    }

    public interface ContentListener {
        void onContentClick(ContentList_.Content selectedContent);
        void onSendMessage(boolean showMessage, String message);
        void onRequestNextPage();

    }
}


