package edu.asu.msse.sbhard18.placelibrary.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        findAllIds();
        Intent intent = getIntent();
        PlaceDescription placeDescription = (PlaceDescription) intent.getSerializableExtra("data");
        setDetails(placeDescription);
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
