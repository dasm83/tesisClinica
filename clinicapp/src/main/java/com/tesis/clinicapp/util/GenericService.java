package com.tesis.clinicapp.util;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.springframework.transaction.annotation.Transactional;

public interface GenericService<T extends Serializable, ID extends Serializable> {
	
	@Transactional(readOnly=false)
	T save(final T o);
	@Transactional
	void delete(final Object object);
	
	T findById(ID id);
	@Transactional(readOnly=false)
	void saveOrUpdate(final T o);
	
	List<T> findAll();
	Criteria getCriteria();
	Long count();
}
