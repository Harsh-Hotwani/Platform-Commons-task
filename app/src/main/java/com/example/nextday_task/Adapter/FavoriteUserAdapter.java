package com.example.nextday_task.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.nextday_task.Modal.ApiUser;
import com.example.nextday_task.Modal.FavoriteUser;
import com.example.nextday_task.R;

import java.util.List;

public class FavoriteUserAdapter extends RecyclerView.Adapter<FavoriteUserAdapter.ViewHolder>{
    private List<FavoriteUser> userList;
    private Context context;
    private OnUnfavoriteClickListener listener;

    public interface OnUnfavoriteClickListener {
        void onUnfavorite(FavoriteUser user);
    }

    public FavoriteUserAdapter(List<FavoriteUser> userList, Context context, OnUnfavoriteClickListener listener) {
        this.userList = userList;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public FavoriteUserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorite_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteUserAdapter.ViewHolder holder, int position) {
        FavoriteUser user = userList.get(position);
        holder.email.setText(user.getId() + " " + user.getEmail());
        holder.name.setText(user.getFirstName() + " " + user.getLastName());
        Glide.with(context).load(user.getAvatar()).into(holder.avatar);

        holder.unfavoriteBtn.setOnClickListener(v -> listener.onUnfavorite(user));
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView avatar;
        TextView email, name;
        Button unfavoriteBtn;

        ViewHolder(View view) {
            super(view);
            avatar = view.findViewById(R.id.avatar);
            email = view.findViewById(R.id.email);
            name = view.findViewById(R.id.name);
            unfavoriteBtn = view.findViewById(R.id.unfavoriteBtn);
        }
    }
    public void updateData(List<FavoriteUser> users) {
        this.userList = users;
        notifyDataSetChanged();
    }

}
