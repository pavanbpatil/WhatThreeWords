
package com.example.whatthreewordspoc.w3wmodels.convertoboth;

import com.google.gson.annotations.Expose;

@SuppressWarnings("unused")
public class W3wConvertModel {

    @Expose
    private Coordinates coordinates;
    @Expose
    private String country;
    @Expose
    private String language;
    @Expose
    private String map;
    @Expose
    private String nearestPlace;
    @Expose
    private Square square;
    @Expose
    private String words;

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public String getNearestPlace() {
        return nearestPlace;
    }

    public void setNearestPlace(String nearestPlace) {
        this.nearestPlace = nearestPlace;
    }

    public Square getSquare() {
        return square;
    }

    public void setSquare(Square square) {
        this.square = square;
    }

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }

}
