package com.tesis.clinicapp.web.form.maintenance;

import java.util.List;

import com.tesis.clinicapp.model.Clasificacion;

public class CatalogoExamDetail {
	
	private Long examCatId;
	private String nombre;
	private String descripcion;
	private long clasificacion;
	private List<CatalogoExamDetailFormItem> items;
	
	public Long getExamCatId() {
		return examCatId;
	}
	public void setExamCatId(Long examCatId) {
		this.examCatId = examCatId;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Long getClasificacion() {
		return clasificacion;
	}
	public void setClasificacion(Long clasificacion) {
		this.clasificacion = clasificacion;
	}
	public List<CatalogoExamDetailFormItem> getItems() {
		return items;
	}
	public void setItems(List<CatalogoExamDetailFormItem> items) {
		this.items = items;
	}
	
}
