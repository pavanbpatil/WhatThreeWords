package com.example.whatthreewordspoc;

import com.what3words.androidwrapper.What3WordsV3;
import com.what3words.javawrapper.request.BoundingBox;
import com.what3words.javawrapper.request.Coordinates;
import com.what3words.javawrapper.response.APIResponse;
import com.what3words.javawrapper.response.Autosuggest;
import com.what3words.javawrapper.response.ConvertTo3WA;
import com.what3words.javawrapper.response.GridSection;

import android.content.Context;
import android.location.Location;
import android.os.AsyncTask;


public class What3Words {
    private What3WordsV3 api;
    private ConverToAddressListener converToAddressListener;
    private ConverToCoordinateListener converToCoordinateListener;
    private AutoSuggestListener autoSuggestListener;
    private GetGridLIstener getGridLIstener;

    public What3Words(Context context, ConverToAddressListener converToAddressListener) {
        api = new What3WordsV3("YUNI3Z8E", context);
        this.converToAddressListener = converToAddressListener;
    }

    public What3Words(Context context, ConverToCoordinateListener converToCoordinateListener) {
        api = new What3WordsV3("YUNI3Z8E", context);
        this.converToCoordinateListener = converToCoordinateListener;
    }

    public What3Words(Context context, AutoSuggestListener autoSuggestListener) {
        api = new What3WordsV3("YUNI3Z8E", context);
        this.autoSuggestListener = autoSuggestListener;
    }

    public What3Words(Context context, GetGridLIstener getGridLIstener) {
        api = new What3WordsV3("YUNI3Z8E", context);
        this.getGridLIstener = getGridLIstener;
    }


    final class ConvertToAddress extends AsyncTask<Void, Void, ConvertTo3WA> {

        private Location location;
        private String language;

        public ConvertToAddress(Location location, String language) {
            this.location = location;
            this.language = language;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected ConvertTo3WA doInBackground(Void... params) {
            ConvertTo3WA words = api.convertTo3wa(new Coordinates(location.getLatitude(), location.getLongitude()))
                    .language(language)
                    .execute();
            return words;
        }

        @Override
        protected void onPostExecute(ConvertTo3WA result) {
            converToAddressListener.convertToW3wAddress(result);
        }
    }

    final class ConvertToCoordinates extends AsyncTask<Void, Void, com.what3words.javawrapper.response.ConvertToCoordinates> {
        private String w3wAddress;

        public ConvertToCoordinates(String w3wAddress) {
            this.w3wAddress = w3wAddress;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected com.what3words.javawrapper.response.ConvertToCoordinates doInBackground(Void... params) {
            com.what3words.javawrapper.response.ConvertToCoordinates coordinates = api.convertToCoordinates(w3wAddress)
                    .execute();
            return coordinates;
        }

        @Override
        protected void onPostExecute(com.what3words.javawrapper.response.ConvertToCoordinates result) {
            converToCoordinateListener.convertToCoordinates(result);
        }
    }

    final class AutoSuggest extends AsyncTask<Void, Void, Autosuggest> {
        private String w3wAddress;
        private String clipToCountry;
        private Location focusLocation;

        public AutoSuggest(String w3wAddress, Location focusLocation, String clipToCountry) {
            this.w3wAddress = w3wAddress;
            this.focusLocation = focusLocation;
            this.clipToCountry = clipToCountry;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Autosuggest doInBackground(Void... params) {
            Autosuggest autosuggest = api.autosuggest(w3wAddress)
                    .clipToCountry(clipToCountry)
                    .focus(new Coordinates(focusLocation.getLatitude(), focusLocation.getLongitude()))
                    .execute();
            return autosuggest;

        }

        @Override
        protected void onPostExecute(Autosuggest result) {
            autoSuggestListener.autoSuggest(result);
        }
    }

    final class Grid extends AsyncTask<Void, Void, GridSection> {
        private Location southWestLocation;
        private Location northEastLocation;

        public Grid(Location southWestLocation, Location northEastLocation) {
            this.southWestLocation = southWestLocation;
            this.northEastLocation = northEastLocation;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected GridSection doInBackground(Void... params) {
            // Obtain a grid section within the provided bounding box
            GridSection gridSection = api.gridSection(
                    new BoundingBox(
                            new Coordinates(southWestLocation.getLatitude(), southWestLocation.getLongitude()),
                            new Coordinates(northEastLocation.getLatitude(), northEastLocation.getLongitude())
                    )
            ).execute();
            return gridSection;
        }

        @Override
        protected void onPostExecute(GridSection result) {
            getGridLIstener.getGrid(result);
        }
    }


    public interface ConverToAddressListener {
        void convertToW3wAddress(ConvertTo3WA convertTo3WA);
    }

    public interface ConverToCoordinateListener {
        void convertToCoordinates(com.what3words.javawrapper.response.ConvertToCoordinates convertToCoordinates);
    }

    public interface AutoSuggestListener {
        void autoSuggest(Autosuggest autosuggest);
    }

    public interface GetGridLIstener {
        void getGrid(GridSection gridSection);
    }

}
