package com.me.squad.intivefdvapp.userdetails;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.me.squad.intivefdvapp.R;
import com.me.squad.intivefdvapp.model.User;
import com.squareup.picasso.Picasso;

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

        // Get bundle data
        Bundle bundle = getArguments();
        User user = (User) bundle.getSerializable("selected");

        // Fill user data
        TextView usernameView = root.findViewById(R.id.username);
        usernameView.setText(String.format("%s %s", getString(R.string.username_label), user.getLogin().getUsername()));
        TextView firstNameView = root.findViewById(R.id.user_first_name);
        firstNameView.setText(String.format("%s %s", getString(R.string.first_name_label), user.getName().getFirst()));
        TextView lastNameView = root.findViewById(R.id.user_last_name);
        lastNameView.setText(String.format("%s %s", getString(R.string.last_name_label), user.getName().getLast()));
        TextView emailView = root.findViewById(R.id.user_email);
        emailView.setText(String.format("%s %s", getString(R.string.email_label), user.getEmail()));
        ImageView imageView = root.findViewById(R.id.user_image);
        Picasso.get()
                .load(user.getPicture().getLarge())
                .placeholder(R.drawable.image_placeholder)
                .error(R.drawable.no_image_available)
                .fit()
                .into(imageView);

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
