package upa.jiangnan.care.dbservice;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import upa.jiangnan.care.bean.Advice;
import upa.jiangnan.care.bean.Memo;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DB_Manager_Memo {

	private DatabaseHelper databaseHelper;
	private SQLiteDatabase db;
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH:mm");
	SimpleDateFormat dateFormat_date = new SimpleDateFormat("yy-MM-dd");

	public DB_Manager_Memo(Context context) {
		databaseHelper = new DatabaseHelper(context);
		db = databaseHelper.getWritableDatabase();
	}

	// 遍历advice表，取出所有数据
	public List<Memo> query() {
		List<Memo> memoList = new ArrayList<Memo>();
		Cursor c = db.rawQuery("select * from memo", null);
		while (c.moveToNext()) {
			Memo memo = new Memo();
			memo.setId(c.getInt(c.getColumnIndex("id")));
			memo.setMemo_title(c.getString(c.getColumnIndex("memo_title")));
			memo.setMemo_detail(c.getString(c.getColumnIndex("memo_detail")));
			try {
				if(memo.getMemo_date() != null){
					memo.setMemo_date(dateFormat.parse(c.getString(c.getColumnIndex("memo_date_time"))));
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
			memoList.add(memo);
		}

		return memoList;
	}
	
	//根据id更新一条数据到memo
	public void updateMemoByID(Memo memo){
		ContentValues values = new ContentValues();
		values.put("memo_title", memo.getMemo_title());
		values.put("memo_detail", memo.getMemo_detail());
		values.put("memo_date_time", dateFormat_date.format(memo.getMemo_date()));
		String[] args = {String.valueOf(memo.getId())};
		db.update("memo", values, "id=?",args);
	}
	
	//插入一条数据
	public void insertMemo(String title,String detail){
		ContentValues values = new ContentValues();
		values.put("memo_title", title);
		values.put("memo_detail", detail);
		db.insert("memo", null, values);
	}
	
	//根据id删除
	public void deleteMemoByID(int id){
		String[] args = {String.valueOf(id)};
		db.delete("memo","id=?" , args);
	}


}
