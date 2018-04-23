package com.rjt.android.myapplication;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class LocationsFragment extends android.support.v4.app.Fragment {
    private ListView mListView;
    private CustomLocationAdapter mLocationsAdapter;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View mView = inflater.inflate(R.layout.fragment_location_list, container, false);
        mListView = mView.findViewById(R.id.location_view);
        Bundle bSource = getArguments();
        ArrayList<MyLocation> data = (ArrayList<MyLocation>) bSource.getSerializable("DATA");
        Log.d("DATASIZE", data.size()+"");
        mLocationsAdapter = new CustomLocationAdapter(getActivity(), data);
        mListView.setAdapter(mLocationsAdapter);
        return mView;
    }
}
