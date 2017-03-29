package com.tesis.clinicapp.web.controller.maintenance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tesis.clinicapp.model.Paciente;
import com.tesis.clinicapp.service.PacienteService;
import com.tesis.clinicapp.util.TableData;
import com.tesis.clinicapp.web.form.maintenance.pacientesMainForm;

@Controller
public class PacienteMaintenanceController {
	
	/**
	 * where the request is coming from
	 */
	private static final String URL = "/maintenance/pacientes.htm";
	private static final String URLj = "/maintenance/paciente-ajax.json";
	private static final String URLt = "/maintenance/paciente.txt";
	/**
	 * name of the jsp which corresponds to URL
	 */
	private static final String JSP = "/maintenance/pacientes";
	/**
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
	public ModelAndView get(HttpServletRequest request){
		
		request.setAttribute("title", "Pacientes");
		
		return new ModelAndView(JSP,FORM,new pacientesMainForm());
		
	}
	
	@RequestMapping(method = RequestMethod.POST, value = URLt, produces = "text/plain")
	public @ResponseBody String postX(HttpServletRequest request, HttpServletResponse response, pacientesMainForm form){
		Paciente paciente = new Paciente();
		String mensaje = null; 
		
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
			mensaje= "Paciente Guardado";
		}
		else if(form.getAction().equals("D")){ // doing delete; bye to patient
			paciente = pacientService.findByAltId(form.getDui()); // by dui so we don't rely on sequence
			pacientService.delete(paciente);
			mensaje = "Paciente Eliminado";
		}
		
		return mensaje;
	}
	
	
	@RequestMapping(method = RequestMethod.POST, value = URLj, produces="application/json")
	public @ResponseBody TableData dataTable(HttpServletRequest request, @RequestParam(value="order[0][column]") int col,
			@RequestParam(value="order[0][dir]") String dir){
		TableData json = new TableData();
		json.setDraw(Integer.parseInt(request.getParameter("draw")));
		json.setRecordsFiltered(pacientService.count());
		json.setRecordsTotal(pacientService.count());
		json.setData(getPacientList(
									Integer.parseInt(request.getParameter("start")),
									Integer.parseInt(request.getParameter("length")),
									col,
									dir
								));
		
		return json;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Map<String,String>> getPacientList(int start,int length,int col,String order){
		List<Map<String,String>> brief = new ArrayList<>();
		List<Paciente> pacient = pacientService.getFilteredList(start,length,col,order);
		
		for(Paciente p:pacient ){
			Map<String,String> list = new HashMap<>();
			list.put("DT_RowId", p.getId().toString());
			list.put("nombres",p.getNombres().toString());
			list.put("apellidos",p.getApellidos().toString());
			list.put("edad", String.valueOf(p.getEdad()));
			list.put("dui",p.getDui().toString());
			list.put("nit",p.getNit().toString());
			list.put("sexo", String.valueOf(p.getSexo()));
			list.put("nacionalidad",p.getNacionalidad().toString());
			list.put("profesion",p.getProfesion().toString());
			list.put("estCivil",p.getEstadoCivil().toString());
			list.put("municipio", p.getMunicipio().toString());
			list.put("departamento", p.getDepartamento().toString());
			list.put("direccion", p.getDireccion().toString());
			list.put("email", p.getEmail().toString());
			list.put("telefono", p.getTelefono().toString());
		
			brief.add(list);
		}
		
		return brief;
	}
	
	

}
