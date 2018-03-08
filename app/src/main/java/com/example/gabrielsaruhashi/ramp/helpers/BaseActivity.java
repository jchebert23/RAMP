package com.example.gabrielsaruhashi.ramp.helpers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.gabrielsaruhashi.ramp.R;

public class BaseActivity extends AppCompatActivity {

    protected final void onCreate(Bundle savedInstanceState, int layoutId)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        //getSupportActionBar().setDisplayShowTitleEnabled(false);
        //assert myToolbar != null;
        //myToolbar.setLogo(R.mipmap.logo_big);

    }

}
