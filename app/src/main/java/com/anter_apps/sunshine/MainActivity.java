package com.anter_apps.sunshine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.anter_apps.sunshine.fragments.FragmentList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new FragmentList())
                    .commit();
        }
    }
}
