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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.asu.msse.sbhard18.placelibrary.R;

public class ModifyActivity extends AppCompatActivity {

    protected EditText mNameTV;
    protected EditText mDescriptionTV;
    protected EditText mCategoryTV;
    protected EditText mAddressTitleTV;
    protected EditText mAddressDetailTV;
    protected EditText mElevationTV;
    protected EditText mLatTV;
    protected EditText mLongTV;
    protected EditText mImageTV;
    protected Button mSaveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        findAllIds();
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
        mImageTV = findViewById(R.id.tv_image);
        mSaveBtn = findViewById(R.id.btn_save);
    }

    protected boolean validate(String name, String description, String category, String addressTitle, String addressDetail, String elevationString, String latString, String longString, String image) {

        if (name.length() == 0) {
            showToast("Please Enter a Name");
            return false;
        }
        if (description.length() == 0) {
            showToast("Please Enter a description");
            return false;
        }
        if (category.length() == 0) {
            showToast("Please Enter a Category");
            return false;
        }
        if (addressTitle.length() == 0) {
            showToast("Please Enter a Address Title");
            return false;
        }
        if (addressDetail.length() == 0) {
            showToast("Please Enter a Address Detail");
            return false;
        }
        if (image.length() == 0) {
            showToast("Please Enter a Image");
            return false;
        }
        if (elevationString.length() == 0) {
            showToast("Please Enter elevation");
            return false;
        }
        if (latString.length() == 0) {
            showToast("Please Enter a Latitude");
            return false;
        }
        if (longString.length() == 0) {
            showToast("Please Enter a Longitude");
            return false;
        }
        return true;
    }

    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }
}
