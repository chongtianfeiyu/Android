package upa.jiangnan.care.fragment;

import upa.jiangnan.care.R;
import upa.jiangnan.care.activity.HeartRateActivity;
import upa.jiangnan.care.activity.PulseActivity;
import upa.jiangnan.care.activity.TemperatureActivity;
import upa.jiangnan.care.bean.Patient;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SignFragment extends Fragment {
	
	private LinearLayout heart_rate_view;
	private LinearLayout temperature_view;
	private LinearLayout pulse_view;
	
	private TextView name_tv;
	private TextView bed_tv;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.fragment_sign, container, false);
		
		name_tv = (TextView) linearLayout.findViewById(R.id.p_name_sign);
		bed_tv = (TextView) linearLayout.findViewById(R.id.p_bed);
		
		name_tv.setText(((Patient) getActivity().getIntent()
				.getSerializableExtra("patient_detail")).getName());
		bed_tv.setText(((Patient) getActivity().getIntent()
				.getSerializableExtra("patient_detail")).getRoom_bed_num());
		
		heart_rate_view = (LinearLayout) linearLayout.findViewById(R.id.heart_rate);
		temperature_view = (LinearLayout) linearLayout.findViewById(R.id.temperature);
		pulse_view = (LinearLayout) linearLayout.findViewById(R.id.pulse);
		
		heart_rate_view.setOnClickListener(listener);
		temperature_view.setOnClickListener(listener);
		pulse_view.setOnClickListener(listener);
		
		return linearLayout;
	}
	
	OnClickListener listener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch(v.getId()){
			case R.id.heart_rate:
				Intent heart_rate_intent = new Intent(getActivity(),HeartRateActivity.class);
				getActivity().startActivity(heart_rate_intent);
				break;
			case R.id.temperature:
				Intent tem_intent = new Intent(getActivity(),TemperatureActivity.class);
				getActivity().startActivity(tem_intent);
				break;
			case R.id.pulse:
				Intent pulse_intent = new Intent(getActivity(),PulseActivity.class);
				getActivity().startActivity(pulse_intent);
				break;
			}
		}
	};
	
	

}
