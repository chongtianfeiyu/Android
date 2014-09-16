package upa.jiangnan.care.adapter;

import java.util.List;

import upa.jiangnan.care.R;
import upa.jiangnan.care.activity.CallPageActivity;
import upa.jiangnan.care.bean.Nurse;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

public class NurseListAdapter extends BaseAdapter {
	
	private Context context;
	private List<Nurse> nurse_list;
	

	public NurseListAdapter(Context context, List<Nurse> nurse_list) {
		super();
		this.context = context;
		this.nurse_list = nurse_list;
	}

	@Override
	public int getCount() {
		return nurse_list.size();
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
			convertView = LayoutInflater.from(context).inflate(R.layout.item_nurse, parent, false);
		}
		TextView nurse_name = (TextView) convertView.findViewById(R.id.nurse_name);
		TextView nurse_domain = (TextView) convertView.findViewById(R.id.nurse_domain);
		TextView nurse_work_position = (TextView) convertView.findViewById(R.id.nurse_worposition);
		TextView nurse_last_call = (TextView) convertView.findViewById(R.id.nurse_last_call);
		
		nurse_name.setText(nurse_list.get(position).getName());
		nurse_domain.setText(nurse_list.get(position).getDomain());
		nurse_work_position.setText(nurse_list.get(position).getWork_position());
		nurse_last_call.setText(nurse_list.get(position).getLast_calltime());
		
		//护士列表拨打电话按钮
		ImageButton nurse_call = (ImageButton) convertView.findViewById(R.id.nurse_call);
		nurse_call.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context,CallPageActivity.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable("nurse",nurse_list.get(position));
				intent.putExtras(bundle);
				context.startActivity(intent);
			}
		});
		
		return convertView;
	}

}
