package com.example.myapplication.controller;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.model.lastmess;
import com.example.myapplication.model.message;

import java.util.List;

public class lastmess_ctrl extends BaseAdapter {
    final private Context context;
    final private int layout;
    final private List<lastmess> lvMessage;

    public lastmess_ctrl(Context context, int layout, List<lastmess> lvMessage) {
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

        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView mess = (TextView) convertView.findViewById(R.id.lastmess);
        lastmess message = lvMessage.get(position);
        mess.setText(message.getLastmess());
        name.setText(message.getName());


        return convertView;
    }
}
