package com.me.squad.intivefdvapp.userdetails;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.me.squad.intivefdvapp.R;
import com.me.squad.intivefdvapp.model.User;

import static com.google.common.base.Preconditions.checkNotNull;

public class DetailsFragment extends Fragment implements DetailsContract.View {

    private DetailsContract.Presenter detailsPresenter;

    public DetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_details, container, false);
        Bundle bundle = getArguments();
        User user = (User) bundle.getSerializable("selected");
        TextView textView = root.findViewById(R.id.test_text);
        textView.setText(user.getName().getFirst());
        return root;
    }

    @Override
    public void setPresenter(@NonNull DetailsContract.Presenter presenter) {
        detailsPresenter = checkNotNull(presenter, "Presenter can't be null");
    }

    public static DetailsFragment newInstance() {
        return new DetailsFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        detailsPresenter.start();
    }

}
