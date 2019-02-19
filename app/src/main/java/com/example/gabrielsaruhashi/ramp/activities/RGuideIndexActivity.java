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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.gabrielsaruhashi.ramp.R;
import com.example.gabrielsaruhashi.ramp.adapters.SectionAdapter;
import com.example.gabrielsaruhashi.ramp.models.Category;
import com.example.gabrielsaruhashi.ramp.models.Section;
import com.example.gabrielsaruhashi.ramp.models.SubCategory;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.parceler.Parcels;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

import static android.support.v7.widget.DividerItemDecoration.VERTICAL;

public class RGuideIndexActivity extends AppCompatActivity {

    public final static int NUMBER_OF_SECTIONS = 3;
    public final static String[] SECTION_TITLES = {"What are treatment options?", "What are payment options?", "Where do I start?"};

    ArrayList<Section> sections;

    RecyclerView rvSections;
    SectionAdapter adapter;
    TextView title;
    Category category;
    SubCategory subcategory;
    String name;
    AsyncHttpClient client;
    String base_url = "https://powerful-coast-14567.herokuapp.com/api/guide/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_index);

        sections = new ArrayList<>();
        client = new AsyncHttpClient();
        adapter = new SectionAdapter(sections);
        rvSections = (RecyclerView) findViewById(R.id.rvSections);
        rvSections.setLayoutManager(new GridLayoutManager(this, 1));
        rvSections.setAdapter(adapter);
        title = findViewById(R.id.titleTxt);
        if (getIntent().hasExtra(Category.class.getSimpleName())) {
            // coming from category
            category = Parcels.unwrap(getIntent().getParcelableExtra(Category.class.getSimpleName()));
            title.setText(category.getStatus().toString() + " Guide");
            name = category.getStatus();
        } else if (getIntent().hasExtra(SubCategory.class.getSimpleName())) {
            // coming from subcategory
            subcategory = Parcels.unwrap(getIntent().getParcelableExtra(SubCategory.class.getSimpleName()));
            title.setText(subcategory.getTitle().toString() + " Guide");
            name = subcategory.getTitle();
        } else {
            getHardCoded();
        }
        Resources res = getResources();
        Drawable horizontalLine = ResourcesCompat.getDrawable(res, R.drawable.horizontal_line, null);
        DividerItemDecoration itemDecor = new DividerItemDecoration(rvSections.getContext(), VERTICAL);
        itemDecor.setDrawable(horizontalLine);
        rvSections.addItemDecoration(itemDecor);

        final Context context = this;
        getGuide();
    }

    //get the list of current objects
    private void getGuide() {
        String url = base_url + String.format(name);
        // set request parameters
        RequestParams params = new RequestParams();
        params.put("param", "api_key");

        // execute GET request using client to get JSON response
        client.get(url, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                try {
                    JSONArray results = response.getJSONObject(0).getJSONArray("sections");
                    // iterate through array, create section objects
                    for (int i = 0; i < results.length(); i++) {
                        Section section = new Section(results.getJSONObject(i).getString("title"), results.getJSONObject(i).getString("content"), i);
                        sections.add(section);
                        adapter.notifyItemInserted(sections.size() - 1);
                    }
                } catch (JSONException e) {
                    Log.d("RGuideIndexActivity", "failed to parse categories");
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.i("GuideClient", "Failed");
            }
        });

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
    private void getHardCoded() {
        for(int i = 0; i < NUMBER_OF_SECTIONS; i++){
            Section section = new Section(SECTION_TITLES[i], null, i);
            sections.add(section);
            adapter.notifyItemInserted(sections.size() - 1);
        }
    }
}

