package com.madd.madd.tmdb.Models.Lists.Actor;

import com.madd.madd.tmdb.Utilities.References;

import org.json.JSONException;
import org.json.JSONObject;

public class ActorCard {

    private String name;
    private String character;
    private String profilePath;



    public ActorCard(JSONObject movieJSON) {
        try {
            this.name = movieJSON.getString("name");
            this.character = movieJSON.getString("character");
            this.profilePath = "";
            if( !movieJSON.getString("profile_path").equals(References.NULL_ITEM) ) {
                this.profilePath = "https://image.tmdb.org/t/p/w500/" +
                        movieJSON.getString("profile_path");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getProfilePath() {
        return profilePath;
    }

    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }
}
