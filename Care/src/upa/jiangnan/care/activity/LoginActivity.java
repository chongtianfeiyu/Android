package upa.jiangnan.care.activity;

import upa.jiangnan.care.R;
import upa.jiangnan.care.data.AdviceData;
import upa.jiangnan.care.data.MemoData;
import upa.jiangnan.care.data.PatientData;
import upa.jiangnan.care.dbservice.DB_Manager_Advice;
import upa.jiangnan.care.dbservice.DB_Manager_Memo;
import upa.jiangnan.care.dbservice.DB_Manager_Nurse;
import upa.jiangnan.care.dbservice.DB_Manager_Patient;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/*
 * 在loginactivity中，登录按钮有两个作用
 * 1、登录，跳转到main页面
 * 2、启动notification，作为演示效果
 */

public class LoginActivity extends Activity {

	private Button login_button;
	private EditText username_editText;
	private EditText password_editText;
	
	private NotificationManager nm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		login_button = (Button) findViewById(R.id.button_login);
		username_editText = (EditText) findViewById(R.id.username);
		password_editText = (EditText) findViewById(R.id.password);

		login_button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				insertData();
				startNotification();
				
//				Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//				startActivity(intent);
				
				String username = username_editText.getText().toString();
				String password = password_editText.getText().toString();
				loginCheck(username, password);
			}
		});
	}

	public void loginCheck(String username, String password) {
		if (username.equals("admin") && password.equals("admin")) {
			Intent intent = new Intent(LoginActivity.this, MainActivity.class);
			startActivity(intent);
			finish();
		} else {
			Toast.makeText(this, "请输入正确用户名，密码", Toast.LENGTH_SHORT).show();
		}

	}

	// 因为没有服务器，为使评委处可以正常使用程序。在LoginActivity中，完成数据库的输入操作
	public void insertData() {
		DB_Manager_Nurse db_nurse = new DB_Manager_Nurse(this);
		if (db_nurse.query().size() == 0) {
			db_nurse.insertNurseData();
		}

		DB_Manager_Patient db_patient = new DB_Manager_Patient(this);
		if (db_patient.query().size() == 0) {
			PatientData patientData = new PatientData(this);
			patientData.insertPatientData();
		}

		DB_Manager_Advice db_advice = new DB_Manager_Advice(this);
		if (db_advice.query().size() == 0) {
			AdviceData adviceData = new AdviceData(this);
			adviceData.insertAdviceData();
		}

		DB_Manager_Memo db_memo = new DB_Manager_Memo(this);
		if (db_memo.query().size() == 0) {
			MemoData memoData = new MemoData(this);
			memoData.insertMemoData();
		}
	}

	// 启动notification方法
	public void startNotification() {
		
		nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		
		Notification n = new Notification(R.drawable.logo, "采集血样，血样检查",
				System.currentTimeMillis());
		n.setLatestEventInfo(
				LoginActivity.this,
				"采集血样，血样检查",
				"到病室与病人沟通，进行术中健康宣教，使病人基本了解术中的配合。 换病人服，作好术前准备工作及病历，写好去手术室的时间。 与手术",
				PendingIntent.getActivity(LoginActivity.this, 1, getIntent(), 0));
		nm.notify(R.layout.activity_login, n);
		

	}

}
