package com.tesis.clinicapp.service;


import java.util.List;

import com.tesis.clinicapp.model.Paciente;
import com.tesis.clinicapp.util.GenericService;

public interface PacienteService extends GenericService<Paciente, Long> {

	public Paciente findByAltId(String dui);

	public List<Paciente> getByName(String name);

	public Paciente getByExactName(String pName);
	
	public List<Paciente> getFilteredList(int start, int length, int col, String order, String search);

}
