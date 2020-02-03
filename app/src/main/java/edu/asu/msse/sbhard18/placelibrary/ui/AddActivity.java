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
