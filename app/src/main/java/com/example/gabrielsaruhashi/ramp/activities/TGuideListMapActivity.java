package com.example.gabrielsaruhashi.ramp.activities;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.gabrielsaruhashi.ramp.clients.Two11Client;
import com.example.gabrielsaruhashi.ramp.R;
import com.example.gabrielsaruhashi.ramp.models.Place;
import com.example.gabrielsaruhashi.ramp.fragments.PlacesFragment;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcels;

import java.util.ArrayList;

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

    /*
    Boolean for switching between list view (default) and map view.
    Depending on boolean value, upon click of button either list view or map view will be loaded into the fragment container.
     */
    boolean isMapView = false;
    private PlacesFragment placesFragment;

    private final static String KEY_LOCATION = "location";
    ArrayList<Place> places = new ArrayList<>();

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
        // load places fragment
        Two11Client newClient = new Two11Client();
        newClient.search("enfield", new JsonHttpResponseHandler() {
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONArray placesJson = response.getJSONArray("results");
                    places.clear();
                    places.addAll(Place.fromJson(placesJson));
                    placesFragment = PlacesFragment.newInstance(places);
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.fragment_placeholder, PlacesFragment.newInstance(places));
                    ft.commit();
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

    public void onSwitchView (View view) {
        if (isMapView) {
            // trigger list view
            view.setSelected(true);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            mapFragment.getView().setVisibility(View.INVISIBLE);
            ft.replace(R.id.fragment_placeholder, PlacesFragment.newInstance(places));
            ft.commit();
            isMapView = false;
        } else {
            // trigger map view
            view.setSelected(false);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.hide(placesFragment);
            ft.commit();
            // load map fragment
            FragmentManager fm = getSupportFragmentManager();
            mapFragment = SupportMapFragment.newInstance();
            fm.beginTransaction().replace(R.id.fragment_placeholder, mapFragment).commit();
            Two11Client newClient = new Two11Client();
            newClient.search("enfield", new JsonHttpResponseHandler() {
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    try {
                        JSONArray placesJson = response.getJSONArray("results");
                        places.clear();
                        places.addAll(Place.fromJson(placesJson));
                        // TODO (masayukinagase) instead of doing this try / catch, move it to an AsyncTask
                        if (mapFragment != null) {
                            Log.d("search", "entered");
                            mapFragment.getMapAsync(new OnMapReadyCallback() {
                                @Override
                                public void onMapReady(GoogleMap map) {
                                    Log.d("search", "ready");
                                    LatLng location = null;
                                    for(int i = 0; i < places.size(); i++){
                                        location = new LatLng(places.get(i).getLat(), places.get(i).getLon());
                                        Marker m = map.addMarker(new MarkerOptions().position(location)
                                                .title(places.get(i).getName())
                                        );
                                        m.setTag(places.get(i));
                                        map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                                            @Override
                                            public boolean onMarkerClick(Marker marker) {
                                                Place currentPlace = (Place) marker.getTag();
                                                Intent i = new Intent(TGuideListMapActivity.this, PlaceDetailsActivity.class);
                                                i.putExtra("places", Parcels.wrap(currentPlace));
                                                startActivity(i);
                                                return true;

                                            }
                                        });

                                        Log.d("search", "latitude:" + places.get(i).getLat());
                                    }
                                    map.moveCamera(CameraUpdateFactory.newLatLng(location));
                                    map.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 8.0f));
                                    loadMap(map);
                                }
                            });
                        } else {
                            Log.d("search", "failed");
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
            isMapView = true;
        }
    }

}
