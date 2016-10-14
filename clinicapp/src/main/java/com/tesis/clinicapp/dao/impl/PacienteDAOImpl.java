package com.tesis.clinicapp.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.tesis.clinicapp.dao.PacienteDAO;
import com.tesis.clinicapp.model.PacienteDatos;
import com.tesis.clinicapp.util.GenericDAOImpl;

@Repository("PacienteDAO")
public class PacienteDAOImpl extends GenericDAOImpl<PacienteDatos, Long> implements PacienteDAO {

	/**
	 * constructor notifies parent class about which model/entity we are going to work with
	 */
	public PacienteDAOImpl(){
		super(PacienteDatos.class);
	}
	
	public PacienteDatos findByAltId(String dui){
		/// criteria object is created to add restrictions to the generated query
		Criteria crit = getCriteria();
		/// getting the patient with a dui equal to our dui parameter
		crit.add(Restrictions.eq("dui", dui));
		/// i'm sure i'll only get one patient with this dui, so i use uniqueResult
		/// if multiple results are expected i would use .list() method instead
		return (PacienteDatos) crit.uniqueResult();
	}
	
}
