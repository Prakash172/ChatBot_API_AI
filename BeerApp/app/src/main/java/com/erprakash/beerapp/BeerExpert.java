package com.erprakash.beerapp;

import java.util.ArrayList;

public class BeerExpert {
    private ArrayList<String> beers = new ArrayList<String>();
    public  ArrayList<String> getBeerExpert(String color){
        switch (color) {
            case "Green":
                beers.add("Heinnken");
                beers.add("Saigon");
                beers.add("East India Pale Ale");
                beers.add("Gruen");
                beers.add("HopeSlam Ale");
                beers.add("Tuborg");
                beers.add("Terrapin");
                break;
            case "Red":
                beers.add("100. Dale's Pale Ale.");
                beers.add("Breckenridge Vanilla Porter.");
                beers.add("Brooklyn Brewery Lager.");
                beers.add("Surly Brewing Darkness.");
                beers.add("New Belgium Fat Tire.");
                break;
            case "Amber":
                beers.add("Sam Aden Bosten Lager");
                beers.add("Breckenridge Vanilla Porter");
                beers.add("Brooklyn Brewery Lager");
                beers.add("Surly Brewing Darkness");
                beers.add("New Belgium Fat Tire");

                break;
            default:
                beers.add("King Fisher");
                beers.add("Blue Moon");
                beers.add("Bud Light");
                beers.add("Orange Boom");
                break;
        }
        return beers;
    }
}
