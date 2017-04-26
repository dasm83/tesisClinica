package com.tesis.clinicapp.web.controller.maintenance;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.tesis.clinicapp.service.UsuarioService;

@Controller
public class UsuarioMaintenanceController {

	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(UsuarioMaintenanceController.class);
	
	private static final String URL = "/maintenance/usuario.htm";
	private static final String URLj = "/maintenance/user-ajax.json";
	private static final String URLops = "/maintenance/userOp.txt";
	
	private static final String JSP = "/maintenance/usuario";
	
	private static final String FORM = "UsuariosMainForm";
	
	@Autowired
	private UsuarioService userService;
	
}
