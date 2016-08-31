package com.example.user.test01;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by USER on 2016-08-02.
 */
public class CreateTestDataInsert extends Activity {

    String dbName;
    SQLiteDatabase database;
    String tableName = "household";

    public CreateTestDataInsert() {

    }

    public CreateTestDataInsert(String dbName) {
        this.dbName = dbName;
    }

    public CreateTestDataInsert(String dbName, String tableName) {
        this.dbName = dbName;
        this.tableName = tableName;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        openDataBase();

    }

    public void openDataBase() {

        try {
            database = openOrCreateDatabase(dbName, MODE_PRIVATE, null);
//            database = SQLiteDatabase.openOrCreateDatabase(dbName, null);
        } catch(Exception e) {
            e.printStackTrace();
            Log.e("DB Error", "openOrCreateDatabase method error");
        }
    }

    public void insertData() {
        try {
            String sql = "INSERT INTO " + tableName + "(account, amount, date) VALUES " +
                    "('용돈', 100000, 20160801);";
            database.execSQL(sql);
            sql = "INSERT INTO " + tableName + "(account, amount, date) VALUES " +
                    "('지출', 2600, 20160801);";
            database.execSQL(sql);
            sql = "INSERT INTO " + tableName + "(account, amount, date) VALUES " +
                    "('지출', 10000, 20160801);";
            database.execSQL(sql);
            sql = "INSERT INTO " + tableName + "(account, amount, date) VALUES " +
                    "('지출', 2600, 20160802);";
            database.execSQL(sql);
            sql = "INSERT INTO " + tableName + "(account, amount, date) VALUES " +
                    "('지출', 6000, 20160802);";
            database.execSQL(sql);
            sql = "INSERT INTO " + tableName + "(account, amount, date) VALUES " +
                    "('지출', 2600, 20160803);";
            database.execSQL(sql);
            sql = "INSERT INTO " + tableName + "(account, amount, date) VALUES " +
                    "('지출', 6000, 20160803);";
            database.execSQL(sql);
            sql = "INSERT INTO " + tableName + "(account, amount, date) VALUES " +
                    "('지출', 2600, 20160804);";
            database.execSQL(sql);
            sql = "INSERT INTO " + tableName + "(account, amount, date) VALUES " +
                    "('지출', 6000, 20160804);";
            database.execSQL(sql);
            sql = "INSERT INTO " + tableName + "(account, amount, date) VALUES " +
                    "('지출', 1000, 20160804);";
            database.execSQL(sql);
            sql = "INSERT INTO " + tableName + "(account, amount, date) VALUES " +
                    "('지출', 2600, 20160805);";
            database.execSQL(sql);
            sql = "INSERT INTO " + tableName + "(account, amount, date) VALUES " +
                    "('지출', 6000, 20160805);";
            database.execSQL(sql);
            sql = "INSERT INTO " + tableName + "(account, amount, date) VALUES " +
                    "('지출', 3000, 20160805);";
            database.execSQL(sql);
            sql = "INSERT INTO " + tableName + "(account, amount, date) VALUES " +
                    "('지출', 2600, 20160806);";
            database.execSQL(sql);
            sql = "INSERT INTO " + tableName + "(account, amount, date) VALUES " +
                    "('지출', 5000, 20160806);";
            database.execSQL(sql);
            sql = "INSERT INTO " + tableName + "(account, amount, date) VALUES " +
                    "('용돈', 100000, 20160807);";
            database.execSQL(sql);
            sql = "INSERT INTO " + tableName + "(account, amount, date) VALUES " +
                    "('기타비용', 10000, 20160807);";
            database.execSQL(sql);
            sql = "INSERT INTO " + tableName + "(account, amount, date) VALUES " +
                    "('기타수입', 30000, 20160807);";
            database.execSQL(sql);
            sql = "INSERT INTO " + tableName + "(account, amount, date) VALUES " +
                    "('지출', 2600, 20160808);";
            database.execSQL(sql);
            sql = "INSERT INTO " + tableName + "(account, amount, date) VALUES " +
                    "('지출', 2600, 20160809);";
            database.execSQL(sql);
            sql = "INSERT INTO " + tableName + "(account, amount, date) VALUES " +
                    "('지출', 6000, 20160809);";
            database.execSQL(sql);
            sql = "INSERT INTO " + tableName + "(account, amount, date) VALUES " +
                    "('지출', 6000, 20160810);";
            database.execSQL(sql);
            sql = "INSERT INTO " + tableName + "(account, amount, date) VALUES " +
                    "('지출', 2600, 20160810);";
            database.execSQL(sql);
            sql = "INSERT INTO " + tableName + "(account, amount, date) VALUES " +
                    "('지출', 25000, 20160811);";
            database.execSQL(sql);
            sql = "INSERT INTO " + tableName + "(account, amount, date) VALUES " +
                    "('지출', 6000, 20160811);";
            database.execSQL(sql);
            sql = "INSERT INTO " + tableName + "(account, amount, date) VALUES " +
                    "('지출', 2600, 20160811);";
            database.execSQL(sql);
            sql = "INSERT INTO " + tableName + "(account, amount, date) VALUES " +
                    "('지출', 6000, 20160812);";
            database.execSQL(sql);
            sql = "INSERT INTO " + tableName + "(account, amount, date) VALUES " +
                    "('지출', 2600, 20160812);";
            database.execSQL(sql);
            sql = "INSERT INTO " + tableName + "(account, amount, date) VALUES " +
                    "('지출', 6000, 20160813);";
            database.execSQL(sql);
            sql = "INSERT INTO " + tableName + "(account, amount, date) VALUES " +
                    "('지출', 2600, 20160813);";
            database.execSQL(sql);
            sql = "INSERT INTO " + tableName + "(account, amount, date) VALUES " +
                    "('용돈', 100000, 20160814);";
            database.execSQL(sql);
            sql = "INSERT INTO " + tableName + "(account, amount, date) VALUES " +
                    "('지출', 30000, 20160814);";
            database.execSQL(sql);
            sql = "INSERT INTO " + tableName + "(account, amount, date) VALUES " +
                    "('지출', 20000, 20160815);";
            database.execSQL(sql);
            sql = "INSERT INTO " + tableName + "(account, amount, date) VALUES " +
                    "('지출', 2600, 20160816);";
            database.execSQL(sql);
            sql = "INSERT INTO " + tableName + "(account, amount, date) VALUES " +
                    "('지출', 6000, 20160816);";
            database.execSQL(sql);
            sql = "INSERT INTO " + tableName + "(account, amount, date) VALUES " +
                    "('지출', 2600, 20160817);";
            database.execSQL(sql);
            sql = "INSERT INTO " + tableName + "(account, amount, date) VALUES " +
                    "('지출', 6000, 20160817);";
            database.execSQL(sql);
            sql = "INSERT INTO " + tableName + "(account, amount, date) VALUES " +
                    "('지출', 5000, 20160817);";
            database.execSQL(sql);
            sql = "INSERT INTO " + tableName + "(account, amount, date) VALUES " +
                    "('지출', 6000, 20160818);";
            database.execSQL(sql);
            sql = "INSERT INTO " + tableName + "(account, amount, date) VALUES " +
                    "('지출', 2600, 20160818);";
            database.execSQL(sql);
            sql = "INSERT INTO " + tableName + "(account, amount, date) VALUES " +
                    "('지출', 6000, 20160819);";
            database.execSQL(sql);
            sql = "INSERT INTO " + tableName + "(account, amount, date) VALUES " +
                    "('지출', 2600, 20160819);";
            database.execSQL(sql);
            sql = "INSERT INTO " + tableName + "(account, amount, date) VALUES " +
                    "('지출', 6000, 20160820);";
            database.execSQL(sql);
            sql = "INSERT INTO " + tableName + "(account, amount, date) VALUES " +
                    "('지출', 2600, 20160820);";
            database.execSQL(sql);
            sql = "INSERT INTO " + tableName + "(account, amount, date) VALUES " +
                    "('지출', 6000, 20160821);";
            database.execSQL(sql);
            sql = "INSERT INTO " + tableName + "(account, amount, date) VALUES " +
                    "('지출', 2600, 20160821);";
            database.execSQL(sql);
            sql = "INSERT INTO " + tableName + "(account, amount, date) VALUES " +
                    "('지출', 12000, 20160821);";
            database.execSQL(sql);
            sql = "INSERT INTO " + tableName + "(account, amount, date) VALUES " +
                    "('지출', 2600, 20160822);";
            database.execSQL(sql);
            sql = "INSERT INTO " + tableName + "(account, amount, date) VALUES " +
                    "('지출', 6000, 20160822);";
            database.execSQL(sql);
            sql = "INSERT INTO " + tableName + "(account, amount, date) VALUES " +
                    "('기타비용', 10000, 20160823);";
            database.execSQL(sql);
            sql = "INSERT INTO " + tableName + "(account, amount, date) VALUES " +
                    "('지출', 6000, 20160823);";
            database.execSQL(sql);
            sql = "INSERT INTO " + tableName + "(account, amount, date) VALUES " +
                    "('지출', 2600, 20160823);";
            database.execSQL(sql);
            sql = "INSERT INTO " + tableName + "(account, amount, date) VALUES " +
                    "('지출', 6000, 20160824);";
            sql = "INSERT INTO " + tableName + "(account, amount, date) VALUES " +
                    "('용돈', 100000, 20160824);";
            database.execSQL(sql);
            sql = "INSERT INTO " + tableName + "(account, amount, date) VALUES " +
                    "('지출', 6000, 20160825);";
            sql = "INSERT INTO " + tableName + "(account, amount, date) VALUES " +
                    "('지출', 2600, 20160825);";
            database.execSQL(sql);
            sql = "INSERT INTO " + tableName + "(account, amount, date) VALUES " +
                    "('지출', 6000, 20160826);";
            database.execSQL(sql);
            sql = "INSERT INTO " + tableName + "(account, amount, date) VALUES " +
                    "('지출', 2600, 20160826);";
            database.execSQL(sql);
            sql = "INSERT INTO " + tableName + "(account, amount, date) VALUES " +
                    "('지출', 6000, 20160827);";
            database.execSQL(sql);
            sql = "INSERT INTO " + tableName + "(account, amount, date) VALUES " +
                    "('지출', 2600, 20160827);";
            database.execSQL(sql);
            sql = "INSERT INTO " + tableName + "(account, amount, date) VALUES " +
                    "('용돈', 100000, 20160828);";
            database.execSQL(sql);
            sql = "INSERT INTO " + tableName + "(account, amount, date) VALUES " +
                    "('기타비용', 25000, 20160828);";
            database.execSQL(sql);
            sql = "INSERT INTO " + tableName + "(account, amount, date) VALUES " +
                    "('기타비용', 10000, 20160829);";
            database.execSQL(sql);
            sql = "INSERT INTO " + tableName + "(account, amount, date) VALUES " +
                    "('지출', 2600, 20160829);";
            database.execSQL(sql);
            sql = "INSERT INTO " + tableName + "(account, amount, date) VALUES " +
                    "('지출', 6000, 20160829);";
            database.execSQL(sql);
            sql = "INSERT INTO " + tableName + "(account, amount, date) VALUES " +
                    "('지출', 2600, 20160830);";
            database.execSQL(sql);
            sql = "INSERT INTO " + tableName + "(account, amount, date) VALUES " +
                    "('지출', 6000, 20160830);";
            database.execSQL(sql);
            sql = "INSERT INTO " + tableName + "(account, amount, date) VALUES " +
                    "('기타수입', 12000, 20160830);";
            database.execSQL(sql);
            sql = "INSERT INTO " + tableName + "(account, amount, date) VALUES " +
                    "('지출', 2600, 20160831);";
            database.execSQL(sql);
            sql = "INSERT INTO " + tableName + "(account, amount, date) VALUES " +
                    "('지출', 12000, 20160831);";
            database.execSQL(sql);


        } catch(Exception e) {
            e.printStackTrace();
            Log.e("DB Error", "insertData Error");
        }
    }// end of insertData

    public int searchAndCheckData() {
        String sql = "SELECT date FROM " + tableName + ";";
        Cursor cursor = database.rawQuery(sql, null);

        int inOrNothing = cursor.getCount();
        int returnData = 0;

        if (inOrNothing > 0) {
            returnData = 1;
        } else if (inOrNothing == 0) {
            returnData = 0;
        } else {
            returnData = 0;
        }

        return returnData;
    }// end of searchAndCheckData


//    public Cursor searchTodayDataList(String date) {
//        Cursor cursor = null;
//
//        int intDate = Integer.parseInt(date);
//
//        String sql = "SELECT account, amount, date FROM household WHERE date = " + intDate;
//
//        try {
//            cursor = database.rawQuery(sql, null);
//        } catch(Exception e) {
//            e.printStackTrace();
//        }
//
//
//        return cursor;
//    }

}
