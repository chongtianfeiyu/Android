package upa.jiangnan.care.data;

import upa.jiangnan.care.dbservice.DatabaseHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class PatientData {
	
	private DatabaseHelper databaseHelper;
	private SQLiteDatabase db;

	public PatientData(Context context) {
		databaseHelper = new DatabaseHelper(context);
		db = databaseHelper.getWritableDatabase();
	}
	
	public void insertPatientData() {
		// 第一条数据
		ContentValues values_1 = new ContentValues();
		values_1.put("name", "黄小杰");
		values_1.put("age", 45);
		values_1.put("sex", 1);
		values_1.put("room_bed_num", "101-01床");
		values_1.put("hospital_num", "130374-5");
		values_1.put("in_hospital_time", "2014-7-10入院");
		values_1.put("to_doctor_name", "王幸");
		values_1.put("cure_detail", "主要诊断：煤工尘肺壹期  肺痿病");
		db.insert("patient", null, values_1);

		// 第二条数据
		ContentValues values_2 = new ContentValues();
		values_2.put("name", "杨大腿");
		values_2.put("age", 38);
		values_2.put("sex", 1);
		values_2.put("room_bed_num", "101-02床");
		values_2.put("hospital_num", "130632-2");
		values_2.put("in_hospital_time", "2014-7-12入院");
		values_2.put("to_doctor_name", "王幸");
		values_2.put("cure_detail", "主要诊断：水泥尘肺壹期  肺痿病");
		db.insert("patient", null, values_2);
		
		// 第三条数据
		ContentValues values_3 = new ContentValues();
		values_3.put("name", "段大肠");
		values_3.put("age", 64);
		values_3.put("sex", 0);
		values_3.put("room_bed_num", "101-03床");
		values_3.put("hospital_num", "130382-3");
		values_3.put("in_hospital_time", "2014-7-12入院");
		values_3.put("to_doctor_name", "王幸");
		values_3.put("cure_detail", "主要诊断：水泥尘肺壹期  肺痿病");
		db.insert("patient", null, values_3);

		// 第四条数据
		ContentValues values_4 = new ContentValues();
		values_4.put("name", "马大头");
		values_4.put("age", 84);
		values_4.put("sex", 1);
		values_4.put("room_bed_num", "101-04床");
		values_4.put("hospital_num", "H130632-1");
		values_4.put("in_hospital_time", "2014-7-15入院");
		values_4.put("to_doctor_name", "王幸");
		values_4.put("cure_detail", "主要诊断：煤工尘肺壹期  肺痿病");
		db.insert("patient", null, values_4);
		
		// 第五条数据
		ContentValues values_5 = new ContentValues();
		values_5.put("name", "王大锤");
		values_5.put("age", 73);
		values_5.put("sex", 1);
		values_5.put("room_bed_num", "102-01床");
		values_5.put("hospital_num", "130420-1");
		values_5.put("in_hospital_time", "2014-7-14入院");
		values_5.put("to_doctor_name", "王幸");
		values_5.put("cure_detail", "主要诊断：煤工尘肺壹期  肺痿病");
		db.insert("patient", null, values_5);
		
		// 第六条数据
		ContentValues values_6 = new ContentValues();
		values_6.put("name", "李狗蛋");
		values_6.put("age", 42);
		values_6.put("sex", 1);
		values_6.put("room_bed_num", "102-02床");
		values_6.put("hospital_num", "130683-5");
		values_6.put("in_hospital_time", "2014-7-18入院");
		values_6.put("to_doctor_name", "赵美丽");
		values_6.put("cure_detail", "主要诊断：煤工尘肺壹期  肺痿病");
		db.insert("patient", null, values_6);
		
		// 第七条数据
		ContentValues values_7 = new ContentValues();
		values_7.put("name", "张大猫");
		values_7.put("age", 55);
		values_7.put("sex", 0);
		values_7.put("room_bed_num", "102-03床");
		values_7.put("hospital_num", "130420-5");
		values_7.put("in_hospital_time", "2014-7-16入院");
		values_7.put("to_doctor_name", "赵美丽");
		values_7.put("cure_detail", "主要诊断：煤工尘肺壹期  肺痿病");
		db.insert("patient", null, values_7);
		
		// 第八条数据
		ContentValues values_8 = new ContentValues();
		values_8.put("name", "李大鱼");
		values_8.put("age", 39);
		values_8.put("sex", 1);
		values_8.put("room_bed_num", "102-04床");
		values_8.put("hospital_num", "130420-5");
		values_8.put("in_hospital_time", "2014-7-16入院");
		values_8.put("to_doctor_name", "赵美丽");
		values_8.put("cure_detail", "主要诊断：煤工尘肺壹期  肺痿病");
		db.insert("patient", null, values_8);
		
		// 第九条数据
		ContentValues values_9 = new ContentValues();
		values_9.put("name", "赵无言");
		values_9.put("age", 50);
		values_9.put("sex", 0);
		values_9.put("room_bed_num", "103-01床");
		values_9.put("hospital_num", "130420-5");
		values_9.put("in_hospital_time", "2014-7-16入院");
		values_9.put("to_doctor_name", "赵美丽");
		values_9.put("cure_detail", "主要诊断：煤工尘肺壹期  肺痿病");
		db.insert("patient", null, values_9);
	}

}
