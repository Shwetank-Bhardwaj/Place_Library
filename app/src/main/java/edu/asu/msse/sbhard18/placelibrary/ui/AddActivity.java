package edu.asu.msse.sbhard18.placelibrary.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;

import edu.asu.msse.sbhard18.placelibrary.model.PlaceDescription;
import edu.asu.msse.sbhard18.placelibrary.R;

public class AddActivity extends AppCompatActivity {
    private EditText mNameTV;
    private EditText mDescriptionTV;
    private EditText mCategoryTV;
    private EditText mAddressTitleTV;
    private EditText mAddressDetailTV;
    private EditText mElevationTV;
    private EditText mLatTV;
    private EditText mLongTV;
    private Button mSaveBtn;
    private EditText mImageTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        findAllIds();
        Intent intent = getIntent();
        Serializable data = intent.getSerializableExtra("data");
        if (data != null) {
            PlaceDescription placeDescription = (PlaceDescription) data;
            setDetails(placeDescription);
        }
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
        });
    }

    private void setDetails(PlaceDescription placeDescription) {
        mNameTV.setText(placeDescription.getName());
        mDescriptionTV.setText(placeDescription.getDescription());
        mCategoryTV.setText(placeDescription.getCategory());
        mAddressTitleTV.setText(placeDescription.getAddressTitle());
        mAddressDetailTV.setText(placeDescription.getAddressDetail());
        mDescriptionTV.setText(placeDescription.getDescription());
        mDescriptionTV.setText(placeDescription.getDescription());
        mDescriptionTV.setText(placeDescription.getDescription());
        mDescriptionTV.setText(placeDescription.getDescription());
        mDescriptionTV.setText(placeDescription.getDescription());
        mDescriptionTV.setText(placeDescription.getDescription());
        mDescriptionTV.setText(placeDescription.getDescription());
    }

    private boolean validate(String name, String description, String category, String addressTitle, String addressDetail, String elevationString, String latString, String longString, String image) {

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
}
