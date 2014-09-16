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
		 * ҳ�����Ͻǵ������ں�����
		 * date1_tvΪ��������
		 * date2_tvΪ���������
		 */
		date1_tv = (TextView) linearLayout.findViewById(R.id.memo_date1);
		date2_tv = (TextView) linearLayout.findViewById(R.id.memo_date2);
		
		Calendar cal = Calendar.getInstance();
		date1_tv.setText(cal.get(Calendar.MONTH)+1 + "��" + cal.get(Calendar.DATE) + "��");
		date2_tv.setText(getWeekOfDate(new Date()));
		
		
		/*
		 * �����ݿ��ȡ���б����ļ�¼
		 * �ŵ�memo_list�У������뵽adapter
		 */
		db_manager = new DB_Manager_Memo(getActivity());
		memo_list = db_manager.query();
		memo_list_view = (ListView) linearLayout.findViewById(R.id.memo_list);
		MemoListAdapter memoListAdapter = new MemoListAdapter(getActivity(),memo_list);
		memo_list_view.setAdapter(memoListAdapter);
		
		/*
		 * ��ӱ�����ť�����Ӧ���¼�
		 * �����ӣ��������ݿ��¼���
		 */
		ImageButton add_memo = (ImageButton) linearLayout.findViewById(R.id.add_memo);
		add_memo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				DB_Manager_Memo db_manager_memo = new DB_Manager_Memo(getActivity());
				db_manager_memo.insertMemo("�¼���", "");
				
				//����listview
				memo_list = db_manager.query();
				MemoListAdapter memoListAdapter = new MemoListAdapter(getActivity(),memo_list);
				memo_list_view.setAdapter(memoListAdapter);
			}
		});
		
		/*
		 * Ϊmemo_list_view���itemclick�¼���Ч��ͬ�༭Ч��
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
	 * ��ȡ��������� 
	 */
	public static String getWeekOfDate(Date dt) {
        String[] weekDays = {"������", "����һ", "���ڶ�", "������", "������", "������", "������"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }
	
	

}
