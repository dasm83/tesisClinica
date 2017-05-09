package com.tesis.clinicapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tesis.clinicapp.dao.RolDAO;
import com.tesis.clinicapp.model.Rol;
import com.tesis.clinicapp.service.RolService;
import com.tesis.clinicapp.util.GenericServiceImpl;

@Service
public class RolServiceImpl extends GenericServiceImpl<Rol, String> implements RolService {
	
	@Autowired
	RolDAO dao;
	
}
