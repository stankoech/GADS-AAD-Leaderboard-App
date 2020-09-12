package com.stankoech.gadsaadleaderboard.model;

public class GadsModel {

    private String ln_hours;
    private String name;
    private String country ;
    private String image_url;
    private String score;

public GadsModel(){

}

    public GadsModel(String ln_hours, String name, String country, String image_url, String score ){
        this.ln_hours = ln_hours;
        this.name = name;
        this.country = country;
        this.image_url = image_url;
        this.score = score;


    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getLn_hours() {
        return ln_hours;
    }

    public void setLn_hours(String ln_hours) {
        this.ln_hours = ln_hours;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }



}
