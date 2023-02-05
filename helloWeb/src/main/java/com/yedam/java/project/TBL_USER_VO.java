package com.yedam.java.project;

public class TBL_USER_VO {
	private int userNumber;
	private String id;
	private String pw;
	private String name;
	private int age;
	private String phoneNumber;
	private String subject;

	

	public TBL_USER_VO() {
	}

	public int getUserNumber() {
		return userNumber;
	}

	public void setUserNumber(int userNumber) {
		this.userNumber = userNumber;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
	@Override
	public String toString() {
		if(id.length() <= 5) {
			id+= "\t";
		}
		if(name.length() <= 5) {
			name += "\t";
		}
		if(subject.length() <= 5) {
			subject += "\t";
		}
		return  "       예담직업전문학교 수강생 정보      \n"+
				"┌───────┬───────────────────────┐\n"+
			    "│ ID    │ " + id+"\t\t│\n"+
				"│ NAME  │ " + name+"\t\t│\n"+ 
				"│ AGE   │ " + age+"\t\t\t│\n"+
				"│ PHONE │ " + phoneNumber+"\t\t│\n"+
				"│ SUBJT │ " + subject+"\t\t│\n"+
				"└───────┴───────────────────────┘";
		
		
	}

}