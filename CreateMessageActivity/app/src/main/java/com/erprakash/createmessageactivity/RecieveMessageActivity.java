package com.erprakash.createmessageactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class RecieveMessageActivity extends AppCompatActivity {

    TextView tv ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recieve_message);
        tv = findViewById(R.id.textView);
        Intent intent = getIntent();
        String message = intent.getStringExtra("Message");
        tv.setText(message);
    }
}
