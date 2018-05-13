package com.erprakash.createmessageactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class CreateMessageActivity extends AppCompatActivity {
    EditText message ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_message);
        message = findViewById(R.id.editText);
    }

    public void sendOnClick(View view) {
//        Intent intent = new Intent(this,RecieveMessageActivity.class);
        String str = message.getText().toString();
        Intent intent= new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT,str);
        Intent chosenIntent = Intent.createChooser(intent,"Sending message");
        startActivity(chosenIntent);
    }
}
