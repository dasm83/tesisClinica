package com.tesis.clinicapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tesis.clinicapp.dao.UsuarioDAO;
import com.tesis.clinicapp.model.Usuario;
import com.tesis.clinicapp.service.UsuarioService;
import com.tesis.clinicapp.util.GenericServiceImpl;

@Service
public class UsuarioServiceImpl extends GenericServiceImpl<Usuario, String> implements UsuarioService {
	
	@Autowired
	UsuarioDAO dao;
	
	
	
}
