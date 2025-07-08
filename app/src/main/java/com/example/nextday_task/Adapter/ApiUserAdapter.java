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
import com.example.nextday_task.R;

import java.util.List;

public class ApiUserAdapter extends RecyclerView.Adapter<ApiUserAdapter.ViewHolder>{
    private List<ApiUser> userList;
    private OnUserActionListener listener;
    private Context context;

    public ApiUserAdapter(List<ApiUser> userList, Context context, OnUserActionListener listener) {
        this.userList = userList;
        this.listener = listener;
        this.context = context;
    }

    public interface OnUserActionListener {
        void onFavorite(ApiUser user);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ApiUser user = userList.get(position);
        holder.email.setText(user.getId() + " " + user.getEmail());
        holder.name.setText(user.getFirst_name() + " " + user.getLast_name());
        Glide.with(context).load(user.getAvatar()).placeholder(R.drawable.ic_user_placeholder).into(holder.avatar);

        holder.favoriteBtn.setOnClickListener(v -> listener.onFavorite(user));
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView avatar;
        TextView email, name;
        Button favoriteBtn;

        ViewHolder(View view) {
            super(view);
            avatar = view.findViewById(R.id.avatar);
            email = view.findViewById(R.id.email);
            name = view.findViewById(R.id.name);
            favoriteBtn = view.findViewById(R.id.favoriteBtn);
        }
    }
    public void updateData(List<ApiUser> users) {
        this.userList = users;
        notifyDataSetChanged();
    }
}
