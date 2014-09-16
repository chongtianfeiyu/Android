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
	 * ��ʱҽ���ͳ���ҽ����radiogroup������ ���Ϊ��ʱҽ�����Ҳ�Ϊ����ҽ��
	 */
	private RadioGroup advice_choose;
	private RadioButton advice_left;
	private RadioButton advice_right;

	// advicefragment�ϲ��˵Ļ�����Ϣ�ؼ�
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
	 * ��ʱҽ���ͳ���ҽ����fragment�Լ�fragmentmanager������
	 * ������ΪʱǶ��fragment������fragmentmanager��Ҫ��getChildFragmentManager����ʼ��
	 * �ұ������oncreate֮���ʼ��
	 */
	private FragmentManager fragmentManager;
	AdviceLeftFragment adviceLeftFragment = new AdviceLeftFragment();
	AdviceRigtFragment adviceRightFragment = new AdviceRigtFragment();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		LinearLayout linearLayout = (LinearLayout) inflater.inflate(
				R.layout.fragment_advice, container, false);

		// ��ʱҽ���ͳ���ҽ���ĳ�ʼ��
		advice_choose = (RadioGroup) linearLayout
				.findViewById(R.id.advice_choose);
		advice_left = (RadioButton) linearLayout.findViewById(R.id.advice_left);
		advice_right = (RadioButton) linearLayout
				.findViewById(R.id.advice_right);

		// ���˻�����Ϣ�ؼ��ĳ�ʼ��
		p_name_tv = (TextView) linearLayout.findViewById(R.id.p_name_advice);
		p_bed_tv = (TextView) linearLayout.findViewById(R.id.p_bed);
		p_sex_age_tv = (TextView) linearLayout.findViewById(R.id.p_sex_age);
		p_intime_tv = (TextView) linearLayout.findViewById(R.id.p_intime);
		p_num_tv = (TextView) linearLayout.findViewById(R.id.p_num);
		p_doctor_tv = (TextView) linearLayout.findViewById(R.id.p_doctor);

		// ��ʾ���˻�����Ϣ�ؼ��ĸ�ֵ
		p_name_tv.setText(((Patient) getActivity().getIntent()
				.getSerializableExtra("patient_detail")).getName()); // ����
		p_bed_tv.setText(((Patient) getActivity().getIntent()
				.getSerializableExtra("patient_detail")).getRoom_bed_num()); // �����ʹ���
		if (((Patient) getActivity().getIntent().getSerializableExtra(
				"patient_detail")).getSex() == 1) {
			p_sex_age_tv.setText("(��)"
					+ ((Patient) getActivity().getIntent()
							.getSerializableExtra("patient_detail")).getAge()
					+ "��"); // �Ա�1Ϊ����
		} else {
			p_sex_age_tv.setText("(Ů)"
					+ ((Patient) getActivity().getIntent()
							.getSerializableExtra("patient_detail")).getAge()
					+ "��"); // �Ա�0ΪŮ��
		}
		p_intime_tv.setText(((Patient) getActivity().getIntent()
				.getSerializableExtra("patient_detail")).getIn_hospital_time()); // ��Ժʱ��
		p_num_tv.setText("סԺ�ţ�"
				+ ((Patient) getActivity().getIntent().getSerializableExtra(
						"patient_detail")).getHospital_num()); // סԺ��
		p_doctor_tv.setText("ҽʦ��"
				+ ((Patient) getActivity().getIntent().getSerializableExtra(
						"patient_detail")).getTo_doctor_name()); // ��Ӧ����ҽʦ

		advice_group_init();

		// ��ʱҽ���ͳ���ҽ����radiogroup�ļ���
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
		// �Ҳ�ɸѡ��popupwindow
		sift_down = (ImageButton) linearLayout
				.findViewById(R.id.sift_down);
		sort_down = (ImageButton) linearLayout.findViewById(R.id.sort_down);
		// ê��
		final View anchor = linearLayout.findViewById(R.id.pop_anchor);
		sift_down.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (pwMyPopWindow_sift.isShowing()) {
					pwMyPopWindow_sift.dismiss();// �ر�
					
				} else {
					// pwMyPopWindow_sift.showAsDropDown(sift_down, -300, 200);
					pwMyPopWindow_sift.showAsDropDown(anchor);// ��ʾ
					sift_down.setBackgroundResource(R.drawable.up);
				}
			}
		});
		
		
		sort_down.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (pwMyPopWindow_sort.isShowing()) {
					pwMyPopWindow_sort.dismiss();// �ر�
				} else {
					// pwMyPopWindow.showAsDropDown(sift_down, -300, 200);
					pwMyPopWindow_sort.showAsDropDown(anchor);// ��ʾ
					sort_down.setBackgroundResource(R.drawable.up);
				}
			}
		});

		return linearLayout;
	}

	// ��ʼ��popupwindow_sift
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
		 * �����������Ժ��б�Ҫ
		 * setFocusable����δtrue��popupwindow���������ʧȥ���㣬
		 * ������������ť��������activityʱ��popupwindow�Դ���
		 * setBackgroundDrawable������popupwindow�ڵ�����������window��ʧ
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

	// ��ʼ��popupwindow_sift
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

	// Ĭ��״̬�£���ʾ����ҽ��
	public void advice_group_init() {
		// fragmentManager�ĳ�ʼ������oncreat����ã�����getChildFragmentManager����д��ǰ��
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
