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

import com.tesis.clinicapp.model.Paciente;
import com.tesis.clinicapp.service.PacienteService;
import com.tesis.clinicapp.web.form.maintenance.pacientesMainForm;

@Controller
public class PacienteMaintenanceController {
	
	/**
	 * where the request is coming from
	 */
	private static final String URL = "/maintenance/pacientes.htm";
	private static final String URLx = "/maintenance/pacient-ajax.htm";
	/**
	 * name of the jsp which corresponds to URL
	 */
	private static final String JSP = "/maintenance/pacientes";
	private static final String JSPx = "/maintenance/pacient-ajax";
	/**
	 * name of the form created in JSP
	 */
	private static final String FORM = "pacientesMainForm";
	
	
	/**
	 * A service instance to access PacienteDatos table.
	 * We always access database through services.
	 */
	@Autowired
	private PacienteService pacientService;
	
	@RequestMapping(method = RequestMethod.GET, value = URL)
	public ModelAndView get(HttpServletRequest request) throws JsonGenerationException, JsonMappingException, IOException{
		
		/// we obtain a list with all the patients available in database
		List<Paciente> pacientes = pacientService.findAll();
		/// patients list is converted to a json array
		ObjectMapper mapper = new ObjectMapper();
		request.setAttribute("pacientList", mapper.writeValueAsString(pacientes));
		
		/// we have to set the view's title (text inserted on title html tag)
		request.setAttribute("title", "Pacientes");
		
		return new ModelAndView(JSP,FORM,new pacientesMainForm());
		
	}
	
	@RequestMapping(method = RequestMethod.POST, value = URLx)
	public String postX(HttpServletRequest request, HttpServletResponse response, pacientesMainForm form){
		Paciente paciente = new Paciente();
		
		if(form.getAction().equals("I") || form.getAction().equals("U")){ /// doing insert or update; creating or modifying
			paciente.setId(form.getId());
			paciente.setNombres(form.getNames());
			paciente.setApellidos(form.getSurnames());
			paciente.setDui(form.getDui());
			paciente.setNit(form.getNit());
			paciente.setSexo(form.getSex());
			paciente.setEdad(form.getAge());
			paciente.setProfesion(form.getJob());
			paciente.setNacionalidad(form.getNation());
			paciente.setEstadoCivil(form.getMaritalStatus());
			paciente.setDepartamento(form.getDepartament());
			paciente.setMunicipio(form.getMunicipio());
			paciente.setDireccion(form.getAddress());
			paciente.setTelefono(form.getPhone());
			paciente.setEmail(form.getEmail());
			
			pacientService.saveOrUpdate(paciente);
			/// this indicates an OK status
			response.setStatus(200);
			/// message to be displayed as an ajax response. "msj" attribute exists on ajax jsp
			request.setAttribute("msj", "Paciente guardado satisfactoriamente");
		}
		else if(form.getAction().equals("D")){ // doing delete; bye to patient
			paciente = pacientService.findByAltId(form.getDui()); // by dui so we don't rely on sequence
			pacientService.delete(paciente);
			response.setStatus(200);
			request.setAttribute("msj", "Paciente eliminado satisfactoriamente");
		}
		
		return JSPx;
	}

}
