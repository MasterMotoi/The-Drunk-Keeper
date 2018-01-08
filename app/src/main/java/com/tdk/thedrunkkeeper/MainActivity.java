package com.tdk.thedrunkkeeper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button mbroTexting = (Button) findViewById(R.id.main_activity_bro_texting_btn);
        Button mdangerZone = (Button) findViewById(R.id.main_activity_danger_zone_btn);
        Button mbt3 = (Button) findViewById(R.id.main_activity_3_btn);
        Button mbt4 = (Button) findViewById(R.id.main_activity_4_btn);
        Button mbt5 = (Button) findViewById(R.id.main_activity_5_btn);
        Button mabout = (Button) findViewById(R.id.main_activity_about_us_btn);

        mbroTexting.setOnClickListener(this);
        mdangerZone.setOnClickListener(this);
        mbt3.setOnClickListener(this);
        mbt4.setOnClickListener(this);
        mbt5.setOnClickListener(this);
        mabout.setOnClickListener(this);

        mbroTexting.setTag(1);
        mdangerZone.setTag(2);
        mbt3.setTag(3);
        mbt4.setTag(4);
        mbt5.setTag(5);
        mabout.setTag(6);


    }

    @Override
    public void onClick(View view) {

            int index = (int) view.getTag();
            Intent intent = null;

            if (index == 1){
                intent = new Intent(this, BroTexting.class);
                startActivity(intent);
            }

            if (index == 2){
                Toast.makeText(this, "DANGER ZOOOOOOOONE", Toast.LENGTH_SHORT).show();
            }

            if (index == 3){
                Toast.makeText(this, "Bouton 3", Toast.LENGTH_SHORT).show();

            }

            if (index == 4){
                Toast.makeText(this, "Bouton 4", Toast.LENGTH_SHORT).show();

            }

            if (index == 5){
                Toast.makeText(this, "Bouton 5", Toast.LENGTH_SHORT).show();

            }

            if (index == 6){
                intent = new Intent(this, AboutUs.class);
                startActivity(intent);
            }

    }
}
