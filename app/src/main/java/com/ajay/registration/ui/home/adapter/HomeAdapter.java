package com.ajay.registration.ui.home.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ajay.registration.R;
import com.ajay.registration.db.User;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {

    private final ArrayList<User> userList = new ArrayList<>();

    public void addUserList(List<User> list) {
        userList.clear();
        userList.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        User user = userList.get(position);
        holder.tvName.setText(user.getName());
        holder.tvAddress.setText(user.getAddress());
        holder.tvEmail.setText(user.getEmail());
        holder.tvGender.setText(user.getGender());
        holder.tvMobile.setText(user.getMobileNo());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    static class HomeViewHolder extends RecyclerView.ViewHolder {

        TextView tvName,tvMobile,tvGender,tvEmail,tvAddress;
        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvItemName);
            tvMobile = itemView.findViewById(R.id.tvItemMobile);
            tvGender = itemView.findViewById(R.id.tvItemGender);
            tvEmail = itemView.findViewById(R.id.tvItemEmail);
            tvAddress = itemView.findViewById(R.id.tvItemAddress);
        }
    }
}
