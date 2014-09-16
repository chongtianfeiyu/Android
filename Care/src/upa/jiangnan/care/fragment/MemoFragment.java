package upa.jiangnan.care.fragment;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import upa.jiangnan.care.R;
import upa.jiangnan.care.adapter.MemoListAdapter;
import upa.jiangnan.care.bean.Memo;
import upa.jiangnan.care.dbservice.DB_Manager_Memo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MemoFragment extends Fragment {
	
	private ListView memo_list_view;
	private List<Memo> memo_list;
	private DB_Manager_Memo db_manager;
	
	private TextView date1_tv;
	private TextView date2_tv;
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.fragment_memo, container, false);
		
		/*
		 * 页面左上角当天日期和星期
		 * date1_tv为当天日期
		 * date2_tv为当天的星期
		 */
		date1_tv = (TextView) linearLayout.findViewById(R.id.memo_date1);
		date2_tv = (TextView) linearLayout.findViewById(R.id.memo_date2);
		
		Calendar cal = Calendar.getInstance();
		date1_tv.setText(cal.get(Calendar.MONTH)+1 + "月" + cal.get(Calendar.DATE) + "日");
		date2_tv.setText(getWeekOfDate(new Date()));
		
		
		/*
		 * 从数据库获取所有备忘的记录
		 * 放到memo_list中，并传入到adapter
		 */
		db_manager = new DB_Manager_Memo(getActivity());
		memo_list = db_manager.query();
		memo_list_view = (ListView) linearLayout.findViewById(R.id.memo_list);
		MemoListAdapter memoListAdapter = new MemoListAdapter(getActivity(),memo_list);
		memo_list_view.setAdapter(memoListAdapter);
		
		/*
		 * 添加备忘按钮及其对应到事件
		 * 点击添加，插入数据库新加项
		 */
		ImageButton add_memo = (ImageButton) linearLayout.findViewById(R.id.add_memo);
		add_memo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				DB_Manager_Memo db_manager_memo = new DB_Manager_Memo(getActivity());
				db_manager_memo.insertMemo("新加项", "");
				
				//更新listview
				memo_list = db_manager.query();
				MemoListAdapter memoListAdapter = new MemoListAdapter(getActivity(),memo_list);
				memo_list_view.setAdapter(memoListAdapter);
			}
		});
		
		/*
		 * 为memo_list_view添加itemclick事件，效果同编辑效果
		 */
		memo_list_view.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				RelativeLayout memo_detail_view = (RelativeLayout) parent.findViewById(R.id.memo_detail_layout);
				if(memo_detail_view.getVisibility() == View.VISIBLE){
					memo_detail_view.setVisibility(View.GONE);
				}else{
					memo_detail_view.setVisibility(View.VISIBLE);
				}
			}
		});
		
		return linearLayout;
	}
	
	/*
	 * 获取当天的星期 
	 */
	public static String getWeekOfDate(Date dt) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }
	
	

}
