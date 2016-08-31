package com.example.user.test01.memo;

/**
 * Created by USER on 2016-08-08.
 */

import android.content.Context;
import android.database.Cursor;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.user.test01.R;


public class MemoListCursorAdapter extends CursorAdapter {

    String forTvTitle = "title";
    String forTvDate = "inputdate";


    public MemoListCursorAdapter(Context context, Cursor cursor) {

        super(context, cursor, true);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.memo_row, viewGroup, false);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView tvDate = (TextView)view.findViewById(R.id.tv_main_memo_list_view_row_date);
        TextView tvTitle = (TextView)view.findViewById(R.id.tv_main_memo_list_view_row_title);

        tvDate.setText(cursor.getString(cursor.getColumnIndex(forTvDate)));
        tvTitle.setText(cursor.getString(cursor.getColumnIndex(forTvTitle)));
    }
}
