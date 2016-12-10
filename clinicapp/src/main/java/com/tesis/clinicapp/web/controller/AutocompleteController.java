package com.tesis.clinicapp.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tesis.clinicapp.model.Laboratorista;
import com.tesis.clinicapp.model.Paciente;
import com.tesis.clinicapp.service.LaboratoristaService;
import com.tesis.clinicapp.service.PacienteService;
import com.tesis.clinicapp.util.AutocompleteData;

@Controller
public class AutocompleteController {
	
	@Autowired
	private PacienteService pacientService;
	
	@Autowired
	private LaboratoristaService labService;
	
	/**
	 * Read pacients in db whose name is like "query" param
	 * 
	 * @param request
	 * @return {@link AutocompleteData}
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/*/auto.json", params = "sugP", produces = "application/json")
	public @ResponseBody AutocompleteData getPacientList(HttpServletRequest request){
		AutocompleteData suggestions = new AutocompleteData();
		List<Paciente> pacientList = pacientService.getByName(request.getParameter("query"));
		
		pacientList.forEach(p->{
			suggestions.addPair(p.toString(), p.getId().toString());
		});
		
		return suggestions;
	}
	
	/**
	 * Read laboratoristas in db whose name is like "query" param
	 * 
	 * @param request
	 * @return {@link AutocompleteData}
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/*/auto.json", params = "sugL", produces = "application/json")
	public @ResponseBody AutocompleteData getLabsList(HttpServletRequest request){
		AutocompleteData suggestions = new AutocompleteData();
		List<Laboratorista> labsList = labService.getByName(request.getParameter("query"));
		
		labsList.forEach(l->{
			suggestions.addPair(l.toString(), l.getId().toString());
		});
		
		return suggestions;
	}

}
