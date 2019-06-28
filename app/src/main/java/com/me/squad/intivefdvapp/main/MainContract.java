package com.me.squad.intivefdvapp.main;

import com.me.squad.intivefdvapp.BasePresenter;
import com.me.squad.intivefdvapp.BaseView;
import com.me.squad.intivefdvapp.model.User;

import java.util.List;

public interface MainContract {
    interface View extends BaseView<Presenter> {
        void hideProgressBar();
        void showUnknownErrorToast();
        void setupResultList(List<User> users);
    }

    interface Presenter extends BasePresenter {

    }
}
