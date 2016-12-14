package com.tesis.clinicapp.dao;

import java.util.List;

import com.tesis.clinicapp.model.Paciente;
import com.tesis.clinicapp.util.GenericDAO;

public interface PacienteDAO extends GenericDAO<Paciente, Long> {

	Paciente findByAltId(String dui);

	List<Paciente> getByName(String name);

	Paciente getByExactName(String pName);
	
    List getFilteredList(int draw,int start, int length);

}
