package com.tesis.clinicapp.dao;

import java.util.List;

import com.tesis.clinicapp.model.Usuario;
import com.tesis.clinicapp.util.GenericDAO;

public interface UsuarioDAO extends GenericDAO<Usuario, String> {

	List<Usuario> getFilteredList(int start, int length, int col, String order);

}
