package com.rjt.android.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.zip.Inflater;

public class MymapFragment extends Fragment implements OnMapReadyCallback {
    private SupportMapFragment mMapFragment;
    private ArrayList<MyLocation> locations;

    public MymapFragment() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_mymap, container, false);
        mMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapFragment);
        mMapFragment.getMapAsync(this);
        return mView;
    }

    // This fragment is responsible for computing locations
    @Override
    public void onMapReady(GoogleMap googleMap) {
        try {
            locations = new AsyncLocatingTask().execute(getActivity()).get();
            deliverLocations act = (deliverLocations)getActivity();
            act.onSendToActivity(locations);
            for (int i = 0; i < locations.size(); i++) {
                LatLng marker = new LatLng(locations.get(i).getGmLoc().getLat(), locations.get(i).getGmLoc().getLng());
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker, 12));
                googleMap.addMarker(new MarkerOptions().title("Loc" + String.valueOf(i)).position(marker));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    public interface deliverLocations{
        void onSendToActivity(ArrayList<MyLocation> locs);
    }
}
