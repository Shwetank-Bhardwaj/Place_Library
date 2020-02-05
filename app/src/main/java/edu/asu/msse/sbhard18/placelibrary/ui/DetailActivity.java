package edu.asu.msse.sbhard18.placelibrary.ui;

import androidx.appcompat.app.AppCompatActivity;

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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import edu.asu.msse.sbhard18.placelibrary.model.PlaceDescription;
import edu.asu.msse.sbhard18.placelibrary.R;

public class DetailActivity extends AppCompatActivity {

    private TextView mNameTV;
    private TextView mDescriptionTV;
    private TextView mCategoryTV;
    private TextView mAddressTitleTV;
    private TextView mAddressDetailTV;
    private TextView mElevationTV;
    private TextView mLatTV;
    private TextView mLongTV;
    private PlaceDescription mPlaceDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        findAllIds();
        Intent intent = getIntent();
        mPlaceDescription = (PlaceDescription) intent.getSerializableExtra("data");
        setDetails(mPlaceDescription);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.deletemenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_delete) {
            Intent data = new Intent();
            data.putExtra("data", mPlaceDescription);
            setResult(RESULT_OK, data);
            finish();
        }
        return true;
    }

    private void setDetails(PlaceDescription placeDescription) {
        mNameTV.setText(String.format("%s: %s", getString(R.string.name), placeDescription.getName()));
        mDescriptionTV.setText(String.format("%s: %s", getString(R.string.description), placeDescription.getDescription()));
        mCategoryTV.setText(String.format("%s: %s", getString(R.string.category), placeDescription.getCategory()));
        mAddressTitleTV.setText(String.format("%s: %s", getString(R.string.address), placeDescription.getAddressTitle()));
        mAddressDetailTV.setText(String.format("%s: \n%s", getString(R.string.address_detail), placeDescription.getAddressDetail()));
        mElevationTV.setText(String.format("%s: %s", getString(R.string.elevation), placeDescription.getElevation()));
        mLatTV.setText(String.format("%s: %s", getString(R.string.lat), placeDescription.getLatitude()));
        mLongTV.setText(String.format("%s: %s", getString(R.string.longitude), placeDescription.getLongitude()));
    }

    private void findAllIds() {
        mNameTV = findViewById(R.id.tv_name);
        mDescriptionTV = findViewById(R.id.tv_description);
        mCategoryTV = findViewById(R.id.tv_category);
        mAddressTitleTV = findViewById(R.id.tv_address);
        mAddressDetailTV = findViewById(R.id.tv_address_detail);
        mElevationTV = findViewById(R.id.tv_elevation);
        mLatTV = findViewById(R.id.tv_lat);
        mLongTV = findViewById(R.id.tv_long);
    }
}
