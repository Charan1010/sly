package com.example.chara.myapplication;

/**
 * Created by chara on 06-03-2017.
 */
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chara on 06-03-2017.
 */

public class UserAdapter extends ArrayAdapter {
    List list=new ArrayList();
    public UserAdapter(Context context, int resource) {
        super(context, resource);
    }

    static class DataHandler
    {
        TextView name;
        TextView age;
       TextView about;
    }
    @Override
    public void add(Object object) {
        Log.d("log","In object add");
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return this.list.size();
    }
    public Object getItem(int position)
    {
        return this.list.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        DataHandler handler=new DataHandler();
        if(row == null) {
            Log.d("log","inside getview");
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=inflater.inflate(R.layout.row_layout,parent,false);
            handler.name=(TextView)row.findViewById(R.id.name);
            handler.age=(TextView)row.findViewById(R.id.age);
            handler.about=(TextView)row.findViewById(R.id.about);
            row.setTag(handler);
        }
        else
        {
            handler=(DataHandler)row.getTag();
        }
        User user;
        user=(User)this.getItem(position);
        handler.name.setText(user.getname());
        handler.age.setText(user.getage());
        handler.about.setText(user.getAbout());
       // handler.date.setText(user.getdate());
       // handler.time.setText(user.gettime());
        return row;

    }
}
