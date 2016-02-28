package com.anter_apps.sunshine.fragments;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.anter_apps.sunshine.DetailActivity;
import com.anter_apps.sunshine.R;
import com.anter_apps.sunshine.httpManagment.HttpManager;
import com.anter_apps.sunshine.parser.JsonParser;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by mostafa on 26/02/16.
 */
public class FragmentList extends Fragment {
    private ArrayAdapter<String> mAdapter;
    private static String[] forecastList;
    public FragmentList(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // to handle menu event
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.forecastfragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_refresh){
            new DownloadFilesTask().execute();
            return true;
        }
        return super.onOptionsItemSelected(item);
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

        //initialize adapter and add it to list view
        mAdapter = new ArrayAdapter<String>(
                getActivity(),
                R.layout.list_item_forecast,
                R.id.list_item_forecast_textview,
                weekForecastList);

        ListView listView = (ListView) view.findViewById(R.id.listview_forecast);
        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra(Intent.EXTRA_TEXT, mAdapter.getItem(position));
                startActivity(intent);
            }
        });


        return view;
    }

    private class DownloadFilesTask extends AsyncTask<Void, Void, String[]> {
        @Override
        protected String[] doInBackground(Void... params) {
            try {
                String[] list = new JsonParser().getWeatherDataFromJson(HttpManager.downloadUrl("http://api.openweathermap.org/data/2.5/forecast/daily?q=Asyut&cnt=7&mode=json&units=metrics&appid=44db6a862fba0b067b1930da0d769e98"), 7);
                return list;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }

        }

        @Override
        protected void onPostExecute(String[] strings) {
            super.onPostExecute(strings);
            if(strings != null){
                mAdapter.clear();
                for (String s:strings){
                    mAdapter.add(s);
                }
            }
        }
    }


}
