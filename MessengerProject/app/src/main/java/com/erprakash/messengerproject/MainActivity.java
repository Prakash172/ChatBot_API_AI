package com.erprakash.messengerproject;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import static android.content.ContentValues.TAG;

public class MainActivity extends Activity implements DataDisplay {
TextView serverMessage;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        serverMessage=(TextView)findViewById(R.id.textView1);
        String ip = Utils.getIPAddress(true);
        Log.i("ip",  ip);
    }

   public void connect(View view)
   {
	    MyServer server= new MyServer();
	   	 server.setEventListener(this);
	   	 server.startListening();

   }
   public void Display(String message)
   {
	   serverMessage.setText(""+message);
   }
}
