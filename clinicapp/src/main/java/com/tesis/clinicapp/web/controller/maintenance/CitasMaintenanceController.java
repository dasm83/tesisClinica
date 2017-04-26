package com.tesis.clinicapp.web.controller.maintenance;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tesis.clinicapp.model.CatalogoExamen;
import com.tesis.clinicapp.model.CatalogoItemsExamen;
import com.tesis.clinicapp.model.Citas;
import com.tesis.clinicapp.model.Examen;
import com.tesis.clinicapp.service.CitasService;
import com.tesis.clinicapp.web.form.maintenance.laboratoristaMainForm;

@Controller
public class CitasMaintenanceController {
	
	/**
	 * where the request is coming from
	 */
	private static final String URL = "/maintenance/citas.htm";
	private static final String URLcitasj = "/maintenance/citas-ajax.json";
	/**
	 * name of the jsp which corresponds to URL
	 */
	private static final String JSP = "/maintenance/citas";
	/**
	 * name of the form created in JSP
	 */
	
	@Autowired
	private CitasService citService;
	
	@RequestMapping(method = RequestMethod.GET, value = URL)
    public ModelAndView get(HttpServletRequest request){
		request.setAttribute("title", "Programacion de Citas");
		return new ModelAndView(JSP);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = URLcitasj)
	public @ResponseBody List<Map<String,String>> referenceData(HttpServletRequest request){
		 List<Citas> C= citService.findAll();
		 List<Map<String,String>> brief = new ArrayList<>();
		 
		 for(Citas cit : C){
				Map<String,String> list = new HashMap<>();
				list.put("date", cit.getFechaReserva().toString());
				list.put("type","funciono");
				list.put("title","Examen de heces");
				list.put("description", "examen en ayunas");
				list.put("url","#");
				brief.add(list);
			}
		 
		 
		 // for(int q=0;q<1;q++){
		//	  Map<String,String> map = new HashMap<>();
		//	 	map.put("date","2017-04-19 13:30:00");
		//	 	map.put("type","me funciono :)");
		//	 	map.put("title","Examenes de heces");
		//	 	map.put("description","Lorem Ipsum dolor set");
		//	 	map.put("url","http://www.event1.com/");
		//	 	brief.add(map);
		//		}
         return brief;
	}
}
