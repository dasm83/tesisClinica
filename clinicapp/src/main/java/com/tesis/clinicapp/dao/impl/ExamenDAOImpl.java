package com.tesis.clinicapp.dao.impl;

import org.springframework.stereotype.Repository;

import com.tesis.clinicapp.dao.ExamenDAO;
import com.tesis.clinicapp.model.Examen;
import com.tesis.clinicapp.util.GenericDAOImpl;

@Repository("ExamenDAO")
public class ExamenDAOImpl extends GenericDAOImpl<Examen, Long> implements ExamenDAO {
	
	public ExamenDAOImpl(){
		super(Examen.class);
	}

}
