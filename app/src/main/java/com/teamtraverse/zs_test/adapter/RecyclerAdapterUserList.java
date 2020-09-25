package com.teamtraverse.zs_test.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.teamtraverse.zs_test.R;
import com.teamtraverse.zs_test.models.DataModel;
import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapterUserList extends RecyclerView.Adapter<RecyclerAdapterUserList.ViewHolder> {
    private List<DataModel> listOfUsers;
    private Context context;

    public RecyclerAdapterUserList(List<DataModel> listOfUsers, Context context) {
        this.listOfUsers = listOfUsers;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_recycler_user,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DataModel userDetails = listOfUsers.get(position);
        holder.fullName.setText(userDetails.getOwner().getFullname());
        holder.teamName.setText(userDetails.getOwner().getPrimaryTeam());
        holder.title.setText(userDetails.getEvent().getTitle());
        Glide.with(context).load(userDetails.getPhoto()).centerCrop().into(holder.avatar);
    }

    @Override
    public int getItemCount() {
        return listOfUsers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView fullName,teamName,title;
        ImageView avatar;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            fullName = itemView.findViewById(R.id.fullname);
            teamName = itemView.findViewById(R.id.primaryTeam);
            title = itemView.findViewById(R.id.title);
            avatar = itemView.findViewById(R.id.avatar);
        }
    }
}
