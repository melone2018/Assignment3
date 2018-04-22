package com.rjt.android.myapplication;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class LocationsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    ArrayList<MyLocation> locationList;
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class LocationHolder extends RecyclerView.ViewHolder{

        public LocationHolder(View itemView) {
            super(itemView);
        }
    }
}

