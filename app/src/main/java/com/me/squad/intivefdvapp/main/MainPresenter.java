package com.me.squad.intivefdvapp.main;

import android.support.annotation.NonNull;
import android.util.Log;

import static com.google.common.base.Preconditions.checkNotNull;

public class MainPresenter implements MainContract.Presenter {

    private final MainContract.View mainView;

    @Override
    public void start() {
        Log.d("TEST", "Started");
    }

    public MainPresenter(@NonNull MainContract.View view) {
        mainView = checkNotNull(view, "View can't be null");
        mainView.setPresenter(this);
    }
}
