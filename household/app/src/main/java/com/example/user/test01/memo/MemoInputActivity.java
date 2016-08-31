package com.example.user.test01.memo;

/**
 * Created by USER on 2016-08-08.
 */

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.test01.R;
import com.example.user.test01.common.Util;

import java.util.Calendar;

public class MemoInputActivity extends AppCompatActivity {

    private String TAG = "MemoInputActivity";

    // 데이터베이스 관련 변수들
    String dbName = "samplenote2";
    SQLiteDatabase db;
    Cursor cursor;
    String tableName = "notes";

    String title, body;
    int inputDate;
    // 끝. 데이터베이스 관련 변수들

    // 날짜 관련 변수들
    int intCalYear, intCalMonth, intCalDay;
    String strCalYear, strCalMonth, strCalDay;
    int todayIntYear, todayIntMonth, todayIntDay;
    String todayStrYear, todayStrMonth, todayStrDay;
    // 끝. 날짜 관련 변수들

    // 에디터박스들
    EditText editTextTitle, editTextBody;
    // 끝. 에디터박스들


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_memo);


        // 데이터베이스 열기
        openDatabase();
        // 끝. 데이터베이스 열기

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
        int getIntTodayDate = Integer.parseInt(getTodayDate);
        inputDate = getIntTodayDate;
        // 오늘 날짜 구하기

    }

    // 데이터베이스 연결
    private void openDatabase() {
        db = openOrCreateDatabase(dbName, MODE_PRIVATE, null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_memo_input, menu);
        Log.d(TAG, "onCreateOptionsMenu - 최초 메뉴키를 눌렀을 때 호출됨");

        return true;
    }// 끝. onCreateOptionsMenu

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(TAG, "onOptionItemSelected - 메뉴 항목을 클릭했을 때 호출됨");

        int id = item.getItemId();

        switch (id) {
            case R.id.menu_memo_list :
                Toast.makeText(MemoInputActivity.this, "메모 리스트가 선택되었습니다.", Toast.LENGTH_SHORT).show();
                Intent intentMemoInput = new Intent(MemoInputActivity.this, MemoActivity.class);
                startActivity(intentMemoInput);
                finish();
                return true;



        }

        return super.onOptionsItemSelected(item);
    }// 끝. onOptionsItemSelected

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK :

                Toast.makeText(MemoInputActivity.this, "뒤로가기를 누르셨습니다.", Toast.LENGTH_SHORT).show();

                new AlertDialog.Builder(MemoInputActivity.this)
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

    // 초기화 버튼
    public void button_memo_input_clear_onclick(View view) {
        editTextTitle = (EditText)findViewById(R.id.edittext_activity_input_memo_title);
        editTextBody = (EditText)findViewById(R.id.edittext_activity_input_memo_content);

        editTextTitle.setText("");
        editTextBody.setText("");
    }

    // 취소 버튼
    public void button_memo_input_cancel_onclick(View view) {
        Intent goBackIntent = new Intent(MemoInputActivity.this, MemoActivity.class);
        startActivity(goBackIntent);
        finish();
    }

    // 입력 버튼
    public void button_memo_input_onclick(View view) {
        String strTitle, strBody = "";
        editTextTitle = (EditText)findViewById(R.id.edittext_activity_input_memo_title);
        editTextBody = (EditText)findViewById(R.id.edittext_activity_input_memo_content);
        strTitle = editTextTitle.getText().toString();
        strBody = editTextBody.getText().toString();
        ContentValues ctValues = new ContentValues();
        ctValues.put("title", strTitle);
        ctValues.put("body", strBody);
        ctValues.put("inputdate", inputDate);
        long result = db.insert(tableName, null, ctValues);

        if (result == -1) {
            Util.showAlert(MemoInputActivity.this, "실패", "메모 저장에 실패했습니다.");
        } else {
            Util.showAlert(MemoInputActivity.this, "성공", "메모 저장에 성공했습니다.");

            Intent returnIntent = new Intent(MemoInputActivity.this, MemoActivity.class);
            startActivity(returnIntent);
            finish();
        }

    }


    // 액티비티가 끝날 때
    @Override
    protected void onDestroy() {

        super.onDestroy();

        if(cursor != null) {
            if(!(cursor.isClosed())) {
                cursor.close();
            }
        }// cursor 닫기

        if(db != null) {
            if(db.isOpen()) {
                db.close();
            }
        }// db 닫기
    }
}
