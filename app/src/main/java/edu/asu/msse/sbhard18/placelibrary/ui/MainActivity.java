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

import edu.asu.msse.sbhard18.placelibrary.ui.adapter.MainAdapter;
import edu.asu.msse.sbhard18.placelibrary.model.PlaceDescription;
import edu.asu.msse.sbhard18.placelibrary.database.PlaceManager;
import edu.asu.msse.sbhard18.placelibrary.database.PlaceManagerImpl;
import edu.asu.msse.sbhard18.placelibrary.R;

import static edu.asu.msse.sbhard18.placelibrary.utility.Constants.ADD_ACTIVITY_REQ_CODE;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private MainAdapter mMainAdapter;
    private PlaceManager mPlaceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findAllIds();
        mPlaceManager = new PlaceManagerImpl(this);
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
        if (item.getItemId() == R.id.action_add) {
            Intent intent = new Intent(MainActivity.this, AddActivity.class);
            startActivityForResult(intent, ADD_ACTIVITY_REQ_CODE);
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ADD_ACTIVITY_REQ_CODE){
            if(resultCode == RESULT_OK){
                mPlaceManager.add((PlaceDescription)data.getSerializableExtra("data"));
                updateRecyclerView();
            }
        }
    }

    private void updateRecyclerView() {
        mMainAdapter.updateList(mPlaceManager.getPlaces());
    }

    private void setRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mMainAdapter = new MainAdapter(this, mPlaceManager.getPlaces());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, linearLayoutManager.getOrientation());
        mRecyclerView.addItemDecoration(dividerItemDecoration);
        mRecyclerView.setAdapter(mMainAdapter);
    }

    private void findAllIds() {
        mRecyclerView = findViewById(R.id.recycler_View);
    }
}
