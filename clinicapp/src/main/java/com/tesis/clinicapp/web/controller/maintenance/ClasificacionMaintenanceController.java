package com.tesis.clinicapp.web.controller.maintenance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tesis.clinicapp.model.Clasificacion;
import com.tesis.clinicapp.model.Laboratorista;
import com.tesis.clinicapp.service.ClasificacionService;
import com.tesis.clinicapp.web.dataTable.DataToJSON;
import com.tesis.clinicapp.web.form.maintenance.clasificacionMainForm;
import com.tesis.clinicapp.web.form.maintenance.laboratoristaMainForm;

@Controller
public class ClasificacionMaintenanceController {

	private final String URL = "/maintenance/clasificacion.htm";
	private final String URLj = "/maintenance/clasificacion-ajax.json";
	private final String URLt = "/maintenance/clasificacion.txt";
	
	private final String JSP = "/maintenance/clasificacion";
	
	private static final String FORM = "clasificacionMainForm";
	
	
	@Autowired
	private ClasificacionService clasifiService;
	
	
	@RequestMapping(method = RequestMethod.GET , value = URL)
	public ModelAndView get(HttpServletRequest request){
		
		request.setAttribute("title","Clasificacion de Examenes");
		return new ModelAndView(JSP,FORM,new clasificacionMainForm());
		
	}
	
	@RequestMapping(method = RequestMethod.POST, value = URLt, produces = "text/plain")
    public @ResponseBody String delExam(HttpServletRequest request, clasificacionMainForm form){
		String mensaje=null;
		Clasificacion cla= new Clasificacion();
		
		if(form.getAction().equals("I") || form.getAction().equals("U")){
			
			cla.setId(form.getId());
			cla.setCategoria(form.getCategory());
			cla.setDescripcion(form.getDescription());

			clasifiService.saveOrUpdate(cla);
			mensaje="Guardado Satisfactoriamente";
			
		}
		else if(form.getAction().equals("d")){
			cla = clasifiService.findById(form.getId());
			clasifiService.delete(cla);	
			mensaje="Paciente eliminado satisfactoriamente";
		}
		
		return mensaje;
		
	}
	
	
	@RequestMapping(method= RequestMethod.POST, value= URLj, produces="application/json")
	public @ResponseBody DataToJSON dataTable(HttpServletRequest request){
		DataToJSON json = new DataToJSON();
		json.setDraw(Integer.parseInt(request.getParameter("draw")));
		json.setRecordsFiltered(clasifiService.count());
		json.setRecordsTotal(clasifiService.count());
		json.setData(getClasifiList(Integer.parseInt(request.getParameter("draw")),
				Integer.parseInt(request.getParameter("start")),
				Integer.parseInt(request.getParameter("length"))));
		return json;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Map<String,String>> getClasifiList(int draw,int start,int length){
		List<Map<String,String>> brief = new ArrayList<>();
		List<Clasificacion> cla = clasifiService.getFilteredList(draw, start, length);
		//List<Laboratorista> lab = LabService.getFilteredList(draw,start,length);
		
		for(Clasificacion obj : cla ){
			Map<String,String> list = new HashMap<>();
			list.put("DT_RowId", obj.getId().toString());
			list.put("categoria", obj.getCategoria().toString());
			list.put("descripcion", obj.getDescripcion().toString());
			brief.add(list);
			
		}
		
		return brief;
	}
	
	
				
}
