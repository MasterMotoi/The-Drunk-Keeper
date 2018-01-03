package com.tdk.thedrunkkeeper;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class BroTexting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bro_texting);

        final EditText name = (EditText) findViewById(R.id.bro_texting_name_in);
        Button sendButton = (Button) findViewById(R.id.bro_texting_send_btn);
        final EditText number = (EditText) findViewById(R.id.bro_texting_phone_numb_in);
        final TextView intro = (TextView) findViewById(R.id.bro_texting_intro_txt);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num = number.getText().toString();
                String nom = name.getText().toString();
                Intent sms = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:"+num));
                sms.putExtra("sms_body", "Salut "+nom+" che passa");
                startActivity(sms);
                //SmsManager manager = SmsManager.getDefault();
                //manager .sendTextMessage(num, null, "Salut "+nom+" c'est la voix qui te parle", null, null);

            }
        });

    }
}
