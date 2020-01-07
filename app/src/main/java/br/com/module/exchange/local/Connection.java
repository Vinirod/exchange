package br.com.module.exchange.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Connection extends SQLiteOpenHelper {

    private static String name = "banco.db";

    private static int version = 1;

    public Connection(@Nullable Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table HISTORY(id integer primary key autoincrement,"+
                "fromConvert varchar(500), toConvert varchar(500), result varchar(500), date varchar(500), hourMinuteAndSecond varchar(500))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
