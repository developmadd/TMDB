package com.madd.madd.tmdb.UI.TVShowDetail;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.madd.madd.tmdb.Data.Cast.Model.Cast;
import com.madd.madd.tmdb.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActorAdapter extends RecyclerView.Adapter<ActorAdapter.ViewHolder> {


    private List<Cast.Actor> actorCardList = new ArrayList<>();

    public List<Cast.Actor> getList() {
        return actorCardList;
    }

    ActorAdapter() {

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.section_actor,viewGroup,false);
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

        @BindView(R.id.IV_Section_Actor) ImageView imageView;
        @BindView(R.id.TV_Section_Actor_Name) TextView textViewName;
        @BindView(R.id.TV_Section_Actor_Character) TextView textViewCharacter;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        void bind( Cast.Actor actorCard ){
            if (!actorCard.getProfilePath().isEmpty() ) {
                Glide.with(imageView)
                        .load(actorCard.getProfilePath())
                        .centerCrop()
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


