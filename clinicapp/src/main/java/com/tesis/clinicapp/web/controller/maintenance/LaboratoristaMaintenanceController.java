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
import com.tesis.clinicapp.model.Paciente;
import com.tesis.clinicapp.service.LaboratoristaService;
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
	private LaboratoristaService laboratoristService;
	
	@RequestMapping(method = RequestMethod.GET, value = URL)
    public ModelAndView get(HttpServletRequest request) throws JsonGenerationException, JsonMappingException, IOException{
		
		/// we obtain a list with all the patients available in database
		List<Laboratorista> laboratoristas= laboratoristService.findAll();
		/// patients list is converted to a json array
		ObjectMapper mapper = new ObjectMapper();
		request.setAttribute("laboratoristaList", mapper.writeValueAsString(laboratoristas));
		
		/// we have to set the view's title (text inserted on title html tag)
//		request.setAttribute("title", "Pacientes");
		
		return new ModelAndView(JSP,FORM,new laboratoristaMainForm());
		
	}
	
	@RequestMapping(method = RequestMethod.POST, value = URLx)
	public String postX(HttpServletRequest request, HttpServletResponse response, laboratoristaMainForm form){
	
		Laboratorista laboratorista= new Laboratorista();
		
		if(form.getAction().equals("I") || form.getAction().equals("U")){
			
			laboratorista.setId(form.getId());
			laboratorista.setNombres(form.getNames());
			laboratorista.setApellidos(form.getSurnames());
			laboratorista.setDui(form.getDui());
			laboratorista.setEdad(form.getAge());
			laboratorista.setNit(form.getNit());
			laboratorista.setJvplc(form.getJvplc());
			laboratorista.setProfesion(form.getJob());

			laboratoristService.saveOrUpdate(laboratorista);
			response.setStatus(200);
			request.setAttribute("msj", "Guarado Satisfactoriamente");
			
		}
		else if(form.getAction().equals("d")){
			laboratorista = laboratoristService.findByAltId(form.getDui());
			laboratoristService.delete(laboratorista);
			response.setStatus(200);
			request.setAttribute("msj", "Paciente eliminado satisfactoriamente");
		}
	
		return JSPx;
	
	}
}