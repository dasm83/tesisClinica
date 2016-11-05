package com.tesis.clinicapp.model;
// Generated 11-02-2016 09:42:18 PM by Hibernate Tools 5.2.0.Beta1

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * ItemsExamen generated by hbm2java
 */
@Entity
@Table(name = "items_examen", schema = "clinica")
public class ItemsExamen implements java.io.Serializable {

	private ItemsExamenId id;
	private CatalogoItemsExamen catalogoItemsExamen;
	private Examen examen;
	private String valor;

	public ItemsExamen() {
	}

	public ItemsExamen(ItemsExamenId id, CatalogoItemsExamen catalogoItemsExamen, Examen examen, String valor) {
		this.id = id;
		this.catalogoItemsExamen = catalogoItemsExamen;
		this.examen = examen;
		this.valor = valor;
	}

	@EmbeddedId

	@AttributeOverrides({ @AttributeOverride(name = "examenId", column = @Column(name = "examen_id", nullable = false)),
			@AttributeOverride(name = "catItemExamenId", column = @Column(name = "cat_item_examen_id", nullable = false)) })
	public ItemsExamenId getId() {
		return this.id;
	}

	public void setId(ItemsExamenId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cat_item_examen_id", nullable = false, insertable = false, updatable = false)
	public CatalogoItemsExamen getCatalogoItemsExamen() {
		return this.catalogoItemsExamen;
	}

	public void setCatalogoItemsExamen(CatalogoItemsExamen catalogoItemsExamen) {
		this.catalogoItemsExamen = catalogoItemsExamen;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "examen_id", nullable = false, insertable = false, updatable = false)
	public Examen getExamen() {
		return this.examen;
	}

	public void setExamen(Examen examen) {
		this.examen = examen;
	}

	@Column(name = "valor", nullable = false)
	public String getValor() {
		return this.valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

}
