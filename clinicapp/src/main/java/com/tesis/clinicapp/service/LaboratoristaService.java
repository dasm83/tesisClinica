package com.tesis.clinicapp.service;

import com.tesis.clinicapp.model.Laboratorista;
import com.tesis.clinicapp.util.GenericService;

public interface LaboratoristaService extends GenericService<Laboratorista,Long> {
	
	public Laboratorista findByAltId(String dui);

}
