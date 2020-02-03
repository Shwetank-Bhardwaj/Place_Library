package edu.asu.msse.sbhard18.placelibrary.database;
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

import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import edu.asu.msse.sbhard18.placelibrary.model.PlaceDescription;
import edu.asu.msse.sbhard18.placelibrary.R;

public class PlaceManagerImpl implements PlaceManager {
    private static final String TAG = PlaceManagerImpl.class.getSimpleName();
    private Context context;
    private List<PlaceDescription> mPlaceDescriptionList = new ArrayList<>();
    private List<String> mPlaces = new ArrayList<>();

    public PlaceManagerImpl(Context context) {
        this.context = context;
        initialize();
    }

    @Override
    public boolean add(PlaceDescription place) {
        for (int i = 0; i < mPlaceDescriptionList.size(); i++) {
            if(String.valueOf(mPlaceDescriptionList.get(i)).equals(place.getName())){
                return false;
            }
        }
        mPlaceDescriptionList.add(place);
        return true;
    }

    @Override
    public void remove(PlaceDescription place) {
        PlaceDescription placeDescription = null;
        for (int i = 0; i < mPlaceDescriptionList.size(); i++) {
            PlaceDescription temp = mPlaceDescriptionList.get(i);
            if (temp.getName().equals(place.getName())) {
                placeDescription = temp;
                break;
            }
        }
        if (placeDescription != null) {
            mPlaceDescriptionList.remove(placeDescription);
        }
    }

    @Override
    public void modify(PlaceDescription place) {
        PlaceDescription placeDescription = null;
        int position = 0;
        for (int i = 0; i < mPlaceDescriptionList.size(); i++) {
            PlaceDescription temp = mPlaceDescriptionList.get(i);
            if (temp.getName().equals(place.getName())) {
                placeDescription = temp;
                position = i;
                break;
            }
        }
        if (placeDescription != null) {
            mPlaceDescriptionList.remove(placeDescription);
        }
        mPlaceDescriptionList.add(position, place);
    }

    @Override
    public void calculateDistance(PlaceDescription place1, PlaceDescription place2) {

    }

    @Override
    public List<PlaceDescription> getPlaces() {
        return mPlaceDescriptionList;
    }

    private void initialize() {
        initializePlaces();
        try {
            InputStream inputStream = context.getResources().openRawResource(R.raw.places);
            BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder jsonString = new StringBuilder();
            for (String line; (line = r.readLine()) != null; ) {
                jsonString.append(line).append('\n');
            }
            JSONObject jsonObject = new JSONObject(jsonString.toString());

            for (String place : mPlaces) {
                PlaceDescription placeDescription = new PlaceDescription(String.valueOf(jsonObject.get(place)));
                mPlaceDescriptionList.add(placeDescription);
            }
        } catch (IOException | JSONException e) {
            Log.e(TAG, "Exception in reading Json " + e.getMessage());
        }
    }

    private void initializePlaces() {
        mPlaces.add("ASU-West");
        mPlaces.add("UAK-Anchorage");
        mPlaces.add("Toreros");
        mPlaces.add("Barrow-Alaska");
        mPlaces.add("Calgary-Alberta");
        mPlaces.add("London-England");
        mPlaces.add("Moscow-Russia");
        mPlaces.add("New-York-NY");
        mPlaces.add("Notre-Dame-Paris");
        mPlaces.add("Circlestone");
        mPlaces.add("Reavis-Ranch");
        mPlaces.add("Rogers-Trailhead");
        mPlaces.add("Reavis-Grave");
        mPlaces.add("Muir-Woods");
        mPlaces.add("Windcave-Peak");
    }
}
