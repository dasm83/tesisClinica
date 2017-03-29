package com.tesis.clinicapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tesis.clinicapp.dao.ExamenDAO;
import com.tesis.clinicapp.model.Examen;
import com.tesis.clinicapp.service.ExamenService;
import com.tesis.clinicapp.util.GenericServiceImpl;

@Service
public class ExamenServiceImpl extends GenericServiceImpl<Examen, Long> implements ExamenService{
	
	@Autowired
	ExamenDAO dao;

	@SuppressWarnings("rawtypes")
	@Override
	public List getFilteredList(int start, int length, int col, String order) {
		return dao.getFilteredList(start,length,col,order);
	}

}
