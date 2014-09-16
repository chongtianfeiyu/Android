package upa.jiangnan.care.bean;

import java.io.Serializable;

public class Patient implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1146157672170089715L;
	private int id;
	// 病患姓名
	private String name;
	// 病患点年龄
	private int age;
	// 病患性别 男：1 女：0
	private int sex;
	// 病患的病房号，床号
	private String room_bed_num;
	// 住院号
	private String hospital_num;
	// 入院时间
	private String in_hospital_time;
	// 医师名字
	private String to_doctor_name;
	// 主要诊断
	private String cure_detail;

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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getRoom_bed_num() {
		return room_bed_num;
	}

	public void setRoom_bed_num(String room_bed_num) {
		this.room_bed_num = room_bed_num;
	}

	public String getHospital_num() {
		return hospital_num;
	}

	public void setHospital_num(String hospital_num) {
		this.hospital_num = hospital_num;
	}

	public String getIn_hospital_time() {
		return in_hospital_time;
	}

	public void setIn_hospital_time(String in_hospital_time) {
		this.in_hospital_time = in_hospital_time;
	}

	public String getTo_doctor_name() {
		return to_doctor_name;
	}

	public void setTo_doctor_name(String to_doctor_name) {
		this.to_doctor_name = to_doctor_name;
	}

	public String getCure_detail() {
		return cure_detail;
	}

	public void setCure_detail(String cure_detail) {
		this.cure_detail = cure_detail;
	}

}
