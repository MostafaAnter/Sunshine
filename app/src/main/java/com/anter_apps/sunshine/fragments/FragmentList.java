package com.anter_apps.sunshine.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anter_apps.sunshine.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by mostafa on 26/02/16.
 */
public class FragmentList extends Fragment {
    public FragmentList(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        // create some Dummy data
        String[] forecastList = {
                "Today-Sunny-88/63",
                "Tomorrow-Foggy-70/46",
                "Weds-Cloudy-72/63",
                "Thurs-Rainy-64/51",
                "Fri-foggy-70/46",
                "Sat-Sunny-76/48"
        };
        //convert array to list
        List<String> weekForecastList = new ArrayList<>(Arrays.asList(forecastList));

        return view;
    }
}
