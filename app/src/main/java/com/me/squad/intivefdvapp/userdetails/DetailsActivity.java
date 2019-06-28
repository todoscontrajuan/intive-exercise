package com.me.squad.intivefdvapp.userdetails;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.me.squad.intivefdvapp.R;
import com.me.squad.intivefdvapp.model.User;
import com.me.squad.intivefdvapp.util.ActivityUtils;

public class DetailsActivity extends AppCompatActivity {

    private DetailsPresenter detailsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        DetailsFragment detailsFragment = (DetailsFragment) getSupportFragmentManager().findFragmentById(R.id.details_content_frame);
        if (detailsFragment == null) {
            // Create the fragment
            detailsFragment = DetailsFragment.newInstance();

            Intent intent = getIntent();
            final User user = (User) intent.getSerializableExtra("selected");

            if (getIntent().hasExtra("selected")) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("selected", user);
                detailsFragment.setArguments(bundle);
            }

            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), detailsFragment, R.id.details_content_frame);
        }

        // Create the presenter
        detailsPresenter = new DetailsPresenter(detailsFragment);
    }
}
