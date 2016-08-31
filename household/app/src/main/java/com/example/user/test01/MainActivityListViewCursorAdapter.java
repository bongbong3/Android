package com.example.user.test01;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by USER on 2016-08-02.
 */
public class MainActivityListViewCursorAdapter extends CursorAdapter {

    private static final String TAG = "MALVCursorAdapter";

    public MainActivityListViewCursorAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.mainactivity_listview_row, viewGroup, false);
        return view;

    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView tvType = (TextView)view.findViewById(R.id.textview_type_main_activity_listview_row);
        TextView tvDate = (TextView)view.findViewById(R.id.textview_date_main_activity_listview_row);
        TextView tvAmount = (TextView)view.findViewById(R.id.textview_amount_main_activity_listview_row);

        java.text.NumberFormat nfAmount = java.text.NumberFormat.getInstance();

//        int intDate = cursor.getInt(cursor.getColumnIndex("date"));
        int intAmount = cursor.getInt(cursor.getColumnIndex("amount"));

        String strAccount = (String)cursor.getString(cursor.getColumnIndex("account"));
        String strDate = (String)cursor.getString(cursor.getColumnIndex("date"));
//        String strAmount = (String)cursor.getString(cursor.getColumnIndex("amount"));
//        String strDate = nfAmount.format(intDate);
        String strAmount = nfAmount.format(intAmount);

//        Log.d(TAG, strAccount);
//        Log.d(TAG, strDate);
//        Log.d(TAG, strAmount);

        tvType.setText(strAccount);
        tvDate.setText(strDate);
        tvAmount.setText(strAmount);

        if (strAccount.equals("지출") || strAccount.equals("기타비용")) {

            tvType.setBackgroundColor(Color.BLUE);
        } else if(strAccount.equals("용돈") || strAccount.equals("기타수입")) {

            tvType.setBackgroundColor(Color.RED);
        }
//        tvType.setText(cursor.getString(cursor.getColumnIndex("account")));
//        tvDate.setText(cursor.getString(cursor.getColumnIndex("date")));
//        tvAmount.setText(cursor.getString(cursor.getColumnIndex("amount")));
    }
}
