
package com.example.whatthreewordspoc.w3wmodels.autosuggest;

import com.google.gson.annotations.Expose;

@SuppressWarnings("unused")
public class Suggestion {

    @Expose
    private String country;
    @Expose
    private Long distanceToFocusKm;
    @Expose
    private String language;
    @Expose
    private String nearestPlace;
    @Expose
    private Long rank;
    @Expose
    private String words;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Long getDistanceToFocusKm() {
        return distanceToFocusKm;
    }

    public void setDistanceToFocusKm(Long distanceToFocusKm) {
        this.distanceToFocusKm = distanceToFocusKm;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getNearestPlace() {
        return nearestPlace;
    }

    public void setNearestPlace(String nearestPlace) {
        this.nearestPlace = nearestPlace;
    }

    public Long getRank() {
        return rank;
    }

    public void setRank(Long rank) {
        this.rank = rank;
    }

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }

}
