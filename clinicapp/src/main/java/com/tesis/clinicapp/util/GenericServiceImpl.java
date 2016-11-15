package com.tesis.clinicapp.util;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;

public class GenericServiceImpl<T extends Serializable, ID extends Serializable> implements GenericService<T, ID> {
	
	@Autowired
	GenericDAO<T, ID> dao;

	@Override
	public T save(T o) {
		return dao.save(o);
	}

	@Override
	public void delete(Object object) {
		dao.delete(object);
	}

	@Override
	public T findById(ID id) {
		return dao.findById(id);
	}

	@Override
	public void saveOrUpdate(T o) {
		dao.saveOrUpdate(o);
	}

	@Override
	public List<T> findAll() {
		return dao.findAll();
	}

	@Override
	public Criteria getCriteria() {
		return dao.getCriteria();
	}

	@Override
	public Long count(){
		return dao.count();
	}

}
