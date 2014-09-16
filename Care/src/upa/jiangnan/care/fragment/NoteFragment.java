package upa.jiangnan.care.fragment;

import upa.jiangnan.care.R;
import upa.jiangnan.care.adapter.NoteListAdapter;
import upa.jiangnan.care.bean.Patient;
import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class NoteFragment extends Fragment {

	private String[] note_title = { "意识", "呼吸", "血压", "血氧饱和度", "吸氧", "入量／出量",
			"皮肤情况", "管路护理", "病情观察及措施" };
	
	private NoteListAdapter noteListAdapter;
	private ListView note_list_view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		LinearLayout linearLayout = (LinearLayout) inflater.inflate(
				R.layout.fragment_note, container, false);
		
		note_list_view = (ListView) linearLayout.findViewById(R.id.note_item);
		
		noteListAdapter = new NoteListAdapter(getActivity(), note_title);
		note_list_view.setAdapter(noteListAdapter);
		
		TextView name = (TextView) linearLayout.findViewById(R.id.p_name_sign);
		name.setText(((Patient) getActivity().getIntent()
				.getSerializableExtra("patient_detail")).getName());
		
		TextView bed_room = (TextView) linearLayout.findViewById(R.id.p_bed);
		bed_room.setText(((Patient) getActivity().getIntent()
				.getSerializableExtra("patient_detail")).getRoom_bed_num());
		
		final EditText note_date = (EditText) linearLayout.findViewById(R.id.note_date);
		note_date.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				final AlertDialog note_date_dialog = new AlertDialog.Builder(getActivity()).create();
				note_date_dialog.show();
				note_date_dialog.getWindow().setContentView(R.layout.dialog_date_time_picker);
				
				final DatePicker date_picker_view = (DatePicker) note_date_dialog
						.getWindow().findViewById(R.id.date_picker);
				
				note_date_dialog.getWindow().findViewById(R.id.date_ok)
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						StringBuffer sb = new StringBuffer();
						sb.append(String.format(
								"%d-%02d-%02d",
								date_picker_view.getYear(),
								date_picker_view.getMonth() + 1,
								date_picker_view.getDayOfMonth()));
						sb.append(" ");
						note_date.setText(sb);
						note_date_dialog.dismiss();
					}
				});
				note_date_dialog.getWindow()
				.findViewById(R.id.date_cancel)
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						note_date_dialog.dismiss();
					}
				});
				
			}
		});
		
		return linearLayout;
	}

}
