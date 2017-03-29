package com.tesis.clinicapp.service;

import java.util.List;

import com.tesis.clinicapp.model.Citas;
import com.tesis.clinicapp.model.Paciente;
import com.tesis.clinicapp.util.GenericService;

public interface CitasService extends GenericService<Citas, Long> {
	
	public List<Citas> getByDate();
	

}
