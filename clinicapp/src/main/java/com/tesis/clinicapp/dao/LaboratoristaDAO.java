package com.tesis.clinicapp.dao;

import java.util.List;

import com.tesis.clinicapp.model.Laboratorista;
import com.tesis.clinicapp.util.GenericDAO;

public interface LaboratoristaDAO extends GenericDAO<Laboratorista,Long> {
	
	Laboratorista findByAltId(String dui);
    @SuppressWarnings("rawtypes")
	public List getFilteredList(int start, int length, int col, String order, String search);
	Laboratorista getByExactName(String lName);
	List<Laboratorista> getByName(String name);

}
