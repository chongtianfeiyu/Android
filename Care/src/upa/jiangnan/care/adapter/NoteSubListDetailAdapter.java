package upa.jiangnan.care.adapter;

import upa.jiangnan.care.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class NoteSubListDetailAdapter extends BaseAdapter {
	
	private Context context;
	private String item_title;
	private int count;
	
	public NoteSubListDetailAdapter(Context context, String item_title, int count) {
		super();
		this.context = context;
		this.item_title = item_title;
		this.count = count;
	}

	@Override
	public int getCount() {
		return count;
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
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView == null){
			convertView = LayoutInflater.from(context).inflate(R.layout.item_sub_detail_note, parent, false);
		}
		return convertView;
	}

}
