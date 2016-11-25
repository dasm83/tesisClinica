package com.tesis.clinicapp.service.impl;

import com.tesis.clinicapp.dao.ClasificacionDAO;
import com.tesis.clinicapp.model.Clasificacion;
import com.tesis.clinicapp.service.ClasificacionService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tesis.clinicapp.util.GenericServiceImpl;

@Service
public class ClasificacionServiceImpl extends GenericServiceImpl<Clasificacion,Long> implements ClasificacionService{

	@Autowired
	ClasificacionDAO dao;
	
	@Override
	
	public List getFilteredList(int draw, int start, int length) {
		
		return dao.getFilteredList(draw, start, length);
	}

}
