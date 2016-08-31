package com.example.user.test01;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
/**
 * Created by USER on 2016-08-08.
 */
public class MainActivityListViewAdapter extends ArrayAdapter<MainListViewRows> {

    private Context context;
    private int resource;
    private ArrayList<MainListViewRows> items;

    public MainActivityListViewAdapter(Context context, int resource, ArrayList<MainListViewRows> objects) {
        super(context, resource, objects);
        this.context = context;
        this.items = objects;
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if ( v == null ) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.mainactivity_listview_row, parent, false);
        }

        MainListViewRows mainRows = items.get(position);

        if (mainRows != null) {
            TextView tvType = (TextView) convertView.findViewById(R.id.textview_type_main_activity_listview_row);
            TextView tvDate = (TextView) convertView.findViewById(R.id.textview_date_main_activity_listview_row);
            TextView amount = (TextView) convertView.findViewById(R.id.textview_amount_main_activity_listview_row);
        }

        return convertView;
    }
}
