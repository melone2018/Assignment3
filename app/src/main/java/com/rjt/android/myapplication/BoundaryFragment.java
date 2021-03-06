package com.rjt.android.myapplication;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.rjt.android.myapplication.api.OnBoardApiService;
import com.rjt.android.myapplication.api.OnBoardRetrofitInstance;
import com.rjt.android.myapplication.model.pojo_AreaId.BoundaryResponse;
import com.rjt.android.myapplication.model.pojo_AreaId.Item;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class BoundaryFragment extends Fragment  implements OnMapReadyCallback,  GoogleMap.OnPolylineClickListener,
        GoogleMap.OnPolygonClickListener{
    private SupportMapFragment boundaryFragment;
    private final static String CITY_TYPE = "PL";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_boundary, container, false);
        boundaryFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.city_boundary);
        boundaryFragment.getMapAsync(this);
        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Add polylines and polygons to the map. This section shows just
        // a single polyline. Read the rest of the tutorial to learn more.
        final String cityName = "St. Charles";
        String cityNameWithState = cityName + " IL";
        List<LatLng> cityLatLngs = obtainCityLatLng(cityNameWithState);
        String loc2Info = "-88.26274 41.94791, -88.26425 41.94864, -88.26444 41.94869, -88.26857 41.94873, -88.26877 41.94877, -88.26883 41.94877, -88.26901 41.94868, -88.27203 41.94745, -88.27219 41.94755, -88.27237 41.94766, -88.2728 41.947, -88.27264 41.9469, -88.27247 41.94679, -88.27341 41.94512, -88.2733 41.94339, -88.27355 41.94286, -88.27374 41.9428, -88.27379 41.94278, -88.27412 41.94332, -88.2747 41.94313, -88.27598 41.94116, -88.27915 41.93763, -88.27924 41.93745, -88.27947 41.93737, -88.27964 41.93748, -88.28619 41.93655, -88.28634 41.93641, -88.28652 41.93649, -88.29205 41.93786, -88.29221 41.93784, -88.29241 41.93782, -88.29576 41.93591, -88.29594 41.93583, -88.29613 41.93589, -88.29899 41.9375, -88.29901 41.93786, -88.29902 41.93806, -88.29676 41.94276, -88.29659 41.94286, -88.2964 41.94279, -88.2941 41.94122, -88.29066 41.94545, -88.29254 41.95175, -88.29274 41.95175, -88.30552 41.95025, -88.30572 41.95025, -88.30824 41.95003, -88.30865 41.95018, -88.30876 41.95002, -88.30888 41.94985, -88.3131 41.95223, -88.31328 41.9523, -88.31332 41.95211, -88.31411 41.94983, -88.3148 41.94807, -88.3146 41.94805, -88.31244 41.94641, -88.31247 41.94621, -88.3124 41.94603, -88.3164 41.93579, -88.31836 41.9293, -88.31856 41.92928, -88.32041 41.92931, -88.32131 41.93017, -88.32149 41.93054, -88.32082 41.93223, -88.32084 41.93316, -88.31985 41.93503, -88.31979 41.93523, -88.32028 41.93589, -88.32033 41.9359, -88.32038 41.93591, -88.32043 41.93581, -88.32165 41.93563, -88.32185 41.93566, -88.32204 41.9357, -88.32371 41.93432, -88.32378 41.9343, -88.32397 41.93436, -88.32606 41.9344, -88.32786 41.93147, -88.32791 41.93144, -88.32848 41.9328, -88.32855 41.93299, -88.33232 41.9344, -88.33252 41.9344, -88.33272 41.93438, -88.33846 41.93288, -88.33976 41.9302, -88.33932 41.92898, -88.33924 41.92892, -88.33924 41.92873, -88.34004 41.92656, -88.34003 41.92652, -88.33957 41.9261, -88.3411 41.9261, -88.34031 41.92507, -88.34044 41.92357, -88.34097 41.92124, -88.34108 41.92107, -88.34152 41.92062, -88.3414 41.92046, -88.34111 41.91922, -88.34118 41.91903, -88.34167 41.91754, -88.34159 41.91751, -88.34148 41.91734, -88.34004 41.91272, -88.34005 41.91267, -88.34004 41.91262, -88.34112 41.91157, -88.34221 41.91045, -88.34429 41.91047, -88.3447 41.91014, -88.34469 41.90811, -88.34469 41.90791, -88.34366 41.90698, -88.34365 41.90678, -88.34364 41.90658, -88.34385 41.90438, -88.34145 41.90405, -88.34012 41.902, -88.34014 41.90197, -88.34016 41.90191, -88.34287 41.90027, -88.34058 41.89768, -88.34086 41.89605, -88.34085 41.89452, -88.34055 41.89424, -88.3405 41.89424, -88.3404 41.89407, -88.33985 41.89408, -88.33986 41.89655, -88.33861 41.89715, -88.33843 41.89706, -88.33762 41.89771, -88.33762 41.89776, -88.33765 41.89926, -88.33386 41.8985, -88.33281 41.89827, -88.33269 41.89794, -88.33267 41.89787, -88.33216 41.89816, -88.33212 41.89822, -88.3321 41.89825, -88.33039 41.89844, -88.33019 41.89849, -88.33004 41.89862, -88.32982 41.89896, -88.32976 41.89915, -88.3296 41.89926, -88.32792 41.89807, -88.32787 41.89807, -88.32782 41.89802, -88.3274 41.89803, -88.3272 41.89803, -88.327 41.89803, -88.32617 41.89824, -88.32617 41.89804, -88.32617 41.89799, -88.32508 41.898, -88.32508 41.89805, -88.32508 41.89825, -88.3244 41.89811, -88.32435 41.89806, -88.3242 41.89806, -88.324 41.89807, -88.32376 41.89827, -88.32376 41.89807, -88.3233 41.89756, -88.32194 41.89788, -88.32182 41.89772, -88.32159 41.89658, -88.32065 41.8972, -88.3205 41.89734, -88.32036 41.89748, -88.32092 41.89788, -88.32095 41.89804, -88.32096 41.8981, -88.32072 41.89815, -88.32068 41.89814, -88.32064 41.89818, -88.32058 41.89815, -88.32054 41.8982, -88.32041 41.89818, -88.32014 41.89823, -88.32011 41.8982, -88.32004 41.89823, -88.32001 41.8982, -88.31994 41.89823, -88.31991 41.89821, -88.31941 41.89822, -88.31901 41.89795, -88.31865 41.89787, -88.31827 41.89824, -88.31822 41.89824, -88.31817 41.89829, -88.31817 41.89834, -88.31728 41.89854, -88.31708 41.89853, -88.31683 41.89852, -88.31683 41.89828, -88.31683 41.89808, -88.31598 41.89803, -88.31578 41.89802, -88.3154 41.89768, -88.31475 41.89823, -88.31369 41.89811, -88.31369 41.89828, -88.31369 41.89848, -88.31363 41.89854, -88.31343 41.89853, -88.31323 41.89852, -88.31219 41.89832, -88.30611 41.89888, -88.30599 41.8988, -88.30583 41.89868, -88.30547 41.89829, -88.30545 41.89809, -88.30355 41.89912, -88.3035 41.89893, -88.30262 41.89718, -88.30248 41.89704, -88.30197 41.89825, -88.30189 41.89844, -88.30139 41.89913, -88.29999 41.89831, -88.29999 41.89828, -88.29991 41.89824, -88.29882 41.89832, -88.29828 41.89837, -88.29823 41.89832, -88.29803 41.89833, -88.29783 41.89833, -88.29713 41.8984, -88.29685 41.89835, -88.29665 41.89835, -88.29645 41.89836, -88.29606 41.89836, -88.29598 41.89841, -88.29593 41.89836, -88.2956 41.89837, -88.2954 41.89837, -88.2952 41.89837, -88.29429 41.89859, -88.29424 41.89839, -88.2924 41.89843, -88.29233 41.89848, -88.29208 41.89899, -88.29198 41.89901, -88.29067 41.89879, -88.29051 41.89867, -88.29035 41.89855, -88.28924 41.89824, -88.28921 41.89826, -88.28857 41.89856, -88.28852 41.89862, -88.28849 41.89905, -88.28745 41.89929, -88.28719 41.8989, -88.2872 41.89885, -88.28723 41.89865, -88.28712 41.89863, -88.28706 41.89882, -88.28696 41.89879, -88.28623 41.89837, -88.28609 41.8983, -88.28595 41.89817, -88.28583 41.89818, -88.28565 41.89811, -88.28561 41.8981, -88.28557 41.89809, -88.28522 41.89802, -88.28462 41.898, -88.28454 41.89757, -88.28446 41.89758, -88.28452 41.89801, -88.28453 41.89806, -88.28343 41.89857, -88.28325 41.89867, -88.28151 41.89881, -88.28055 41.89883, -88.28048 41.89888, -88.28042 41.89883, -88.28042 41.89878, -88.27939 41.89871, -88.27919 41.89871, -88.27822 41.89858, -88.27804 41.89864, -88.27785 41.89871, -88.27737 41.89845, -88.27712 41.89867, -88.27692 41.89867, -88.27672 41.89867, -88.27047 41.89723, -88.27027 41.89724, -88.27007 41.89724, -88.26538 41.89804, -88.2652 41.89813, -88.26515 41.89833, -88.26261 41.89999, -88.26241 41.89999, -88.26241 41.90024, -88.26243 41.90237, -88.26243 41.90328, -88.26244 41.9038, -88.26245 41.90429, -88.26245 41.90497, -88.26245 41.90517, -88.26246 41.90579, -88.26247 41.90645, -88.26247 41.9067, -88.26247 41.90683, -88.26247 41.90713, -88.26248 41.90762, -88.26248 41.90784, -88.26248 41.90789, -88.2625 41.91, -88.2625 41.91015, -88.26251 41.91104, -88.26252 41.91272, -88.26252 41.91277, -88.26252 41.91313, -88.26252 41.91337, -88.26255 41.91552, -88.26255 41.91591, -88.26256 41.91642, -88.26255 41.91661, -88.26254 41.91697, -88.2623 41.91676, -88.2615 41.91657, -88.25891 41.91637, -88.25836 41.91647, -88.25691 41.91624, -88.2552 41.91637, -88.25133 41.9158, -88.24968 41.91599, -88.24931 41.91639, -88.24894 41.91777, -88.24912 41.91896, -88.249 41.92025, -88.24951 41.92068, -88.25011 41.92087, -88.25069 41.92074, -88.25126 41.92033, -88.25201 41.92045, -88.25204 41.92136, -88.25204 41.92155, -88.25204 41.92175, -88.25267 41.92388, -88.25279 41.92404, -88.25027 41.92658, -88.25043 41.92671, -88.25227 41.92929, -88.25234 41.92947, -88.25241 41.92966, -88.25388 41.92909, -88.25404 41.92896, -88.25411 41.9289, -88.26108 41.92714, -88.26127 41.9271, -88.26264 41.92708, -88.26265 41.92764, -88.26264 41.92841, -88.26263 41.92875, -88.26264 41.92936, -88.26265 41.93023, -88.26271 41.9352, -88.26273 41.93699, -88.26273 41.93726, -88.26271 41.93832, -88.26271 41.94014, -88.26271 41.94215, -88.26273 41.9464, -88.26274 41.94791";
        List<LatLng> coordinates2 = convertCoordinates(loc2Info);
        //String city
        Log.d("LatLng", cityLatLngs.get(0).latitude + " " + cityLatLngs.get(0).longitude);
        Retrofit retrofit = OnBoardRetrofitInstance.getOnBoardRetrofitInstance();
        retrofit.create(OnBoardApiService.class)
                .getArea(cityLatLngs.get(0).latitude, cityLatLngs.get(0).longitude)
                .subscribeOn(Schedulers.io())
                .observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
                .subscribe(new Observer<BoundaryResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("onSubscribe", "Disposable");
                    }

                    @Override
                    public void onNext(BoundaryResponse boundaryResponse) {
                        Log.d("onNext", "Got Boundary Response");
                       // Log.d("onNextContent", boundaryResponse.getResponse().getResult());
                        List<Item> items = boundaryResponse.getResponse().getResult().getPackage().getItem();
                        ListIterator<Item> iterator = items.listIterator();
                        while(iterator.hasNext()){
                            Item item = iterator.next();
                            if(item.getType().equals(CITY_TYPE) && item.getName().contains(cityName)){
                                Log.d("AREAID", item.getId());
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Error Msg", e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

        PolygonOptions polygonOptions2 = new PolygonOptions();
        polygonOptions2.addAll(coordinates2);
        Polygon polygon2 = googleMap.addPolygon(polygonOptions2);
        polygon2.setPoints(coordinates2);

    }

    public List<LatLng> convertCoordinates(String locationInfo){
        List<LatLng> coordinates = new ArrayList<>();
        String[] locs = locationInfo.split("\\,");

        for(int i = 0; i < locs.length; i++){
            String loc = locs[i].trim();
            int spacePoc = loc.indexOf(' ');
            //Log.d("LOCATION", loc + " space: " + spacePoc + " totalLen: " + loc.length());
            LatLng coord = new LatLng(Double.valueOf(loc.substring(spacePoc
            +1, loc.length())), Double.valueOf(loc.substring(0, spacePoc)));
          //  Log.d("LatLng", coord.latitude + ", " + coord.longitude);
            coordinates.add(coord);
        }
        return coordinates;
    }

    public List<LatLng> obtainCityLatLng(String cityName){
        List<LatLng> result = new ArrayList<>();
        try{
            Geocoder geocoder = new Geocoder(getActivity());
            List<Address> addresses = geocoder.getFromLocationName(cityName, 4);
            if(addresses.size() > 0){
                for(int i = 0; i < addresses.size(); i++){
                    if(addresses.get(i).getCountryName().equals("United States")){
                        double latitude = addresses.get(i).getLatitude();
                        double longitude = addresses.get(i).getLongitude();
                        result.add(new LatLng(latitude, longitude));
                    }
                }
            }
            return result;
        }catch (IOException e){
            e.printStackTrace();
        }
        return result;
    }
    @Override
    public void onPolygonClick(Polygon polygon) {

    }

    @Override
    public void onPolylineClick(Polyline polyline) {

    }
}
