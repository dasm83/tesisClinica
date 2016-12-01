package com.tesis.clinicapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tesis.clinicapp.dao.PacienteDAO;
import com.tesis.clinicapp.model.Paciente;
import com.tesis.clinicapp.service.PacienteService;
import com.tesis.clinicapp.util.GenericServiceImpl;

@Service
public class PacienteServiceImpl extends GenericServiceImpl<Paciente, Long> implements PacienteService {

	/**
	 * service access database through dao
	 */
	@Autowired
	PacienteDAO dao;
	
	public Paciente findByAltId(String dui){
		return dao.findByAltId(dui);
	}
	
	public List<Paciente> getByName(String name){
		return dao.getByName(name);
	}
	
	public Paciente getByExactName(String pName){
		return dao.getByExactName(pName);
	}
	
}
