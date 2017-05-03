package com.tesis.clinicapp.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import com.tesis.clinicapp.dao.UsuarioDAO;
import com.tesis.clinicapp.model.Usuario;
import com.tesis.clinicapp.util.GenericDAOImpl;

@Repository("UsuarioDAO")
public class UsuarioDAOImpl extends GenericDAOImpl<Usuario, String> implements UsuarioDAO {

	public UsuarioDAOImpl(){
		super(Usuario.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> getFilteredList(int start, int length, int col, String order){
		Criteria crit = getCriteria();
		String prop = "";
		
		switch(col){
			case 0:
				prop = "nombre";
				break;
			case 1:
				prop = "rol";
				break;
			case 2:
				prop = "habilitado";
				break;
		}
		
		crit.setFirstResult(start);
		crit.setMaxResults(length);
		
		if(order.equals("asc")){
			crit.addOrder(Order.asc(prop));
		} else{
			crit.addOrder(Order.desc(prop));
		}

		return crit.list();
	}

}
