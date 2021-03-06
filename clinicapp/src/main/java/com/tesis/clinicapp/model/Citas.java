package com.tesis.clinicapp.model;
// Generated 11-02-2016 09:42:18 PM by Hibernate Tools 5.2.0.Beta1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Citas generated by hbm2java
 */
@Entity
@Table(name = "citas", schema = "clinica")
public class Citas implements java.io.Serializable {

	private Long id;
	private Paciente paciente;
	private Date fechaReserva;
	private String descripcion;

	public Citas() {
	}

	public Citas(Long id, Paciente paciente, Date fechaReserva, String descripcion) {
		this.id = id;
		this.paciente = paciente;
		this.fechaReserva = fechaReserva;
		this.descripcion = descripcion;
	}

	@Id

	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "paciente_id", nullable = false)
	public Paciente getPaciente() {
		return this.paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_reserva", nullable = false, length = 29)
	public Date getFechaReserva() {
		return this.fechaReserva;
	}

	public void setFechaReserva(Date fechaReserva) {
		this.fechaReserva = fechaReserva;
	}

	@Column(name = "descripcion", nullable = false)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
