package com.tesis.clinicapp.model;
// Generated 11-02-2016 09:42:18 PM by Hibernate Tools 5.2.0.Beta1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Laboratorista generated by hbm2java
 */
@Entity
@Table(name = "laboratorista", schema = "clinica")
public class Laboratorista implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7398199516231337190L;
	private Long id;
	private String nombres;
	private String apellidos;
	private String profesion;
	private Integer edad;
	private String dui;
	private String nit;
	private int jvplc;
	private Set<Examen> examens = new HashSet<Examen>(0);

	public Laboratorista() {
	}

	public Laboratorista(String nombres, String apellidos, String dui, String nit, int jvplc) {
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.dui = dui;
		this.nit = nit;
		this.jvplc = jvplc;
	}

	public Laboratorista(String nombres, String apellidos, String profesion, Integer edad, String dui,
			String nit, int jvplc, Set<Examen> examens) {
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.profesion = profesion;
		this.edad = edad;
		this.dui = dui;
		this.nit = nit;
		this.jvplc = jvplc;
		this.examens = examens;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "nombres", nullable = false)
	public String getNombres() {
		return this.nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	@Column(name = "apellidos", nullable = false)
	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	@Column(name = "profesion")
	public String getProfesion() {
		return this.profesion;
	}

	public void setProfesion(String profesion) {
		this.profesion = profesion;
	}

	@Column(name = "edad")
	public Integer getEdad() {
		return this.edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	@Column(name = "dui", nullable = false)
	public String getDui() {
		return this.dui;
	}

	public void setDui(String dui) {
		this.dui = dui;
	}

	@Column(name = "nit", nullable = false)
	public String getNit() {
		return this.nit;
	}

	public void setNit(String nit) {
		this.nit = nit;
	}

	@Column(name = "jvplc", nullable = false)
	public int getJvplc() {
		return this.jvplc;
	}

	public void setJvplc(int jvplc) {
		this.jvplc = jvplc;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "laboratorista")
	public Set<Examen> getExamens() {
		return this.examens;
	}

	public void setExamens(Set<Examen> examens) {
		this.examens = examens;
	}

}
