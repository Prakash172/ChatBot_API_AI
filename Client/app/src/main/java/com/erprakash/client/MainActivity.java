package com.erprakash.client;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class MainActivity extends Activity {
    Thread m_objThreadClient;
    EditText message;
    Button button;
    RecyclerViewAdapter adapter;
    List<MessageAdapter> data;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        message = findViewById(R.id.editText);
        data = new ArrayList<>();
        button = findViewById(R.id.buttonImageView);
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        adapter = new RecyclerViewAdapter(data, getApplicationContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    public void Start(View view) {
        m_objThreadClient = new Thread(new Runnable() {
            public void run() {


                final Socket socket;
                try{
                    socket = IO.socket("http://192.168.1.1:8080");

                    socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {

                        @Override
                        public void call(Object... args) {
                            socket.emit("chat_initiate", "hi");
                            socket.disconnect();
                        }

                    }).on("message", new Emitter.Listener() {
                        @Override
                        public void call(Object... args) {
                            socket.emit("message", "hi");
                        }

                    }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {

                        @Override
                        public void call(Object... args) {}

                    });
                    socket.connect();

                }
                catch(Exception e){
                    e.printStackTrace();
              }
            }
        });

        m_objThreadClient.start();

    }

}
