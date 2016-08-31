package com.example.user.test01.memo;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.user.test01.MainActivity;
import com.example.user.test01.R;

public class MemoActivity extends AppCompatActivity {

    // 로그 관련
    private static final String TAG = "MemoActivity";
    // 로그 관련

    // SQLite DB 관련
    private SampleDBHandler sampleDbHandler;
    private String dbName = "samplenote2";
    private String tableName = "notes";
    private int dbVersion = 1;
    private SQLiteDatabase db = null;
    private Cursor cursor = null;
    private String sqliteDBTag = "SQLite";
    // SQLite DB 관련

    // 메인 메모 UI 관련 변수
    ListView mainMemoListView;
    // 끝. 메인 메모 UI 관련 변수


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo);

//        Intent intent = getIntent();
//        dbName = intent.getExtras().getString("dbName");
//        dbVersion = intent.getExtras().getInt("dbVersion");
//        Log.d(TAG, dbName);

        // db 생성
        sampleDbHandler = new SampleDBHandler(
                MemoActivity.this,
                dbName,
                null,
                dbVersion
        );

        db = sampleDbHandler.getWritableDatabase();
        // 끝. db 생성

        mainMemoListView = (ListView)findViewById(R.id.listview_activity_main_memo_list);

        String sql = "SELECT * FROM " + tableName;

        // 쿼리로 결과 커서에 받아오기
        cursor = db.rawQuery(sql, null);
        MemoListCursorAdapter listAdapter = new MemoListCursorAdapter(MemoActivity.this, cursor);
        mainMemoListView.setAdapter(listAdapter);
        Log.d(TAG, "main memo listview setAdapter 실행 완료");

        mainMemoListView.setOnItemClickListener(new MemoListViewClickListener());


    }// 끝. onCreate

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_memo_list, menu);
        Log.d(TAG, "onCreateOptionsMenu - 최초 메뉴키를 눌렀을 때 호출됨");

        return true;
    }// 끝. onCreateOptionsMenu

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(TAG, "onOptionItemSelected - 메뉴 항목을 클릭했을 때 호출됨");

        int id = item.getItemId();

        switch (id) {
            case R.id.menu_memo_input :
                Toast.makeText(MemoActivity.this, "메모 쓰기가 선택되었습니다.", Toast.LENGTH_SHORT).show();
                Intent intentMemoInput = new Intent(MemoActivity.this, MemoInputActivity.class);
                startActivity(intentMemoInput);
                finish();
                return true;
            case R.id.menu_memo_sample_data_create :
                Toast.makeText(MemoActivity.this, "샘플메모DB 생성이 시작되었습니다.", Toast.LENGTH_SHORT).show();
                new Fetch_SampleMemoDB().execute();
                return true;
            case R.id.menu_memo_list_in_listview :
                mainMemoListView = (ListView)findViewById(R.id.listview_activity_main_memo_list);

                String sql = "SELECT * FROM " + tableName;

                // 쿼리로 결과 커서에 받아오기
                cursor = db.rawQuery(sql, null);
                MemoListCursorAdapter listAdapter = new MemoListCursorAdapter(MemoActivity.this, cursor);
                mainMemoListView.setAdapter(listAdapter);
                Log.d(TAG, "main memo listview setAdapter 실행 완료");
                return true;
            case R.id.menu_go_household_activity :
                Toast.makeText(MemoActivity.this, "가계부로 이동합니다.", Toast.LENGTH_SHORT).show();
                Intent intentHouseHold = new Intent(MemoActivity.this, MainActivity.class);
                startActivity(intentHouseHold);
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }// 끝. onOptionsItemSelected

    private class Fetch_SampleMemoDB extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }// 끝. onPreExecute

        @Override
        protected String doInBackground(Void... voids) {

            try {
                // 샘플 메모 데이터 생성
                sampleDbHandler.makeSampleData(db);
            } catch(Exception e) {
                e.printStackTrace();
                Log.e(sqliteDBTag, "SQLite DB 샘플 메모 DB 생성 중 예외 발생");
            } finally {

            }

            return null;
        }// 끝. doInBackground

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(MemoActivity.this, "샘플 메모 DB 생성 작업이 끝났습니다.", Toast.LENGTH_SHORT).show();
            // 리스트뷰 불러오기
            mainMemoListView = (ListView)findViewById(R.id.listview_activity_main_memo_list);
            // 모든 메모 데이터 불러오는 쿼리
            String sql = "SELECT * FROM " + tableName;

            // 쿼리로 결과 커서에 받아오기
            cursor = db.rawQuery(sql, null);
            try {
                // 커서의 결과가 있다면
                if (cursor.getCount() > 0) {
                    // 커서 아답터 실행
                    MemoListCursorAdapter listAdapter = new MemoListCursorAdapter(MemoActivity.this, cursor);
                    mainMemoListView.setAdapter(listAdapter);
                    Log.d(TAG, "main memo listview setAdapter 실행 완료");
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MemoActivity.this);

                    builder.setTitle("예외 발생")
                            .setMessage("리스트뷰에 추가될 데이터가 조회되지 않았습니다.")
                            .setCancelable(false)
                            .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int whichButton) {
                                    dialogInterface.cancel();
                                }
                            });

                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            } catch(Exception e) {
                e.printStackTrace();
                Log.e(sqliteDBTag, "메인메모 리스트뷰에 뿌려줄 커서 실행에서 예외가 발생");
            } finally {
                cursor.close();
            }
        }// 끝. onPostExecute
    }// 끝. Fetch_SampleMemoDB


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK :

                Toast.makeText(MemoActivity.this, "뒤로가기를 누르셨습니다.", Toast.LENGTH_SHORT).show();

                new AlertDialog.Builder(MemoActivity.this)
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


    // 리스트뷰 클릭 이벤트
    private class MemoListViewClickListener implements AdapterView.OnItemClickListener {

        String forTitle = "title";
        String forDate = "inputdate";
        String forBody = "body";
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
            Cursor viewObject = (Cursor)adapterView.getAdapter().getItem(position);


            String inputDate = viewObject.getString(viewObject.getColumnIndex(forDate));
            String title = viewObject.getString(viewObject.getColumnIndex(forTitle));
            String body = viewObject.getString(viewObject.getColumnIndex(forBody));

            /*Log.d(TAG, title);
            Log.d(TAG, inputDate);
            Log.d(TAG, body);*/

            // 메모 보기 액티비티로 전달
            Intent memoViewIntent = new Intent(MemoActivity.this, MemoViewActivity.class);
            memoViewIntent.putExtra("title", title);
            memoViewIntent.putExtra("inputDate", inputDate);
            memoViewIntent.putExtra("body", body);
            startActivity(memoViewIntent);
            finish();
            // 끝. 메모 보기 액티비티로 전달



        }
    }// 끝. MemoListViewClickListener



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
