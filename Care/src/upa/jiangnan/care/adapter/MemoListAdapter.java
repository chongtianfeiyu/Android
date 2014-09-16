package upa.jiangnan.care.adapter;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.w3c.dom.Text;

import upa.jiangnan.care.R;
import upa.jiangnan.care.bean.Memo;
import upa.jiangnan.care.data.MemoData;
import upa.jiangnan.care.dbservice.DB_Manager_Memo;
import upa.jiangnan.care.dialog.SpeakDialog;
import upa.jiangnan.care.util.AudioRecorder;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Environment;
import android.renderscript.ProgramFragmentFixedFunction.Builder.Format;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;

public class MemoListAdapter extends BaseAdapter {

	private SpeakDialog speakDialog;
	private AudioRecorder audioRecorder;

	private Context context;
	private List<Memo> memo_list;

	SimpleDateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH:mm");
	SimpleDateFormat dateFormat_date = new SimpleDateFormat("yy-MM-dd");

	public MemoListAdapter(Context context, List<Memo> memo_list) {
		super();
		this.context = context;
		this.memo_list = memo_list;
	}

	@Override
	public int getCount() {
		return memo_list.size();
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
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.item_memo, parent, false);
		}

		// memo中到内容
		final TextView memo_title_tv = (TextView) convertView
				.findViewById(R.id.memo_title);
		TextView memo_date_time = (TextView) convertView
				.findViewById(R.id.memo_date_time);
		// edit中内容
		final EditText memo_detail_edit = (EditText) convertView
				.findViewById(R.id.memo_detail_edit);
		final EditText memo_title_edit = (EditText) convertView
				.findViewById(R.id.memo_title_to_edit);

		// memo中 brief内容设置
		memo_title_tv.setText(memo_list.get(position).getMemo_title());
		if (memo_list.get(position).getMemo_date() != null) {
			memo_date_time.setText(dateFormat.format(memo_list.get(position)
					.getMemo_date()));
		}

		// edit内容设置
		memo_title_edit.setText(memo_list.get(position).getMemo_title());
		memo_detail_edit.setText(memo_list.get(position).getMemo_detail());

		/*
		 * 时间、日期选择
		 */
		final TextView date_time_picker_tv = (TextView) convertView
				.findViewById(R.id.date_time_picker);
		date_time_picker_tv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				final AlertDialog date_time_picker_dialog = new AlertDialog.Builder(
						context).create();
				date_time_picker_dialog.show();
				date_time_picker_dialog.getWindow().setContentView(
						R.layout.dialog_date_time_picker);

				final DatePicker date_picker_view = (DatePicker) date_time_picker_dialog
						.getWindow().findViewById(R.id.date_picker);
				//TimePicker time_picker_view = (TimePicker) date_time_picker_dialog.getWindow().findViewById(R.id.time_picker);
				//time_picker_view.setIs24HourView(true);

				date_time_picker_dialog.getWindow().findViewById(R.id.date_ok)
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
								date_time_picker_tv.setText(sb);
								date_time_picker_dialog.dismiss();
							}
						});
				date_time_picker_dialog.getWindow()
						.findViewById(R.id.date_cancel)
						.setOnClickListener(new OnClickListener() {

							@Override
							public void onClick(View v) {
								date_time_picker_dialog.dismiss();
							}
						});
			}
		});

		// 点击编辑按钮后，弹出详情
		final RelativeLayout memo_detail_view = (RelativeLayout) convertView
				.findViewById(R.id.memo_detail_layout);
		ImageButton edit_btn = (ImageButton) convertView
				.findViewById(R.id.memo_edit);
		final RelativeLayout brief_layout = (RelativeLayout) convertView
				.findViewById(R.id.brief_memo_layout);
		final CheckBox memo_check = (CheckBox) convertView
				.findViewById(R.id.memo_check);
		edit_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (memo_detail_view.getVisibility() == View.VISIBLE) {
					memo_detail_view.setVisibility(View.GONE);
					brief_layout.setBackgroundColor(Color.parseColor("#ffffff"));
					memo_check.setChecked(false);
				} else {
					// 显示详情,在显示详情到同时把数据放入edittext
					memo_detail_view.setVisibility(View.VISIBLE);
					brief_layout.setBackgroundColor(Color.parseColor("#1dc5c3"));
					memo_check.setChecked(true);
				}
			}
		});

		/*
		 * 编辑后保存
		 */
		Button memo_save = (Button) convertView.findViewById(R.id.memo_save);
		memo_save.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 编辑并更新当前memo
				DB_Manager_Memo db_manager_memo = new DB_Manager_Memo(context);
				int id = memo_list.get(position).getId();
				// 在memo_list中删除该memo
				memo_list.remove(position);

				try {
					Date memo_date = dateFormat_date.parse(date_time_picker_tv
							.getText().toString());

					Memo memo = new Memo(id, memo_title_edit.getText()
							.toString(), memo_detail_edit.getText().toString(),
							memo_date);

					// 同时更新该类中到memo_list
					memo_list.add(memo);
					db_manager_memo.updateMemoByID(memo);
					memo_title_tv.setText(memo.getMemo_title());
					MemoListAdapter.this.notifyDataSetChanged();
				} catch (ParseException e) {
					e.printStackTrace();
				}
				// 更新完成后，把编辑框隐藏
				memo_detail_view.setVisibility(View.GONE);

			}
		});

		// 删除按钮
		ImageButton delete_btn = (ImageButton) convertView
				.findViewById(R.id.memo_delete);
		delete_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				DB_Manager_Memo db_manager_memo = new DB_Manager_Memo(context);
				db_manager_memo.deleteMemoByID(memo_list.get(position).getId());
				memo_list.remove(position);
				MemoListAdapter.this.notifyDataSetChanged();
			}
		});

		/*
		 * 长按录音按钮，开始录音
		 */
		Button memo_voice = (Button) convertView.findViewById(R.id.memo_voice);
		audioRecorder = new AudioRecorder("memovoice" + position);
		memo_voice.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {

				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					speakDialog = new SpeakDialog(context);
					speakDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
					speakDialog.show();

					try {
						audioRecorder.start();
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
				case MotionEvent.ACTION_UP:
					speakDialog.dismiss();
					try {
						audioRecorder.stop();
					} catch (IOException e) {
						e.printStackTrace();
					}
					// speakDialog.setVisibility(View.VISIBLE);
					// speakDialog.setVisibility(View.GONE);
					break;
				}
				return false;
			}
		});

		/*
		 * 点击播放按钮，听当前备忘对应的语音内容
		 */
		ImageButton play_voice = (ImageButton) convertView
				.findViewById(R.id.play_voice);
		play_voice.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				System.out.println("play--------->");
				MediaPlayer mp = new MediaPlayer();
				try {
					mp.setDataSource(Environment.getExternalStorageDirectory()
							.getAbsolutePath() + "/memovoice5.3gp");
					mp.prepare();
					mp.start();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		return convertView;
	}
}
