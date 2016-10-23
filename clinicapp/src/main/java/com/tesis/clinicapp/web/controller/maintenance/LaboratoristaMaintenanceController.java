package com.tesis.clinicapp.web.controller.maintenance;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tesis.clinicapp.model.Laboratorista;
import com.tesis.clinicapp.model.PacienteDatos;
import com.tesis.clinicapp.service.PacienteService;
//import com.tesis.clinicapp.service.PacienteService;
import com.tesis.clinicapp.web.form.maintenance.laboratoristaMainForm;
import com.tesis.clinicapp.web.form.maintenance.pacientesMainForm;


@Controller
public class LaboratoristaMaintenanceController {
	
	/**
	 * where the request is coming from
	 */
	private static final String URL = "/maintenance/laboratorista.htm";
	private static final String URLx = "/maintenance/laboratorista-ajax.htm";
	/**
	 * name of the jsp which corresponds to URL
	 */
	private static final String JSP = "/maintenance/laboratorista";
	private static final String JSPx = "/maintenance/laboratorista-ajax";
	/**
	 * name of the form created in JSP
	 */
	private static final String FORM = "laboratoristaMainForm";
	
	/**
	 * A service instance to access PacienteDatos table.
	 * We always access database through services.
	 */
	@Autowired
	private PacienteService pacientService;
	
	@RequestMapping(method = RequestMethod.GET, value = URL)
    public ModelAndView get(HttpServletRequest request) throws JsonGenerationException, JsonMappingException, IOException{
		
		/// we obtain a list with all the patients available in database
	//	List<PacienteDatos> pacientes = pacientService.findAll();
		/// patients list is converted to a json array
//		ObjectMapper mapper = new ObjectMapper();
//		request.setAttribute("pacientList", mapper.writeValueAsString(pacientes));
		
		/// we have to set the view's title (text inserted on title html tag)
//		request.setAttribute("title", "Pacientes");
		
		return new ModelAndView(JSP,FORM,new laboratoristaMainForm());
		
	}
}