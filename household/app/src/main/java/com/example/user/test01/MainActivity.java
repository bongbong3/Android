package com.example.user.test01;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v4.view.PagerAdapter;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.test01.common.Util;
import com.example.user.test01.memo.MemoActivity;

import java.lang.reflect.Type;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // 로그 변수
    private static final String TAG = "MainActivity";
    // 끝. 로그 변수

    // 다이알로그
    ProgressDialog pDialog;
    // 끝. 다이알로그

    // 로그인 기능
    String id;
    // 끝. 로그인 기능

    // 날짜
    Calendar calendar;
    EditText edtxtDate;
    Calendar myCalendar;
    static final int DATE_DIALOG_ID = 0;

    int intCalYear;
    String strCalYear;
    int intCalMonth;
    String strCalMonth;
    int intCalDay;
    String strCalDay;
    int todayIntYear;
    int todayIntMonth;
    int todayIntDay;
    String todayStrYear;
    String todayStrMonth;
    String todayStrDay;

    int calculateStartYear; // 하단 텍스트뷰 계산에 필요한 날짜 시작일
    int calculateEndYear;   // 하단 텍스트뷰 계산에 필요한 날짜 종료일
    // 끝. 날짜

    // 데이터베이스 컨트롤
    private MySQLiteOpenHelper myHelper;
    String dbName = "test01.db";
    String mainTableName = "household";
    int dbVersion = 2;
    private SQLiteDatabase db;
    String tag = "SQLite";
    Cursor cursor;
    Cursor otherCursor;

    String conditionInput;
    // 끝. 데이터베이스 컨트롤

    // 상단 입력 뷰
    EditText edtxtAmount;
    Button buttonSubmit;
    Spinner spinnerChoice;
    // 끝. 상단 입력 뷰

    // 중단 리스트뷰
    ListView listViewContent;
    // 끝. 중단 리스트뷰


    // 화면 하단 텍스트뷰
    TextView textViewIncome;
    TextView textViewRemain;
    // 끝. 화면 하단 텍스트뷰

    // 측면 리스트뷰에 필요한 변수들
    private ListView lvNavList;
    private FrameLayout flContainer;
    private DrawerLayout dlDrawer;
    List<Item> sideSlideItems;
    ItemListAdapter sideSlideItemListAdapter;
    // 끝. 측면 리스트뷰에 필요한 변수들

    // 측면 네비게이션에 들어갈 클래스
    public class Item {
        Drawable ItemDrawable;
        String ItemString;
        Item(Drawable drawable, String t) {
            ItemDrawable = drawable;
            ItemString = t;
        }
    }// end of item

    // view holder
    static class ViewHolder {
        ImageView icon;
        TextView text;
    }
    // end of view holder

    // listview에 쓰일 baseAdapter 상속
    public class ItemBaseAdapter extends BaseAdapter {

        Context context;
        List<Item> list;

        ItemBaseAdapter(Context c, List<Item> l) {
            context = c;
            list = l;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        public List<Item> getList() {
            return list;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            return null;
        }
    }
    // 끝. listview에 쓰일 baseAdapter 상속

    // listview에 쓰일 ItemListAdapter 클래스
    public class ItemListAdapter extends ItemBaseAdapter {

        ItemListAdapter(Context c, List<Item> l) {
            super(c, l);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup viewGroup) {
            View rowView = convertView;

            // view 재사용
            if (rowView == null) {
                LayoutInflater inflater = ((Activity)context).getLayoutInflater();
                rowView = inflater.inflate(R.layout.row, null);

                ViewHolder viewHolder = new ViewHolder();
                viewHolder.icon = (ImageView)rowView.findViewById(R.id.rowImageView);
                viewHolder.text = (TextView)rowView.findViewById(R.id.rowTextView);
                rowView.setTag(viewHolder);
            }

            ViewHolder holder = (ViewHolder)rowView.getTag();
            holder.icon.setImageDrawable(list.get(position).ItemDrawable);
            holder.text.setText(list.get(position).ItemString);

            holder.text.setBackgroundColor(Color.parseColor("Green"));

            return rowView;
        }
    }
    // 끝. listview에 쓰일 ItemListAdapter 클래스

    // initItem 메소드
    private void initItem() {
        sideSlideItems = new ArrayList<Item>();

        TypedArray arrayDrawable = getResources().obtainTypedArray(R.array.icon);
        TypedArray arrayText = getResources().obtainTypedArray(R.array.text);

        for (int i=0; i<arrayDrawable.length(); i++) {
            Drawable d = arrayDrawable.getDrawable(i);
            String s = arrayText.getString(i);
            Item item = new Item(d, s);
            sideSlideItems.add(item);
        }

        arrayDrawable.recycle();
        arrayText.recycle();
    }
    // 끝. initItem 메소드

    @Override
    public void onBackPressed() {
        if(dlDrawer.isDrawerOpen(lvNavList)) {
            dlDrawer.closeDrawer(lvNavList);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 스피너 구현
        spinnerChoice = (Spinner)findViewById(R.id.spinner_income_cost);
        ArrayList<String> listSpinner = new ArrayList<String>();
        listSpinner.add("용돈");
        listSpinner.add("지출");
        listSpinner.add("기타수입");
        listSpinner.add("기타비용");
        ArrayAdapter<String> adapterSpinner = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, listSpinner);
        spinnerChoice.setAdapter(adapterSpinner);
        // 끝. 스피너 구현


        // 측면 리스트뷰 구현
        dlDrawer = (DrawerLayout)findViewById(R.id.drawer_layout_main);
        lvNavList = (ListView)findViewById(R.id.listview_drawer_main_activity);
        flContainer = (FrameLayout)findViewById(R.id.frame_layout_main_activity_main);

        initItem();

        sideSlideItemListAdapter = new ItemListAdapter(MainActivity.this, sideSlideItems);
        lvNavList.setAdapter(sideSlideItemListAdapter);
        lvNavList.setOnItemClickListener(new DrawerItemClickListener());
        // 끝. 측면 리스트뷰 구현

        // 중단 리스트뷰
        listViewContent = (ListView)findViewById(R.id.listview_main_activity);
        // 끝. 중단 리스트뷰

        // 하단 텍스트뷰
        textViewIncome = (TextView)findViewById(R.id.tv_income);
        textViewRemain = (TextView)findViewById(R.id.tv_remain);

        textViewIncome.setText("용돈 : ");
        textViewRemain.setText("잔고 : ");
        // 끝. 하단 텍스트뷰

        // 데이터베이스 생성
        myHelper = new MySQLiteOpenHelper(
                MainActivity.this, // 현재 화면의 제어권자
                dbName, // db 이름
                null,   // 커서팩토리 null, 표준 커서가 사용됨
                dbVersion   // 버전
        );
        // 끝. 데이터베이스 생성

        try {
            db = myHelper.getWritableDatabase();
        } catch(SQLiteException e) {
            e.printStackTrace();
            Log.e(tag, "데이터베이스를 불러올 수 없음");
            //finish();   // 액티비티 종료
        }// end of try-catch


        // 인풋 에딧 텍스트와 버튼 셋팅하기
        edtxtAmount = (EditText)findViewById(R.id.edtxt_input_amount);
        buttonSubmit = (Button)findViewById(R.id.button_input_submit);
        edtxtDate = (EditText)findViewById(R.id.edtxt_main_date);


        // 오늘 날짜 구하기
        Calendar calendar = Calendar.getInstance();

        intCalYear = calendar.get(Calendar.YEAR);
        strCalYear = Integer.toString(intCalYear);
        todayIntYear = intCalYear;
        todayStrYear = strCalYear;

        intCalMonth = calendar.get(Calendar.MONTH) + 1;
        strCalMonth = Integer.toString(intCalMonth);
        if (intCalMonth < 10) {
            strCalMonth = "0" + Integer.toString(intCalMonth);
        }
        todayIntMonth = intCalMonth;
        todayStrMonth = strCalMonth;

        intCalDay = calendar.get(Calendar.DAY_OF_MONTH);
        strCalDay = Integer.toString(intCalDay);
        if (intCalDay < 10) {
            strCalDay = "0" + Integer.toString(intCalDay);
        }
        todayIntDay = intCalDay;
        todayStrDay = strCalDay;

        String getTodayDate = strCalYear + strCalMonth + strCalDay;

        edtxtDate.setText(strCalYear + " - " + strCalMonth);

            // 달력 셋팅하기
            edtxtDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showDialog(DATE_DIALOG_ID);
                }
            });

            // 끝. 달력 셋팅하기
        // 끝. 오늘 날짜 구하기.

        // 리스트뷰에 해당 날짜의 리스트가 있으면 불러오기
        db = myHelper.getWritableDatabase();
        String todaySql = "SELECT * FROM household WHERE date = " + getTodayDate + " ORDER BY DATE DESC";
        cursor = db.rawQuery(todaySql, null);
        ListView listviewMainActivity = (ListView)findViewById(R.id.listview_main_activity);

        if (cursor.getCount() > 0) {
            MainActivityListViewCursorAdapter mainCursorAdapter = new MainActivityListViewCursorAdapter(
                    MainActivity.this, cursor, true
            );
            listviewMainActivity.setAdapter(mainCursorAdapter);
        }
        // 끝. 리스트뷰 불러오기

    }// end of onCreate


    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int yearSelected, int monthOfYear, int dayOfMonth) {
            intCalYear = yearSelected;
            intCalMonth = monthOfYear + 1;
            intCalDay = dayOfMonth;
            strCalYear = Integer.toString(intCalYear);

            strCalMonth = Integer.toString(intCalMonth);

            if(intCalMonth < 10) {
                strCalMonth = "0" + strCalMonth;
            }

            strCalDay = Integer.toString(intCalDay);

            if(intCalDay < 10) {
                strCalDay = "0" + strCalDay;
            }

            edtxtDate = (EditText)findViewById(R.id.edtxt_main_date);
            edtxtDate.setText(intCalYear + " - " + intCalMonth);
        }
    };

    @Override
    protected Dialog onCreateDialog(int id) {
        switch(id) {
            case DATE_DIALOG_ID :
                DatePickerDialog d1 = new DatePickerDialog(MainActivity.this, mDateSetListener, intCalYear, intCalMonth-1, intCalDay);
                if(Build.VERSION.SDK_INT >= 11) {
                    d1.getDatePicker().setCalendarViewShown(false);
                }
                return d1;
        }
        return null;
    }

    // 금액 입력 버튼
    public void button_submit_onclick(View v) {
        String input = edtxtAmount.getText().toString();
        String choice = spinnerChoice.getSelectedItem().toString();
        conditionInput = input;
        int intConditionInput = 0;
        String totalDate = null;
        String inputSql = null;
        Log.d(TAG, "금액 입력 버튼 클릭");

        try {
            intConditionInput = Integer.parseInt(conditionInput);

            totalDate = strCalYear + strCalMonth + strCalDay;
            Log.d(TAG, "금액 입력 버튼 클릭 안의 try-catch 문");

            if (choice.equals("지출") || choice.equals("기타비용")) {
                inputSql = "INSERT INTO " + mainTableName + " (_id, account, amount, date) VALUES " +
                        "(null, '" + choice + "', " + intConditionInput + ", " + totalDate + ");";
                try {
                    db.execSQL(inputSql);
                } catch(Exception e) {
                    e.printStackTrace();
                    Util.showAlert(MainActivity.this, "DB", "DB 입력에 실패하였습니다.");
                } // 끝. try-catch 문
                Log.d(TAG, totalDate);
                Log.d(TAG, "금액 입력 버튼 클릭 안의 지출, 기타비용 입력폼 실행");

            } else {
                inputSql = "INSERT INTO " + mainTableName + " (_id, account, amount, date) VALUES " +
                        "(null, '" + choice + "', " + intConditionInput + ", " + totalDate + ");";
                try {
                    db.execSQL(inputSql);
                } catch(Exception e) {
                    e.printStackTrace();
                    Util.showAlert(MainActivity.this, "DB", "DB 입력에 실패하였습니다.");
                } // 끝. try-catch 문
                Log.d(TAG, totalDate);
                Log.d(TAG, "금액 입력 버튼 클릭 안의 용돈, 기타수입" +
                        " 입력폼 실행");

            }// 끝. if-else 문


            String strConvertDate;
//            if(intCalMonth < 10) {
//                strConvertDate = intCalYear + "0" + intCalMonth;
//            } else {
//                strConvertDate = intCalYear + "" + intCalMonth;
//            }
            strConvertDate = todayStrYear + todayStrMonth;
            String startConvertDate = strConvertDate + "00";
            String endConvertDate = strConvertDate + "32";
            int intStartConvertDate = Integer.parseInt(startConvertDate);
            int intEndConvertDate = Integer.parseInt(endConvertDate);
            calculateStartYear = intStartConvertDate;
            calculateEndYear = intEndConvertDate;
            String sql = "SELECT _id, account, amount, date FROM " + mainTableName + " WHERE DATE >= " + intStartConvertDate +
                    " AND DATE <= " + intEndConvertDate;
            cursor = db.rawQuery(sql, null);

            if (cursor.getCount() > 0) {
                MainActivityListViewCursorAdapter mainListViewCursorAdapter = new MainActivityListViewCursorAdapter(MainActivity.this, cursor, true);
                listViewContent.setAdapter(mainListViewCursorAdapter);
            }

            String calculateSql = "SELECT account, amount FROM " + mainTableName + " WHERE DATE >= " + calculateStartYear + " AND DATE <= " +
                    calculateEndYear;
            otherCursor = db.rawQuery(calculateSql, null);

            int inAmount = 0;
            int outAmount = 0;
            String strAccount = null;
            String strIn = null;
            String strOut = null;

            if (otherCursor.getCount() > 0) {
                while(otherCursor.moveToNext()) {

                    strAccount = otherCursor.getString(otherCursor.getColumnIndex("account"));
                    if (strAccount.equals("지출") || strAccount.equals("기타비용")) {
                        int getAmount = otherCursor.getInt(otherCursor.getColumnIndex("amount"));
                        outAmount += getAmount;
                    } else {
                        int getAmount = otherCursor.getInt(otherCursor.getColumnIndex("amount"));
                        inAmount += getAmount;
                    }

                }
            }// 끝. cursor.getCount

            textViewIncome = (TextView)findViewById(R.id.tv_income);
            textViewRemain = (TextView)findViewById(R.id.tv_remain);

            int remainAmount = inAmount - outAmount;

            NumberFormat nfAmount = NumberFormat.getInstance();

            String strRemainAmount = nfAmount.format(remainAmount);
            String strInAmount = nfAmount.format(inAmount);
            String strOutAmount = Integer.toString(outAmount);

            textViewIncome.setText(strInAmount);
            textViewRemain.setText(strRemainAmount);



        } catch(Exception e) {
            e.printStackTrace();
            Util.showAlert(MainActivity.this, "입력값", "숫자가아닙니다.");
        }

    }
    // 끝. 금액 입력 버튼

    // 검색 버튼
    public void button_main_search_onclick(View v) {
        ArrayList<String> noneArrayList = new ArrayList<String>();
        noneArrayList.clear();
        ArrayAdapter<String> noneArrayAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,
                noneArrayList);
        listViewContent.setAdapter(noneArrayAdapter);

        String strConvertDate;
        if(intCalMonth < 10) {
            strConvertDate = intCalYear + "0" + intCalMonth;
        } else {
            strConvertDate = intCalYear + "" + intCalMonth;
        }
        String startConvertDate = strConvertDate + "00";
        String endConvertDate = strConvertDate + "32";
        int intStartConvertDate = Integer.parseInt(startConvertDate);
        int intEndConvertDate = Integer.parseInt(endConvertDate);
        calculateStartYear = intStartConvertDate;
        calculateEndYear = intEndConvertDate;
        String sql = "SELECT _id, account, amount, date FROM " + mainTableName + " WHERE DATE >= " + intStartConvertDate + " AND DATE <= " +
        intEndConvertDate;
        cursor = db.rawQuery(sql, null);

        if (cursor.getCount() > 0) {
            MainActivityListViewCursorAdapter mainListViewCursorAdapter = new MainActivityListViewCursorAdapter(MainActivity.this, cursor, true);
            listViewContent.setAdapter(mainListViewCursorAdapter);
        }

        String calculateSql = "SELECT account, amount FROM " + mainTableName + " WHERE DATE >= " + calculateStartYear + " AND DATE <= " +
                calculateEndYear;
        otherCursor = db.rawQuery(calculateSql, null);

        int inAmount = 0;
        int outAmount = 0;
        String strAccount = null;
        String strIn = null;
        String strOut = null;

        if (otherCursor.getCount() > 0) {
            while(otherCursor.moveToNext()) {

                strAccount = otherCursor.getString(otherCursor.getColumnIndex("account"));
                if (strAccount.equals("지출") || strAccount.equals("기타비용")) {
                    int getAmount = otherCursor.getInt(otherCursor.getColumnIndex("amount"));
                    outAmount += getAmount;
                } else {
                    int getAmount = otherCursor.getInt(otherCursor.getColumnIndex("amount"));
                    inAmount += getAmount;
                }

            }
        }// 끝. cursor.getCount

        textViewIncome = (TextView)findViewById(R.id.tv_income);
        textViewRemain = (TextView)findViewById(R.id.tv_remain);

        int remainAmount = inAmount - outAmount;

        NumberFormat nfAmount = NumberFormat.getInstance();

        String strRemainAmount = nfAmount.format(remainAmount);
        String strInAmount = nfAmount.format(inAmount);
        String strOutAmount = Integer.toString(outAmount);

        textViewIncome.setText(strInAmount);
        textViewRemain.setText(strRemainAmount);
    }
    // 끝. 검색 버튼

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK :

                Toast.makeText(MainActivity.this, "뒤로가기를 누르셨습니다.", Toast.LENGTH_SHORT).show();

                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("어플리케이션 종료")
                        .setMessage("어플리케이션을 종료하시겠습니까?")
                        .setPositiveButton("예", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // 프로세스 종료
                                android.os.Process.killProcess(android.os.Process.myPid());
                            }
                        })
                        .setNegativeButton("아니오", null)
                        .show();

                break;
            default :

                break;
        }

        return super.onKeyDown(keyCode, event);

    }// end of onKeyDown

    // 사이드 슬라이드 삽입 리스너
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
            switch(position) {
                case 0 :
                    Intent intent0 = new Intent(MainActivity.this, MainActivity.class);
                    intent0.putExtra("id", id);
                    intent0.putExtra("dbName", dbName);
                    startActivityForResult(intent0, 1);
                    break;
                case 1 :
                    Intent intent3 = new Intent(MainActivity.this, MemoActivity.class);
                    intent3.putExtra("id", id);
                    intent3.putExtra("dbName", dbName);
                    intent3.putExtra("dbVersion", dbVersion);
                    startActivityForResult(intent3, 1);
                    finish();
                    break;

                case 2 :
                    new Fetch_sample_data().execute();
                    break;

                case 3 :
                    myHelper.dropAndRemakeTable(db);
                    break;
                case 4 :
                    break;
                case 5 :

                    break;
            }// end of switch
        }// end of onItemClick
    }// end of DrawerItemClickListener


    private class Fetch_sample_data extends AsyncTask<Void, Void, String> {

        int checkData = 0;
        CreateTestDataInsert ctdi = new CreateTestDataInsert(dbName, mainTableName);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = Util.pDialogShow(MainActivity.this, "데이터 생성 중입니다.", false);
        }// end of onPreExecute

        @Override
        protected String doInBackground(Void... args) {

            try {
                myHelper.insertData(db, mainTableName);
                checkData = myHelper.searchAndCheckData(db, mainTableName);
            } catch(Exception e) {
                e.printStackTrace();
                Log.e("CreateTestDataInsert", "data insert error");
            }

            return null;
        }// end of doInBackground

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            pDialog.dismiss();

            if (checkData == 0) {
                Util.showAlert(MainActivity.this, "Sample DB 생성 실패!", "Sample DB 생성에 실패하였습니다.");
            } else {
                Util.showAlert(MainActivity.this, "Sample DB 생성 성공!", "Sample DB 생성에 성공하였습니다.");
            }

            // 오늘 날짜 구하기
            calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH) + 1;
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            String strYear = Integer.toString(year);
            String strMonth = null;
            if (month < 10) {
                strMonth = "0" + Integer.toString(month);
            } else {
                strMonth = Integer.toString(month);
            }
            String strDay = null;
            if (day < 10) {
                strDay = "0" + Integer.toString(day);
            } else {
                strDay = Integer.toString(day);
            }

            String date = strYear + strMonth + strDay;

//            Cursor todayCursor = null;
//            cursor = ctdi.searchTodayDataList(date);
            int intDate = Integer.parseInt(date);
            String sql = "SELECT * FROM "+ mainTableName +" WHERE date = " + intDate;
            cursor = db.rawQuery(sql, null);

            ArrayList<String> arrayListAccount = new ArrayList<String>();
            ArrayList<String> arrayListAmount = new ArrayList<String>();
            ArrayList<String> arrayListDate = new ArrayList<String>();
            ArrayList<MainListViewRows> arrayListMainListViewRows = new ArrayList<MainListViewRows>();

            String partOfAccount = null;
            String partOfAmount = null;
            String partOfDate = null;

            if (cursor.getCount() > 0) {
                while(cursor.moveToNext()) {
                    partOfAccount = cursor.getString(cursor.getColumnIndex("account"));
                    partOfAmount = cursor.getString(cursor.getColumnIndex("amount"));
                    partOfDate = cursor.getString(cursor.getColumnIndex("date"));

                    MainListViewRows mainListViewRows = new MainListViewRows(partOfAccount, partOfAmount, partOfDate);
                    arrayListMainListViewRows.add(mainListViewRows);
                }
            }// end of todayCursor.getCount

            ArrayAdapter<MainListViewRows> arrayAdapterForMainListView = new ArrayAdapter<MainListViewRows>(MainActivity.this,
                    R.layout.mainactivity_listview_row, arrayListMainListViewRows);
            ListView mainListView = (ListView)findViewById(R.id.listview_main_activity);
            mainListView.setAdapter(arrayAdapterForMainListView);

        } // end of onPostExecute
    }// end of Fetch_sample_data


    // 종료할 때
    // 커서들 닫아주기 위해서
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(cursor != null) {
            if(!(cursor.isClosed())) {
                cursor.close();
            }
        }// cursor 닫기
        if(otherCursor != null) {
            if(!(otherCursor.isClosed())) {
                otherCursor.close();
            }
        }// otherCursor 닫기
        if(db != null) {
            if(db.isOpen()) {
                db.close();
            }
        }// db 닫기

    }// onDestroy
}