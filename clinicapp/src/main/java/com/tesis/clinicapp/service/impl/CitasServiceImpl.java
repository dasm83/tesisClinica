package com.tesis.clinicapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tesis.clinicapp.dao.CitasDAO;
import com.tesis.clinicapp.dao.PacienteDAO;
import com.tesis.clinicapp.model.Citas;
import com.tesis.clinicapp.model.Paciente;
import com.tesis.clinicapp.service.CitasService;
import com.tesis.clinicapp.service.PacienteService;
import com.tesis.clinicapp.util.GenericServiceImpl;

@Service
public class CitasServiceImpl extends GenericServiceImpl<Citas, Long> implements CitasService{

	@Autowired
	CitasDAO dao;
	
	public List<Citas> getByDate(){
		return dao.getByDate();
	}
}
