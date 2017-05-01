package com.tesis.clinicapp.web.controller.maintenance;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tesis.clinicapp.service.UsuarioService;
import com.tesis.clinicapp.web.form.maintenance.UsuariosMainForm;

@Controller
public class UsuarioMaintenanceController {

	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(UsuarioMaintenanceController.class);
	
	private static final String URL = "/admin/usuario.htm";
	private static final String URLj = "/admin/user-ajax.json";
	private static final String URLops = "/admin/userOp.txt";
	
	private static final String JSP = "/admin/usuario";
	
	private static final String FORM = "UsuariosMainForm";
	
	@Autowired
	private UsuarioService userService;
	
	@RequestMapping(method = RequestMethod.GET, value = URL)
	public ModelAndView get(HttpServletRequest request){
		request.setAttribute("title", "Usuarios");
		return new ModelAndView(JSP,FORM,new UsuariosMainForm());
	}
	
}
