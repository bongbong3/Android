package com.example.user.test01.memo;



import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SampleDBHandler extends SQLiteOpenHelper {


    private String tableName = "notes";

    public SampleDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + tableName +
                " (_id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT NOT NULL, body TEXT, inputdate INTEGER);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE " + tableName;
        db.execSQL(sql);
        onCreate(db);
    }

    public void makeSampleData(SQLiteDatabase db) {
        String sql = "INSERT INTO " + tableName + " (title, body, inputdate) VALUES (" +
                "'샘플1', '좋은 하루입니다.', 20100801)";
        db.execSQL(sql);
        sql = "INSERT INTO " + tableName + " (title, body, inputdate) VALUES (" +
                "'샘플2', '잠은 잘 주무셨습니까?', 20100802)";
        db.execSQL(sql);
        sql = "INSERT INTO " + tableName + " (title, body, inputdate) VALUES (" +
                "'샘플3', '오늘 하루도 행운이 가득하기를.', 20100803)";
        db.execSQL(sql);
        sql = "INSERT INTO " + tableName + " (title, body, inputdate) VALUES (" +
                "'샘플4', '처음 시작할 때의 마음처럼 꾸준하게 나아가기를', 20100804)";
        db.execSQL(sql);
        sql = "INSERT INTO " + tableName + " (title, body, inputdate) VALUES (" +
                "'샘플5', '떠오르는 태양처럼 늘 활기차게 생활할 수 있기를', 20100805)";
        db.execSQL(sql);
        sql = "INSERT INTO " + tableName + " (title, body, inputdate) VALUES (" +
                "'샘플6', '삶이 그대를 속일지라도, 슬퍼하거나 노하지 말라 우울한 날들을 견디면 믿으라, 기쁨의 날이 오리니 " +
                "마음은 미래에 사는 것 현재는 슬픈 것 모든 것은 순간적인 것, 지나가는 것이니 그리고 지나가는 것은 훗날 소중하게 " +
                "되리니', 20100806)";
        db.execSQL(sql);
        sql = "INSERT INTO " + tableName + " (title, body, inputdate) VALUES (" +
                "'샘플7', '경기도 안양시 동안구 시민대로 235 안양시청', 20100807)";
        db.execSQL(sql);
        sql = "INSERT INTO " + tableName + " (title, body, inputdate) VALUES (" +
                "'샘플8', '동해물과 백두산이 마르고 닳도록 하느님이 보우하사 우리나라 만세 무궁화 삼천리 화려강산 대한사람 대한으로" +
                " 길이 보전하세', 20100808)";
        db.execSQL(sql);
        sql = "INSERT INTO " + tableName + " (title, body, inputdate) VALUES (" +
                "'샘플9', '남산 위에 저 소나무 철갑을 두른 듯 바람서리 불변함은 우리 기상일세 무궁화 삼천리 화려강산 대한사람 대한으로" +
                " 길이 보전하세', 20100809)";
        db.execSQL(sql);
        sql = "INSERT INTO " + tableName + " (title, body, inputdate) VALUES (" +
                "'샘플10', '1초는 순식간. 60초가 모이면 1분. 1분도 잠깐. 60분이 모이면 1시간. 1시간은 넉넉. 12시간이 모이면 반나절. " +
                "반나절이면 무언가를 하나 했을 시간. 반나절이 2번 보이면 하루. 일상의 단위. 하루가 7번 지나가면 1주일. 한 주기의 끝. " +
                "약 4주가 모이면 한 달. 무언가를 이루어 나가는데 중간점검하는 시기. 12달이 모이면 1년. 사람의 또 한 번의 생애 중 일부가 " +
                "지나가는 시기', 20100810)";
        db.execSQL(sql);
        sql = "INSERT INTO " + tableName + " (title, body, inputdate) VALUES (" +
                "'샘플11', '잘 못하면 열심히라도 해보자', 20100811)";
        db.execSQL(sql);
        sql = "INSERT INTO " + tableName + " (title, body, inputdate) VALUES (" +
                "'샘플12', '1만 시간을 채우면 전문가가 된다고 한다.', 20100812)";
        db.execSQL(sql);
        sql = "INSERT INTO " + tableName + " (title, body, inputdate) VALUES (" +
                "'샘플13', '1만 시간은 피드백 받아가며 의식을 가지고 하루에 8시간씩 4년을 보내면 되는 시간이라고 한다.', 20100813)";
        db.execSQL(sql);
        sql = "INSERT INTO " + tableName + " (title, body, inputdate) VALUES (" +
                "'샘플14', '하루 8시간을 취침한다고 생각하고 하루의 11시간을 한 가지 목표에 몰두한다고 가정한다면.', 20100814)";
        db.execSQL(sql);
        sql = "INSERT INTO " + tableName + " (title, body, inputdate) VALUES (" +
                "'샘플15', '약 2년 6개월이면 전문가의 반열에 올라설 수 있다는 계산이 나온다.', 20100815)";
        db.execSQL(sql);
        sql = "INSERT INTO " + tableName + " (title, body, inputdate) VALUES (" +
                "'샘플16', '늘 그렇듯이 말은 쉽고 예상이란 빗나가라고 존재하는 것이다.', 20100816)";
        db.execSQL(sql);
        sql = "INSERT INTO " + tableName + " (title, body, inputdate) VALUES (" +
                "'샘플17', '뛰어난 예체능인은 하루를 그렇게 알차게 보내서 프로가 되나보다.', 20100817)";
        db.execSQL(sql);
        sql = "INSERT INTO " + tableName + " (title, body, inputdate) VALUES (" +
                "'샘플18', '나도 프로가 되자.', 20100818)";
        db.execSQL(sql);

    }
}
