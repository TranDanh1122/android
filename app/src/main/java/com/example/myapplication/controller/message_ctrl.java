package com.example.myapplication.controller;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.model.anotheruser;
import com.example.myapplication.model.message;

import java.util.List;

public class message_ctrl extends BaseAdapter {
    final private Context context;
    final private int layout;
    final private List<message> lvMessage;

    public message_ctrl(Context context, int layout, List<message> lvMessage) {
        this.context = context;
        this.layout = layout;
        this.lvMessage = lvMessage;
    }

    @Override
    public int getCount() {
        return lvMessage.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView =inflater.inflate(layout,null);
//        Ánh xạ
        TextView mess = (TextView) convertView.findViewById(R.id.message);
//        Gán giá trị
        message anotheruser = lvMessage.get(position);
        mess.setText(anotheruser.getMessageText());

        if(anotheruser.getGravity()=="left"){
            mess.setGravity(Gravity.LEFT);

        }else {
            mess.setGravity(Gravity.RIGHT);

        }

        return convertView;
    }
}
