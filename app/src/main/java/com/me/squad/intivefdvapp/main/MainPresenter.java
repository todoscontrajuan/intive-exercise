package com.me.squad.intivefdvapp.main;

import android.support.annotation.NonNull;
import android.util.Log;

import com.me.squad.intivefdvapp.model.Results;
import com.me.squad.intivefdvapp.model.User;
import com.me.squad.intivefdvapp.service.RetrofitInstance;
import com.me.squad.intivefdvapp.service.UserService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.google.common.base.Preconditions.checkNotNull;

public class MainPresenter implements MainContract.Presenter {

    private final MainContract.View mainView;
    private List<User> users;

    private static final String TAG = "INTIVEFDV_APP";

    private UserService service = RetrofitInstance.getRetrofitInstance().create(UserService.class);

    @Override
    public void start() {
        loadFirstPage();
    }

    public MainPresenter(@NonNull MainContract.View view) {
        mainView = checkNotNull(view, "View can't be null");
        mainView.setPresenter(this);
    }

    private void loadFirstPage() {
        Call<Results> call = service.getUsers(1);
        call.enqueue(new Callback<Results>() {
            @Override
            public void onResponse(Call<Results> call, Response<Results> response) {
                if (response.body() != null) {
                    users = response.body().getResults();
                    mainView.setupResultList(users);
                } else {
                    Log.d(TAG, "Response body of getUsers was null");
                    mainView.showUnknownErrorToast();
                }
                mainView.hideMainProgressBar();
            }

            @Override
            public void onFailure(Call<Results> call, Throwable t) {
                Log.d(TAG, "Something went wrong. Error: " + t.getMessage());
                mainView.showUnknownErrorToast();
                mainView.hideMainProgressBar();
            }
        });
    }

    @Override
    public void loadNextPage(int page) {
        Call<Results> call = service.getUsers(page);
        call.enqueue(new Callback<Results>() {
            @Override
            public void onResponse(Call<Results> call, Response<Results> response) {
                if (response.body() != null) {
                    mainView.hideGridProgressBar();
                    List<User> newUsers = response.body().getResults();
                    mainView.addNewUsersToAdapter(newUsers);
                } else {
                    Log.d(TAG, "Response body of getUsers was null");
                    mainView.showUnknownErrorToast();
                }
            }

            @Override
            public void onFailure(Call<Results> call, Throwable t) {
                Log.d(TAG, "Something went wrong. Error: " + t.getMessage());
                mainView.showUnknownErrorToast();
            }
        });
    }

}
