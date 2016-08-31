package com.example.user.test01.memo;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.test01.MainActivity;
import com.example.user.test01.R;

/**
 * Created by USER on 2016-08-08.
 */
public class MemoViewActivity extends AppCompatActivity {

    private final String TAG = "MemoViewActivity";
    String title, inputDate;

    // 데이터베이스 관련 변수들
    String dbName = "samplenote2";
    SQLiteDatabase db;
    Cursor cursor;
    String tableName = "notes";
    // 끝. 데이터베이스 관련 변수들


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_view);

        // 데이터베이스 열기
        openDatabase();

        // 전달된 인텐트에서 값 얻어오기
        Intent memoIntent = getIntent();
        title = memoIntent.getExtras().getString("title");
        inputDate = memoIntent.getExtras().getString("inputDate");
        String body = memoIntent.getExtras().getString("body");

        // 각 텍스트뷰 매칭
        TextView tvInputDate = (TextView)findViewById(R.id.textview_memo_view_input_date);
        TextView tvTitle = (TextView)findViewById(R.id.textview_memo_view_title);
        TextView tvBody = (TextView)findViewById(R.id.textview_memo_view_body);

        // 각 항목에 값 배정
        tvInputDate.setText(inputDate);
        tvTitle.setText(title);
        tvBody.setText(body);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_memo_view, menu);
        Log.d(TAG, "onCreateOptionsMenu - 최초 메뉴키를 눌렀을 때 호출됨");

        return true;
    }// 끝. onCreateOptionsMenu


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK :

                Toast.makeText(MemoViewActivity.this, "뒤로가기를 누르셨습니다.", Toast.LENGTH_SHORT).show();

                new AlertDialog.Builder(MemoViewActivity.this)
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


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(TAG, "onOptionItemSelected - 메뉴 항목을 클릭했을 때 호출됨");

        int id = item.getItemId();

        switch (id) {
            case R.id.menu_memo_view_input :
                Toast.makeText(MemoViewActivity.this, "메모 쓰기가 선택되었습니다.", Toast.LENGTH_SHORT).show();
                Intent intentMemoInput = new Intent(MemoViewActivity.this, MemoInputActivity.class);
                startActivity(intentMemoInput);
                finish();
                return true;
            case R.id.menu_memo_view_list :
                Toast.makeText(MemoViewActivity.this, "메모 리스트가 선택되었습니다.", Toast.LENGTH_SHORT).show();
                Intent intentMemoList = new Intent(MemoViewActivity.this, MemoActivity.class);
                startActivity(intentMemoList);
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }// 끝. onOptionsItemSelected


    // 데이터베이스 연결
    private void openDatabase() {
        db = openOrCreateDatabase(dbName, MODE_PRIVATE, null);
    }


    public void button_memo_view_delete_onclick(View view) {

        int intInputDate = Integer.parseInt(inputDate);

        String query = "DELETE FROM " + tableName +
                "WHERE title = '" + title + "' AND inputdate = " + intInputDate + ";";

        String where[] = new String[]{title, inputDate};
        int success = db.delete(tableName, "title=? AND inputdate=?", where);

        if (success == 0) {
            Toast.makeText(MemoViewActivity.this, "메모 삭제에 실패했습니다.", Toast.LENGTH_SHORT).show();
        } if (success == 1) {
            Toast.makeText(MemoViewActivity.this, "메모 삭제에 성공했습니다.", Toast.LENGTH_SHORT).show();
        }

        Intent goMemoListIntent = new Intent(MemoViewActivity.this, MemoActivity.class);
        startActivity(goMemoListIntent);
        finish();

    }




    // 프로그램 끝날 때 디비와 커서 닫아주기
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!(cursor == null)) {
            if (!(cursor.isClosed())) {
                cursor.close();
            }
        }
        if (!(db == null)) {
            if (db.isOpen()) {
                db.close();
            }
        }
    }// 끝. onDestroy


}
