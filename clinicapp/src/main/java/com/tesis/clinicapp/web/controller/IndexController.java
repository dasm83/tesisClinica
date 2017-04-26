package com.tesis.clinicapp.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tesis.clinicapp.service.CitasService;

@Controller
public class IndexController {
	
	private static final String URL = "/index.htm";

	@Autowired
	private CitasService citService;

	@RequestMapping(method = RequestMethod.GET, value = URL)
	public String get(HttpServletRequest request){
		request.setAttribute("title", "Actividades Programadas");
		request.setAttribute("citas",citService.getByDate());
	    
		return "index";
	}
	
}
