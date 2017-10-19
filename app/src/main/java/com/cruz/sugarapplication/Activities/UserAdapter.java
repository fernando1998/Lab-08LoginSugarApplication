package com.cruz.sugarapplication.Activities;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cruz.sugarapplication.Model.User;
import com.cruz.sugarapplication.R;

import java.util.List;

/**
 * Created by FERNANDO on 16/10/2017.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder>  {


    private List<User> users;

    public UserAdapter(List<User> users){
        this.users = users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView picture;
        public TextView fullname;
        public TextView email;

        public ViewHolder(View itemView) {
            super(itemView);
            picture = (ImageView) itemView.findViewById(R.id.picture_image);
            fullname = (TextView) itemView.findViewById(R.id.fullname_text);
            email = (TextView) itemView.findViewById(R.id.email_text);
        }
    }

    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(UserAdapter.ViewHolder viewHolder, int position) {
        User user = this.users.get(position);
        viewHolder.fullname.setText(user.getFullname());
        viewHolder.email.setText(user.getEmail());

    }

    @Override
    public int getItemCount() {
        return this.users.size();
    }

}
