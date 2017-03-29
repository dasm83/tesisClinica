package com.tesis.clinicapp.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.tesis.clinicapp.dao.CitasDAO;
import com.tesis.clinicapp.model.Citas;
import com.tesis.clinicapp.model.Paciente;
import com.tesis.clinicapp.util.GenericDAOImpl;

@Repository("CitasDAO")
public class CitasDAOImpl extends GenericDAOImpl<Citas, Long> implements CitasDAO {

	public CitasDAOImpl(){
		super(Citas.class);
	}

	@SuppressWarnings("rawtypes")
	public List<Citas> getByDate() {
		List<Citas> list;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MMMMM/yyyy hh:mm:ss");  
		Criteria crit = getCriteria();
		GregorianCalendar gregorianCalendar = new GregorianCalendar();
		Calendar c1 = GregorianCalendar.getInstance();
		c1.set(Calendar.HOUR_OF_DAY,0);
		c1.set(Calendar.MINUTE,0);
		c1.set(Calendar.SECOND,0);
		System.out.println(sdf.format(c1.getTime()));	
		crit.add(Restrictions.gt("fechaReserva", c1.getTime()));
		c1.set(Calendar.HOUR_OF_DAY,23);
		c1.set(Calendar.MINUTE,59);
		c1.set(Calendar.SECOND,59);
		System.out.println(sdf.format(c1.getTime()));
		crit.add(Restrictions.lt("fechaReserva", c1.getTime()));
		crit.addOrder(Order.asc("fechaReserva"));
		list = crit.list();
		return list;
	}
	
	
	
}
