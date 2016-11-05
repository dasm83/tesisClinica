package com.tesis.clinicapp.service;


import com.tesis.clinicapp.model.Paciente;
import com.tesis.clinicapp.util.GenericService;

public interface PacienteService extends GenericService<Paciente, Long> {

	public Paciente findByAltId(String dui);

}
