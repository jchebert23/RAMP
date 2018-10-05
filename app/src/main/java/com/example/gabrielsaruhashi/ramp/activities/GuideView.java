package com.example.gabrielsaruhashi.ramp.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

import com.example.gabrielsaruhashi.ramp.R;
import com.example.gabrielsaruhashi.ramp.Section0Fragment;

public class GuideView extends AppCompatActivity {

//    public final static int NUMBER_OF_SECTIONS = 3;
//    public final static String[] SECTION_TITLES = {"What are treatment options?", "What are payment options?", "Where do I start?"};
//    public final static String[] SECTION_CONTENT = {"1. A talkline is a free service that everyone can use. It can also be called a “hotline” or “warmline”. A talkline is a phone number a person can call to speak privately to a trained counselor about anything that is bothering them. The counselor can help the caller find other services in their area that can help them in the long-term.\n" +
//            "2. A support group is a group of people facing the same mental health challenge who meet to share their experiences and think about ways to help cope with challenges in their lives. Sometimes a trained counselor leads the group, but usually the people in the group come together as equals. This is a great choice for those who want to join a community of people in the same situation.\n" +
//            "3. Counseling or therapy is a chance for a person to meet one-on-one with a mental health counselor to find solutions to challenges they face in their daily lives, usually once a week. There are two types of counselors: psychiatrists and psychologists. Psychiatrists can prescribe medication while psychologists cannot. After the first meeting, the mental health care provider can help you decide which kind of counselor is right for you. \n" +
//            "4. Intensive day programs may also be called “outpatient programs” or “intensive outpatient programs”. At this level of care, a person joins a program at a mental health care center and spends a couple of days a week at the center. Sometimes, patients meet and talk about their problems in a group and sometimes patients work one-on-one with counselors. The goal is to help people build the skills they need to return to their lives as before. Usually, a person who moves on from an intensive day program will be given help finding a counselor or therapist that can keep supporting them afterwards.\n" +
//            "5. Residential programs may also be called “inpatient programs”. At this level of care, a person joins a program at a mental health care center and lives at the center full-time. Each patient is given a bed and food while they are staying at the center. Usually, both medical doctors and mental health counselors work there. People talk in groups about their problems and also get personal therapy. To get into one of these centers, a person sometimes needs their current doctor or therapist to give them a recommendation. A person who moves on from residential treatment may spend some time doing intensive day treatment before going back to their daily life as before.\n",
//            "Insurance: Many mental health  providers accept insurance. They may accept private insurance, Medicaid, and/or Medicare. A person can get in touch with each service provider for a list of the types of insurance they accept. A person who wants to use their insurance to pay for mental health care must also check if their insurance plan covers the kind of care they are hoping to get. An insurance company’s website usually has a Summary of Benefits and Coverage for each insurance plan.\n" +
//                    "\n" +
//                    "Sliding scale: Some service providers charge a fee on a sliding scale. This means that the price will be different for each person depending on how much money they make.   \n" +
//                    "\n" +
//                    "Set fee: Some service providers charge a set fee. This means that the price is the same for every person.  ",
//            "You can look through the categories below to find resources that are right for you. If you are not sure where to start, here are a few major mental health resources in New Haven:\n" +
//                    "\n" +
//                    "1.         Behavioral Health: Integrated Wellness Group\n" +
//                    "\n" +
//                    "2.         The Connection\n" +
//                    "\n" +
//                    "3.         Shoreline Wellness Center\n"};
//
//    ArrayList<Section> sections;
//
//    RecyclerView rvSections;
//    SectionContentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        sections = new ArrayList<>();
//        adapter = new SectionContentAdapter(sections);
//        rvSections = (RecyclerView) findViewById(R.id.rvGuide);
//        rvSections.setLayoutManager(new GridLayoutManager(this, 1));
//        rvSections.setAdapter(adapter);
//
//        getCurrentCategories();

        // Begin the transaction
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
// Replace the contents of the container with the new fragment
        ft.replace(R.id.placeholder, new Section0Fragment());
// or ft.add(R.id.your_placeholder, new FooFragment());
// Complete the changes added above
        ft.commit();
        setContentView(R.layout.activity_guide_view);
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
//    private void getCurrentCategories() {
//        for(int i = 0; i < NUMBER_OF_SECTIONS; i++){
//            Section section = new Section(SECTION_TITLES[i], SECTION_CONTENT[i], i);
//            sections.add(section);
//            adapter.notifyItemInserted(sections.size() - 1);
//        }
//    }
}

