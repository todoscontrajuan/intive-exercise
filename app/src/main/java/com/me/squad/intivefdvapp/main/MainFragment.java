package com.me.squad.intivefdvapp.main;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.me.squad.intivefdvapp.R;
import com.me.squad.intivefdvapp.adapter.ResultsContainerAdapter;
import com.me.squad.intivefdvapp.model.User;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class MainFragment extends Fragment implements MainContract.View {

    private MainContract.Presenter mainPresenter;
    private RecyclerView resultsContainer;
    private ProgressBar progressBar;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        // Setup views
        resultsContainer = root.findViewById(R.id.results_container);
        progressBar = root.findViewById(R.id.progressbar);

        return root;
    }

    @Override
    public void setPresenter(@NonNull MainContract.Presenter presenter) {
        mainPresenter = checkNotNull(presenter, "Presenter can't be null");
    }

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        mainPresenter.start();
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showUnknownErrorToast() {
        Toast.makeText(getContext(), "An unknown error has occurred, try again later", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setupResultList(List<User> users) {
        RecyclerView.LayoutManager manager = new GridLayoutManager(getContext(), 4);
        resultsContainer.setLayoutManager(manager);
        ResultsContainerAdapter adapter = new ResultsContainerAdapter(getContext(), users);
        resultsContainer.setAdapter(adapter);
    }
}
