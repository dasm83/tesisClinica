package com.tesis.clinicapp.web.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tesis.clinicapp.model.Citas;
import com.tesis.clinicapp.service.CitasService;
import com.tesis.clinicapp.service.ExamenService;
import com.tesis.clinicapp.service.PacienteService;

@Controller
public class IndexController {
	
	private static final String URL = "/index.htm";
	
	@Autowired
	private ExamenService exService;
	
	@Autowired
	private PacienteService patService;

	@Autowired
	private CitasService citService;

	@RequestMapping(method = RequestMethod.GET, value = URL)
	public String get(HttpServletRequest request){
		 List<Citas> C;
		 request.setAttribute("title", "       Actividades Programadas");
		
		
		//request.setAttribute("exams", exService.getLatest(10));
		
		//request.setAttribute("exams", exService.getLatest(10));
		
		request.setAttribute("citas",citService.getByDate());
		C = citService.getByDate();
	    System.out.println(C.size());	    
		return "index";
	}
	
}
