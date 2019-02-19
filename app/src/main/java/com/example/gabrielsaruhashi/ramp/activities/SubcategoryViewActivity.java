package com.example.gabrielsaruhashi.ramp.activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.gabrielsaruhashi.ramp.R;
import com.example.gabrielsaruhashi.ramp.adapters.SubCategoryAdapter;
import com.example.gabrielsaruhashi.ramp.models.Category;
import com.example.gabrielsaruhashi.ramp.models.SubCategory;

import org.parceler.Parcels;

import java.util.ArrayList;

public class SubcategoryViewActivity extends AppCompatActivity {

    //public final static int NUMBER_OF_CATEGORIES = 6;
    public final static ArrayList<SubCategory> GUIDE_DESCRIPTIONS = new ArrayList<SubCategory>(){
        {
            SubCategory first = new SubCategory("Primary Care", 0);
            SubCategory second = new SubCategory("Women's Health", 0);
            SubCategory third = new SubCategory("Children (Pediatrics)", 0);
            add(first);
            add(second);
            add(third);
        }
    };

    //The array list that stores the guides
    ArrayList<SubCategory> subcats = new ArrayList<>();

    RecyclerView rvSubCategories;
    SubCategoryAdapter adapter;
    Category category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subcategoryview);


        adapter = new SubCategoryAdapter(subcats);

        rvSubCategories = (RecyclerView) findViewById(R.id.rvSubCategories);
        rvSubCategories.setLayoutManager(new LinearLayoutManager(this));
        rvSubCategories.setAdapter(adapter);

        category = (Category) Parcels.unwrap(getIntent().getParcelableExtra(Category.class.getSimpleName()));

        TextView generalTitle = findViewById(R.id.SubCategoryTitle);
        generalTitle.setText(category.getStatus().toString());

        if (haveNetworkConnection()) {
            // get subcats from API if internet
            getCurrentSubCategories();
        } else {
            getHardCoded();
        }
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

    //https://stackoverflow.com/questions/5588804/android-button-setonclicklistener-design
    public void onClickListen (View view) {
        //https://stackoverflow.com/questions/6121797/android-how-to-change-layout-on-button-click
        Intent intentMain = new Intent(SubcategoryViewActivity.this, RGuideIndexActivity.class);
        intentMain.putExtra(Category.class.getSimpleName(), Parcels.wrap(category));
        SubcategoryViewActivity.this.startActivity(intentMain);
    }

    // if no internet
    private void getHardCoded(){
        for(int i = 0; i < GUIDE_DESCRIPTIONS.size(); i++){
            SubCategory subcat = GUIDE_DESCRIPTIONS.get(i);
            subcats.add(subcat);
            adapter.notifyItemInserted(subcats.size() - 1);

        }
    }

    private void getCurrentSubCategories() {
        for (int i = 0; i < category.getSubcategories().size(); i++) {
            subcats.add(category.getSubcategories().get(i));
            adapter.notifyItemInserted(category.getSubcategories().size() - 1);
        }
    }

    private boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected()) {
                    haveConnectedWifi = true;
                }
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }
}
