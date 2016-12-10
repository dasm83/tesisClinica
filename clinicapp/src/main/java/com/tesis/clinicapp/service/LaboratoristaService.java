package com.tesis.clinicapp.service;
import java.util.List;

import com.tesis.clinicapp.model.Laboratorista;
import com.tesis.clinicapp.util.GenericService;

public interface LaboratoristaService extends GenericService<Laboratorista,Long> {
	
	public Laboratorista findByAltId(String dui);
	@SuppressWarnings("rawtypes")
	public List getFilteredList(int draw,int start, int length);
	public Laboratorista getByExactName(String lName);
	public List<Laboratorista> getByName(String name);

}
