package com.tesis.clinicapp.dao.impl;

import org.springframework.stereotype.Repository;

import com.tesis.clinicapp.dao.UsuarioDAO;
import com.tesis.clinicapp.model.Usuario;
import com.tesis.clinicapp.util.GenericDAOImpl;

@Repository("UsuarioDAO")
public class UsuarioDAOImpl extends GenericDAOImpl<Usuario, String> implements UsuarioDAO {

	public UsuarioDAOImpl(){
		super(Usuario.class);
	}

}
