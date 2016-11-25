package com.tesis.clinicapp.dao;

import com.tesis.clinicapp.util.GenericDAO;
import java.util.List;
import com.tesis.clinicapp.model.Clasificacion;

public interface ClasificacionDAO extends GenericDAO<Clasificacion,Long> {

		@SuppressWarnings("rawtypes")
		public List getFilteredList(int draw, int start, int length);
	
}
