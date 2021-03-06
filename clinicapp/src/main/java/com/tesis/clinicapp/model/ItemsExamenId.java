package com.tesis.clinicapp.model;
// Generated 11-02-2016 09:42:18 PM by Hibernate Tools 5.2.0.Beta1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ItemsExamenId generated by hbm2java
 */
@Embeddable
public class ItemsExamenId implements java.io.Serializable {

	private int examenId;
	private int catItemExamenId;

	public ItemsExamenId() {
	}

	public ItemsExamenId(int examenId, int catItemExamenId) {
		this.examenId = examenId;
		this.catItemExamenId = catItemExamenId;
	}

	@Column(name = "examen_id", nullable = false)
	public int getExamenId() {
		return this.examenId;
	}

	public void setExamenId(int examenId) {
		this.examenId = examenId;
	}

	@Column(name = "cat_item_examen_id", nullable = false)
	public int getCatItemExamenId() {
		return this.catItemExamenId;
	}

	public void setCatItemExamenId(int catItemExamenId) {
		this.catItemExamenId = catItemExamenId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ItemsExamenId))
			return false;
		ItemsExamenId castOther = (ItemsExamenId) other;

		return (this.getExamenId() == castOther.getExamenId())
				&& (this.getCatItemExamenId() == castOther.getCatItemExamenId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getExamenId();
		result = 37 * result + this.getCatItemExamenId();
		return result;
	}

}
