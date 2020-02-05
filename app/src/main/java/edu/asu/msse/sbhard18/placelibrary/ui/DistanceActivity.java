package edu.asu.msse.sbhard18.placelibrary.ui;

/*
 * Copyright 2020  Shwetank Bhardwaj,
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * @author Shwetank Bhardwaj, sbhard18@asu.edu
 * Software Engineering, CIDSE, ASU Poly
 *
 * @version January 2020
 */

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import edu.asu.msse.sbhard18.placelibrary.R;
import edu.asu.msse.sbhard18.placelibrary.database.PlaceManager;
import edu.asu.msse.sbhard18.placelibrary.database.PlaceManagerImpl;
import edu.asu.msse.sbhard18.placelibrary.model.PlaceDescription;

public class DistanceActivity extends AppCompatActivity {

    private Spinner mPlace1Spinner;
    private Spinner mPlace2Spinner;
    private TextView mDistanceTv;
    private TextView mInitialBearingTv;
    private PlaceManager mPlaceManager;
    private List<PlaceDescription> mPlacesList;
    private List<String> mPlacesNameList;
    private PlaceDescription placeDescription1;
    private PlaceDescription placeDescription2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distance);
        findAllIds();
        mPlaceManager = PlaceManagerImpl.getInstance(getApplicationContext());
        mPlacesList = mPlaceManager.getPlaces();
        mPlacesNameList = new ArrayList<>(mPlacesList.size());
        for (PlaceDescription placeDescription : mPlacesList) {
            mPlacesNameList.add(placeDescription.getName());
        }
        setSpinners();
    }

    private void setSpinners() {

        ArrayAdapter<String> placeDescriptionArrayAdapter = new ArrayAdapter<String>(
                getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item,
                mPlacesNameList);

        mPlace1Spinner.setAdapter(placeDescriptionArrayAdapter);
        mPlace1Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                placeDescription1 = mPlacesList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mPlace2Spinner.setAdapter(placeDescriptionArrayAdapter);
        mPlace2Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                placeDescription2 = mPlacesList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setData(double distance, double initialBearing) {
        mDistanceTv.setText(String.format("Distance between Places is %s", distance));
        mInitialBearingTv.setText(String.format("Initial Bearing between Places is %s", initialBearing));
    }

    private void findAllIds() {
        mPlace1Spinner = findViewById(R.id.spinner2);
        mPlace2Spinner = findViewById(R.id.spinner3);
        mDistanceTv = findViewById(R.id.tv_distance);
        mInitialBearingTv = findViewById(R.id.tv_initial_bearing);
    }

    public void calculateDistanceAndInitialBearing(View view) {
        double distance = mPlaceManager.calculateDistance(placeDescription1, placeDescription2);
        double initialBearing = mPlaceManager.calculateInitialBearing(placeDescription1, placeDescription2);
        setData(distance, initialBearing);
    }
}
