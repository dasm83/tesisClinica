package com.tesis.clinicapp.web.controller.maintenance;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tesis.clinicapp.model.Examen;
import com.tesis.clinicapp.model.Laboratorista;
import com.tesis.clinicapp.service.LaboratoristaService;
import com.tesis.clinicapp.service.PacienteService;
import com.tesis.clinicapp.web.dataTable.DataToJSON;
import com.tesis.clinicapp.web.form.maintenance.laboratoristaMainForm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class LaboratoristaMaintenanceController {
	
	/**
	 * where the request is coming from
	 */
	private static final String URL = "/maintenance/laboratorista.htm";
	private static final String URLj = "/maintenance/lab-ajax.json";
	private static final String URLops = "/maintenance/lab.txt";
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
	private LaboratoristaService LabService;
	
	
	@RequestMapping(method = RequestMethod.GET, value = URL)
    public ModelAndView get(HttpServletRequest request){
		request.setAttribute("title", "Laboratorista");
		return new ModelAndView(JSP,FORM,new laboratoristaMainForm());
		
	}
	
	
	@RequestMapping(method = RequestMethod.POST, value = URLops, produces = "text/plain")
    public @ResponseBody String delExam(HttpServletRequest request, laboratoristaMainForm form){
		String mensaje=null;
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

			LabService.saveOrUpdate(laboratorista);
			mensaje="Guardado Satisfactoriamente";
			
		}
		else if(form.getAction().equals("d")){
			laboratorista = LabService.findByAltId(form.getDui());
			LabService.delete(laboratorista);
			mensaje="Paciente eliminado satisfactoriamente";
		}
		
		return mensaje;
		
	}
	
	
	@RequestMapping(method = RequestMethod.POST, value = URLj, produces = "application/json")
	public @ResponseBody DataToJSON dataTable(HttpServletRequest request){
		DataToJSON json = new DataToJSON();
		json.setDraw(Integer.parseInt(request.getParameter("draw")));
		json.setRecordsTotal(LabService.count());
		json.setRecordsFiltered(LabService.count());
		json.setData(getlabsList(Integer.parseInt(request.getParameter("draw")),
				Integer.parseInt(request.getParameter("start")),
				Integer.parseInt(request.getParameter("length"))));
		
	return json;	
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String,String>> getlabsList(int draw,int start,int length){
		List<Map<String,String>> brief = new ArrayList<>();
		List<Laboratorista> lab = LabService.getFilteredList(draw,start,length);
		
		for(Laboratorista labo : lab  ){
			Map<String,String> list = new HashMap<>();
			list.put("DT_RowId", labo.getId().toString());
			list.put("nombres", labo.getNombres().toString());
			list.put("apellidos", labo.getApellidos().toString());
			list.put("profesion", labo.getProfesion().toString());
			list.put("edad", labo.getEdad().toString());
			list.put("dui", labo.getDui().toString());
			list.put("nit", labo.getNit().toString());
			
			brief.add(list);
		}
		
		return brief;
	}
	
	

}
