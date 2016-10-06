package com.tesis.clinicapp.model;
// Generated 10-05-2016 09:37:42 PM by Hibernate Tools 4.3.1.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Login generated by hbm2java
 */
@Entity
@Table(name = "login", schema = "clinica")
public class Login implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2523233076681306695L;
	private int idLogin;
	private String user;
	private String password;

	public Login() {
	}

	public Login(int idLogin) {
		this.idLogin = idLogin;
	}

	public Login(int idLogin, String user, String password) {
		this.idLogin = idLogin;
		this.user = user;
		this.password = password;
	}

	@Id

	@Column(name = "id_login", unique = true, nullable = false)
	public int getIdLogin() {
		return this.idLogin;
	}

	public void setIdLogin(int idLogin) {
		this.idLogin = idLogin;
	}

	@Column(name = "user")
	public String getUser() {
		return this.user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	@Column(name = "password")
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
