package upa.jiangnan.care.activity;

import java.util.ArrayList;
import java.util.List;

import com.fortysevendeg.swipelistview.SwipeListView;
import com.jn.care.zxing.MipcaActivityCapture;

import upa.jiangnan.care.R;
import upa.jiangnan.care.adapter.SwipeAdapter_advice_right;
import upa.jiangnan.care.bean.Advice;
import upa.jiangnan.care.bean.Patient;
import upa.jiangnan.care.dbservice.DB_Manager_Advice;
import upa.jiangnan.care.fragment.AdviceFragment;
import upa.jiangnan.care.fragment.AdviceRigtFragment;
import upa.jiangnan.care.fragment.MemoFragment;
import upa.jiangnan.care.fragment.NoteFragment;
import upa.jiangnan.care.fragment.SignFragment;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class PatientDetailActivity extends FragmentActivity {

	private final static int SCANNIN_GREQUEST_CODE = 1;

	private SwipeAdapter_advice_right swipeAdapter_advice_right;
	List<Advice> advice_list = new ArrayList<Advice>();

	// 头部的名称和返回控件
	private ImageButton head_left;
	private TextView head_middle;

	// 底部菜单的四个button
	private ImageButton sign_btn;
	private ImageButton advice_btn;
	private ImageButton note_btn;
	private ImageButton memo_btn;

	// 声明并初始化四个fragment和fragmentmanager
	private FragmentManager fragmentManager = getSupportFragmentManager();
	SignFragment signFragment = new SignFragment();
	AdviceFragment adviceFragment = new AdviceFragment();
	NoteFragment noteFragment = new NoteFragment();
	MemoFragment memoFragment = new MemoFragment();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_patient_detail);


		// 初始化底部四个控件
		sign_btn = (ImageButton) findViewById(R.id.sign);
		advice_btn = (ImageButton) findViewById(R.id.advice);
		note_btn = (ImageButton) findViewById(R.id.note);
		memo_btn = (ImageButton) findViewById(R.id.memo);

		// 初始化头部的返回和标题控件
		head_left = (ImageButton) findViewById(R.id.head_left);
		head_middle = (TextView) findViewById(R.id.head_middle);

		fragmentInit();

		// 设置底部四个button的监听
		sign_btn.setOnClickListener(listener);
		advice_btn.setOnClickListener(listener);
		note_btn.setOnClickListener(listener);
		memo_btn.setOnClickListener(listener);

		// 头部标题的text
		head_middle
				.setText(((Patient) getIntent().getSerializableExtra(
						"patient_detail")).getName()
						+ "("
						+ ((Patient) getIntent().getSerializableExtra(
								"patient_detail")).getRoom_bed_num() + ")");
		// 头部返回键监听
		head_left.setOnClickListener(listener);
	}

	// 点击底部菜单时，fragment的切换
	OnClickListener listener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.sign:
				/* 替换fragment中内容为体征 */
				FragmentTransaction sign_ft = fragmentManager
						.beginTransaction();
				sign_ft.replace(R.id.detail_container, signFragment);
				sign_ft.commit();

				/* 修改按钮背景 */
				sign_btn.setBackgroundResource(R.drawable.sign_selected);
				advice_btn.setBackgroundResource(R.drawable.advice_unselected);
				note_btn.setBackgroundResource(R.drawable.note_unselected);
				memo_btn.setBackgroundResource(R.drawable.memo_unselected);
				break;
			case R.id.advice:
				/* 替换fragment中内容为医嘱 */
				FragmentTransaction advice_ft = fragmentManager
						.beginTransaction();
				advice_ft.replace(R.id.detail_container, adviceFragment);
				advice_ft.commit();

				/* 修改按钮背景 */
				sign_btn.setBackgroundResource(R.drawable.sign_unselected);
				advice_btn.setBackgroundResource(R.drawable.advice_selected);
				note_btn.setBackgroundResource(R.drawable.note_unselected);
				memo_btn.setBackgroundResource(R.drawable.memo_unselected);
				break;
			case R.id.note:
				/* 替换fragment中内容为记录 */
				FragmentTransaction note_ft = fragmentManager
						.beginTransaction();
				note_ft.replace(R.id.detail_container, noteFragment);
				note_ft.commit();

				/* 修改按钮背景 */
				sign_btn.setBackgroundResource(R.drawable.sign_unselected);
				advice_btn.setBackgroundResource(R.drawable.advice_unselected);
				note_btn.setBackgroundResource(R.drawable.note_selected);
				memo_btn.setBackgroundResource(R.drawable.memo_unselected);
				break;
			case R.id.memo:
				/* 替换fragment中内容为备忘 */
				FragmentTransaction memo_ft = fragmentManager
						.beginTransaction();
				memo_ft.replace(R.id.detail_container, memoFragment);
				memo_ft.commit();

				/* 修改按钮背景 */
				sign_btn.setBackgroundResource(R.drawable.sign_unselected);
				advice_btn.setBackgroundResource(R.drawable.advice_unselected);
				note_btn.setBackgroundResource(R.drawable.note_unselected);
				memo_btn.setBackgroundResource(R.drawable.memo_selected);
				break;
			case R.id.head_left:
				// 头部返回键
//				Intent intent = new Intent(PatientDetailActivity.this,
//						MainActivity.class);
//				startActivity(intent);
				finish();
				break;
			}

		}
	};

	// 进入patientdetailactivity时，显示的fragment
	public void fragmentInit() {
		FragmentTransaction advice_ft = fragmentManager.beginTransaction();
		advice_ft.add(R.id.detail_container, adviceFragment);
		advice_ft.commit();

	}
	
	@Override
	protected void onActivityResult(int arg0, int arg1, final Intent arg2) {
		System.out.println("advice right execute");
		// 测试
		final AlertDialog advice_execute_Dialog = new AlertDialog.Builder(this)
				.create();
		// checkDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		advice_execute_Dialog.show();
		advice_execute_Dialog.getWindow().setContentView(
				R.layout.dialog_advice_execute);

		advice_execute_Dialog.getWindow().findViewById(R.id.confirm)
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {

						int id = ((Advice) arg2
								.getSerializableExtra("advice_right")).getId();
						Advice advice = new Advice(id, 1);
						DB_Manager_Advice db_manager_advice = new DB_Manager_Advice(
								PatientDetailActivity.this);
						db_manager_advice.updateAdviceByID(advice);

						
//						advice_list = db_manager_advice.query();
//						swipeAdapter_advice_right = new SwipeAdapter_advice_right(
//								PatientDetailActivity.this, advice_list);
						// SwipeListView splv = (SwipeListView) LayoutInflater
						// .from(PatientDetailActivity.this)
						// .inflate(R.layout.fragment_advice_right, null,
						// false).findViewById(R.id.advice_right);
						// splv.setAdapter(swipeAdapter_advice_right);
//						swipeAdapter_advice_right.notifyDataSetChanged();
						AdviceRigtFragment.notifyMyAdapter(PatientDetailActivity.this);
						for (int i = 0; i < db_manager_advice.query().size(); i++) {
							System.out.println(db_manager_advice.query().get(i)
									.getExecute_ok());
						}
						advice_execute_Dialog.dismiss();

					}
				});
		advice_execute_Dialog.getWindow().findViewById(R.id.cancel)
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						Intent intent = new Intent(PatientDetailActivity.this,
								MipcaActivityCapture.class);
						startActivityForResult(intent, SCANNIN_GREQUEST_CODE);
						advice_execute_Dialog.dismiss();

					}
				});
	}
	
	//长按返回键  注销
	@Override
	public boolean onKeyLongPress(int keyCode, KeyEvent event) {
		switch(keyCode){
		case KeyEvent.KEYCODE_BACK:
			Intent intent = new Intent(PatientDetailActivity.this,LoginActivity.class);
			startActivity(intent);
			break;

		}
			
		return super.onKeyLongPress(keyCode, event);
	}

}
