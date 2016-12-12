package com.tesis.clinicapp.dao.impl;

import org.springframework.stereotype.Repository;

import com.tesis.clinicapp.dao.ItemsExamenDAO;
import com.tesis.clinicapp.model.ItemsExamen;
import com.tesis.clinicapp.model.ItemsExamenId;
import com.tesis.clinicapp.util.GenericDAOImpl;

@Repository("ItemsExamenDAO")
public class ItemsExamenDAOImpl extends GenericDAOImpl<ItemsExamen, ItemsExamenId> implements ItemsExamenDAO{

}
