package upa.jiangnan.care.dbservice;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "care.db";
	private static final int DATABASE_VERSION = 1;

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	public DatabaseHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.i("CARE", "create database!");
		String sqlString_nurse = "create table nurse(id INTEGER PRIMARY KEY AUTOINCREMENT,name varchar(20),onwork Integer,domain varchar(20),work_position varchar(20),last_calltime varchar(20))";
		db.execSQL(sqlString_nurse);
		String sqlString_patient = "create table patient(id INTEGER PRIMARY KEY AUTOINCREMENT,name varchar(20),age Integer,sex Integer,room_bed_num varchar(20),hospital_num varchar(20),in_hospital_time varchar(20),to_doctor_name varchar(20),cure_detail varchar(20))";
		db.execSQL(sqlString_patient);
		String sqlString_advice = "create table advice(id INTEGER PRIMARY KEY AUTOINCREMENT,advice_title varchar(20),detail varchar(20),long_short Integer,frequence varchar(20),from_time varchar(20),to_time varchar(20),execute_ok Integer)";
		db.execSQL(sqlString_advice);
		String sqlString_memo = "create table memo(id INTEGER PRIMARY KEY AUTOINCREMENT,memo_title varchar(20),memo_detail varchar(20),memo_date_time DATETIME)";
		db.execSQL(sqlString_memo);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.i("CARE", "upgrade database!");
	}

	@Override
	public void onOpen(SQLiteDatabase db) {
		super.onOpen(db);
		Log.i("CARE", "open database!");
	}

}
