package com.tesis.clinicapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tesis.clinicapp.dao.LaboratoristaDAO;
import com.tesis.clinicapp.model.Laboratorista;
import com.tesis.clinicapp.service.LaboratoristaService;
import com.tesis.clinicapp.util.GenericServiceImpl;

@Service
public class LaboratoristaServiceImpl extends GenericServiceImpl<Laboratorista,Long> implements LaboratoristaService{

	
	@Autowired
	LaboratoristaDAO dao;
	
	public Laboratorista findByAltId(String dui){
		return dao.findByAltId(dui);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List getFilteredList(int start, int length, int col, String order) {
          return dao.getFilteredList(start, length, col, order);
	}
	
	public Laboratorista getByExactName(String lName){
		return dao.getByExactName(lName);
	}
	
	public List<Laboratorista> getByName(String name){
		return dao.getByName(name);
	}
		
}
