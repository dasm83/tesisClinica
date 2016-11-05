package com.tesis.clinicapp.dao;

import com.tesis.clinicapp.model.Paciente;
import com.tesis.clinicapp.util.GenericDAO;

public interface PacienteDAO extends GenericDAO<Paciente, Long> {

	Paciente findByAltId(String dui);

}
