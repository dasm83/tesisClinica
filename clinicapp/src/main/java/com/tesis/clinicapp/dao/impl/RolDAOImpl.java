package com.tesis.clinicapp.dao.impl;

import org.springframework.stereotype.Repository;

import com.tesis.clinicapp.dao.RolDAO;
import com.tesis.clinicapp.model.Rol;
import com.tesis.clinicapp.util.GenericDAOImpl;

@Repository("RolDAO")
public class RolDAOImpl extends GenericDAOImpl<Rol, String> implements RolDAO {
	
	public RolDAOImpl(){
		super(Rol.class);
	}

}
