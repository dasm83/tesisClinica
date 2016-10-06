package com.tesis.clinicapp.util;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;

public interface GenericDAO<T, ID extends Serializable> {

	T save(final T o);
	void delete(final Object object);
	T findById(ID id);
	void saveOrUpdate(final T o);
	List<T> findAll();
	Criteria getCriteria();
	
}
