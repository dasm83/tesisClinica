package com.tesis.clinicapp.dao.impl;

import com.tesis.clinicapp.dao.ClasificacionDAO;
import java.util.List;
import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;
import com.tesis.clinicapp.model.Clasificacion;
import com.tesis.clinicapp.model.Examen;
import com.tesis.clinicapp.util.GenericDAOImpl;

@Repository("ClasificacionDAO")
public class ClasificacionDAOImpl extends GenericDAOImpl<Clasificacion,Long> implements ClasificacionDAO {

	public ClasificacionDAOImpl(){
		super(Clasificacion.class);
	}
	
	@SuppressWarnings("rawtypes")
	public List getFilteredList(int draw, int start, int length) {
		Criteria cri = getCriteria();
		cri.setFirstResult(start);
		cri.setMaxResults(length);
		return cri.list();
	}

}
