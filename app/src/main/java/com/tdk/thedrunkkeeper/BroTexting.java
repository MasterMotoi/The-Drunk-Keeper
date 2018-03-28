package com.tdk.thedrunkkeeper;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BroTexting extends AppCompatActivity implements View.OnClickListener {

    private static final int PICK_CONTACT_REQUEST = 1;
    private EditText number;
    private String num;
    private Button sendButton;
    private int code;
    private String sCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bro_texting);

        sendButton = (Button) findViewById(R.id.bro_texting_send_btn);
        Button mcontact = (Button) findViewById(R.id.bro_texting_contact_btn);
        this.number = (EditText) findViewById(R.id.bro_texting_phone_numb_in);
        final TextView intro = (TextView) findViewById(R.id.bro_texting_intro_txt);

        sendButton.setTag(0);
        mcontact.setTag(1);

        sendButton.setOnClickListener(this);
        mcontact.setOnClickListener(this);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            Log.d("The Drunk Keeper", "Permission is not granted, requesting");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 123);
            sendButton.setEnabled(false);
        } else {
            Log.d("The Drunk Keeper", "Permission is granted");
        }

    }

    // On the click of either one of the buttons
    @Override
    public void onClick(View v) {
        int tag = (int) v.getTag();

        if (tag == 0) {
            num = this.number.getText().toString();
            randomDigit();
            smsSend(num, sCode);
            Toast.makeText(this, "SMS sent !", Toast.LENGTH_SHORT).show();
        }

        if (tag == 1) {
            pickContact();
        }

    }

    // Send a sms
    private void smsSend(String numero, String digit) {
        SmsManager manager = SmsManager.getDefault();
        manager.sendTextMessage(numero, null, "Hello World ! The code is : " + digit, null, null);
        SharedPreferences preferences = this.getSharedPreferences("com.The-Drunk-Keeper.app", Context.MODE_PRIVATE);
        preferences.edit().putString("digit", digit).apply();
    }

    // Function that allows to choose a contact
    private void pickContact() {
        Intent pickContactIntent = new Intent(Intent.ACTION_PICK, Uri.parse("content://contacts"));
        pickContactIntent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE); // Show user only contacts w/ phone numbers
        startActivityForResult(pickContactIntent, PICK_CONTACT_REQUEST);
    }

    // Function that get the contact and his number
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == PICK_CONTACT_REQUEST) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                // The user picked a contact.
                // The Intent's data Uri identifies which contact was selected.

                Uri contactUri = data.getData();
                // We only need the NUMBER column, because there will be only one row in the result
                String[] projection = {ContactsContract.CommonDataKinds.Phone.NUMBER};

                // Perform the query on the contact to get the NUMBER column
                // We don't need a selection or sort order (there's only one result for the given URI)
                // CAUTION: The query() method should be called from a separate thread to avoid blocking
                // your app's UI thread. (For simplicity of the sample, this code doesn't do that.)
                // Consider using CursorLoader to perform the query.
                Cursor cursor = getContentResolver()
                        .query(contactUri, projection, null, null, null);
                cursor.moveToFirst();

                // Retrieve the phone number from the NUMBER column
                int column = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                num = cursor.getString(column);

                number.setText(num);
            }
        }
    }

    // Ask a permission
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 123) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d("PLAYGROUND", "Permission has been granted");
                sendButton.setEnabled(true);
            } else {
                Log.d("PLAYGROUND", "Permission has been denied or request cancelled");
                sendButton.setEnabled(false);
            }
        }
    }

    // Create a random digit
    protected void randomDigit () {
        code = (int)(Math.random() * 9999);
        sCode = ""+code;
        switch (sCode.length()){
            case 1:
                sCode = "000" + sCode;
                break;
            case 2:
                sCode = "00" + sCode;
                break;
            case 3:
                sCode = "0" + sCode;
                break;
        }
    }
}