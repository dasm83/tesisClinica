package com.tesis.clinicapp.service;
import java.util.List;

import com.tesis.clinicapp.model.Laboratorista;
import com.tesis.clinicapp.util.GenericService;

public interface LaboratoristaService extends GenericService<Laboratorista,Long> {
	
	public Laboratorista findByAltId(String dui);
	public List getFilteredList(int draw,int star, int length);

}
