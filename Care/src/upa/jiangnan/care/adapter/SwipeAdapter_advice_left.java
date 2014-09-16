package upa.jiangnan.care.adapter;

import java.util.List;

import com.jn.care.zxing.MipcaActivityCapture;

import upa.jiangnan.care.R;
import upa.jiangnan.care.bean.Advice;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SwipeAdapter_advice_left extends BaseAdapter {
	
	private final static int SCANNIN_GREQUEST_CODE = 1;
	private Context context;
	private List<Advice> advice_left;
	
	public SwipeAdapter_advice_left(Context context, List<Advice> advice_left) {
		super();
		this.context = context;
		this.advice_left = advice_left;
	}

	@Override
	public int getCount() {
		return advice_left.size();
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
			convertView = LayoutInflater.from(context).inflate(R.layout.item_advice_left, parent, false);
			
			//短期医嘱的每个item中的内容
			TextView title_tv = (TextView) convertView
					.findViewById(R.id.advice_left_title);
			TextView detail_tv = (TextView) convertView
					.findViewById(R.id.advice_left_detail);
			TextView fre_tv = (TextView) convertView
					.findViewById(R.id.advice_left_freq);
			TextView from = (TextView) convertView
					.findViewById(R.id.advice_left_from);
			TextView to = (TextView) convertView.findViewById(R.id.advice_left_to);
			LinearLayout period = (LinearLayout) convertView
					.findViewById(R.id.advice_left_period);
			
			//对短期医嘱中每个item赋值
			title_tv.setText(advice_left.get(position).getAdvice_title());
			detail_tv.setText(advice_left.get(position).getDetail());
			if ("".equals(advice_left.get(position).getFrom_time())) {
				fre_tv.setText(advice_left.get(position).getFrequence());
				period.setVisibility(View.GONE);
				fre_tv.setVisibility(View.VISIBLE);
			} else {
				from.setText("始：" + advice_left.get(position).getFrom_time());
				to.setText("至：" + advice_left.get(position).getTo_time());
				period.setVisibility(View.VISIBLE);
				fre_tv.setVisibility(View.GONE);
			}
			
			//执行按钮，打开二维码扫描
			TextView execute_left_tv = (TextView) convertView.findViewById(R.id.execute_left_tv);
			execute_left_tv.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(context, MipcaActivityCapture.class);
					//context.startActivity(intent);
					((Activity)context).startActivityForResult(intent, SCANNIN_GREQUEST_CODE);
				}
			});
		}
		return convertView;
	}
	
	public  void onActivityResult(int requestCode, int resultCode, Intent data) {
		System.out.println("advice right execute");
	}
	
}
