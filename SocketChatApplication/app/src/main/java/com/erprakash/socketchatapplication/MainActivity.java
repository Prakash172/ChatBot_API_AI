package com.erprakash.socketchatapplication;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    Button send;
    TextView textView;
    private static Socket socket;
    private static PrintWriter printWriter;
    String message = "";
    private static String IP = "103.95.81.131";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editText);
        send = findViewById(R.id.button);
        textView = findViewById(R.id.clientTextView);
        try {
            new Server().startServer();
            textView.append("Server Started at 5000: \n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //
//    public void serverMessage(View view) throws IOException {
//
//    }
    public void sendMessage(View view) {

        message = editText.getText().toString();
        MyTast mt = new MyTast();
        mt.execute();
        Toast.makeText(this, "message sent", Toast.LENGTH_LONG).show();
    }

    class MyTast extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                socket = new Socket(IP, 5000);
                printWriter = new PrintWriter(socket.getOutputStream());
                printWriter.write(message);
                printWriter.flush();
                printWriter.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public class Server {

        private Socket socket;
        private ServerSocket serverSocket;
        private InputStreamReader isr;
        private BufferedReader br;
        private String message;
        TextView messageRecieved;
        EditText editText;
        Button send;

        void startServer() throws IOException {

            messageRecieved = findViewById(R.id.serverTextView);
            editText = findViewById(R.id.serverEditText);
            send = findViewById(R.id.serverButton);

            serverSocket = new ServerSocket(5000);
            socket = serverSocket.accept();
            while (!message.equals("Exit")) {
                isr = new InputStreamReader(socket.getInputStream());
                br = new BufferedReader(isr);
                message = br.readLine();
                messageRecieved.append("\nClient: " + message + "\n");


            }
            isr.close();
            br.close();
            socket.close();
            serverSocket.close();
        }
    }

}
