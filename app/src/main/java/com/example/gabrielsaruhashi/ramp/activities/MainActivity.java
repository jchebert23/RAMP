package com.example.gabrielsaruhashi.ramp.activities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.SearchView;

import com.example.gabrielsaruhashi.ramp.R;
import com.example.gabrielsaruhashi.ramp.adapters.CategoryAdapter;
import com.example.gabrielsaruhashi.ramp.models.Category;
import com.example.gabrielsaruhashi.ramp.models.SubCategory;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

//NOTE: comments and help with code: https://www.youtube.com/watch?list=PLrT2tZ9JRrf6ANjkPapBLGsCwdyKQlt_J&v=fzWcnoVs9d4
public class MainActivity extends AppCompatActivity {

    public final static int NUMBER_OF_CATEGORIES = 6;
    public final static String[] CATEGORIES_DESCRIPTIONS = {"I need a job.", "I'm hungry.", "I need a doctor.", "I need housing.", "I need the weather.", "I need a lawyer."};
    public final static int[] CATEGORIES_IMAGES = {R.drawable.ic_mental_category, R.drawable.ic_law_category, R.drawable.ic_mental_category, R.drawable.ic_law_category, R.drawable.ic_mental_category, R.drawable.ic_law_category};
    //The array list that stores the categories
    ArrayList<Category> categories;
    ArrayList<Category> apiCats;
    AsyncHttpClient client;
    String base_url = "https://powerful-coast-14567.herokuapp.com/api/";

    //track adapter and recycle view
    RecyclerView rvCategories;
    CategoryAdapter adapter;
    SearchView svSearchview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        client = new AsyncHttpClient();
        apiCats = new ArrayList<Category>();
        //initialize the adapter
        adapter = new CategoryAdapter(apiCats);
        rvCategories = (RecyclerView) findViewById(R.id.rvCategories);
        rvCategories.setLayoutManager(new GridLayoutManager(this, 2));
        rvCategories.setAdapter(adapter);
        // get reference to searchview
        //svSearchview = (SearchView) findViewById(R.id.svSearchbar);
        //svSearchview.setIconifiedByDefault(false);  // set the default or resting state of the search field

        if (haveNetworkConnection()) {
            getCurrentCategories();
        } else {
            getHardCoded();
        }
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
    private void getCurrentCategories() {
        String url = base_url + String.format("categories");
        // set request parameters
        RequestParams params = new RequestParams();
        params.put("param", "api_key");

        // execute GET request using client to get JSON response
        client.get(url, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONArray results = response.getJSONArray("categories");

                    // iterate through array, create category objects
                    for (int i = 0; i < results.length(); i++) {
                        ArrayList<SubCategory> subcats = new ArrayList<>();
                        JSONArray jsonsubs = results.getJSONObject(i).getJSONArray("subcategories");
                        for (int j = 0; j < jsonsubs.length(); j++) {
                            SubCategory subcat = new SubCategory(jsonsubs.getJSONObject(j).getString("name"), jsonsubs.getJSONObject(j).getInt("hasGuide"));
                            subcats.add(subcat);
                        }
                        Category newCategory = new Category(results.getJSONObject(i).getString("name"), results.getJSONObject(i).getString("icon"), subcats);
                        apiCats.add(newCategory);
                        adapter.notifyItemInserted(apiCats.size() - 1);
                    }
                } catch (JSONException e) {
                    Log.d("MainActivity", "failed to parse categories");
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.i("APIClient", "Failed");
            }
        });

    }

    // from StackOverflow
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

    private void getHardCoded() {
        for(int i = 0; i < NUMBER_OF_CATEGORIES; i++){
            Category category = new Category(CATEGORIES_DESCRIPTIONS[i], CATEGORIES_IMAGES[i]);
            apiCats.add(category);
            adapter.notifyItemInserted(apiCats.size() - 1);
        }
    }
}

