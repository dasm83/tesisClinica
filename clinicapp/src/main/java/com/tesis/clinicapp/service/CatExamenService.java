package com.tesis.clinicapp.service;

import java.util.List;

import com.tesis.clinicapp.model.CatalogoExamen;
import com.tesis.clinicapp.util.GenericService;

public interface CatExamenService extends GenericService<CatalogoExamen, Long> {

	public List getFilteredList(int draw,int start, int length);
}
