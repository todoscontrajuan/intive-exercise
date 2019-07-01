package com.me.squad.intivefdvapp.main;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.me.squad.intivefdvapp.R;
import com.me.squad.intivefdvapp.adapter.ResultsContainerAdapter;
import com.me.squad.intivefdvapp.model.User;
import com.me.squad.intivefdvapp.util.PaginationScrollListener;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class MainFragment extends Fragment implements MainContract.View {

    private MainContract.Presenter mainPresenter;
    private RecyclerView resultsContainer;
    private ProgressBar progressBar;

    private boolean isLoading = false;
    private boolean isLastPage = false;
    // Just loading the first 5 pages
    private int TOTAL_PAGES = 5;
    private int currentPage = 1;

    ResultsContainerAdapter adapter;

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

        mainPresenter.start();

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
    public void hideMainProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showUnknownErrorToast() {
        Toast.makeText(getContext(), "An unknown error has occurred, try again later", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setupResultList(List<User> users) {
        GridLayoutManager manager = new GridLayoutManager(getContext(), 4);
        resultsContainer.setLayoutManager(manager);
        adapter = new ResultsContainerAdapter(getContext(), users);
        resultsContainer.setAdapter(adapter);

        resultsContainer.addOnScrollListener(new PaginationScrollListener(manager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                // Increment page index to load the next one
                currentPage += 1;
                mainPresenter.loadNextPage(currentPage);
            }

            @Override
            public int getTotalPageCount() { return TOTAL_PAGES; }

            @Override
            public boolean isLastPage() { return isLastPage; }

            @Override
            public boolean isLoading() { return isLoading; }
        });

        checkForLastPage();
    }

    @Override
    public void hideGridProgressBar() {
        adapter.removeLoadingFooter();
        isLoading = false;
    }

    @Override
    public void addNewUsersToAdapter(List<User> users) {
        adapter.addAll(users);
        checkForLastPage();
    }

    private void checkForLastPage() {
        if (currentPage != TOTAL_PAGES) {
            adapter.addLoadingFooter();
        } else {
            isLastPage = true;
        }
    }
}
