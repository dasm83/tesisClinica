package com.tesis.clinicapp.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.tesis.clinicapp.dao.LaboratoristaDAO;
import com.tesis.clinicapp.model.Laboratorista;
import com.tesis.clinicapp.util.GenericDAOImpl;

@Repository("LaboratoristaDAO")
public class LaboratoristaDAOImpl extends GenericDAOImpl<Laboratorista,Long> implements LaboratoristaDAO {
	
	private static final Logger logger = Logger.getLogger(PacienteDAOImpl.class);
		
	public LaboratoristaDAOImpl(){
		super(Laboratorista.class);
	}
	
	public Laboratorista findByAltId(String dui){
		/// criteria object is created to add restrictions to the generated query
		Criteria crit = getCriteria();
		/// getting the patient with a dui equal to our dui parameter
		crit.add(Restrictions.eq("dui", dui));
		/// i'm sure i'll only get one patient with this dui, so i use uniqueResult
		/// if multiple results are expected i would use .list() method instead
		return (Laboratorista) crit.uniqueResult();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List getFilteredList(int start, int length, int col, String order, String search) {
		Criteria crit = getCriteria();
		String prop = "";
		
		switch(col){
			case 0:
				prop = "nombres";
				break;
			case 1:
				prop = "apellidos";
				break;
			case 2:
				prop = "profesion";
				break;
		}
		
		crit.setFirstResult(start);
		crit.setMaxResults(length);
		
		if(order.equals("asc")){
			crit.addOrder(Order.asc(prop));
		} else{
			crit.addOrder(Order.desc(prop));
		}
		
		if(search != null){
			Criterion byName = Restrictions.ilike("nombres", "%"+search+"%");
			Criterion bySurname = Restrictions.ilike("apellidos", "%"+search+"%");
			Criterion byJob = Restrictions.ilike("profesion", "%"+search+"%");
			
			crit.add(Restrictions.or(byName,bySurname,byJob));
		}

		return crit.list();
	}
	
	public Laboratorista getByExactName(String lName){
		Criteria crit = getCriteria();
		
		crit.add(Restrictions.sqlRestriction("nombres||' '||apellidos = '"+lName+"'"));
		
		return (Laboratorista) crit.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Laboratorista> getByName(String name){
		List<Laboratorista> list;
		Criteria crit = getCriteria();
		Criterion names = Restrictions.ilike("nombres", name, MatchMode.ANYWHERE);
		Criterion last = Restrictions.ilike("apellidos", name, MatchMode.ANYWHERE);
		crit.add(Restrictions.or(names,last));
		list = crit.list();
		
		logger.debug(list.size()+" pacients found");
		
		return list;
	}
	
	
}
