package com.tesis.clinicapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "items_valores_referencia", schema = "clinica")
public class ItemsValoresReferencia implements java.io.Serializable {

	private int id;
	private CatalogoItemsExamen catalogoItemsExamen;
	private Integer edadMinima;
	private Integer edadMaxima;
	private Character sexo;
	private Long valorRefMinimo;
	private Long valorRefMaximo;

	public ItemsValoresReferencia() {
	}

	public ItemsValoresReferencia(int id) {
		this.id = id;
	}

	public ItemsValoresReferencia(int id, CatalogoItemsExamen catalogoItemsExamen, Integer edadMinima,
			Integer edadMaxima, Character sexo, Long valorRefMinimo, Long valorRefMaximo) {
		this.id = id;
		this.catalogoItemsExamen = catalogoItemsExamen;
		this.edadMinima = edadMinima;
		this.edadMaxima = edadMaxima;
		this.sexo = sexo;
		this.valorRefMinimo = valorRefMinimo;
		this.valorRefMaximo = valorRefMaximo;
	}

	@Id
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "catalogo_items")
	public CatalogoItemsExamen getCatalogoItemsExamen() {
		return this.catalogoItemsExamen;
	}

	public void setCatalogoItemsExamen(CatalogoItemsExamen catalogoItemsExamen) {
		this.catalogoItemsExamen = catalogoItemsExamen;
	}

	@Column(name = "edad_minima")
	public Integer getEdadMinima() {
		return this.edadMinima;
	}

	public void setEdadMinima(Integer edadMinima) {
		this.edadMinima = edadMinima;
	}

	@Column(name = "edad_maxima")
	public Integer getEdadMaxima() {
		return this.edadMaxima;
	}

	public void setEdadMaxima(Integer edadMaxima) {
		this.edadMaxima = edadMaxima;
	}

	@Column(name = "sexo", length = 1)
	public Character getSexo() {
		return this.sexo;
	}

	public void setSexo(Character sexo) {
		this.sexo = sexo;
	}

	@Column(name = "valor_ref_minimo")
	public Long getValorRefMinimo() {
		return this.valorRefMinimo;
	}

	public void setValorRefMinimo(Long valorRefMinimo) {
		this.valorRefMinimo = valorRefMinimo;
	}

	@Column(name = "valor_ref_maximo")
	public Long getValorRefMaximo() {
		return this.valorRefMaximo;
	}

	public void setValorRefMaximo(Long valorRefMaximo) {
		this.valorRefMaximo = valorRefMaximo;
	}


}
