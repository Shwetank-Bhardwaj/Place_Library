package edu.asu.msse.sbhard18.placelibrary.model;

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

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

import edu.asu.msse.sbhard18.placelibrary.utility.Constants;

public class PlaceDescription implements Serializable {

    private String mName;
    private String mDescription;
    private String mCategory;
    private String mAddressTitle;
    private String mAddressDetail;
    private String image;
    private int mElevation;
    private double mLatitude;
    private double mLongitude;

    public PlaceDescription() {

    }

    public PlaceDescription(String dataJson) {
        extractData(dataJson);
    }

    /**
     * @param dataJson Takes String in json format and parse that into member variables
     */
    private void extractData(String dataJson) {
        try {
            JSONObject jsonObject = new JSONObject(dataJson);
            mName = (String) jsonObject.get(Constants.NAME);
            mDescription = (String) jsonObject.get(Constants.DESCRIPTION);
            mCategory = (String) jsonObject.get(Constants.CATEGORY);
            mAddressTitle = (String) jsonObject.get(Constants.ADDRESS_TITLE);
            mAddressDetail = (String) jsonObject.get(Constants.ADDRESS_STREET);
            mElevation = (int) jsonObject.get(Constants.ELEVATION);
            mLatitude = (double) jsonObject.get(Constants.LATITUDE);
            mLongitude = (double) jsonObject.get(Constants.LONGITUDE);
            image = (String) jsonObject.get(Constants.IMAGE);
        } catch (JSONException e) {
            Log.e(Constants.JSON_ERROR, "Error while parsing Json");
            e.printStackTrace();
        }

    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getCategory() {
        return mCategory;
    }

    public void setCategory(String mCategory) {
        this.mCategory = mCategory;
    }

    public String getAddressTitle() {
        return mAddressTitle;
    }

    public void setAddressTitle(String mAddressTitle) {
        this.mAddressTitle = mAddressTitle;
    }

    public String getAddressDetail() {
        return mAddressDetail;
    }

    public void setAddressDetail(String mAddressDetail) {
        this.mAddressDetail = mAddressDetail;
    }

    public int getElevation() {
        return mElevation;
    }

    public void setElevation(int mElevation) {
        this.mElevation = mElevation;
    }

    public double getLatitude() {
        return mLatitude;
    }

    public void setLatitude(double mLatitude) {
        this.mLatitude = mLatitude;
    }

    public double getLongitude() {
        return mLongitude;
    }

    public void setLongitude(double mLongitude) {
        this.mLongitude = mLongitude;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    /**
     * @return String (This will be null in case of an error while building Json to string)
     */
    public String toJsonString() {
        String jsonData = null;
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put(Constants.NAME, mName);
            jsonObject.put(Constants.DESCRIPTION, mDescription);
            jsonObject.put(Constants.CATEGORY, mCategory);
            jsonObject.put(Constants.ADDRESS_TITLE, mAddressTitle);
            jsonObject.put(Constants.ADDRESS_STREET, mAddressDetail);
            jsonObject.put(Constants.ELEVATION, mElevation);
            jsonObject.put(Constants.LATITUDE, mLatitude);
            jsonObject.put(Constants.LONGITUDE, mLongitude);
            jsonObject.put(Constants.IMAGE, image);
            jsonData = jsonObject.toString();
        } catch (JSONException e) {
            Log.e(Constants.JSON_ERROR, "Error while making Json String");
            e.printStackTrace();
        }
        return jsonData;
    }
}
