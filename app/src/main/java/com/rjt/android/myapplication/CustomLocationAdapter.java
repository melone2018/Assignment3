package com.rjt.android.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomLocationAdapter extends BaseAdapter {
    private ArrayList<MyLocation> mLocations;
    private LayoutInflater mLayoutInflater;

    public CustomLocationAdapter(Context context, ArrayList<MyLocation> mLocations){
        this.mLocations = mLocations;
        mLayoutInflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return mLocations.size();
    }

    @Override
    public Object getItem(int position) {
        return mLocations.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.item_location_layout, null);
            holder = new ViewHolder();
            holder.headlineView =  convertView.findViewById(R.id.location_name);
            holder.reporterInterestView =  convertView.findViewById(R.id.types);
            holder.reportedDateView = convertView.findViewById(R.id.opened);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.headlineView.setText(mLocations.get(position).getFormatted_address());
        holder.reporterInterestView.setText("By, " + mLocations.get(position).getTypes());

        holder.reportedDateView.setText(mLocations.get(position).isOpened() == false ? "Open" : "Closed");
        return convertView;
    }

    static class ViewHolder {
        TextView headlineView;
        TextView reporterInterestView;
        TextView reportedDateView;
    }
}
