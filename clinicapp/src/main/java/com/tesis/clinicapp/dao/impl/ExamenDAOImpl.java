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
	public List getFilteredList(int start, int length, int col, String order){
		
		Criteria crit = getCriteria().createAlias("catalogoExamen", "catEx").createAlias("laboratorista", "lab");
		String prop = "";
		
		switch(col){
			case 0:
				prop = "catEx.nombre";
				break;
			case 1:
				prop = "lab.nombres";
				break;
			case 2:
				prop = "fecha";
				break;
		}
		
		crit.setFirstResult(start);
		crit.setMaxResults(length);
		
		if(order.equals("asc")){
			crit.addOrder(Order.asc(prop));
		} else{
			crit.addOrder(Order.desc(prop));
		}
		
		return crit.list();
	}

}
