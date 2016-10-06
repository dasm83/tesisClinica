package com.tesis.clinicapp.util;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.springframework.transaction.annotation.Transactional;

public interface GenericService<T extends Serializable, ID extends Serializable> {
	
	@Transactional(readOnly=false)
	T save(final T o);
	@Transactional(readOnly=false)
	void delete(final Object object);
	@Transactional(readOnly=true)
	T findById(ID id);
	@Transactional(readOnly=false)
	void saveOrUpdate(final T o);
	@Transactional(readOnly=true)
	List<T> findAll();
	Criteria getCriteria();

}
