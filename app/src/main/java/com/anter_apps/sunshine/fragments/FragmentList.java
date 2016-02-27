package com.anter_apps.sunshine.fragments;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.anter_apps.sunshine.R;
import com.anter_apps.sunshine.httpManagment.HttpManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by mostafa on 26/02/16.
 */
public class FragmentList extends Fragment {
    private ArrayAdapter<String> mAdapter;
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

        //initialize adapter
        mAdapter = new ArrayAdapter<String>(
                getActivity(),
                R.layout.list_item_forecast,
                R.id.list_item_forecast_textview,
                weekForecastList);

        ListView listView = (ListView) view.findViewById(R.id.listview_forecast);
        listView.setAdapter(mAdapter);

        new DownloadFilesTask().execute();

        return view;
    }

    private class DownloadFilesTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            try {
                HttpManager.downloadUrl("http://api.openweathermap.org/data/2.5/forecast/daily?lat=27.180037&lon=31.195682&mode=json&units=metric&cnt=7&appid=44db6a862fba0b067b1930da0d769e98");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }


}
