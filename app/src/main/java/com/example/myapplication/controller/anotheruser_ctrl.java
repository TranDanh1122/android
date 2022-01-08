package com.example.myapplication.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.model.anotheruser;

import java.util.List;

public class anotheruser_ctrl extends BaseAdapter {
    final private Context context;
    final private int layout;
    final private List<anotheruser> lvResult;

    public anotheruser_ctrl(Context context, int layout, List<anotheruser> lvResult) {
        this.context = context;
        this.layout = layout;
        this.lvResult = lvResult;
    }

    @Override
    public int getCount() {
        return lvResult.size();
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
        ImageView img = (ImageView) convertView.findViewById(R.id.avatar);
        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView id = (TextView) convertView.findViewById(R.id.userid);


//        Gán giá trị
        anotheruser anotheruser = lvResult.get(position);

        name.setText(anotheruser.getName());
        id.setText(anotheruser.getId());
        return convertView;

    }
}
