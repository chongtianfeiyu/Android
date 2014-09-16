package upa.jiangnan.care.dbservice;

import java.util.ArrayList;
import java.util.List;

import upa.jiangnan.care.bean.Nurse;
import upa.jiangnan.care.bean.Patient;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DB_Manager_Patient {

	private DatabaseHelper databaseHelper;
	private SQLiteDatabase db;

	public DB_Manager_Patient(Context context) {
		databaseHelper = new DatabaseHelper(context);
		db = databaseHelper.getWritableDatabase();
	}

	// 遍历nurse表，取出所有数据
	public List<Patient> query() {
		List<Patient> patientList = new ArrayList<Patient>();
		Cursor c = db.rawQuery("select * from patient", null);
		while (c.moveToNext()) {
			Patient patient = new Patient();
			patient.setId(c.getInt(c.getColumnIndex("id")));
			patient.setName(c.getString(c.getColumnIndex("name")));
			patient.setAge(c.getInt(c.getColumnIndex("age")));
			patient.setSex(c.getInt(c.getColumnIndex("sex")));
			patient.setRoom_bed_num(c.getString(c
					.getColumnIndex("room_bed_num")));
			patient.setHospital_num(c.getString(c
					.getColumnIndex("hospital_num")));
			patient.setIn_hospital_time(c.getString(c
					.getColumnIndex("in_hospital_time")));
			patient.setTo_doctor_name(c.getString(c
					.getColumnIndex("to_doctor_name")));
			patient.setCure_detail(c.getString(c.getColumnIndex("cure_detail")));
			patientList.add(patient);
		}

		return patientList;
	}


}
