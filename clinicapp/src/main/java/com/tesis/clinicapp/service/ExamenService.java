package com.tesis.clinicapp.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tesis.clinicapp.model.Examen;
import com.tesis.clinicapp.util.GenericService;

public interface ExamenService extends GenericService<Examen, Long>{

	@SuppressWarnings("rawtypes")
	@Transactional(readOnly = true)
	List getFilteredList(int draw, int start, int length);

}
