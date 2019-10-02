package com.example.gabrielsaruhashi.ramp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gabrielsaruhashi.ramp.R;
import com.example.gabrielsaruhashi.ramp.adapters.SectionContentAdapter;
import com.example.gabrielsaruhashi.ramp.models.Section;

import java.util.ArrayList;


public class SectionFragment extends Fragment {


    public static SectionFragment newInstance(int sectionNum, String sectionTitle, String sectionContent, ArrayList<String> sectionNames, ArrayList<String> sectionContents) {
        SectionFragment tempFrag = new SectionFragment();
        Bundle args = new Bundle();
        args.putInt("sectionNum", sectionNum);
        args.putString("sectionTitle", sectionTitle);
        args.putString("sectionContent", sectionContent);
        args.putStringArrayList("sectionNames", sectionNames);
        args.putStringArrayList("sectionContents", sectionContents);
        tempFrag.setArguments(args);
        return tempFrag;
    }
    ArrayList<Section> sections;
    ArrayList<String> sectionNames;
    ArrayList<String> sectionContents;
    RecyclerView rvSections;
    SectionContentAdapter adapter;
    int sectionNum;
    String sectionTitle;
    String sectionContent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get back arguments
        sectionNum = getArguments().getInt("sectionNum", 0);
        sectionTitle = getArguments().getString("sectionTitle", "");
        sectionContent = getArguments().getString("sectionContent", "");
        sectionNames = getArguments().getStringArrayList("sectionNames");
        sectionContents = getArguments().getStringArrayList("sectionContents");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_section, container, false);
    }

    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        sections = new ArrayList<>();
        adapter = new SectionContentAdapter(sections);
        rvSections = (RecyclerView) view.findViewById(R.id.rvGuide);
        rvSections.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        rvSections.setAdapter(adapter);

        getCurrentCategories();
    }

    //get the list of current objects
    private void getCurrentCategories() {
        Section section = new Section(sectionNames.get(sectionNum), sectionContents.get(sectionNum), sectionNum);
        sections.add(section);
        adapter.notifyItemInserted(0);
    }

}
