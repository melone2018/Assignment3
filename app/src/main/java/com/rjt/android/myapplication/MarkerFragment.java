package com.rjt.android.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MarkerFragment extends Fragment {
    TextView addressTv;
    TextView coordinateTv;
    TextView nearbyTv;

    public MarkerFragment(){}
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        if (container != null) {
            container.removeAllViews();
        }

        View mView = inflater.inflate(R.layout.fragment_marker, container, false);
        addressTv = mView.findViewById(R.id.addressContent);
        coordinateTv = mView.findViewById(R.id.coordinateContent);
        nearbyTv = mView.findViewById(R.id.nearbyContent);
        Bundle b = getArguments();
        MyLocation myLocation = (MyLocation) b.getSerializable("MARKERDATA");
        String address = myLocation.getFormatted_address();
        String coordinate ="(" + myLocation.getGmLoc().getLat() + ", " + myLocation.getGmLoc().getLng()+")";
        String types = myLocation.getTypes();
        addressTv.setText(address);
        coordinateTv.setText(coordinate);
        nearbyTv.setText(types);

        return mView;
    }

}
