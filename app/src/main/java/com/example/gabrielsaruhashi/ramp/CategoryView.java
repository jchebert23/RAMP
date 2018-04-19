package com.example.gabrielsaruhashi.ramp;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

import com.example.gabrielsaruhashi.ramp.models.SubCategory;

import java.util.ArrayList;

public class CategoryView extends AppCompatActivity {

    public final static int NUMBER_OF_SUBCATEGORIES = 6;
    public final static String[] SUBCATEGORIES_TITLES = {"A", "B", "C", "D", "E", "F"};


    //The array list that stores the categories
    ArrayList<SubCategory> subcategories;

    //track adapter and recycle view
    RecyclerView rvSubcategories;
    SubCategoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_view);

        subcategories = new ArrayList<>();
        //initialize the adapter
        adapter = new SubCategoryAdapter(subcategories);
        rvSubcategories = (RecyclerView) findViewById(R.id.rvSubcategories);
        rvSubcategories.setLayoutManager(new GridLayoutManager(this, 1));
        rvSubcategories.setAdapter(adapter);


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
        for(int i = 0; i < NUMBER_OF_SUBCATEGORIES; i++){
            SubCategory subcategory = new SubCategory(SUBCATEGORIES_TITLES[i], "", null);
            subcategories.add(subcategory);
            adapter.notifyItemInserted(subcategories.size() - 1);
        }
    }
}