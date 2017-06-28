package com.apex.user.vo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserVO {

	private int id;
	private String firstname;
	private String middlename;
	private String lastname;

	public UserVO(int id, String firstname, String middlename, String lastname) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.middlename = middlename;
		this.lastname = lastname;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getMiddlename() {
		return middlename;
	}

	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public UserVO() {
		super();
		// TODO Auto-generated constructor stub
	}

}
