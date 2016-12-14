package com.tesis.clinicapp.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.tesis.clinicapp.dao.CatExamenDAO;
import com.tesis.clinicapp.model.CatalogoExamen;
import com.tesis.clinicapp.util.GenericDAOImpl;

@Repository("CatExamenDAO")
public class CatExamenDAOImpl extends GenericDAOImpl<CatalogoExamen, Long> implements CatExamenDAO {

	public CatExamenDAOImpl(){
		super(CatalogoExamen.class);
	}
	
	@SuppressWarnings("rawtypes")
	public List getFilteredList(int draw, int start, int length) {
		Criteria cri = getCriteria();
		cri.setFirstResult(start);
		cri.setMaxResults(length);
		return cri.list();
	}
	
}
