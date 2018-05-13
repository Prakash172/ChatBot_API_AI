package com.erprakash.beerapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private BeerExpert beerExpert ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        beerExpert = new BeerExpert();
    }

    public void onClickFindBeer(View view) {
        TextView display = findViewById(R.id.textView);
        Spinner spinner = findViewById(R.id.spinner);
        String color = String.valueOf(spinner.getSelectedItem());
        ArrayList<String > beer = beerExpert.getBeerExpert(color);
        StringBuffer beers = new StringBuffer();
        clear(display,beers);
        for(int i = 0 ; i < beer.size() ; i++ )
            beers.append(i + 1).append(":").append(beer.get(i)).append("\n");
        display.setText(beers);
        beer.clear();
    }

    private void clear(TextView display,StringBuffer beers) {
        display.setText("");
        beers.setLength(0);

    }
}
