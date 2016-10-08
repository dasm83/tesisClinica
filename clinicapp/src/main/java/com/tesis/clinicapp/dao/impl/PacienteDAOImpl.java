package com.tesis.clinicapp.dao.impl;

import org.springframework.stereotype.Repository;

import com.tesis.clinicapp.dao.PacienteDAO;
import com.tesis.clinicapp.model.PacienteDatos;
import com.tesis.clinicapp.util.GenericDAOImpl;

@Repository("PacienteDAO")
public class PacienteDAOImpl extends GenericDAOImpl<PacienteDatos, Long> implements PacienteDAO {

	public PacienteDAOImpl(){
		super(PacienteDatos.class);
	}
	
}
