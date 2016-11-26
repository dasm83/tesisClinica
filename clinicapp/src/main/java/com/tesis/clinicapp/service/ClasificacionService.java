package com.tesis.clinicapp.service;

import java.util.List;

import com.tesis.clinicapp.model.Clasificacion;
import com.tesis.clinicapp.util.GenericService;

public interface ClasificacionService extends GenericService<Clasificacion,Long>{

	public List getFilteredList(int draw,int start, int length);

}
