package br.com.module.exchange.local.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.module.exchange.local.Connection;
import br.com.module.exchange.model.History;

public class HistoryDAO {

    private Connection connection;

    private SQLiteDatabase sqliteDatabase;

    public HistoryDAO(Context context){
        this.connection = new Connection(context);
        this.sqliteDatabase = connection.getWritableDatabase();
    }

    public long insert(History history){
        ContentValues contentValues = new ContentValues();
        Cursor cursor = sqliteDatabase.query("HISTORY", new String[]{"id", "fromConvert", "toConvert", "result", "date", "hourMinuteAndSecond"}, null, null, null, null, null);
        contentValues.put("fromConvert", history.getFrom());
        contentValues.put("toConvert", history.getTo());
        contentValues.put("result", history.getResult());
        contentValues.put("date", history.getDate());
        contentValues.put("hourMinuteAndSecond", history.getHourMinuteAndSeconds());
        return sqliteDatabase.insert("HISTORY", null, contentValues);
    }

    public List<History> loadHistory(){
        List<History> mListHistory = new ArrayList<>();
        mListHistory.clear();
        Cursor cursor = sqliteDatabase.query("HISTORY", new String[]{"id", "fromConvert", "toConvert", "result", "date", "hourMinuteAndSecond"}, null, null, null, null, null);
        int i = 0;
        while(cursor.moveToNext()){
            History historyCursor = new History();

            historyCursor.setId(cursor.getInt(0));
            historyCursor.setFrom(cursor.getString(1));
            historyCursor.setTo(cursor.getString(2));
            historyCursor.setResult(cursor.getString(3));
            historyCursor.setDate(cursor.getString(4));
            historyCursor.setHourMinuteAndSeconds(cursor.getString(5));
            mListHistory.add(historyCursor);
        }
        return mListHistory;
    }
}
