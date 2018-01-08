package com.tdk.thedrunkkeeper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class AboutUs extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        ImageButton mGeo = (ImageButton) findViewById(R.id.about_us_geo_btn);
        ImageButton mFab = (ImageButton) findViewById(R.id.about_us_fab_btn);

        mGeo.setTag(0);
        mFab.setTag(1);

        mGeo.setOnClickListener(this);
        mFab.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        int tag = (int) v.getTag();
        Intent intent = null;

        if (tag == 0){
            intent = new Intent(this, geo.class);
        }

        if (tag == 1){
            intent = new Intent(this, fab.class);
        }

        startActivity(intent);
    }
}
