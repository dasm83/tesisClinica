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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * CatalogoItemsExamen generated by hbm2java
 */
@Entity
@Table(name = "catalogo_items_examen", schema = "clinica")
public class CatalogoItemsExamen implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7811989416434265059L;
	private Long id;
	private CatalogoExamen catalogoExamen;
	private String nombre;
	private String descripcion;
	private Set<ItemsExamen> itemsExamens = new HashSet<ItemsExamen>(0);

	public CatalogoItemsExamen() {
	}

	public CatalogoItemsExamen(Long id, CatalogoExamen catalogoExamen, String nombre) {
		this.id = id;
		this.catalogoExamen = catalogoExamen;
		this.nombre = nombre;
	}

	public CatalogoItemsExamen(Long id, CatalogoExamen catalogoExamen, String nombre, String descripcion,
			Set<ItemsExamen> itemsExamens) {
		this.id = id;
		this.catalogoExamen = catalogoExamen;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.itemsExamens = itemsExamens;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cat_examen_id", nullable = false)
	public CatalogoExamen getCatalogoExamen() {
		return this.catalogoExamen;
	}

	public void setCatalogoExamen(CatalogoExamen catalogoExamen) {
		this.catalogoExamen = catalogoExamen;
	}

	@Column(name = "nombre", nullable = false)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "descripcion")
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "catalogoItemsExamen")
	public Set<ItemsExamen> getItemsExamens() {
		return this.itemsExamens;
	}

	public void setItemsExamens(Set<ItemsExamen> itemsExamens) {
		this.itemsExamens = itemsExamens;
	}

}
