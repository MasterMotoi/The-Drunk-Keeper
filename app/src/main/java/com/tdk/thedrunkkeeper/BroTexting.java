package com.tdk.thedrunkkeeper;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class BroTexting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bro_texting);

        EditText name = (EditText) findViewById(R.id.bro_texting_name_in);
        Button sendButton = (Button) findViewById(R.id.bro_texting_send_btn);
        EditText number = (EditText) findViewById(R.id.bro_texting_phone_numb_in);
        final TextView intro = (TextView) findViewById(R.id.bro_texting_intro_txt);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intro.setText("Hello World");
            }
        });

    }
}
