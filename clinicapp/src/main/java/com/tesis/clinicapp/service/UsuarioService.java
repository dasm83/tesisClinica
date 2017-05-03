package com.tesis.clinicapp.service;

import java.util.List;

import com.tesis.clinicapp.model.Usuario;
import com.tesis.clinicapp.util.GenericService;

public interface UsuarioService extends GenericService<Usuario, String> {

	List<Usuario> getFilteredList(int start, int length, int col, String order);

}
