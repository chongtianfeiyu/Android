package upa.jiangnan.care.adapter;

import java.util.List;

import upa.jiangnan.care.R;
import upa.jiangnan.care.activity.BaseInfoActivity;
import upa.jiangnan.care.activity.CallPageActivity;
import upa.jiangnan.care.activity.CallRecordActivity;
import upa.jiangnan.care.bean.Patient;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SwipeAdapter extends BaseAdapter {
	private Context context;
	private List<Patient> patient_list;
	
	public SwipeAdapter(Context context, List<Patient> patient_list) {
		super();
		this.context = context;
		this.patient_list = patient_list;
	}

	@Override
	public int getCount() {
		return patient_list.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		if(convertView == null){
			convertView = LayoutInflater.from(context).inflate(R.layout.item_patient, parent, false);
			
			//前段段textview段赋值
			TextView text_name = (TextView) convertView.findViewById(R.id.patient_name);
			TextView text_age = (TextView) convertView.findViewById(R.id.patient_age);
			TextView text_room_bed_num = (TextView) convertView.findViewById(R.id.patient_room_bed_num);
			TextView text_hospital_num = (TextView) convertView.findViewById(R.id.patient_hospital_num);
			TextView text_in_hospital_time = (TextView) convertView.findViewById(R.id.patient_in_hospital_time);
			TextView text_to_doctor_name = (TextView) convertView.findViewById(R.id.patient_to_doctor_name);
			TextView text_cure_detail = (TextView) convertView.findViewById(R.id.patient_cure_detail);
			ImageView sex_tip_view = (ImageView) convertView.findViewById(R.id.sex_tip);
			final Button patient_check_btn = (Button) convertView.findViewById(R.id.patient_check);
			
			text_name.setText(patient_list.get(position).getName());
			text_age.setText(patient_list.get(position).getAge()+"岁");
			text_room_bed_num.setText(patient_list.get(position).getRoom_bed_num());
			text_hospital_num.setText("住院号：" + patient_list.get(position).getHospital_num());
			text_in_hospital_time.setText(patient_list.get(position).getIn_hospital_time());
			text_to_doctor_name.setText(patient_list.get(position).getTo_doctor_name());
			text_cure_detail.setText(patient_list.get(position).getCure_detail());
			
			/*
			 * 根据病床号更改病人头像
			 */
			ImageView patient_pic = (ImageView) convertView.findViewById(R.id.patient_pic);
			String str = patient_list.get(position).getRoom_bed_num().substring(0, 3);
			int i = Integer.parseInt(str);
			if(i%2 == 0){
				patient_pic.setBackgroundResource(R.drawable.patient_pic_blue);
				patient_check_btn.setBackgroundResource(R.drawable.check_main_blue);
			}else{
				patient_pic.setBackgroundResource(R.drawable.patient_pic_green);
				patient_check_btn.setBackgroundResource(R.drawable.check_main_green);
			}
			
			if(patient_list.get(position).getSex() == 1){
				sex_tip_view.setBackgroundResource(R.drawable.man_tip);
			}else{
				sex_tip_view.setBackgroundResource(R.drawable.women_tip);
			}
			
			patient_check_btn.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					//patient_check_btn.setBackgroundResource(R.drawable.uncheck_main);
					
//					CheckDialog checkDialog = new CheckDialog(context);
//					checkDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//					checkDialog.show();
					final AlertDialog checkDialog = new AlertDialog.Builder(context).create();
					//checkDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
					checkDialog.show();
					checkDialog.getWindow().setContentView(R.layout.dialog_check);
					
					checkDialog.getWindow().findViewById(R.id.confirm).setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							patient_check_btn.setBackgroundResource(R.drawable.uncheck_main);
							patient_check_btn.setText("已 查");
							checkDialog.dismiss();
							
						}
					});
					checkDialog.getWindow().findViewById(R.id.cancel).setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							checkDialog.dismiss();
							
						}
					});
				}
			});
			
			//后端的button响应
			Button call_record_btn = (Button) convertView.findViewById(R.id.call_record_btn);
			Button base_info_btn = (Button) convertView.findViewById(R.id.base_info_btn);
			Button call_btn = (Button) convertView.findViewById(R.id.call_btn);
			
			call_record_btn.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent intent_call = new Intent(v.getContext(),CallRecordActivity.class);
					Bundle bundle = new Bundle();
					bundle.putSerializable("nurse_info", patient_list.get(position));
					intent_call.putExtras(bundle);
					v.getContext().startActivity(intent_call);
				}
			});
			base_info_btn.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent intent_info = new Intent(v.getContext(),BaseInfoActivity.class);
					Bundle bundle = new Bundle();
					bundle.putSerializable("nurse_info", patient_list.get(position));
					intent_info.putExtras(bundle);
					v.getContext().startActivity(intent_info);
				}
			});
			
			call_btn.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(context,CallPageActivity.class);
					Bundle bundle = new Bundle();
					bundle.putSerializable("patient", patient_list.get(position));
					intent.putExtras(bundle);
					context.startActivity(intent);
				}
			});
			
			
		}
		return convertView;
	}
	
}
