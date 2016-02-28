package com.anter_apps.sunshine;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.anter_apps.sunshine.fragments.FragmentDetail;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new FragmentDetail())
                    .commit();
        }
    }
}
