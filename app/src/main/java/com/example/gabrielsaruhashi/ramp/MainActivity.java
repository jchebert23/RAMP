package com.example.gabrielsaruhashi.ramp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.SearchView;

import com.example.gabrielsaruhashi.ramp.models.Category;

import java.util.ArrayList;

//NOTE: comments and help with code: https://www.youtube.com/watch?list=PLrT2tZ9JRrf6ANjkPapBLGsCwdyKQlt_J&v=fzWcnoVs9d4
public class MainActivity extends AppCompatActivity {

    public final static int NUMBER_OF_CATEGORIES = 6;
    public final static String[] CATEGORIES_DESCRIPTIONS = {"I need a job.", "I'm hungry.", "I need a doctor.", "I need housing.", "I need the weather.", "I need a lawyer."};

    public final static int[] CATEGORIES_IMAGES = {R.drawable.ic_mental_category, R.drawable.ic_law_category, R.drawable.ic_mental_category, R.drawable.ic_law_category, R.drawable.ic_mental_category, R.drawable.ic_law_category};
    //The array list that stores the categories
    ArrayList<Category> categories;

    //track adapter and recycle view
    RecyclerView rvCategories;
    CategoryAdapter adapter;
    SearchView svSearchview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        categories = new ArrayList<>();
        //initialize the adapter
        adapter = new CategoryAdapter(categories);
        rvCategories = (RecyclerView) findViewById(R.id.rvCategories);
        rvCategories.setLayoutManager(new GridLayoutManager(this, 2));
        rvCategories.setAdapter(adapter);
        // get reference to searchview
        svSearchview = (SearchView) findViewById(R.id.svSearchbar);
        svSearchview.setQueryHint("What are you looking for?"); // set the hint text to display in the query text field
        svSearchview.setIconifiedByDefault(false);  // set the default or resting state of the search field



        getCurrentCategories();
    }

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_category_list, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        final android.support.v7.widget.SearchView searchView = (Sear) MenuItemCompat.getActionView(searchItem);
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

    } */


    //get the list of current objects
    private void getCurrentCategories(){
        for(int i = 0; i < NUMBER_OF_CATEGORIES; i++){
            Category category = new Category(CATEGORIES_DESCRIPTIONS[i], CATEGORIES_IMAGES[i]);
            categories.add(category);
            adapter.notifyItemInserted(categories.size() - 1);
        }
    }
}
