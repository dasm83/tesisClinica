package com.tesis.clinicapp.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tesis.clinicapp.model.CatalogoExamen;
import com.tesis.clinicapp.model.ItemsValoresReferencia;
import com.tesis.clinicapp.model.Laboratorista;
import com.tesis.clinicapp.model.Paciente;
import com.tesis.clinicapp.service.CatExamenService;
import com.tesis.clinicapp.service.ItemsValoresRefService;
import com.tesis.clinicapp.service.LaboratoristaService;
import com.tesis.clinicapp.service.PacienteService;
import com.tesis.clinicapp.util.AutocompleteData;

@Controller
public class AutocompleteController {
	
	@Autowired
	private PacienteService pacientService;
	
	@Autowired
	private LaboratoristaService labService;
	
	@Autowired
	private CatExamenService exService;
	
	@Autowired
	private ItemsValoresRefService valoresService;
	
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
	
	@RequestMapping(method = RequestMethod.POST, value = "/*/auto.json", params = "sugVN", produces = "application/json")
	public @ResponseBody AutocompleteData getRefValsForPat(HttpServletRequest request){
		AutocompleteData vns = new AutocompleteData();
		Paciente p = pacientService.findById(new Long(request.getParameter("pId")));
		CatalogoExamen ex = exService.findById(new Long(request.getParameter("exId")));
		
		ex.getCatalogoItemsExamens().forEach(item->{
			ItemsValoresReferencia vn = valoresService.getSingle(item.getId(), p.getSexo(), p.getEdad());
			String fullVn = (vn != null)?
					(StringUtils.isEmpty(vn.getTipoRango())?
							vn.getValorRefMinimo()+" - "+vn.getValorRefMaximo():formatRange(vn.getTipoRango())+" "+vn.getValorRefMaximo())
					:"";
			vns.addPair(item.getId().toString(), (!fullVn.isEmpty())?"V.N. "+fullVn+" "+item.getUnidad():"");
		});
		
		return vns;
	}
	
	/**
	 * @param symbol
	 * @return
	 * 
	 * With this method we determine what type of message to display to the user for the "tipo_rango"
	 * a reference value has
	 */
	private String formatRange(String symbol){
		String word = "";
		
		switch(symbol){
		case ">":
			word = "Mayor a";
			break;
		case "<":
			word = "Menor de";
			break;
		case "<=":
			word = "Hasta";
			break;
		}
		
		return word;
	}

}
