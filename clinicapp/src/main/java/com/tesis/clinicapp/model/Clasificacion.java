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
 * Clasificacion generated by hbm2java
 */
@Entity
@Table(name = "clasificacion", schema = "clinica")
public class Clasificacion implements java.io.Serializable {

	private Long id;
	private String categoria;
	private String descripcion;
	private Set<CatalogoExamen> catalogoExamens = new HashSet<CatalogoExamen>(0);

	public Clasificacion() {
	}

	public Clasificacion(Long id, String categoria, String descripcion) {
		this.id = id;
		this.categoria = categoria;
		this.descripcion = descripcion;
	}

	public Clasificacion(Long id, String categoria, String descripcion, Set<CatalogoExamen> catalogoExamens) {
		this.id = id;
		this.categoria = categoria;
		this.descripcion = descripcion;
		this.catalogoExamens = catalogoExamens;
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

	@Column(name = "categoria", nullable = false)
	public String getCategoria() {
		return this.categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	@Column(name = "descripcion", nullable = false)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "clasificacion")
	public Set<CatalogoExamen> getCatalogoExamens() {
		return this.catalogoExamens;
	}

	public void setCatalogoExamens(Set<CatalogoExamen> catalogoExamens) {
		this.catalogoExamens = catalogoExamens;
	}

}
