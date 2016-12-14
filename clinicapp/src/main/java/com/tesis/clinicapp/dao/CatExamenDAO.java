package com.tesis.clinicapp.dao;

import java.util.List;

import com.tesis.clinicapp.model.CatalogoExamen;
import com.tesis.clinicapp.util.GenericDAO;

public interface CatExamenDAO extends GenericDAO<CatalogoExamen, Long>{

	@SuppressWarnings("rawtypes")
	public List getFilteredList(int draw, int start, int length);
}
