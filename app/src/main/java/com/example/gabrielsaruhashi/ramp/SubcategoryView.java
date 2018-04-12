package com.example.gabrielsaruhashi.ramp;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.gabrielsaruhashi.ramp.models.SubCategory;

import java.util.ArrayList;

public class SubcategoryView extends AppCompatActivity {

    //public final static int NUMBER_OF_CATEGORIES = 6;
    public final static ArrayList<String> GUIDE_DESCRIPTIONS = new ArrayList<String>(){
        {
            add("Guide 1");
            add("Guide 2");
            add("Guide 3");
            add("Guide 4");
            add("Guide 5");
            add("Guide 6");
        }
    };

    SubCategory dummySubCategory = new SubCategory("Title", "Catch Phrase", GUIDE_DESCRIPTIONS);


    //The array list that stores the guides
    ArrayList<String> guides;

    RecyclerView rvSubCategories;
    GuideAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subcategoryview);


        guides = dummySubCategory.getGuides();
        Log.e("Error", guides.toString());
        //initialize the adapter
        adapter = new GuideAdapter(guides);

        rvSubCategories = (RecyclerView) findViewById(R.id.rvSubCategories);
        rvSubCategories.setLayoutManager(new LinearLayoutManager(this));
        rvSubCategories.setAdapter(adapter);


        TextView generalTitle = findViewById(R.id.SubCategoryTitle);
        generalTitle.setText("DEODEODKE");




        //getCurrentCategories();
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
    private void getCurrentCategories(){
        for(int i = 0; i < GUIDE_DESCRIPTIONS.size(); i++){
            String guide = GUIDE_DESCRIPTIONS.get(i);
            Log.e("GUIDE", guide);
            guides.add(guide);
            //adapter.notifyItemInserted(guides.size() - 1);

        }
    }
}
