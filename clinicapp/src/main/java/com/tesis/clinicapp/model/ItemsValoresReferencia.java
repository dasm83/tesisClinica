package com.tesis.clinicapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "items_valores_referencia", schema = "clinica")
public class ItemsValoresReferencia implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3441440499076331114L;
	private Integer id;
	private CatalogoItemsExamen catalogoItemsExamen;
	private int edadMinima;
	private int edadMaxima;
	private Character sexo;
	private int valorRefMinimo;
	private int valorRefMaximo;
	private String tipoRango;

	public ItemsValoresReferencia() {
	}

	public ItemsValoresReferencia(Integer id, CatalogoItemsExamen catalogoItemsExamen, char sexo, int valorRefMaximo,
			String tipoRango) {
		this.id = id;
		this.catalogoItemsExamen = catalogoItemsExamen;
		this.sexo = sexo;
		this.valorRefMaximo = valorRefMaximo;
		this.tipoRango = tipoRango;
	}

	public ItemsValoresReferencia(int id, CatalogoItemsExamen catalogoItemsExamen, Integer edadMinima,
			Integer edadMaxima, char sexo, Integer valorRefMinimo, int valorRefMaximo, String tipoRango) {
		this.id = id;
		this.catalogoItemsExamen = catalogoItemsExamen;
		this.edadMinima = edadMinima;
		this.edadMaxima = edadMaxima;
		this.sexo = sexo;
		this.valorRefMinimo = valorRefMinimo;
		this.valorRefMaximo = valorRefMaximo;
		this.tipoRango = tipoRango;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
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
	public int getEdadMinima() {
		return this.edadMinima;
	}

	public void setEdadMinima(int edadMinima) {
		this.edadMinima = edadMinima;
	}

	@Column(name = "edad_maxima")
	public int getEdadMaxima() {
		return this.edadMaxima;
	}

	public void setEdadMaxima(int edadMaxima) {
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
	public int getValorRefMinimo() {
		return this.valorRefMinimo;
	}

	public void setValorRefMinimo(int valorRefMinimo) {
		this.valorRefMinimo = valorRefMinimo;
	}

	@Column(name = "valor_ref_maximo")
	public int getValorRefMaximo() {
		return this.valorRefMaximo;
	}

	public void setValorRefMaximo(int valorRefMaximo) {
		this.valorRefMaximo = valorRefMaximo;
	}

	@Column(name = "tipo_rango", length = 20)
	public String getTipoRango() {
		return this.tipoRango;
	}

	public void setTipoRango(String tipoRango) {
		this.tipoRango = tipoRango;
	}
	
}
