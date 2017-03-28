package com.tesis.clinicapp.web.form.maintenance;

import com.tesis.clinicapp.model.CatalogoItemsExamen;

public class CatalogoExamVrDetailsFormItem {
	
	private Integer id;
	private int minAge;
	private int maxAge;
	private Character sex;
	private String vRMin;
	private String vRMax;
	private String typeRango;
	private String estado;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getMinAge() {
		return minAge;
	}
	public void setMinAge(int minAge) {
		this.minAge = minAge;
	}
	public int getMaxAge() {
		return maxAge;
	}
	public void setMaxAge(int maxAge) {
		this.maxAge = maxAge;
	}
	public Character getSex() {
		return sex;
	}
	public void setSex(Character sex) {
		this.sex = sex;
	}
	public String getvRMin() {
		return vRMin;
	}
	public void setvRMin(String vRMin) {
		this.vRMin = vRMin;
	}
	public String getvRMax() {
		return vRMax;
	}
	public void setvRMax(String vRMax) {
		this.vRMax = vRMax;
	}
	public String getTypeRango() {
		return typeRango;
	}
	public void setTypeRango(String typeRango) {
		this.typeRango = typeRango;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	

}
