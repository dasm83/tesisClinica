package com.tesis.clinicapp.service;

import com.tesis.clinicapp.model.PacienteDatos;
import com.tesis.clinicapp.util.GenericService;

public interface PacienteService extends GenericService<PacienteDatos, Long> {

	public PacienteDatos findByAltId(String dui);

}
