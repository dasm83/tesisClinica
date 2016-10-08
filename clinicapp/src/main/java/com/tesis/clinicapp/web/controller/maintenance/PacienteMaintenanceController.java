package com.tesis.clinicapp.web.controller.maintenance;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tesis.clinicapp.model.PacienteDatos;
import com.tesis.clinicapp.service.PacienteService;
import com.tesis.clinicapp.web.form.maintenance.pacientesMainForm;

@Controller
public class PacienteMaintenanceController {
	
	private static final String URL = "/maintenance/pacientes.htm";
	private static final String URLx = "/maintenance/pacient-ajax.htm";
	private static final String JSP = "/maintenance/pacientes";
	private static final String JSPx = "pacient-ajax";
	private static final String FORM = "pacientesMainForm";
	
	@Autowired
	private PacienteService pacientService;
	
	@RequestMapping(method = RequestMethod.GET, value = URL)
	public ModelAndView get(HttpServletRequest request) throws JsonGenerationException, JsonMappingException, IOException{
		
		List<PacienteDatos> pacientes = pacientService.findAll();
		
		ObjectMapper mapper = new ObjectMapper();
		
		request.setAttribute("title", "Pacientes");
		
		request.setAttribute("pacientList", mapper.writeValueAsString(pacientes));
		
		return new ModelAndView(JSP,FORM,new pacientesMainForm());
		
	}
	
	@RequestMapping(method = RequestMethod.POST, value = URLx)
	public String postX(HttpServletRequest request){
		return JSPx;
	}

}
