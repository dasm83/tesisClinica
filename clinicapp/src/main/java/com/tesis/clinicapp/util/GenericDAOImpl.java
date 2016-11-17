package com.tesis.clinicapp.util;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("GenericDAO")
public class GenericDAOImpl<T extends Serializable, ID extends Serializable> implements GenericDAO<T, ID>{
	
	@Autowired
	private SessionFactory sf;
	private Class<T> persistentClass;
	
	
	public GenericDAOImpl(){
		
	}
	
	public GenericDAOImpl(Class<T> persistentClass){
		this.persistentClass = persistentClass;
	}


	@SuppressWarnings("unchecked")
	@Override
	public T save(T o) {
		return (T) sf.getCurrentSession().save(o);
	}

	@Override
	public void delete(Object object) {
		sf.getCurrentSession().delete(object);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T findById(ID id) {
		return (T) sf.getCurrentSession().get(persistentClass, id);
	}

	@Override
	public void saveOrUpdate(T o) {
		sf.getCurrentSession().saveOrUpdate(o);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() {
		return getCriteria().list();
	}
	
	public Criteria getCriteria(){
		return sf.getCurrentSession().createCriteria(persistentClass);
	}

	@Override
	public int count() {
	//	int count = (int)getCriteria().setProjection(Projections.rowCount()).uniqueResult();
		return ((Long)getCriteria().setProjection(Projections.rowCount()).uniqueResult()).intValue();
		
	}

}
