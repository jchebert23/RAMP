package com.example.gabrielsaruhashi.ramp.activities;

import android.app.FragmentTransaction;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.example.gabrielsaruhashi.ramp.R;
import com.example.gabrielsaruhashi.ramp.Two11Client;
import com.example.gabrielsaruhashi.ramp.models.Places;
import com.example.gabrielsaruhashi.ramp.models.PlacesFragment;
import com.example.gabrielsaruhashi.ramp.models.Places_Adapter;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Masayuki on 9/7/18.
 */

public class TGuideListMapActivity extends AppCompatActivity {

    private SupportMapFragment mapFragment;
    private GoogleMap map;
    private LocationRequest mLocationRequest;
    Location mCurrentLocation;
    private long UPDATE_INTERVAL = 60000;  /* 60 secs */
    private long FASTEST_INTERVAL = 5000; /* 5 secs */

    private final static String KEY_LOCATION = "location";
    ArrayList<Places> places = new ArrayList<>();

    /*
     * Define a request code to send to Google Play services This code is
     * returned in Activity.onActivityResult
     */
    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tguide_list_map);
        if (TextUtils.isEmpty(getResources().getString(R.string.google_map_API_key))) {
            throw new IllegalStateException("You forgot to supply a Google Maps API key");
        }

        if (savedInstanceState != null && savedInstanceState.keySet().contains(KEY_LOCATION)) {
            // Since KEY_LOCATION was found in the Bundle, we can be sure that mCurrentLocation
            // is not null.
            mCurrentLocation = savedInstanceState.getParcelable(KEY_LOCATION);
        }
        mapFragment = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map));
        //mapFragment = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_placeholder));
        Two11Client newClient = new Two11Client();
        newClient.search("enfield", new JsonHttpResponseHandler() {
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONArray placesJson = response.getJSONArray("results");
                    places.clear();
                    places.addAll(Places.fromJson(placesJson));
                    Log.d("search", response.toString());
                    Log.d("search", Integer.toString(places.size()));
                    for(int i = 0; i < places.size(); i++){
                        Log.d("search", places.get(i).toPrint());
                    }

                    //TODO: MOVE THIS TO ASYNC
                    if (mapFragment != null) {
                        Log.d("search", "entered");
                        mapFragment.getMapAsync(new OnMapReadyCallback() {
                            @Override
                            public void onMapReady(GoogleMap map) {
                                Log.d("search", "ready");
                                //Log.d("search", "places.size is: " + places.size());
                                LatLng location = null;
                                for(int i = 0; i < places.size(); i++){
                                    location = new LatLng(places.get(i).getLat(), places.get(i).getLon());
                                    map.addMarker(new MarkerOptions().position(location)
                                            .title("Hello World")
                                    );
                                    Log.d("search", "latitude:" + places.get(i).getLat());
                                }
                                map.moveCamera(CameraUpdateFactory.newLatLng(location));
                                loadMap(map);
                            }
                        });
                    } else {
                        Log.d("search", "failed");
                        //Toast.makeText(this, "Error - Map Fragment was null!!", Toast.LENGTH_SHORT).show();
                    }

                    //ADD the recycle view here!!
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.add(R.id.fragment_placeholder, PlacesFragment.newInstance(places));
                    ft.commit();
                    //Log.d("search", "compelted");
                    //Log.d("search", "places.size is: " + places.size());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    protected void loadMap(GoogleMap googleMap) {
        map = googleMap;
        if (map != null) {
            Log.d("search", "success");
            // Map is ready
            Toast.makeText(this, "Map Fragment was loaded properly!", Toast.LENGTH_SHORT).show();
            //MapDemoActivityPermissionsDispatcher.getMyLocationWithPermissionCheck(this);
            //MapDemoActivityPermissionsDispatcher.startLocationUpdatesWithPermissionCheck(this);
        } else {
            Toast.makeText(this, "Error - Map was null!!", Toast.LENGTH_SHORT).show();
        }
    }

}
