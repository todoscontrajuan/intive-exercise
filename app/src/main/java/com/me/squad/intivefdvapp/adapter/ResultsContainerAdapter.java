package com.me.squad.intivefdvapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.me.squad.intivefdvapp.R;
import com.me.squad.intivefdvapp.model.User;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ResultsContainerAdapter extends RecyclerView.Adapter<ResultsContainerAdapter.ResultItemViewHolder>{

    private List<User> resultList;
    private Context context;

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
    public ResultItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.image_item, viewGroup, false);
        return new ResultItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ResultItemViewHolder resultItemViewHolder, int i) {
        Picasso.get()
                .load(resultList.get(i).getPicture().getThumbnail())
                .placeholder(R.drawable.image_placeholder)
                .error(R.drawable.no_image_available)
                .fit()
                .into(resultItemViewHolder.userImage);
        resultItemViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO Go to details
            }
        });
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
}

