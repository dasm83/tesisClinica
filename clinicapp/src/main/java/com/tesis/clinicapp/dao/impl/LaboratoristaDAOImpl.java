package com.tesis.clinicapp.dao.impl;

import com.tesis.clinicapp.dao.LaboratoristaDAO;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import com.tesis.clinicapp.dao.LaboratoristaDAO;
import com.tesis.clinicapp.model.Laboratorista;
import com.tesis.clinicapp.util.GenericDAOImpl;

@Repository("LaboratoristaDAO")
public class LaboratoristaDAOImpl extends GenericDAOImpl<Laboratorista,Long> implements LaboratoristaDAO {
		
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

	@Override
	public List getFilteredList(int draw, int star, int length) {
		Criteria crit = getCriteria();
		
		crit.setFirstResult(star);
		crit.setMaxResults(length);

		return crit.list();
	}
	
	
	
	
}
