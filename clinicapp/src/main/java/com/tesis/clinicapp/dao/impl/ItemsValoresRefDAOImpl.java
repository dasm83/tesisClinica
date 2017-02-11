package com.tesis.clinicapp.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.tesis.clinicapp.dao.ItemsValoresRefDAO;
import com.tesis.clinicapp.model.ItemsValoresReferencia;
import com.tesis.clinicapp.util.GenericDAOImpl;

@Repository("ItemsValoresRefDAO")
public class ItemsValoresRefDAOImpl extends GenericDAOImpl<ItemsValoresReferencia, Integer> implements ItemsValoresRefDAO{

	public ItemsValoresRefDAOImpl(){
		super(ItemsValoresReferencia.class);
	}
	
	@Override
	public ItemsValoresReferencia getSingle(Long id, char sexo, int edad) {
		Criteria crit = getCriteria();
		crit.add(Restrictions.eq("catalogoItemsExamen.id", id));
		crit.add(Restrictions.eq("sexo", new Character(sexo)));
		crit.add(Restrictions.sqlRestriction(edad+" between edad_minima and edad_maxima"));
		
		return (ItemsValoresReferencia) crit.uniqueResult();
	}

}
