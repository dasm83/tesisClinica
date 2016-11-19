package com.tesis.clinicapp.dao;

import java.util.List;

import com.tesis.clinicapp.model.Laboratorista;
import com.tesis.clinicapp.util.GenericDAO;

public interface LaboratoristaDAO extends GenericDAO<Laboratorista,Long> {
	
	Laboratorista findByAltId(String dui);
    public List getFilteredList(int draw,int star, int length);

}
