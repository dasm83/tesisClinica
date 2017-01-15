package com.tesis.clinicapp.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import com.tesis.clinicapp.dao.ExamenDAO;
import com.tesis.clinicapp.model.Examen;
import com.tesis.clinicapp.util.GenericDAOImpl;

@Repository("ExamenDAO")
public class ExamenDAOImpl extends GenericDAOImpl<Examen, Long> implements ExamenDAO {
	
	public ExamenDAOImpl(){
		super(Examen.class);
	}
	
	@SuppressWarnings("rawtypes")
	public List getFilteredList(int draw, int start, int length){
		
		Criteria crit = getCriteria();
		crit.setFirstResult(start);
		crit.setMaxResults(length);
		
		return crit.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Examen> getLatest(int n){
		
		Criteria crit = getCriteria();
		crit.addOrder(Order.desc("id"));
		crit.setFirstResult(0);
		crit.setMaxResults(n);
		
		return crit.list();
		
	}

}
