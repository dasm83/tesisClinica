package com.tesis.clinicapp.web.form.maintenance;

import java.util.List;

public class CatalogoExamVrMainForm {
	
	private Long itemId;
	private String nombre;
	private String unidad;
	private List<CatalogoExamVrDetailsFormItem> items;
	
	public Long getItemId() {
		return itemId;
	}
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getUnidad() {
		return unidad;
	}
	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}
	public List<CatalogoExamVrDetailsFormItem> getItems() {
		return items;
	}
	public void setItems(List<CatalogoExamVrDetailsFormItem> items) {
		this.items = items;
	}
	
	

}
