package com.tesis.clinicapp.dao;

import com.tesis.clinicapp.model.ItemsValoresReferencia;
import com.tesis.clinicapp.util.GenericDAO;

public interface ItemsValoresRefDAO extends GenericDAO<ItemsValoresReferencia, Integer> {

	public ItemsValoresReferencia getSingle(Long id, char sexo, int edad);

}
