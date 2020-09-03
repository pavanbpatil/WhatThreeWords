package com.example.whatthreewordspoc;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.whatthreewordspoc.w3wmodels.autosuggest.AutoSuggestModel;
import com.example.whatthreewordspoc.w3wmodels.convertoboth.W3wConvertModel;
import com.example.whatthreewordspoc.w3wmodels.grid.GridModel;
import com.google.gson.Gson;
import com.what3words.androidwrapper.What3WordsV3;
import com.what3words.javawrapper.response.Autosuggest;
import com.what3words.javawrapper.response.ConvertTo3WA;
import com.what3words.javawrapper.response.GridSection;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvOutput, tvInput;
    Button bToAddress, bToCoordinates, bAutoSuggest, bGrid, bShowOnMap;
    What3WordsV3 api;
    ProgressDialog progDailog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progDailog = new ProgressDialog(MainActivity.this);
        progDailog.setMessage("Loading");
        progDailog.setCancelable(false);

        api = new What3WordsV3("YUNI3Z8E", getApplicationContext());
        tvOutput = findViewById(R.id.tvOutput);
        tvInput = findViewById(R.id.tvInput);
        bToAddress = findViewById(R.id.bToAddress);
        bToCoordinates = findViewById(R.id.bToCoordinates);
        bAutoSuggest = findViewById(R.id.bAutoSuggest);
        bGrid = findViewById(R.id.bGrid);
        bShowOnMap = findViewById(R.id.bShowOnMap);

        bToAddress.setOnClickListener(this);
        bToCoordinates.setOnClickListener(this);
        bAutoSuggest.setOnClickListener(this);
        bGrid.setOnClickListener(this);
        bShowOnMap.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bToAddress:
                tvInput.setText("Input Data:\n 16.807732, 74.111080");
                Location location = new Location("16.807732, 74.111080");
                location.setLatitude(16.807732);
                location.setLongitude(74.111080);
                //new ConvertToAddress().execute();
                new What3Words(MainActivity.this, new What3Words.ConverToAddressListener() {
                    @Override
                    public void convertToW3wAddress(ConvertTo3WA convertTo3WA) {
                        if (convertTo3WA.isSuccessful()) {
                            W3wConvertModel w3wConvertModel = new Gson().fromJson(convertTo3WA.toString(), W3wConvertModel.class);
                            tvOutput.setText(w3wConvertModel.getWords());
                        } else {
                            tvOutput.setText(convertTo3WA.getError().getMessage());
                        }
                    }
                }).new ConvertToAddress(location, "en").execute();

                break;
            case R.id.bToCoordinates:
                tvInput.setText("Input Data:\n reptile.blackberries.mouthful");
                new What3Words(MainActivity.this, new What3Words.ConverToCoordinateListener() {
                    @Override
                    public void convertToCoordinates(com.what3words.javawrapper.response.ConvertToCoordinates convertToCoordinates) {
                        if (convertToCoordinates.isSuccessful()) {
                            W3wConvertModel w3wConvertModel = new Gson().fromJson(convertToCoordinates.toString(), W3wConvertModel.class);
                            tvOutput.setText(w3wConvertModel.getCoordinates().getLat().toString());
                        } else {
                            tvOutput.setText(convertToCoordinates.getError().getMessage());
                        }
                    }
                }).new ConvertToCoordinates("reptile.blackberries.mouthful").execute();
                break;
            case R.id.bAutoSuggest:
                tvInput.setText("Input Data:\n reptile.blackberries.mouthful\n focus on = 16.8098909, 74.1001652");
                Location location1 = new Location("16.8098909, 74.1001652");
                location1.setLatitude(16.8098909);
                location1.setLongitude(74.1001652);
                //new AutoSuggest().execute();
                new What3Words(MainActivity.this, new What3Words.AutoSuggestListener() {
                    @Override
                    public void autoSuggest(Autosuggest autosuggest) {
                        if (autosuggest.isSuccessful()) {
                            AutoSuggestModel autoSuggestModel = new Gson().fromJson(autosuggest.toString(), AutoSuggestModel.class);
                            tvOutput.setText(autosuggest.getSuggestions().get(0).getNearestPlace());
                        } else {
                            tvOutput.setText(autosuggest.getError().getMessage());
                        }
                    }
                }).new AutoSuggest("reptile.blackberries.mouthful", location1, "IN").execute();
                break;
            case R.id.bGrid:
                tvInput.setText("Input Data:\nsouth west 16.8043998,74.0935558\nnorth east 16.8098909,74.1001652");
                //new Grid().execute();
                Location location2 = new Location("16.8043998,74.0935558");
                location2.setLatitude(16.8043998);
                location2.setLongitude(74.0935558);
                Location location3 = new Location("16.8098909,74.1001652");
                location3.setLatitude(16.8098909);
                location3.setLongitude(74.1001652);
                new What3Words(MainActivity.this, new What3Words.GetGridLIstener() {
                    @Override
                    public void getGrid(GridSection gridSection) {
                        if (gridSection.isSuccessful()) {
                            GridModel gridModel = new Gson().fromJson(gridSection.toString(), GridModel.class);
                            tvOutput.setText(gridModel.getLines().get(0).getStart().getLat().toString());
                        } else {
                            tvOutput.setText(gridSection.getError().getMessage());
                        }

                    }
                }).new Grid(location2, location3).execute();
                break;
            case R.id.bShowOnMap:
                tvInput.setText("Input Data:\n reptile.blackberries.mouthful");
                Intent intent = getPackageManager().getLaunchIntentForPackage("com.what3words.android");
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("w3w://show?threewords=reptile.blackberries.mouthful"));
                startActivity(intent);
                break;
        }
    }
}