package com.tesis.clinicapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tesis.clinicapp.service.LaboratoristaService;
import com.tesis.clinicapp.model.Laboratorista;
import com.tesis.clinicapp.util.GenericServiceImpl;
import com.tesis.clinicapp.dao.LaboratoristaDAO;
import com.tesis.clinicapp.dao.PacienteDAO;

@Service
public class LaboratoristaServiceImpl extends GenericServiceImpl<Laboratorista,Long> implements LaboratoristaService{

	@Autowired
	LaboratoristaDAO dao;
	
	public Laboratorista findByAltId(String dui){
		return dao.findByAltId(dui);
	}
}
