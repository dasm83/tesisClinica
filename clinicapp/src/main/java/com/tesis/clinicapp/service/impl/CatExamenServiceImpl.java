package com.tesis.clinicapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tesis.clinicapp.dao.CatExamenDAO;
import com.tesis.clinicapp.model.CatalogoExamen;
import com.tesis.clinicapp.service.CatExamenService;
import com.tesis.clinicapp.util.GenericServiceImpl;

@Service
public class CatExamenServiceImpl extends GenericServiceImpl<CatalogoExamen, Long> implements CatExamenService{

	@Autowired
	private CatExamenDAO dao;
	
	public List getFilteredList(int draw, int start, int length) {
		
		return dao.getFilteredList(draw, start, length);
	}
}
