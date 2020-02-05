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

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import edu.asu.msse.sbhard18.placelibrary.model.PlaceDescription;

public class AddActivity extends ModifyActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setClickListeners();
    }

    private void setClickListeners() {
        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = String.valueOf(mNameTV.getText());
                String description = String.valueOf(mDescriptionTV.getText());
                String category = String.valueOf(mCategoryTV.getText());
                String addressTitle = String.valueOf(mAddressTitleTV.getText());
                String addressDetail = String.valueOf(mAddressDetailTV.getText());
                String elevationString = String.valueOf(mElevationTV.getText());
                String latString = String.valueOf(mLatTV.getText());
                String longString = String.valueOf(mLongTV.getText());
                String image = String.valueOf(mImageTV.getText());

                boolean isSuccess = validate(name, description, category, addressTitle, addressDetail, elevationString, latString, longString, image);

                if (isSuccess) {
                    finishActivityWithResult(name, description, category, addressTitle, addressDetail, elevationString, latString, longString, image);
                }
            }
        });
    }

    private void finishActivityWithResult(String name, String description, String category, String addressTitle, String addressDetail, String elevationString, String latString, String longString, String image) {
        int elevation = Integer.parseInt(elevationString);
        double latitude = Double.parseDouble(latString);
        double longitude = Double.parseDouble(longString);

        PlaceDescription placeDescription = new PlaceDescription();
        placeDescription.setName(name);
        placeDescription.setDescription(description);
        placeDescription.setCategory(category);
        placeDescription.setAddressTitle(addressTitle);
        placeDescription.setAddressDetail(addressDetail);
        placeDescription.setImage(image);
        placeDescription.setElevation(elevation);
        placeDescription.setLatitude(latitude);
        placeDescription.setLongitude(longitude);

        Intent data = new Intent();
        data.putExtra("data", placeDescription);
        setResult(RESULT_OK, data);
        finish();
    }

}
