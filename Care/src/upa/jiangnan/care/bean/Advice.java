package upa.jiangnan.care.bean;

import java.io.Serializable;

/*
 * 医嘱
 */
public class Advice implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4925229490087379512L;
	private int id;
	// 医嘱题目
	private String advice_title;
	// 具体医嘱内容
	private String detail;
	// 长期医嘱：1，短期医嘱：0
	private int long_short;
	// 医嘱频率：比如每日
	private String frequence;
	// 医嘱开始时间
	private String from_time;
	// 医嘱结束时间
	private String to_time;
	// 是否执行完成 1:完成 0:未完成
	private int execute_ok;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAdvice_title() {
		return advice_title;
	}

	public void setAdvice_title(String advice_title) {
		this.advice_title = advice_title;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public int getLong_short() {
		return long_short;
	}

	public void setLong_short(int long_short) {
		this.long_short = long_short;
	}

	public String getFrequence() {
		return frequence;
	}

	public void setFrequence(String frequence) {
		this.frequence = frequence;
	}

	public String getFrom_time() {
		return from_time;
	}

	public void setFrom_time(String from_time) {
		this.from_time = from_time;
	}

	public String getTo_time() {
		return to_time;
	}

	public void setTo_time(String to_time) {
		this.to_time = to_time;
	}

	public int getExecute_ok() {
		return execute_ok;
	}

	public void setExecute_ok(int execute_ok) {
		this.execute_ok = execute_ok;
	}
	
	

	public Advice() {
		super();
	}

	public Advice(int id, int execute_ok) {
		super();
		this.id = id;
		this.execute_ok = execute_ok;
	}
	
	

}
