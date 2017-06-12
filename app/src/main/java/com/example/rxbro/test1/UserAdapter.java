package com.example.rxbro.test1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by rxbro on 6/12/2017.
 */

public class UserAdapter extends BaseAdapter {
    private ArrayList<User> users;
    private Context context;

    public UserAdapter(ArrayList<User> users, Context context) {
        this.users = users;
        this.context = context;

    }
    @Override
    public int getCount() {
        return users.size();
    }
    @Override
    public Object getItem(int position) {
        return users.get(position);
    }
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.recycler_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTeg(holder);
        } else {

        }
        User currentUser = (User)getItem(position);
        holder.userInfo.setText(currentUser.getName() + " " + currentUser.getAddress + " " + currentUser.getEmail());
        return convertView;
    }

    private class ViewHolder {
        TextView userInfo;
        public ViewHolder(View view) {
            userInfo = (TextView)view.findViewById(R.id.Nametv);
        }

    }



}
