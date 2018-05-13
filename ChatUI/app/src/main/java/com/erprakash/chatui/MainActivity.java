package com.erprakash.chatui;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ai.api.AIDataService;
import ai.api.AIListener;
import ai.api.AIServiceException;
import ai.api.android.AIConfiguration;
import ai.api.android.AIService;
import ai.api.model.AIError;
import ai.api.model.AIRequest;
import ai.api.model.AIResponse;

public class MainActivity extends AppCompatActivity implements AIListener {
    EditText message;
    Button button, speakButton;
    RecyclerViewAdapter adapter;
    List<MessageAdapter> data;
    AIService aiService;
    AIRequest aiRequest;
    AIDataService aiDataService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        data = new ArrayList<>();
        message = findViewById(R.id.editText);
        button = findViewById(R.id.buttonImageView);
        speakButton = findViewById(R.id.speakButton);
        final RecyclerView recyclerView = findViewById(R.id.recyclerview);
        adapter = new RecyclerViewAdapter(data, getApplicationContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        int permission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECORD_AUDIO);

        if (permission != PackageManager.PERMISSION_GRANTED) {
//            Log.i(TAG, "Permission to record denied");
            makeRequest();
        }


        // api.ai work
        final AIConfiguration config = new AIConfiguration("API TOKEN ACCESS",
                AIConfiguration.SupportedLanguages.English,
                AIConfiguration.RecognitionEngine.System);
        aiDataService = new AIDataService(config);
        aiRequest = new AIRequest();


        aiService = AIService.getService(this, config);
        aiService.setListener(this);


        speakButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aiService.startListening();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("StaticFieldLeak")
            @Override
            public void onClick(View view) {
                aiRequest.setQuery(message.getText().toString().trim());
                //aiService.startListening();
                data.add(new MessageAdapter(R.drawable.user_pacific, R.drawable.arrow_bg2, message.getText().toString()));
                adapter.notifyDataSetChanged();
//                adapter.setHasStableIds(true);

                new AsyncTask<AIRequest, Void, AIResponse>() {
                    @Override
                    protected AIResponse doInBackground(AIRequest... requests) {
                        final AIRequest request = requests[0];
                        try {
                            final AIResponse response = aiDataService.request(aiRequest);
                            return response;
                        } catch (AIServiceException e) {
                                Toast.makeText(getParent(),e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                        return null;
                    }

                    @Override
                    protected void onPostExecute(AIResponse aiResponse) {
                        if (aiResponse != null) {
                            String response = aiResponse.getResult().getFulfillment().getSpeech();
                            data.add(new MessageAdapter(R.drawable.user_pratikshya, R.drawable.arrow_bg2, response));

                        }
                    }
                }.execute(aiRequest);
            }
        });

    }

    protected void makeRequest() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.RECORD_AUDIO},
                101);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 101: {

                if (grantResults.length == 0
                        || grantResults[0] !=
                        PackageManager.PERMISSION_GRANTED) {

                    Log.i("MESSAGE", "Permission has been denied by user");
                } else {
                    Log.i("MESSAGE", "Permission has been granted by user");
                }
            }
        }
    }

    // permission handeling over

    @SuppressLint("StaticFieldLeak")
    @Override
    public void onResult(AIResponse response) {
        String listenedText = response.getResult().getResolvedQuery();
        aiRequest.setQuery(listenedText);
        data.add(new MessageAdapter(R.drawable.user_pacific, R.drawable.arrow_bg2, listenedText));
        adapter.notifyDataSetChanged();

        new AsyncTask<AIRequest, Void, AIResponse>() {
            @Override
            protected AIResponse doInBackground(AIRequest... requests) {
                final AIRequest request = requests[0];
                try {
                    final AIResponse response = aiDataService.request(aiRequest);
                    return response;
                } catch (AIServiceException e) {
                    Toast.makeText(getParent(),e.getMessage(),Toast.LENGTH_SHORT).show();
                }
                return null;
            }

            @Override
            protected void onPostExecute(AIResponse aiResponse) {
                if (aiResponse != null) {
                    String response = aiResponse.getResult().getFulfillment().getSpeech();
                    data.add(new MessageAdapter(R.drawable.user_pratikshya, R.drawable.arrow_bg2, response));
                    adapter.notifyDataSetChanged();
                }
            }
        }.execute(aiRequest);
        aiService.stopListening();
    }

    @Override
    public void onError(AIError error) {
        //resultSpace.setText(error.toString());
        Toast.makeText(this, error.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAudioLevel(float level) {

    }

    @Override
    public void onListeningStarted() {
        message.setHint("Listening");
    }

    @Override
    public void onListeningCanceled() {
        aiService.cancel();
    }

    @Override
    public void onListeningFinished() {
        message.setHint("Write a message");
        aiService.stopListening();
    }

}

