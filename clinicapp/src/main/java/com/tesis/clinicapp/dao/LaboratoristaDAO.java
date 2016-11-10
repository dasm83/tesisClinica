package com.tesis.clinicapp.dao;

import com.tesis.clinicapp.model.Laboratorista;
import com.tesis.clinicapp.util.GenericDAO;

public interface LaboratoristaDAO extends GenericDAO<Laboratorista,Long>{
	
	Laboratorista findByAltId(String dui);

}
