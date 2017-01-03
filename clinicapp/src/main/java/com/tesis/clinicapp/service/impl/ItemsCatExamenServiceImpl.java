package com.tesis.clinicapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tesis.clinicapp.dao.ExamenDAO;
import com.tesis.clinicapp.dao.ItemsCatExamenDAO;
import com.tesis.clinicapp.model.CatalogoExamen;
import com.tesis.clinicapp.model.CatalogoItemsExamen;
import com.tesis.clinicapp.service.CatExamenService;
import com.tesis.clinicapp.service.ItemsCatExamenService;
import com.tesis.clinicapp.util.GenericServiceImpl;

@Service
public class ItemsCatExamenServiceImpl extends GenericServiceImpl<CatalogoItemsExamen, Long> implements ItemsCatExamenService {
	
	@Autowired
	ItemsCatExamenDAO dao;
	
}
