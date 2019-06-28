package com.me.squad.intivefdvapp.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.me.squad.intivefdvapp.R;
import com.me.squad.intivefdvapp.util.ActivityUtils;

public class MainActivity extends AppCompatActivity {

    private MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainFragment mainFragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (mainFragment == null) {
            // Create the fragment
            mainFragment = MainFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), mainFragment, R.id.contentFrame);
        }

        // Create the presenter
        mainPresenter = new MainPresenter(mainFragment);
    }
}
