package upa.jiangnan.care.activity;

import java.util.ArrayList;
import java.util.List;

import com.jn.care.zxing.MipcaActivityCapture;

import upa.jiangnan.care.R;
import upa.jiangnan.care.bean.Patient;
import upa.jiangnan.care.dbservice.DB_Manager_Patient;
import upa.jiangnan.care.fragment.NursesFragment;
import upa.jiangnan.care.fragment.PatientsFragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class MainActivity extends FragmentActivity {

	private final static int SCANNIN_GREQUEST_CODE = 1;

	private RadioGroup radioGroup_main;
	private RadioButton radioButton_left;
	private RadioButton radioButton_right;

	private FragmentManager fragmentManager = getSupportFragmentManager();

	NursesFragment nurseFragment = new NursesFragment();
	PatientsFragment patientFragment = new PatientsFragment();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		radioGroup_main = (RadioGroup) findViewById(R.id.radioGroup_main);
		radioButton_left = (RadioButton) findViewById(R.id.radiobutton_left);
		radioButton_right = (RadioButton) findViewById(R.id.radiobutton_right);

		radioGroup_init();

		radioGroup_main
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(RadioGroup group, int checkedId) {
						if (checkedId == radioButton_left.getId()) {
							left_radiobutton_checked();

							FragmentTransaction left_transaction = fragmentManager
									.beginTransaction();
							left_transaction.replace(R.id.main_container,
									patientFragment);
							left_transaction.commit();

						} else if (checkedId == radioButton_right.getId()) {
							right_radiobutton_checked();

							FragmentTransaction left_transaction = fragmentManager
									.beginTransaction();
							left_transaction.replace(R.id.main_container,
									nurseFragment);
							left_transaction.commit();
						}
					}
				});
	}

	public void radioGroup_init() {
		radioButton_left.setChecked(true);
		radioButton_right.setChecked(false);
		radioButton_left.setTextColor(Color.parseColor("#1ec6c4"));
		radioButton_right.setTextColor(Color.parseColor("#ffffff"));

		FragmentTransaction left_transaction = fragmentManager
				.beginTransaction();
		left_transaction.add(R.id.main_container, patientFragment);
		left_transaction.commit();
	}

	public void left_radiobutton_checked() {
		System.out.println("left");
		radioButton_left.setTextColor(Color.parseColor("#1ec6c4"));
		radioButton_right.setTextColor(Color.parseColor("#ffffff"));
	}

	public void right_radiobutton_checked() {
		System.out.println("right");
		radioButton_right.setTextColor(Color.parseColor("#1ec6c4"));
		radioButton_left.setTextColor(Color.parseColor("#ffffff"));
	}
	
	/*
	 * 音量上键  启动二维码扫描
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_VOLUME_UP:
			Intent intent = new Intent(this, MipcaActivityCapture.class);
			startActivityForResult(intent, SCANNIN_GREQUEST_CODE);
			break;
		}
		return super.onKeyDown(keyCode, event);
	}
	
	
	@Override
	public boolean onKeyLongPress(int keyCode, KeyEvent event) {
		switch(keyCode){
		case KeyEvent.KEYCODE_BACK:
			Intent intent = new Intent(MainActivity.this,LoginActivity.class);
			startActivity(intent);
			break;

		}
			
		return super.onKeyLongPress(keyCode, event);
	}

	/*
	 * onActivityResult处理二维码扫描后的结果
	 */
	@Override
	protected void onActivityResult(int arg0, int arg1, Intent arg2) {
		//取出数据库中的一个数据，作为演示方式，否则会报空指针
		DB_Manager_Patient db_manager = new DB_Manager_Patient(this);
		List<Patient> patient_list = new ArrayList<Patient>();
		patient_list = db_manager.query();
		
		Intent intent = new Intent(MainActivity.this,PatientDetailActivity.class);
		Bundle bundle = new Bundle();
		bundle.putSerializable("patient_detail", patient_list.get(0));
		intent.putExtras(bundle);
		startActivity(intent);
	}
	
	

}
