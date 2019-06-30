package com.me.squad.intivefdvapp.userdetails;

import android.support.annotation.NonNull;

import static com.google.common.base.Preconditions.checkNotNull;

public class DetailsPresenter implements DetailsContract.Presenter {

    private final DetailsContract.View detailsView;

    @Override
    public void start() {
        // No action needed, leaving this in case if it's needed in the future
    }

    public DetailsPresenter(@NonNull DetailsContract.View view) {
        detailsView = checkNotNull(view, "View can't be null");
        detailsView.setPresenter(this);
    }
}
