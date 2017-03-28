package com.tesis.clinicapp.dao;

import java.util.List;

import com.tesis.clinicapp.model.Citas;
import com.tesis.clinicapp.util.GenericDAO;

public interface CitasDAO extends GenericDAO<Citas, Long> {

	List<Citas> getByDate();
}
