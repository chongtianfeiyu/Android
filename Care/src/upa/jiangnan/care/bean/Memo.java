package upa.jiangnan.care.bean;

import java.util.Date;

//±¸Íü
public class Memo {
	private int id;
	private String memo_title;
	private String memo_detail;
	private Date memo_date;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMemo_title() {
		return memo_title;
	}
	public void setMemo_title(String memo_title) {
		this.memo_title = memo_title;
	}
	public String getMemo_detail() {
		return memo_detail;
	}
	public void setMemo_detail(String memo_detail) {
		this.memo_detail = memo_detail;
	}
	
	public Date getMemo_date() {
		return memo_date;
	}
	public void setMemo_date(Date memo_date) {
		this.memo_date = memo_date;
	}
	public Memo(int id, String memo_title, String memo_detail) {
		super();
		this.id = id;
		this.memo_title = memo_title;
		this.memo_detail = memo_detail;
	}
	public Memo() {
		super();
	}
	public Memo(int id, String memo_title, String memo_detail, Date memo_date) {
		super();
		this.id = id;
		this.memo_title = memo_title;
		this.memo_detail = memo_detail;
		this.memo_date = memo_date;
	}
	
	
	
	
	
	
	
}
