package com.example.gabrielsaruhashi.ramp;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.gabrielsaruhashi.ramp.helpers.BaseActivity;
import com.example.gabrielsaruhashi.ramp.models.Category;

import java.util.ArrayList;

//NOTE: comments and help with code: https://www.youtube.com/watch?list=PLrT2tZ9JRrf6ANjkPapBLGsCwdyKQlt_J&v=fzWcnoVs9d4
public class MainActivity extends BaseActivity {

    public final static int NUMBER_OF_CATEGORIES = 6;
    public final static String[] CATEGORIES_DESCRIPTIONS = {"I need a job.", "I'm hungry.", "I need a doctor.", "I need housing.", "I need the weather.", "I need a lawyer."};

    //The array list that stores the categories
    ArrayList<Category> categories;

    //track adapter and recycle view
    RecyclerView rvCategories;
    CategoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        categories = new ArrayList<>();
        //initialize the adapter
        adapter = new CategoryAdapter(categories);
        rvCategories = (RecyclerView) findViewById(R.id.rvCategories);
        rvCategories.setLayoutManager(new LinearLayoutManager(this));
        rvCategories.setAdapter(adapter);


        getCurrentCategories();
    }

    //get the list of current objects
    private void getCurrentCategories(){
        for(int i = 0; i < NUMBER_OF_CATEGORIES; i++){
            Category category = new Category(CATEGORIES_DESCRIPTIONS[i], "");
            categories.add(category);
            adapter.notifyItemInserted(categories.size() - 1);
        }
    }
}
