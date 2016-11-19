package com.tesis.clinicapp.dao.impl;

import org.springframework.stereotype.Repository;

import com.tesis.clinicapp.dao.CatExamenDAO;
import com.tesis.clinicapp.model.CatalogoExamen;
import com.tesis.clinicapp.util.GenericDAOImpl;

@Repository("CatExamenDAO")
public class CatExamenDAOImpl extends GenericDAOImpl<CatalogoExamen, Long> implements CatExamenDAO {

	public CatExamenDAOImpl(){
		super(CatalogoExamen.class);
	}
	
}
