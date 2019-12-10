package com.madd.madd.tmdb.Models.Lists.Actor;

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
import com.madd.madd.tmdb.Models.Cast;
import com.madd.madd.tmdb.R;

import java.util.List;

public class ActorAdapter extends RecyclerView.Adapter<ActorAdapter.ViewHolder> {

    private Context context;
    private List<Cast.Actor> actorCardList;

    ActorAdapter(List<Cast.Actor> actorCardList) {
        this.actorCardList = actorCardList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        context = viewGroup.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.section_actor,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.bind(actorCardList.get(position));
    }

    @Override
    public int getItemCount() {
        return actorCardList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        private TextView textViewName;
        private TextView textViewCharacter;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.IV_Section_Actor);
            textViewName = itemView.findViewById(R.id.TV_Section_Actor_Name);
            textViewCharacter = itemView.findViewById(R.id.TV_Section_Actor_Character);
        }

        void bind( Cast.Actor actorCard ){
            if (!actorCard.getProfilePath().isEmpty() ) {
                Glide.with(imageView)
                        .load(actorCard.getProfilePath())
                        .centerCrop()
                        .thumbnail(Glide.with(context).load(R.drawable.gif_load))
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .into(imageView);
            } else {
                Glide.with(imageView)
                        .load(R.drawable.image_not_picture)
                        .centerCrop()
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .into(imageView);
            }
            textViewName.setText(actorCard.getName());
            textViewCharacter.setText("\"" + actorCard.getCharacter() + "\"");
        }
    }
}


