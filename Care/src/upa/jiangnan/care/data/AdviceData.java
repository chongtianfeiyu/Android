package upa.jiangnan.care.data;

import upa.jiangnan.care.dbservice.DatabaseHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class AdviceData {

	private DatabaseHelper databaseHelper;
	private SQLiteDatabase db;

	public AdviceData(Context context) {
		databaseHelper = new DatabaseHelper(context);
		db = databaseHelper.getWritableDatabase();
	}

	public void insertAdviceData() {
		// ��һ������
		ContentValues values_1 = new ContentValues();
		values_1.put("advice_title", "�ڿƼ���������");
		values_1.put("detail", "");
		values_1.put("long_short", 1);
		values_1.put("frequence", "ÿ��");
		values_1.put("from_time", "");
		values_1.put("to_time", "");
		values_1.put("execute_ok", 0);
		db.insert("advice", null, values_1);
		
		// �ڶ�������
		ContentValues values_2 = new ContentValues();
		values_2.put("advice_title", "��������");
		values_2.put("detail", "");
		values_2.put("long_short", 1);
		values_2.put("frequence", "ÿ��");
		values_2.put("from_time", "");
		values_2.put("to_time", "");
		values_2.put("execute_ok", 0);
		db.insert("advice", null, values_2);
		
		// ����������
		ContentValues values_3 = new ContentValues();
		values_3.put("advice_title", "���ε�֬��ʳ");
		values_3.put("detail", "");
		values_3.put("long_short", 1);
		values_3.put("frequence", "ÿ��");
		values_3.put("from_time", "");
		values_3.put("to_time", "");
		values_3.put("execute_ok", 0);
		db.insert("advice", null, values_3);
		
		// ����������
		ContentValues values_4 = new ContentValues();
		values_4.put("advice_title", "�������");
		values_4.put("detail", "1-2L/min  3/��");
		values_4.put("long_short", 1);
		values_4.put("frequence", "ÿ��");
		values_4.put("from_time", "");
		values_4.put("to_time", "");
		values_4.put("execute_ok", 0);
		db.insert("advice", null, values_4);
		
		// ����������
		ContentValues values_5 = new ContentValues();
		values_5.put("advice_title", "����Τ��ע��Һ��������5%��");
		values_5.put("detail", "0.10g/100ml  ����ע��Һ  1/��");
		values_5.put("long_short", 1);
		values_5.put("frequence", "ÿ��");
		values_5.put("from_time", "2014-7-10");
		values_5.put("to_time", "2014-8��12");
		values_5.put("execute_ok", 0);
		db.insert("advice", null, values_5);
		
		// ����������
		ContentValues values_6 = new ContentValues();
		values_6.put("advice_title", "������ù�أ�������5%��");
		values_6.put("detail", "0.60g��250ml  ����ע��Һ  1/��");
		values_6.put("long_short", 1);
		values_6.put("frequence", "ÿ��");
		values_6.put("from_time", "2014-7-10");
		values_6.put("to_time", "2014-8��12");
		values_6.put("execute_ok", 0);
		db.insert("advice", null, values_6);
		
		// ����������
		ContentValues values_7 = new ContentValues();
		values_7.put("advice_title", "�԰���ˮ����������Ƭ");
		values_7.put("detail", "0.40g  �ڷ�  3/��");
		values_7.put("long_short", 1);
		values_7.put("frequence", "ÿ��");
		values_7.put("from_time", "2014-7-10");
		values_7.put("to_time", "2014-8��12");
		values_7.put("execute_ok", 0);
		db.insert("advice", null, values_7);
		
		// �ڰ�������
		ContentValues values_8 = new ContentValues();
		values_8.put("advice_title", "������0.9%�Ȼ��ƣ�");
		values_8.put("detail", "0.30g��100ml  ����ע��Һ  1/��");
		values_8.put("long_short", 1);
		values_8.put("frequence", "ÿ��");
		values_8.put("from_time", "2014-7-10");
		values_8.put("to_time", "2014-8��12");
		values_8.put("execute_ok", 0);
		db.insert("advice", null, values_8);
		
		// �ھ�������
		ContentValues values_9 = new ContentValues();
		values_9.put("advice_title", "���ᰱ����ע��Һ��0.9%�Ȼ��ƣ�");
		values_9.put("detail", "0.30g��100ml  ����ע��Һ  1/��");
		values_9.put("long_short", 1);
		values_9.put("frequence", "ÿ��");
		values_9.put("from_time", "2014-7-10");
		values_9.put("to_time", "2014-8��12");
		values_9.put("execute_ok", 0);
		db.insert("advice", null, values_9);
		
		//��������Ϊ��ʱҽ��������
		
		//��ʮ������
		ContentValues values_10 = new ContentValues();
		values_10.put("advice_title", "����������ɳ��ע��Һ");
		values_10.put("detail", "0.30g  ������  1/��");
		values_10.put("long_short", 0);
		values_10.put("frequence", "ÿ��");
		values_10.put("from_time", "2014��8-3");
		values_10.put("to_time", "2014-8��12");
		values_10.put("execute_ok", 0);
		db.insert("advice", null, values_10);
		
		//��ʮһ������
		ContentValues values_11 = new ContentValues();
		values_11.put("advice_title", "ѪҺ���");
		values_11.put("detail", "��ǰ����Ѫ����");
		values_11.put("long_short", 0);
		values_11.put("frequence", "ÿ��");
		values_11.put("from_time", "2014��8-3");
		values_11.put("to_time", "2014-8��12");
		values_11.put("execute_ok", 0);
		db.insert("advice", null, values_11);
		
		//��ʮ��������
		ContentValues values_12 = new ContentValues();
		values_12.put("advice_title", "������Һ���������ϣ�");
		values_12.put("detail", "10.00ml  �ڷ�  3/��");
		values_12.put("long_short", 0);
		values_12.put("frequence", "ÿ��");
		values_12.put("from_time", "2014��8-3");
		values_12.put("to_time", "2014-8��12");
		values_12.put("execute_ok", 0);
		db.insert("advice", null, values_12);
		
		//��ʮ��������
		ContentValues values_13 = new ContentValues();
		values_13.put("advice_title", "����ƽ����");
		values_13.put("detail", "0.60g  �ڷ�  1/��");
		values_13.put("long_short", 0);
		values_13.put("frequence", "ÿ��");
		values_13.put("from_time", "2014��8-3");
		values_13.put("to_time", "2014-8��12");
		values_13.put("execute_ok", 0);
		db.insert("advice", null, values_13);
		
		//��ʮ��������
		ContentValues values_14 = new ContentValues();
		values_14.put("advice_title", "�����Ұ�����Ƭ");
		values_14.put("detail", "1.00g  �ڷ�  1/��");
		values_14.put("long_short", 0);
		values_14.put("frequence", "ÿ��");
		values_14.put("from_time", "2014��8-3");
		values_14.put("to_time", "2014-8��12");
		values_14.put("execute_ok", 0);
		db.insert("advice", null, values_14);
	}

}
