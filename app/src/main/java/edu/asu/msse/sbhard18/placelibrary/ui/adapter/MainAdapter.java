package edu.asu.msse.sbhard18.placelibrary.ui.adapter;

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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.asu.msse.sbhard18.placelibrary.model.PlaceDescription;
import edu.asu.msse.sbhard18.placelibrary.R;
import edu.asu.msse.sbhard18.placelibrary.ui.listeners.RecyclerViewEventListener;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.PlaceViewHolder> {

    private final Context mContext;
    private List<PlaceDescription> mPlaceList;
    private RecyclerViewEventListener mRecyclerViewEventListener;

    public MainAdapter(Context context, List<PlaceDescription> placeDescriptionList, RecyclerViewEventListener recyclerViewEventListener) {
        this.mContext = context;
        this.mPlaceList = placeDescriptionList;
        this.mRecyclerViewEventListener = recyclerViewEventListener;
    }

    @NonNull
    @Override
    public PlaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.place_layout, parent, false);
        return new PlaceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PlaceViewHolder holder, int position) {
        PlaceDescription place = mPlaceList.get(position);
        holder.name.setText(place.getName());
        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRecyclerViewEventListener.onItemClickListener(mPlaceList.get(holder.getAdapterPosition()));
            }
        });
        holder.name.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mRecyclerViewEventListener.onItemLongClickListener(mPlaceList.get(holder.getAdapterPosition()));
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mPlaceList.size();
    }

    class PlaceViewHolder extends RecyclerView.ViewHolder {

        public TextView name;

        public PlaceViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.tv_place);
        }
    }

    public void updateList(List<PlaceDescription> list){
        this.mPlaceList = list;
        notifyDataSetChanged();
    }
}

