package com.rjt.android.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.zip.Inflater;

public class MymapFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {
    private SupportMapFragment mMapFragment;
    private ArrayList<MyLocation> locations;
    GoogleMap mGoogleMap;
    private HashMap<Marker, MyLocation> markerInfo;
    public MymapFragment() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (container != null) {
            container.removeAllViews();
        }
        View mView = inflater.inflate(R.layout.fragment_mymap, container, false);
        mMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapFragment);
        mMapFragment.getMapAsync(this);
        //markerInfo = new
        return mView;
    }

    // This fragment is responsible for computing locations
    @Override
    public void onMapReady(GoogleMap googleMap) {
        try {
            mGoogleMap = googleMap;
            locations = new AsyncLocatingTask().execute(getActivity()).get();
            deliverLocations act = (deliverLocations)getActivity();
            act.onSendToActivity(locations);
            for (int i = 0; i < locations.size(); i++) {
                LatLng marker = new LatLng(locations.get(i).getGmLoc().getLat(), locations.get(i).getGmLoc().getLng());
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker, 12));
                Marker m = googleMap.addMarker(new MarkerOptions().title("BBVA Compass").position(marker));
                m.setTag(String.valueOf(i));
                mGoogleMap.setOnMarkerClickListener(this);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        //Toast.makeText(getContext(), "Marker Clicked", Toast.LENGTH_SHORT).show();
        String tag = marker.getTag().toString();
        MyLocation location = locations.get(Integer.valueOf(tag));
        MarkerFragment mf = new MarkerFragment();
        Bundle b = new Bundle();
        b.putSerializable("MARKERDATA", location);
        mf.setArguments(b);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, mf).commit();
        getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).commit();
        //getActivity().getSupportFragmentManager().beginTransaction().commit();
        return true;
    }

    public interface deliverLocations{
        void onSendToActivity(ArrayList<MyLocation> locs);
    }
}
