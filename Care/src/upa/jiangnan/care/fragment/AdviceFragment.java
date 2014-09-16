package upa.jiangnan.care.fragment;

import upa.jiangnan.care.R;
import upa.jiangnan.care.bean.Patient;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class AdviceFragment extends Fragment {

	/*
	 * 临时医嘱和长期医嘱的radiogroup的声明 左侧为临时医嘱，右侧为长期医嘱
	 */
	private RadioGroup advice_choose;
	private RadioButton advice_left;
	private RadioButton advice_right;

	// advicefragment上病人的基本信息控件
	private TextView p_name_tv;
	private TextView p_bed_tv;
	private ImageView p_sex_image;
	private TextView p_sex_age_tv;
	private TextView p_intime_tv;
	private TextView p_num_tv;
	private TextView p_doctor_tv;

	private PopupWindow pwMyPopWindow_sift;
	private PopupWindow pwMyPopWindow_sort;
	private ImageButton sift_down,sort_down;

	/*
	 * 临时医嘱和长期医嘱的fragment以及fragmentmanager的声明
	 * 这里以为时嵌套fragment，所以fragmentmanager需要用getChildFragmentManager来初始化
	 * 且必须放在oncreate之后初始化
	 */
	private FragmentManager fragmentManager;
	AdviceLeftFragment adviceLeftFragment = new AdviceLeftFragment();
	AdviceRigtFragment adviceRightFragment = new AdviceRigtFragment();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		LinearLayout linearLayout = (LinearLayout) inflater.inflate(
				R.layout.fragment_advice, container, false);

		// 临时医嘱和长期医嘱的初始化
		advice_choose = (RadioGroup) linearLayout
				.findViewById(R.id.advice_choose);
		advice_left = (RadioButton) linearLayout.findViewById(R.id.advice_left);
		advice_right = (RadioButton) linearLayout
				.findViewById(R.id.advice_right);

		// 病人基本信息控件的初始化
		p_name_tv = (TextView) linearLayout.findViewById(R.id.p_name_advice);
		p_bed_tv = (TextView) linearLayout.findViewById(R.id.p_bed);
		p_sex_age_tv = (TextView) linearLayout.findViewById(R.id.p_sex_age);
		p_intime_tv = (TextView) linearLayout.findViewById(R.id.p_intime);
		p_num_tv = (TextView) linearLayout.findViewById(R.id.p_num);
		p_doctor_tv = (TextView) linearLayout.findViewById(R.id.p_doctor);

		// 显示病人基本信息控件的赋值
		p_name_tv.setText(((Patient) getActivity().getIntent()
				.getSerializableExtra("patient_detail")).getName()); // 姓名
		p_bed_tv.setText(((Patient) getActivity().getIntent()
				.getSerializableExtra("patient_detail")).getRoom_bed_num()); // 病房和床号
		if (((Patient) getActivity().getIntent().getSerializableExtra(
				"patient_detail")).getSex() == 1) {
			p_sex_age_tv.setText("(男)"
					+ ((Patient) getActivity().getIntent()
							.getSerializableExtra("patient_detail")).getAge()
					+ "岁"); // 性别：1为男性
		} else {
			p_sex_age_tv.setText("(女)"
					+ ((Patient) getActivity().getIntent()
							.getSerializableExtra("patient_detail")).getAge()
					+ "岁"); // 性别：0为女性
		}
		p_intime_tv.setText(((Patient) getActivity().getIntent()
				.getSerializableExtra("patient_detail")).getIn_hospital_time()); // 入院时间
		p_num_tv.setText("住院号："
				+ ((Patient) getActivity().getIntent().getSerializableExtra(
						"patient_detail")).getHospital_num()); // 住院号
		p_doctor_tv.setText("医师："
				+ ((Patient) getActivity().getIntent().getSerializableExtra(
						"patient_detail")).getTo_doctor_name()); // 对应治疗医师

		advice_group_init();

		// 临时医嘱和长期医嘱的radiogroup的监听
		advice_choose.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if (checkedId == advice_left.getId()) {
					advice_left_checked();
				} else {
					advice_right_checked();
				}
			}
		});
		iniPopupWindow_sift();
		iniPopupWindow_sort();
		// 右侧筛选的popupwindow
		sift_down = (ImageButton) linearLayout
				.findViewById(R.id.sift_down);
		sort_down = (ImageButton) linearLayout.findViewById(R.id.sort_down);
		// 锚点
		final View anchor = linearLayout.findViewById(R.id.pop_anchor);
		sift_down.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (pwMyPopWindow_sift.isShowing()) {
					pwMyPopWindow_sift.dismiss();// 关闭
					
				} else {
					// pwMyPopWindow_sift.showAsDropDown(sift_down, -300, 200);
					pwMyPopWindow_sift.showAsDropDown(anchor);// 显示
					sift_down.setBackgroundResource(R.drawable.up);
				}
			}
		});
		
		
		sort_down.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (pwMyPopWindow_sort.isShowing()) {
					pwMyPopWindow_sort.dismiss();// 关闭
				} else {
					// pwMyPopWindow.showAsDropDown(sift_down, -300, 200);
					pwMyPopWindow_sort.showAsDropDown(anchor);// 显示
					sort_down.setBackgroundResource(R.drawable.up);
				}
			}
		});

		return linearLayout;
	}

	// 初始化popupwindow_sift
	public void iniPopupWindow_sift() {
		LayoutInflater inflater = (LayoutInflater) getActivity()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View layout = inflater.inflate(R.layout.popupwindow_sift, null);
		// pwMyPopWindow = new PopupWindow(layout);
		pwMyPopWindow_sift = new PopupWindow(layout,
				ViewGroup.LayoutParams.FILL_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		pwMyPopWindow_sift.setOutsideTouchable(true);
		/*
		 * 以下两个属性很有必要
		 * setFocusable设置未true，popupwindow以外的区域失去焦点，
		 * 以免点击其他按钮跳到其他activity时，popupwindow仍存在
		 * setBackgroundDrawable可以让popupwindow在点击其他区域后，window消失
		 * 
		 */
		pwMyPopWindow_sift.setFocusable(true);
		pwMyPopWindow_sift.setBackgroundDrawable(new BitmapDrawable());
		pwMyPopWindow_sift.setOnDismissListener(new PopupWindow.OnDismissListener() {
			
			@Override
			public void onDismiss() {
				sift_down.setBackgroundResource(R.drawable.down);
			}
		});

	}

	// 初始化popupwindow_sift
	public void iniPopupWindow_sort() {
		LayoutInflater inflater = (LayoutInflater) getActivity()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View layout = inflater.inflate(R.layout.popupwindow_sort, null);
		// pwMyPopWindow = new PopupWindow(layout);
		pwMyPopWindow_sort = new PopupWindow(layout,
				ViewGroup.LayoutParams.FILL_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		//pwMyPopWindow_sort.setOutsideTouchable(true);
		pwMyPopWindow_sort.setFocusable(true);
		pwMyPopWindow_sort.setBackgroundDrawable(new BitmapDrawable());
		pwMyPopWindow_sort.setOnDismissListener(new PopupWindow.OnDismissListener() {
			
			@Override
			public void onDismiss() {
				sort_down.setBackgroundResource(R.drawable.down);
			}
		});

	}

	// 默认状态下，显示长期医嘱
	public void advice_group_init() {
		// fragmentManager的初始化是在oncreat后面得，所以getChildFragmentManager不能写在前面
		fragmentManager = getChildFragmentManager();
		FragmentTransaction right_transaction = fragmentManager
				.beginTransaction();
		right_transaction.add(R.id.advice_container, adviceRightFragment);
		right_transaction.commit();

		advice_right.setChecked(true);
		advice_right_checked();

	}

	public void advice_left_checked() {
		System.out.println("left checked");
		advice_left.setTextColor(Color.parseColor("#ffffff"));
		advice_right.setTextColor(Color.parseColor("#1ec6c4"));

		fragmentManager = getChildFragmentManager();
		FragmentTransaction left_transaction = fragmentManager
				.beginTransaction();
		left_transaction.replace(R.id.advice_container, adviceLeftFragment);
		left_transaction.commit();
	}

	public void advice_right_checked() {
		System.out.println("right checked");
		advice_left.setTextColor(Color.parseColor("#1ec6c4"));
		advice_right.setTextColor(Color.parseColor("#ffffff"));

		fragmentManager = getChildFragmentManager();
		FragmentTransaction right_transaction = fragmentManager
				.beginTransaction();
		right_transaction.replace(R.id.advice_container, adviceRightFragment);
		right_transaction.commit();
	}

}
