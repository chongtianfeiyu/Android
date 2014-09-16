package upa.jiangnan.care.fragment;

import java.util.ArrayList;
import java.util.List;

import upa.jiangnan.care.R;
import upa.jiangnan.care.adapter.NurseListAdapter;
import upa.jiangnan.care.bean.Nurse;
import upa.jiangnan.care.dbservice.DB_Manager_Nurse;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

public class NursesFragment extends Fragment {
	
	private ListView nurse_listview;
	private List<Nurse> nurse_list = new ArrayList<Nurse>();
	private DB_Manager_Nurse db_manager;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.fragment_nurse_list, container, false);
		nurse_listview = (ListView) linearLayout.findViewById(R.id.nurse_list);
		
		//取出nurses，赋值给nurse_list
		db_manager = new DB_Manager_Nurse(getActivity());
		nurse_list = db_manager.query();
		
		NurseListAdapter nurseListAdapter = new NurseListAdapter(getActivity(),nurse_list);
		nurse_listview.setAdapter(nurseListAdapter);
		return linearLayout;
	}
	
	
	
	

}
