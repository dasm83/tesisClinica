package com.tesis.clinicapp.web.form.maintenance;

/**
 * @author dasm
 * 
 * This class maps to form in "pacientes" maintenance. Each property maps to a field inside the form.
 * We don't need to worry about type conversion because there is a "conversion service" declared in 
 * aplicationContext.xml
 *
 */
public class pacientesMainForm {
	
	private Long id;
	private String names;
	private String surnames;
	private Character sex;
	private int age;
	private String dui;
	private String nit;
	private String job;
	private String nation;
	private String action;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNames() {
		return names;
	}
	public void setNames(String names) {
		this.names = names;
	}
	public String getSurnames() {
		return surnames;
	}
	public void setSurnames(String surnames) {
		this.surnames = surnames;
	}
	public Character getSex() {
		return sex;
	}
	public void setSex(Character sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getDui() {
		return dui;
	}
	public void setDui(String dui) {
		this.dui = dui;
	}
	public String getNit() {
		return nit;
	}
	public void setNit(String nit) {
		this.nit = nit;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}

}
