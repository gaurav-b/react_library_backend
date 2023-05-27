package com.luv2code.springbootlibrary.entity;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.UUID)
	private String userId;
	
	@Column(name = "f_name")
	private String fName;
	
	@Column(name = "m_name")
	private String mName;
	
	@Column(name = "l_name")
	private String lName;
	
	@Column(name = "contact_number")
	private String contactNumber;
	
	private String address;
	
	private String email;
	
	@Column(name = "is_first_login")
	private Boolean isFirstLogin;
	
	@Column(name = "is_verified")
	private Boolean isVerified;
	
	@Column(name = "crtd_date")
	private LocalDate crtdDate;
	
	@Column(name = "lst_updt_date")
	private LocalDate lstUpdtDate;
	
	@Column(name = "lst_updt_by")
	private String lstUpdtBy; 
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "app_user_id", referencedColumnName = "id")
	private AppUser appUser;
	
	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(String userId, String fName, String mName, String lName, String contactNumber, String address,
			String email, Boolean isFirstLogin, Boolean isVerified, LocalDate crtdDate, LocalDate lstUpdtDate,
			String lstUpdtBy) {
		super();
		this.userId = userId;
		this.fName = fName;
		this.mName = mName;
		this.lName = lName;
		this.contactNumber = contactNumber;
		this.address = address;
		this.email = email;
		this.isFirstLogin = isFirstLogin;
		this.isVerified = isVerified;
		this.crtdDate = crtdDate;
		this.lstUpdtDate = lstUpdtDate;
		this.lstUpdtBy = lstUpdtBy;
	}

	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getIsFirstLogin() {
		return isFirstLogin;
	}

	public void setIsFirstLogin(Boolean isFirstLogin) {
		this.isFirstLogin = isFirstLogin;
	}

	public Boolean getIsVerified() {
		return isVerified;
	}

	public void setIsVerified(Boolean isVerified) {
		this.isVerified = isVerified;
	}

	public LocalDate getCrtdDate() {
		return crtdDate;
	}

	public void setCrtdDate(LocalDate crtdDate) {
		this.crtdDate = crtdDate;
	}

	public LocalDate getLstUpdtDate() {
		return lstUpdtDate;
	}

	public void setLstUpdtDate(LocalDate lstUpdtDate) {
		this.lstUpdtDate = lstUpdtDate;
	}

	public String getLstUpdtBy() {
		return lstUpdtBy;
	}

	public void setLstUpdtBy(String lstUpdtBy) {
		this.lstUpdtBy = lstUpdtBy;
	}

	public AppUser getAppUser() {
		return appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", fName=" + fName + ", mName=" + mName + ", lName=" + lName
				+ ", contactNumber=" + contactNumber + ", address=" + address + ", email=" + email + ", isFirstLogin="
				+ isFirstLogin + ", isVerified=" + isVerified + ", crtdDate=" + crtdDate + ", lstUpdtDate="
				+ lstUpdtDate + ", lstUpdtBy=" + lstUpdtBy + "]";
	}
		
}
