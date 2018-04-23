package com.rjt.android.myapplication;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import android.support.v7.app.AppCompatActivity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class AsyncLocatingTask extends AsyncTask<Context, Void, ArrayList<MyLocation>> {
    ArrayList<MyLocation> mMyLocations;
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mMyLocations = new ArrayList<>();
    }

    @Override
    protected ArrayList<MyLocation> doInBackground(final Context... arg0) {
        HttpHandler sh = new HttpHandler();

        // Making a request to url and getting response
        String url_part1 = "https://maps.googleapis.com/maps/api/place/textsearch/json?query=BBVA+Compass&location=42,-88&radius=10000&key=AIzaSyBjzIx-qaBW1jIUECTJeHs7slsQiOnbzYc";

        Log.d("URLPART", url_part1);
        String jsonStr = sh.makeServiceCall(url_part1);
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
                   // try{
                    MyLocation myLocation = new MyLocation(formated_address, vp, geoLoc, icon, id, name, place_id, referene, types);
                    if(c.has("opening_hours")){
                        JSONObject openingHours = c.getJSONObject("opening_hours");
                        myLocation.setOpened(openingHours.getBoolean("open_now"));
                    }
                    mMyLocations.add(myLocation);
                }

            } catch (final JSONException e) {
                ((Activity)arg0[0]).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                    }
                });

            }
        } else {
            ((Activity)arg0[0]).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                }
            });

        }

        return mMyLocations;
    }

    @Override
    protected void onPostExecute(ArrayList<MyLocation> result) {
        super.onPostExecute(result);
        /**
         * Updating parsed JSON data into ListView
         * */
    }
}