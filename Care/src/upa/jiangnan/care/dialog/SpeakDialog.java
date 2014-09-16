package upa.jiangnan.care.dialog;

import upa.jiangnan.care.R;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

public class SpeakDialog extends Dialog {
	
	private Context context;

	public SpeakDialog(Context context) {
		super(context);
		this.context = context;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_memo_speak);
	}
	
	


}
