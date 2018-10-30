package com.example.gabrielsaruhashi.ramp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.gabrielsaruhashi.ramp.models.Places;

import org.parceler.Parcels;

public class PlaceDetailsActivity extends AppCompatActivity {

    TextView tvName;
    TextView tvHours;
    TextView tvPhoneNumber;
    TextView tvDescription;
    TextView tvAddress;
    TextView tvAddInfo;
    TextView tvWalkIns;
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

        Places place = Parcels.unwrap(getIntent().getParcelableExtra("places"));
        if (place.getHours().equals("null")) {
            tvHours.setText("Hours: N/A");
        } else {
            tvHours.setText("Hours: " + place.getHours());
        }
        tvName.setText(place.getName());
        tvPhoneNumber.setText(place.getPhoneNumer());
        tvAddress.setText(place.getAddress());
        tvDescription.setText(place.getDescription());
        if (place.getWalkIns().equals("null")) {
            tvWalkIns.setText("No walk-ins accepted");
        } else {
            tvWalkIns.setText("Walk-ins welcome");
        }
    }
}
