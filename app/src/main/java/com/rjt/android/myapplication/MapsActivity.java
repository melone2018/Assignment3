package com.rjt.android.myapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private String TAG = MapsActivity.class.getSimpleName();

    private ProgressDialog pDialog;
    private ListView lv;
    private ArrayList<MyLocation> mMyLocations;
    // URL to get contacts JSON
    private static String url = "https://maps.googleapis.com/maps/api/place/textsearch/json?query=BBVA+Compass&location=42,-88&radius=10000&key=AIzaSyBjzIx-qaBW1jIUECTJeHs7slsQiOnbzYc";


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);//加载menu文件到布局
        MenuItem mapChoice = menu.findItem(R.id.miCompose);
        MenuItem listChoice = menu.findItem(R.id.miProfile);
        mapChoice.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
               // return false;
                new GetContacts().execute();
                return false;
            }
        });
        listChoice.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return false;
            }
        });
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_main);
        // Find the toolbar view inside the activity layout
        Toolbar toolbar =  findViewById(R.id.toolbar);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        setSupportActionBar(toolbar);

        //MenuItem menuItem = findItem()
        mMyLocations = new ArrayList<>();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
//        new GetContacts().execute();


    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

//        Log.d("locationSIze", mMyLocations.size()+"");
//        // show markers
//        for(int i = 0; i < mMyLocations.size(); i++){
//            double latD = mMyLocations.get(i).getGmLoc().getLat();
//            double lngD = mMyLocations.get(i).getGmLoc().getLng();
//            LatLng sydney = new LatLng(latD, lngD);
//            MarkerOptions marker = new MarkerOptions().position(sydney).title(String.valueOf("Loc"+i));
//            mMap.addMarker(marker);
//            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
//        }
        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    /**
     * Async task class to get json by making HTTP call
     */
    private class GetContacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
//            pDialog = new ProgressDialog(MapsActivity.this);
//            pDialog.setMessage("Please wait...");
//            pDialog.setCancelable(false);
//            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String url_part1 = "https://maps.googleapis.com/maps/api/place/textsearch/json?query=BBVA+Compass&location=";

            String jsonStr = sh.makeServiceCall(url);
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = null;
                    jsonObj = new JSONObject(jsonStr);
                    // Getting JSON Array node
                    JSONArray contacts = jsonObj.getJSONArray("results");

                    for (int i = 0; i < contacts.length(); i++) {

                        ArrayList<String> types = new ArrayList<>();
                        JSONObject c = contacts.getJSONObject(i);
                        String icon = c.getString("icon");

                        String id = c.getString("id");
                        String name = c.getString("name");
                        String formated_address = c.getString("formatted_address");

                        String place_id = c.getString("place_id");
                        String referene = c.getString("reference");

                        JSONArray typeArray = c.getJSONArray("types");

                        for (int j = 0; j < typeArray.length(); j++) {
                            String jsonObject = typeArray.getString(j);
                            types.add(jsonObject);
                        }
                        JSONObject geoObj = c.getJSONObject("geometry");
                        JSONObject locObj = geoObj.getJSONObject("location");

                        GeometryLoc geoLoc = new GeometryLoc(Double.valueOf(locObj.getString("lat")), Double.valueOf(locObj.getString("lng")));
                        JSONObject vpJson = geoObj.getJSONObject("viewport");

                        JSONObject northeast = vpJson.getJSONObject("northeast");
                        JSONObject southwest = vpJson.getJSONObject("southwest");
                        double ne_lat = Double.valueOf(northeast.getString("lat"));
                        double ne_lng = Double.valueOf(northeast.getString("lng"));
                        double sw_lat = Double.valueOf(southwest.getString("lat"));
                        double sw_lng = Double.valueOf(southwest.getString("lng"));
                        ViewPort vp = new ViewPort(ne_lat, ne_lng, sw_lat, sw_lng);
                        MyLocation myLocation = new MyLocation(formated_address, vp, geoLoc, icon, id, name, place_id, referene, types);
                        mMyLocations.add(myLocation);
                    }
                    Log.d("NumLocations", mMyLocations.size()+"");

                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
//            if (pDialog.isShowing())
//                pDialog.dismiss();
            Log.d("locationSIze", mMyLocations.size() + "");
            // show markers
            for (int i = 0; i < mMyLocations.size(); i++) {
                double latD = mMyLocations.get(i).getGmLoc().getLat();
                double lngD = mMyLocations.get(i).getGmLoc().getLng();
                LatLng sydney = new LatLng(latD, lngD);
                MarkerOptions marker = new MarkerOptions().position(sydney).title(String.valueOf("Loc" + i));
                mMap.addMarker(marker);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
            }
            /**
             * Updating parsed JSON data into ListView
             * */
        }

    }
}
