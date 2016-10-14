package com.tesis.clinicapp.dao;

import com.tesis.clinicapp.model.PacienteDatos;
import com.tesis.clinicapp.util.GenericDAO;

public interface PacienteDAO extends GenericDAO<PacienteDatos, Long> {

	PacienteDatos findByAltId(String dui);

}
