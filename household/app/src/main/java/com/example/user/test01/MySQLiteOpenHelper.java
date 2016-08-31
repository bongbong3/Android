package com.example.user.test01;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by USER on 2016-07-26.
 */
public class MySQLiteOpenHelper extends SQLiteOpenHelper {


    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void dropAndRemakeTable(SQLiteDatabase db) {
        String sql = "drop table household";

        String makeSql = "CREATE TABLE household(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "account TEXT, " +
                "amount INTEGER, " +
                "date INTEGER" +
                ");";

        db.execSQL(sql);
        db.execSQL(makeSql);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE household(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "account TEXT, " +
                "amount INTEGER, " +
                "date INTEGER" +
                ");";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "drop table household;"; // 테이블 드랍
        db.execSQL(sql);
        onCreate(db); // 다시 테이블 생성
    }

    public void insertData(SQLiteDatabase db, String tableName) {
        try {
            String sql = "INSERT INTO " + tableName + " VALUES " +
                    "(null, '용돈', 100000, 20160701);";
            db.execSQL(sql);
            sql = "INSERT INTO " + tableName + " VALUES " +
                    "(null, '지출', 2600, 20160701);";
            db.execSQL(sql);
            sql = "INSERT INTO " + tableName + " VALUES " +
                    "(null, '지출', 10000, 20160701);";
            db.execSQL(sql);
            sql = "INSERT INTO " + tableName + " VALUES " +
                    "(null, '지출', 2600, 20160702);";
            db.execSQL(sql);
            sql = "INSERT INTO " + tableName + " VALUES " +
                    "(null, '지출', 6000, 20160702);";
            db.execSQL(sql);
            sql = "INSERT INTO " + tableName + " VALUES " +
                    "(null, '지출', 2600, 20160703);";
            db.execSQL(sql);
            sql = "INSERT INTO " + tableName + " VALUES " +
                    "(null, '지출', 6000, 20160703);";
            db.execSQL(sql);
            sql = "INSERT INTO " + tableName + " VALUES " +
                    "(null, '지출', 2600, 20160704);";
            db.execSQL(sql);
            sql = "INSERT INTO " + tableName + " VALUES " +
                    "(null, '지출', 6000, 20160704);";
            db.execSQL(sql);
            sql = "INSERT INTO " + tableName + " VALUES " +
                    "(null, '지출', 1000, 20160704);";
            db.execSQL(sql);
            sql = "INSERT INTO " + tableName + " VALUES " +
                    "(null, '지출', 2600, 20160705);";
            db.execSQL(sql);
            sql = "INSERT INTO " + tableName + " VALUES " +
                    "(null, '지출', 6000, 20160705);";
            db.execSQL(sql);
            sql = "INSERT INTO " + tableName + " VALUES " +
                    "(null, '지출', 3000, 20160705);";
            db.execSQL(sql);
            sql = "INSERT INTO " + tableName + " VALUES " +
                    "(null, '지출', 2600, 20160706);";
            db.execSQL(sql);
            sql = "INSERT INTO " + tableName + " VALUES " +
                    "(null, '지출', 5000, 20160706);";
            db.execSQL(sql);
            sql = "INSERT INTO " + tableName + " VALUES " +
                    "(null, '용돈', 100000, 20160707);";
            db.execSQL(sql);
            sql = "INSERT INTO " + tableName + " VALUES " +
                    "(null, '기타비용', 10000, 20160707);";
            db.execSQL(sql);
            sql = "INSERT INTO " + tableName + " VALUES " +
                    "(null, '기타수입', 30000, 20160707);";
            db.execSQL(sql);
            sql = "INSERT INTO " + tableName + " VALUES " +
                    "(null, '지출', 2600, 20160708);";
            db.execSQL(sql);
            sql = "INSERT INTO " + tableName + " VALUES " +
                    "(null, '지출', 2600, 20160709);";
            db.execSQL(sql);
            sql = "INSERT INTO " + tableName + " VALUES " +
                    "(null, '지출', 6000, 20160709);";
            db.execSQL(sql);
            sql = "INSERT INTO " + tableName + " VALUES " +
                    "(null, '지출', 6000, 20160710);";
            db.execSQL(sql);
            sql = "INSERT INTO " + tableName + " VALUES " +
                    "(null, '지출', 2600, 20160710);";
            db.execSQL(sql);
            sql = "INSERT INTO " + tableName + " VALUES " +
                    "(null, '지출', 25000, 20160711);";
            db.execSQL(sql);
            sql = "INSERT INTO " + tableName + " VALUES " +
                    "(null, '지출', 6000, 20160711);";
            db.execSQL(sql);
            sql = "INSERT INTO " + tableName + " VALUES " +
                    "(null, '지출', 2600, 20160711);";
            db.execSQL(sql);
            sql = "INSERT INTO " + tableName + " VALUES " +
                    "(null, '지출', 6000, 20160712);";
            db.execSQL(sql);
            sql = "INSERT INTO " + tableName + " VALUES " +
                    "(null, '지출', 2600, 20160712);";
            db.execSQL(sql);
            sql = "INSERT INTO " + tableName + " VALUES " +
                    "(null, '지출', 6000, 20160713);";
            db.execSQL(sql);
            sql = "INSERT INTO " + tableName + " VALUES " +
                    "(null, '지출', 2600, 20160713);";
            db.execSQL(sql);
            sql = "INSERT INTO " + tableName + " VALUES " +
                    "(null, '용돈', 100000, 20160714);";
            db.execSQL(sql);
            sql = "INSERT INTO " + tableName + " VALUES " +
                    "(null, '지출', 30000, 20160714);";
            db.execSQL(sql);
            sql = "INSERT INTO " + tableName + " VALUES " +
                    "(null, '지출', 20000, 20160715);";
            db.execSQL(sql);
            sql = "INSERT INTO " + tableName + " VALUES " +
                    "(null, '지출', 2600, 20160716);";
            db.execSQL(sql);
            sql = "INSERT INTO " + tableName + " VALUES " +
                    "(null, '지출', 6000, 20160716);";
            db.execSQL(sql);
            sql = "INSERT INTO " + tableName + " VALUES " +
                    "(null, '지출', 2600, 20160717);";
            db.execSQL(sql);
            sql = "INSERT INTO " + tableName + " VALUES " +
                    "(null, '지출', 6000, 20160717);";
            db.execSQL(sql);
            sql = "INSERT INTO " + tableName + " VALUES " +
                    "(null, '지출', 5000, 20160717);";
            db.execSQL(sql);
            sql = "INSERT INTO " + tableName + " VALUES " +
                    "(null, '지출', 6000, 20160718);";
            db.execSQL(sql);
            sql = "INSERT INTO " + tableName + " VALUES " +
                    "(null, '지출', 2600, 20160718);";
            db.execSQL(sql);
            sql = "INSERT INTO " + tableName + " VALUES " +
                    "(null, '지출', 6000, 20160719);";
            db.execSQL(sql);
            sql = "INSERT INTO " + tableName + " VALUES " +
                    "(null, '지출', 2600, 20160719);";
            db.execSQL(sql);
            sql = "INSERT INTO " + tableName + " VALUES " +
                    "(null, '지출', 6000, 20160720);";
            db.execSQL(sql);
            sql = "INSERT INTO " + tableName + " VALUES " +
                    "(null, '지출', 2600, 20160720);";
            db.execSQL(sql);
            sql = "INSERT INTO " + tableName + " VALUES " +
                    "(null, '지출', 6000, 20160721);";
            db.execSQL(sql);
            sql = "INSERT INTO " + tableName + " VALUES " +
                    "(null, '지출', 2600, 20160721);";
            db.execSQL(sql);
            sql = "INSERT INTO " + tableName + " VALUES " +
                    "(null, '지출', 12000, 20160721);";
            db.execSQL(sql);
            sql = "INSERT INTO " + tableName + " VALUES " +
                    "(null, '지출', 2600, 20160722);";
            db.execSQL(sql);
            sql = "INSERT INTO " + tableName + " VALUES " +
                    "(null, '지출', 6000, 20160722);";
            db.execSQL(sql);
            sql = "INSERT INTO " + tableName + " VALUES " +
                    "(null, '기타비용', 10000, 20160723);";
            db.execSQL(sql);
            sql = "INSERT INTO " + tableName + " VALUES " +
                    "(null, '지출', 6000, 20160723);";
            db.execSQL(sql);
            sql = "INSERT INTO " + tableName + " VALUES " +
                    "(null, '지출', 2600, 20160723);";
            db.execSQL(sql);
            sql = "INSERT INTO " + tableName + " VALUES " +
                    "(null, '지출', 6000, 20160724);";
            db.execSQL(sql);
            sql = "INSERT INTO " + tableName + " VALUES " +
                    "(null, '용돈', 100000, 20160724);";
            db.execSQL(sql);
            sql = "INSERT INTO " + tableName + " VALUES " +
                    "(null, '지출', 6000, 20160725);";
            db.execSQL(sql);
            sql = "INSERT INTO " + tableName + " VALUES " +
                    "(null, '지출', 2600, 20160725);";
            db.execSQL(sql);
            sql = "INSERT INTO " + tableName + " VALUES " +
                    "(null, '지출', 6000, 20160726);";
            db.execSQL(sql);
            sql = "INSERT INTO " + tableName + " VALUES " +
                    "(null, '지출', 2600, 20160726);";
            db.execSQL(sql);
            sql = "INSERT INTO " + tableName + " VALUES " +
                    "(null, '지출', 6000, 20160727);";
            db.execSQL(sql);
            sql = "INSERT INTO " + tableName + " VALUES " +
                    "(null, '지출', 2600, 20160727);";
            db.execSQL(sql);
            sql = "INSERT INTO " + tableName + " VALUES " +
                    "(null, '용돈', 100000, 20160728);";
            db.execSQL(sql);
            sql = "INSERT INTO " + tableName + " VALUES " +
                    "(null, '기타비용', 25000, 20160728);";
            db.execSQL(sql);
            sql = "INSERT INTO " + tableName + " VALUES " +
                    "(null, '기타비용', 10000, 20160729);";
            db.execSQL(sql);
            sql = "INSERT INTO " + tableName + " VALUES " +
                    "(null, '지출', 2600, 20160729);";
            db.execSQL(sql);
            sql = "INSERT INTO " + tableName + " VALUES " +
                    "(null, '지출', 6000, 20160729);";
            db.execSQL(sql);
            sql = "INSERT INTO " + tableName + " VALUES " +
                    "(null, '지출', 2600, 20160730);";
            db.execSQL(sql);
            sql = "INSERT INTO " + tableName + " VALUES " +
                    "(null, '지출', 6000, 20160730);";
            db.execSQL(sql);
            sql = "INSERT INTO " + tableName + " VALUES " +
                    "(null, '기타수입', 12000, 20160730);";
            db.execSQL(sql);
            sql = "INSERT INTO " + tableName + " VALUES " +
                    "(null, '지출', 2600, 20160731);";
            db.execSQL(sql);
            sql = "INSERT INTO " + tableName + " VALUES " +
                    "(null, '지출', 12000, 20160731);";
            db.execSQL(sql);


        } catch(Exception e) {
            e.printStackTrace();
            Log.e("DB Error", "insertData Error");
        }
    }// end of insertData

    public int searchAndCheckData(SQLiteDatabase db, String tableName) {
        String sql = "SELECT date FROM " + tableName + ";";
        Cursor cursor = db.rawQuery(sql, null);

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

}
