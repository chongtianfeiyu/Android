package upa.jiangnan.care.fragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import upa.jiangnan.care.R;
import upa.jiangnan.care.adapter.SwipeAdapter_advice_left;
import upa.jiangnan.care.adapter.SwipeAdapter_advice_right;
import upa.jiangnan.care.bean.Advice;
import upa.jiangnan.care.dbservice.DB_Manager_Advice;
import upa.jiangnan.care.fragment.AdviceRigtFragment.TestBaseSwipeListViewListener;

import com.fortysevendeg.swipelistview.BaseSwipeListViewListener;
import com.fortysevendeg.swipelistview.SwipeListView;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

public class AdviceLeftFragment extends Fragment {
	private SwipeListView mSwipeListView;
	private SwipeAdapter_advice_left mAdapter;
	public static int deviceWidth;
	private List<Advice> advice_list;
	private DB_Manager_Advice db_manager;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		LinearLayout linearLayout = (LinearLayout) inflater.inflate(
				R.layout.fragment_advice_right, container, false);
		mSwipeListView = (SwipeListView) linearLayout
				.findViewById(R.id.advice_right);
		
		db_manager = new DB_Manager_Advice(getActivity());
		advice_list = new ArrayList<Advice>();
		List<Advice> list = new ArrayList<Advice>();
		list = db_manager.query();
		for(int i = 0;i<list.size();i++)
		{
			if(list.get(i).getLong_short() == 0){
				advice_list.add(list.get(i));
			}
			
		}
		
		mAdapter = new SwipeAdapter_advice_left(getActivity(), advice_list);
		deviceWidth = getDeviceWidth();
		mSwipeListView.setAdapter(mAdapter);
		mSwipeListView
				.setSwipeListViewListener(new TestBaseSwipeListViewListener());
		reload();

		return linearLayout;
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
//			Toast.makeText(getActivity(), testData.get(position),
//					Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onDismiss(int[] reverseSortedPositions) {
			for (int position : reverseSortedPositions) {
				// testData.remove(position);
			}
			mAdapter.notifyDataSetChanged();
		}
	}
}
