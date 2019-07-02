package com.me.squad.intivefdvapp.adapter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.me.squad.intivefdvapp.R;
import com.me.squad.intivefdvapp.model.User;
import com.me.squad.intivefdvapp.userdetails.DetailsActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ResultsContainerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private static final int ITEM = 0;
    private static final int LOADING = 1;

    private List<User> resultList;
    private Context context;

    private boolean isLoadingAdded = false;

    /**
     * @param context    App context
     * @param resultList List of users that will be shown in the RecyclerView
     */
    public ResultsContainerAdapter(Context context, List<User> resultList) {
        this.resultList = resultList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case ITEM:
                View v1 = inflater.inflate(R.layout.image_item, parent, false);
                viewHolder = new ResultItemViewHolder(v1);
                break;
            case LOADING:
                View v2 = inflater.inflate(R.layout.progress_item, parent, false);
                viewHolder = new LoadingViewHolder(v2);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case ITEM:
                final ResultItemViewHolder resultItemViewHolder = (ResultItemViewHolder) holder;
                Picasso.get()
                        .load(resultList.get(position).getPicture().getThumbnail())
                        .placeholder(R.drawable.image_placeholder)
                        .error(R.drawable.no_image_available)
                        .fit()
                        .into(resultItemViewHolder.userImage);
                resultItemViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ActivityOptions options = null;
                        options = ActivityOptions.makeSceneTransitionAnimation((Activity) context, resultItemViewHolder.userImage, context.getString(R.string.picture_transition_name));
                        Intent intent = new Intent(context, DetailsActivity.class);
                        intent.putExtra("selected", resultList.get(resultItemViewHolder.getAdapterPosition()));
                        context.startActivity(intent, options.toBundle());
                    }
                });
                break;
            case LOADING:
                // Do Nothing
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return (position == resultList.size() - 1 && isLoadingAdded) ? LOADING : ITEM;
    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }

    public static class ResultItemViewHolder extends RecyclerView.ViewHolder {
        ImageView userImage;

        ResultItemViewHolder(View itemView) {
            super(itemView);
            userImage = itemView.findViewById(R.id.image_thumbnail);
        }
    }

    public static class LoadingViewHolder extends RecyclerView.ViewHolder {
        LoadingViewHolder(View itemView) {
            super(itemView);
        }
    }

    public void addAll(List<User> users) {
        for (User user : users) {
            add(user);
        }
    }

    public void addLoadingFooter() {
        isLoadingAdded = true;
        add(new User());
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = resultList.size() - 1;
        User item = getItem(position);

        if (item != null) {
            resultList.remove(position);
            notifyItemRemoved(position);
        }
    }

    private void add(User user) {
        resultList.add(user);
        notifyItemInserted(resultList.size() - 1);
    }

    private User getItem(int position) {
        return resultList.get(position);
    }

}

