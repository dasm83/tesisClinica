package com.tesis.clinicapp.service;

import com.tesis.clinicapp.model.ItemsValoresReferencia;
import com.tesis.clinicapp.util.GenericService;

public interface ItemsValoresRefService extends GenericService<ItemsValoresReferencia, Integer>{

	public ItemsValoresReferencia getSingle(Long id, char sexo, int edad);

}
