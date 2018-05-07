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
import android.support.v4.app.FragmentTransaction;
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
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, MymapFragment.deliverLocations {

  //  private SupportMapFragment mMapFragment;
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
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.location_option:
                    LocationsFragment lsf = new LocationsFragment();
                    Bundle b = new Bundle();

                    b.putSerializable("DATA", mMyLocations);
                    lsf.setArguments(b);
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragmentContainer, lsf);
                    transaction.addToBackStack(null);
                    transaction.commit();
                    return true;
            case R.id.map_view:
                    MymapFragment mymapFragment = new MymapFragment();
                    FragmentTransaction transaction2 = getSupportFragmentManager().beginTransaction();
                    transaction2.replace(R.id.fragmentContainer, mymapFragment);
                    transaction2.addToBackStack(null);
                    transaction2.commit();
                return true;
            case R.id.find_boundary:
                BoundaryFragment boundaryFragment = new BoundaryFragment();
                FragmentTransaction transaction1 = getSupportFragmentManager().beginTransaction();
                transaction1.replace(R.id.fragmentContainer, boundaryFragment);
                transaction1.addToBackStack(null);
                transaction1.commit();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_main);
        // Find the toolbar view inside the activity layout
        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        //MenuItem menuItem = findItem()
        mMyLocations = new ArrayList<>();
        MymapFragment mymapFragment = new MymapFragment();
        FragmentTransaction transaction2 = getSupportFragmentManager().beginTransaction();
        transaction2.add(R.id.fragmentContainer, mymapFragment);
        transaction2.addToBackStack(null);
        transaction2.commit();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    }

    @Override
    public void onSendToActivity(ArrayList<MyLocation> locs) {
        mMyLocations = locs;
    }
}
