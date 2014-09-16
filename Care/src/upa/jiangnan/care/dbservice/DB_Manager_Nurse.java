package upa.jiangnan.care.dbservice;

import java.util.ArrayList;
import java.util.List;

import upa.jiangnan.care.bean.Nurse;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DB_Manager_Nurse {

	private DatabaseHelper databaseHelper;
	private SQLiteDatabase db;

	public DB_Manager_Nurse(Context context) {
		databaseHelper = new DatabaseHelper(context);
		db = databaseHelper.getWritableDatabase();
	}

	// 遍历nurse表，取出所有数据
	public List<Nurse> query() {
		List<Nurse> nurseList = new ArrayList<Nurse>();
		Cursor c = db.rawQuery("select * from nurse", null);
		while (c.moveToNext()) {
			Nurse nurse = new Nurse();
			nurse.setId(c.getInt(c.getColumnIndex("id")));
			nurse.setName(c.getString(c.getColumnIndex("name")));
			nurse.setOnWork(c.getInt(c.getColumnIndex("onwork")));
			nurse.setDomain(c.getString(c.getColumnIndex("domain")));
			nurse.setWork_position(c.getString(c
					.getColumnIndex("work_position")));
			nurse.setLast_calltime(c.getString(c
					.getColumnIndex("last_calltime")));
			nurseList.add(nurse);
		}

		return nurseList;
	}

	public void insertNurseData() {
		// 第一条数据
		ContentValues values_1 = new ContentValues();
		values_1.put("name", "朱美美 ");
		values_1.put("onwork", 1);
		values_1.put("domain", "三楼内科");
		values_1.put("work_position", "VIP病房护士长");
		values_1.put("last_calltime", "12:40 am");
		db.insert("nurse", null, values_1);

		// 第二条数据
		ContentValues values_2 = new ContentValues();
		values_2.put("name", "王小八");
		values_2.put("onwork", 1);
		values_2.put("domain", "三楼内科B区");
		values_2.put("work_position", "护士");
		values_2.put("last_calltime", "10:15 am");
		db.insert("nurse", null, values_2);

		// 第三条数据
		ContentValues values_3 = new ContentValues();
		values_3.put("name", "陈西瓜");
		values_3.put("onwork", 1);
		values_3.put("domain", "三楼内科B区");
		values_3.put("work_position", "护士");
		values_3.put("last_calltime", "昨天");
		db.insert("nurse", null, values_3);
		
		// 第四条数据
		ContentValues values_4 = new ContentValues();
		values_4.put("name", "王欠男");
		values_4.put("onwork", 1);
		values_4.put("domain", "三楼内科B区");
		values_4.put("work_position", "护士");
		values_4.put("last_calltime", "8:25 am");
		db.insert("nurse", null, values_4);
		
		// 第五条数据
		ContentValues values_5 = new ContentValues();
		values_5.put("name", "赵石");
		values_5.put("onwork", 1);
		values_5.put("domain", "三楼内科A区");
		values_5.put("work_position", "护士长");
		values_5.put("last_calltime", "11:25 am");
		db.insert("nurse", null, values_5);
	}

}
