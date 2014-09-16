package upa.jiangnan.care.fragment;

import java.util.List;

import upa.jiangnan.care.R;
import upa.jiangnan.care.activity.PatientDetailActivity;
import upa.jiangnan.care.adapter.SwipeAdapter;
import upa.jiangnan.care.bean.Patient;
import upa.jiangnan.care.dbservice.DB_Manager_Patient;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.fortysevendeg.swipelistview.BaseSwipeListViewListener;
import com.fortysevendeg.swipelistview.SwipeListView;

public class PatientsFragment extends Fragment {

	private SwipeListView mSwipeListView;
	private SwipeAdapter mAdapter;
	public static int deviceWidth;
	private List<Patient> patient_list;
	private DB_Manager_Patient db_manager;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		LinearLayout linearLayout = (LinearLayout) inflater.inflate(
				R.layout.fragment_patient_list, container, false);

		mSwipeListView = (SwipeListView) linearLayout
				.findViewById(R.id.example_lv_list);

		// 取出nurses，赋值给nurse_list
		db_manager = new DB_Manager_Patient(getActivity());
		patient_list = db_manager.query();

		mAdapter = new SwipeAdapter(getActivity(), patient_list);
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
		mSwipeListView.setSwipeMode(SwipeListView.SWIPE_MODE_RIGHT);
		mSwipeListView.setSwipeActionLeft(SwipeListView.SWIPE_ACTION_REVEAL);
		// mSwipeListView.setSwipeActionRight(settings.getSwipeActionRight());
		mSwipeListView.setOffsetRight(deviceWidth * 1 / 3);
		// mSwipeListView.setOffsetRight(convertDpToPixel(settings.getSwipeOffsetRight()));
		mSwipeListView.setAnimationTime(0);
		mSwipeListView.setSwipeOpenOnLongPress(false);
	}

	class TestBaseSwipeListViewListener extends BaseSwipeListViewListener {

		@Override
		public void onClickFrontView(int position) {
			super.onClickFrontView(position);
			Intent intent = new Intent(getActivity(),PatientDetailActivity.class);
			Bundle bundle = new Bundle();
			bundle.putSerializable("patient_detail", patient_list.get(position));
			intent.putExtras(bundle);
			startActivity(intent);
		}

		@Override
		public void onDismiss(int[] reverseSortedPositions) {
			for (int position : reverseSortedPositions) {
				//testData.remove(position);
			}
			mAdapter.notifyDataSetChanged();
		}
	}

}
