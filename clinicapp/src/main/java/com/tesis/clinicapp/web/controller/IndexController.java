package com.tesis.clinicapp.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tesis.clinicapp.service.ExamenService;
import com.tesis.clinicapp.service.PacienteService;

@Controller
public class IndexController {
	
	private static final String URL = "/index.htm";
	
	@Autowired
	private ExamenService exService;
	
	@Autowired
	private PacienteService patService;

	@RequestMapping(method = RequestMethod.GET, value = URL)
	public String get(HttpServletRequest request){
		request.setAttribute("title", "Actividad Reciente");
		
		request.setAttribute("exams", exService.getLatest(10));
		
		request.setAttribute("patients", patService.getLatest(10));
		
		return "index";
	}
	
}
