package upa.jiangnan.care.dbservice;

import java.util.ArrayList;
import java.util.List;

import upa.jiangnan.care.bean.Advice;
import upa.jiangnan.care.bean.Memo;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DB_Manager_Advice {

	private DatabaseHelper databaseHelper;
	private SQLiteDatabase db;

	public DB_Manager_Advice(Context context) {
		databaseHelper = new DatabaseHelper(context);
		db = databaseHelper.getWritableDatabase();
	}

	// 遍历advice表，取出所有数据
	public List<Advice> query() {
		List<Advice> adviceList = new ArrayList<Advice>();
		Cursor c = db.rawQuery("select * from advice", null);
		while (c.moveToNext()) {
			Advice advice = new Advice();
			advice.setId(c.getInt(c.getColumnIndex("id")));
			advice.setAdvice_title(c.getString(c.getColumnIndex("advice_title")));
			advice.setDetail(c.getString(c.getColumnIndex("detail")));
			advice.setLong_short(c.getInt(c.getColumnIndex("long_short")));
			advice.setFrequence(c.getString(c
					.getColumnIndex("frequence")));
			advice.setFrom_time(c.getString(c
					.getColumnIndex("from_time")));
			advice.setTo_time(c.getString(c
					.getColumnIndex("to_time")));
			advice.setExecute_ok(c.getInt(c.getColumnIndex("execute_ok")));
			adviceList.add(advice);
		}

		return adviceList;
	}
	
	
	//更新advice 的执行情况 即execute_ok的值
	public void updateAdviceByID(Advice advice){
		ContentValues values = new ContentValues();
		values.put("execute_ok", advice.getExecute_ok());
		String[] args = {String.valueOf(advice.getId())};
		db.update("advice", values, "id=?",args);
	}


}
