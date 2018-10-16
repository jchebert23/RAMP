package com.example.gabrielsaruhashi.ramp.models;

//import android.support.v4.app.Fragment;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gabrielsaruhashi.ramp.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Masayuki on 10/5/18.
 */

public class PlacesFragment extends Fragment {
    public static PlacesFragment newInstance(ArrayList<Places> places){
        PlacesFragment fragment = new PlacesFragment();
        Bundle args = new Bundle();
        //https://stackoverflow.com/questions/42436012/how-to-put-the-arraylist-into-bundle
        //args.putParcelableArrayList(places);
        args.putParcelableArrayList("places", places);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        return inflater.inflate(R.layout.fragment_places, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        ArrayList<Places> places= getArguments().getParcelableArrayList("places");
        RecyclerView rvPlaces = getView().findViewById(R.id.rvPlaces);
        Places_Adapter adapter = new Places_Adapter(places);
        rvPlaces.setAdapter(adapter);
        rvPlaces.setLayoutManager(new LinearLayoutManager(getActivity()));
        Log.e("search", "hi");
    }
}
