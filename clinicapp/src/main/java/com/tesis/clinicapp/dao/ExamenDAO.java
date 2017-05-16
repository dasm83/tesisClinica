package com.tesis.clinicapp.dao;

import java.util.List;

import com.tesis.clinicapp.model.Examen;
import com.tesis.clinicapp.util.GenericDAO;

public interface ExamenDAO extends GenericDAO<Examen, Long> {

	@SuppressWarnings("rawtypes")
	public List getFilteredList(int start, int length, int col, String order, String search);

}
