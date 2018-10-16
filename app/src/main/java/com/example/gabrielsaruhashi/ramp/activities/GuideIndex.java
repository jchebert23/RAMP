package com.example.gabrielsaruhashi.ramp.activities;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

import com.example.gabrielsaruhashi.ramp.R;
import com.example.gabrielsaruhashi.ramp.SectionAdapter;
import com.example.gabrielsaruhashi.ramp.models.Section;

import java.util.ArrayList;

import static android.support.v7.widget.DividerItemDecoration.VERTICAL;

public class GuideIndex extends AppCompatActivity {

    public final static int NUMBER_OF_SECTIONS = 3;
    public final static String[] SECTION_TITLES = {"What are treatment options?", "What are payment options?", "Where do I start?"};

    ArrayList<Section> sections;

    RecyclerView rvSections;
    SectionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_index);

        sections = new ArrayList<>();
        adapter = new SectionAdapter(sections);
        rvSections = (RecyclerView) findViewById(R.id.rvSections);
        rvSections.setLayoutManager(new GridLayoutManager(this, 1));
        rvSections.setAdapter(adapter);

        Resources res = getResources();
        Drawable horizontalLine = ResourcesCompat.getDrawable(res, R.drawable.horizontal_line, null);
        DividerItemDecoration itemDecor = new DividerItemDecoration(rvSections.getContext(), VERTICAL);
        itemDecor.setDrawable(horizontalLine);
        rvSections.addItemDecoration(itemDecor);

        final Context context = this;
        getCurrentCategories();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_category_list, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // perform query here

                // workaround to avoid issues with some emulators and keyboard devices firing twice if a keyboard enter is used
                // see https://code.google.com/p/android/issues/detail?id=24599
                searchView.clearFocus();

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);

    }

    //get the list of current objects
    private void getCurrentCategories() {
        for(int i = 0; i < NUMBER_OF_SECTIONS; i++){
            Section section = new Section(SECTION_TITLES[i], null, i);
            sections.add(section);
            adapter.notifyItemInserted(sections.size() - 1);
        }
    }
}

