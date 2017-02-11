package com.tesis.clinicapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tesis.clinicapp.dao.ItemsValoresRefDAO;
import com.tesis.clinicapp.model.ItemsValoresReferencia;
import com.tesis.clinicapp.service.ItemsValoresRefService;
import com.tesis.clinicapp.util.GenericServiceImpl;

@Service("ItemsValoresRefService")
public class ItemsValoresRefServiceImpl extends GenericServiceImpl<ItemsValoresReferencia, Integer> implements ItemsValoresRefService{

	@Autowired
	ItemsValoresRefDAO dao;
	
	@Override
	public ItemsValoresReferencia getSingle(Long id, char sexo, int edad) {
		return dao.getSingle(id, sexo, edad);
	}

}
