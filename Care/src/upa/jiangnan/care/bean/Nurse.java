package upa.jiangnan.care.bean;

import java.io.Serializable;

public class Nurse implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4100404467619451244L;
	private int id;
	private String name;
	//护士是否值班：1，值班		0，不值班
	private int onWork;
	//护士工作负责区域：比如“三楼内科B区”
	private String domain;
	//护士职位：护士长，护士等
	private String work_position;
	//最近一次呼叫记录
	private String last_calltime;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getOnWork() {
		return onWork;
	}
	public void setOnWork(int onWork) {
		this.onWork = onWork;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getWork_position() {
		return work_position;
	}
	public void setWork_position(String work_position) {
		this.work_position = work_position;
	}
	public String getLast_calltime() {
		return last_calltime;
	}
	public void setLast_calltime(String last_calltime) {
		this.last_calltime = last_calltime;
	}
	
	

}
