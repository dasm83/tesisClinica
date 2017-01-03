package com.tesis.clinicapp.dao.impl;

import org.springframework.stereotype.Repository;

import com.tesis.clinicapp.dao.CatExamenDAO;
import com.tesis.clinicapp.dao.ItemsCatExamenDAO;
import com.tesis.clinicapp.model.CatalogoExamen;
import com.tesis.clinicapp.model.CatalogoItemsExamen;
import com.tesis.clinicapp.util.GenericDAOImpl;

@Repository("ItemsCatExamenDAO")
public class ItemsCatExamenDAOImpl extends GenericDAOImpl<CatalogoItemsExamen, Long> implements ItemsCatExamenDAO{
	
	public ItemsCatExamenDAOImpl(){
		super(CatalogoItemsExamen.class);
	}
	

}
