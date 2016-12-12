package com.tesis.clinicapp.web.form.maintenance;

import java.util.List;

public class ExamDetailForm {
	
	private Long examId;
	private String paciente;
	private String laboratorista;
	private String fecha;
	private String observaciones;
	private Long examType;
	private List<ExamDetailFormItem> items;
	
	
	public Long getExamId() {
		return examId;
	}
	public void setExamId(Long examId) {
		this.examId = examId;
	}
	public String getPaciente() {
		return paciente;
	}
	public void setPaciente(String paciente) {
		this.paciente = paciente;
	}
	public String getLaboratorista() {
		return laboratorista;
	}
	public void setLaboratorista(String laboratorista) {
		this.laboratorista = laboratorista;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public Long getExamType() {
		return examType;
	}
	public void setExamType(Long examType) {
		this.examType = examType;
	}
	public List<ExamDetailFormItem> getItems() {
		return items;
	}
	public void setItems(List<ExamDetailFormItem> items) {
		this.items = items;
	}
	
}
