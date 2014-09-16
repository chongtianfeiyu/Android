package upa.jiangnan.care.fragment;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import upa.jiangnan.care.R;
import upa.jiangnan.care.activity.PatientDetailActivity;
import upa.jiangnan.care.adapter.SwipeAdapter_advice_right;
import upa.jiangnan.care.bean.Advice;
import upa.jiangnan.care.dbservice.DB_Manager_Advice;

import com.fortysevendeg.swipelistview.BaseSwipeListViewListener;
import com.fortysevendeg.swipelistview.SwipeListView;
import com.jn.care.zxing.MipcaActivityCapture;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class AdviceRigtFragment extends Fragment {
	
	private final static int SCANNIN_GREQUEST_CODE = 1;
	
	private static SwipeListView mSwipeListView;
	private static SwipeAdapter_advice_right mAdapter;
	public static int deviceWidth;
	private static List<Advice> advice_list;
	private static DB_Manager_Advice db_manager;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.fragment_advice_right, container, false);
		mSwipeListView = (SwipeListView) linearLayout
				.findViewById(R.id.advice_right);
		
		db_manager = new DB_Manager_Advice(getActivity());
		advice_list = new ArrayList<Advice>();
		List<Advice> list = new ArrayList<Advice>();
		list = db_manager.query();
		for(int i = 0;i<list.size();i++)
		{
			if(list.get(i).getLong_short() == 1){
				advice_list.add(list.get(i));
			}
			
		}
		
		mAdapter = new SwipeAdapter_advice_right(getActivity(), advice_list);
		deviceWidth = getDeviceWidth();
		mSwipeListView.setAdapter(mAdapter);
		mSwipeListView
				.setSwipeListViewListener(new TestBaseSwipeListViewListener());
		reload();

		return linearLayout;
	}
	
	public static void notifyMyAdapter(Context context){

		List<Advice> list = new ArrayList<Advice>();
		list = db_manager.query();
		advice_list.clear();
		for(int i = 0;i<list.size();i++)
		{
			if(list.get(i).getLong_short() == 1){
				advice_list.add(list.get(i));
			}
			
		}

		mAdapter = new SwipeAdapter_advice_right(context, advice_list);
		mSwipeListView.setAdapter(mAdapter);
		//mAdapter.notifyDataSetChanged();
	}
	
	private int getDeviceWidth() {
		return getResources().getDisplayMetrics().widthPixels;
	}

	private void reload() {
		mSwipeListView.setSwipeMode(SwipeListView.SWIPE_MODE_LEFT);
		mSwipeListView.setSwipeActionLeft(SwipeListView.SWIPE_ACTION_REVEAL);
		// mSwipeListView.setSwipeActionRight(settings.getSwipeActionRight());
		mSwipeListView.setOffsetLeft(deviceWidth * 3 / 4);
		// mSwipeListView.setOffsetRight(convertDpToPixel(settings.getSwipeOffsetRight()));
		mSwipeListView.setAnimationTime(0);
		mSwipeListView.setSwipeOpenOnLongPress(false);
	}

	class TestBaseSwipeListViewListener extends BaseSwipeListViewListener {

		@Override
		public void onClickFrontView(int position) {
			super.onClickFrontView(position);
			//Toast.makeText(getActivity(), testData.get(position),
			//Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onDismiss(int[] reverseSortedPositions) {
			for (int position : reverseSortedPositions) {
				//testData.remove(position);
			}
			mAdapter.notifyDataSetChanged();
		}
	}
	
	/*
	@Override
	public void onActivityResult(int requestCode, int resultCode, final Intent data) {
		System.out.println("advice right execute");
		// ²âÊÔ
		final AlertDialog advice_execute_Dialog = new AlertDialog.Builder(getActivity())
				.create();
		// checkDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		advice_execute_Dialog.show();
		advice_execute_Dialog.getWindow().setContentView(
				R.layout.dialog_advice_execute);

		advice_execute_Dialog.getWindow().findViewById(R.id.confirm)
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {

						int id = ((Advice) data
								.getSerializableExtra("advice_right")).getId();
						Advice advice = new Advice(id, 1);
						DB_Manager_Advice db_manager_advice = new DB_Manager_Advice(
								getActivity());
						db_manager_advice.updateAdviceByID(advice);

						
						//advice_list = db_manager_advice.query();
						//swipeAdapter_advice_right = new SwipeAdapter_advice_right(
				//getActivity(), advice_list);
						// SwipeListView splv = (SwipeListView) LayoutInflater
						// .from(PatientDetailActivity.this)
						// .inflate(R.layout.fragment_advice_right, null,
						// false).findViewById(R.id.advice_right);
						// splv.setAdapter(swipeAdapter_advice_right);
						//swipeAdapter_advice_right.notifyDataSetChanged();
						mAdapter.notifyDataSetChanged();
						for (int i = 0; i < db_manager_advice.query().size(); i++) {
							System.out.println(db_manager_advice.query().get(i)
									.getExecute_ok());
						}
						advice_execute_Dialog.dismiss();

					}
				});
		advice_execute_Dialog.getWindow().findViewById(R.id.cancel)
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						Intent intent = new Intent(getActivity(),
								MipcaActivityCapture.class);
						startActivityForResult(intent, SCANNIN_GREQUEST_CODE);
						advice_execute_Dialog.dismiss();

					}
				});
	}
	*/
	
	
	


}
