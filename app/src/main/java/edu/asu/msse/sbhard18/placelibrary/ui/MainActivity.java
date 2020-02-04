package edu.asu.msse.sbhard18.placelibrary.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import edu.asu.msse.sbhard18.placelibrary.ui.adapter.MainAdapter;
import edu.asu.msse.sbhard18.placelibrary.model.PlaceDescription;
import edu.asu.msse.sbhard18.placelibrary.database.PlaceManager;
import edu.asu.msse.sbhard18.placelibrary.database.PlaceManagerImpl;
import edu.asu.msse.sbhard18.placelibrary.R;
import edu.asu.msse.sbhard18.placelibrary.ui.listeners.RecyclerViewEventListener;

import static edu.asu.msse.sbhard18.placelibrary.utility.Constants.ADD_ACTIVITY_REQ_CODE;
import static edu.asu.msse.sbhard18.placelibrary.utility.Constants.DETAIL_ACTIVITY_REQ_CODE;
import static edu.asu.msse.sbhard18.placelibrary.utility.Constants.EDIT_ACTIVITY_REQ_CODE;

public class MainActivity extends AppCompatActivity implements RecyclerViewEventListener {

    private RecyclerView mRecyclerView;
    private MainAdapter mMainAdapter;
    private PlaceManager mPlaceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findAllIds();
        mPlaceManager = PlaceManagerImpl.getInstance(this);
        setRecyclerView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_add:
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivityForResult(intent, ADD_ACTIVITY_REQ_CODE);
                break;
            case R.id.action_navigation:
                Intent distanceIntent = new Intent(MainActivity.this, DistanceActivity.class);
                startActivity(distanceIntent);
                break;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case ADD_ACTIVITY_REQ_CODE:
                if (resultCode == RESULT_OK) {
                    boolean isSuccess = mPlaceManager.add((PlaceDescription) data.getSerializableExtra("data"));
                    if(isSuccess){
                        updateRecyclerView();
                    }else{
                        Toast.makeText(getApplicationContext(), "Place Name already present", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case EDIT_ACTIVITY_REQ_CODE:
                if (resultCode == RESULT_OK) {
                    mPlaceManager.modify((PlaceDescription) data.getSerializableExtra("data"));
                    updateRecyclerView();
                }
                break;
            case DETAIL_ACTIVITY_REQ_CODE:
                if(resultCode == RESULT_OK){
                    mPlaceManager.remove((PlaceDescription) data.getSerializableExtra("data"));
                    updateRecyclerView();
                }
                break;
        }
    }

    protected void updateRecyclerView() {
        mMainAdapter.updateList(mPlaceManager.getPlaces());
    }

    private void setRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mMainAdapter = new MainAdapter(this, mPlaceManager.getPlaces(), this);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, linearLayoutManager.getOrientation());
        mRecyclerView.addItemDecoration(dividerItemDecoration);
        mRecyclerView.setAdapter(mMainAdapter);
    }

    private void findAllIds() {
        mRecyclerView = findViewById(R.id.recycler_View);
    }

    @Override
    public void onItemLongClickListener(PlaceDescription placeDescription) {
        Intent intent = new Intent(MainActivity.this, EditActivity.class);
        intent.putExtra("data", placeDescription);
        startActivityForResult(intent, EDIT_ACTIVITY_REQ_CODE);
    }

    @Override
    public void onItemClickListener(PlaceDescription placeDescription) {
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra("data", placeDescription);
        startActivityForResult(intent, DETAIL_ACTIVITY_REQ_CODE);
    }
}
