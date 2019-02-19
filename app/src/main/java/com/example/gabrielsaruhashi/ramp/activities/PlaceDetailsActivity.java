package com.example.gabrielsaruhashi.ramp.activities;

import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import android.support.v7.app.AppCompatActivity;

import android.support.v7.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gabrielsaruhashi.ramp.R;
import com.example.gabrielsaruhashi.ramp.models.Place;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;


public class PlaceDetailsActivity extends AppCompatActivity {

    TextView tvName;
    TextView tvHours;
    TextView tvPhoneNumber;
    TextView tvDescription;
    TextView tvAddress;
    TextView tvAddInfo;
    TextView tvWalkIns;
    String address;
    ShareDialog shareDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_details);
        tvName = findViewById(R.id.tvName);
        tvHours = findViewById(R.id.tvHours);
        tvPhoneNumber = findViewById(R.id.tvPhoneNumber);
        tvDescription = findViewById(R.id.tvDescription);
        tvAddress = findViewById(R.id.tvAddress);
        tvWalkIns = findViewById(R.id.tvWalkIns);
        shareDialog = new ShareDialog(this);

        Place place = Parcels.unwrap(getIntent().getParcelableExtra("places"));
        if (place.getHours().equals("null")) {
            tvHours.setText("Hours: N/A");
        } else {
            tvHours.setText("Hours: " + place.getHours());
        }
        tvName.setText(place.getName());
        tvPhoneNumber.setText(place.getPhoneNumer());
        tvAddress.setText(place.getAddress());
        address = place.getAddress();
        tvDescription.setText(place.getDescription());
        if (place.getWalkIns().equals("null")) {
            tvWalkIns.setText("No walk-ins accepted");
        } else {
            tvWalkIns.setText("Walk-ins welcome");
        }
    }

    public void onClickListen(View v) {
        //https://developers.google.com/maps/documentation/urls/android-intents
        Uri gmmIntentUri = Uri.parse("geo:41.3083,-72.9279?q=" + Uri.encode(address));
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        } else {
            //https://stackoverflow.com/questions/3500197/how-to-display-toast-in-android
            Toast.makeText(this, "Google Map is not installed",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void onShareListen(View v) {
        ShareLinkContent content = new ShareLinkContent.Builder()
                .setContentUrl(Uri.parse("https://developers.facebook.com"))
                .setQuote("HI")
                .build();
        shareDialog.show(content);
    }
}
