package com.tesis.clinicapp.web.controller.maintenance;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tesis.clinicapp.model.Examen;
import com.tesis.clinicapp.service.ExamenService;
import com.tesis.clinicapp.web.form.maintenance.examenesMainForm;

@Controller
public class ExamenMaintenanceController {
	
	private static final Logger logger = Logger.getLogger(ExamenMaintenanceController.class);

	/**
	 * where the request is coming from
	 */
	private static final String URL = "/maintenance/examenes.htm";
	private static final String URLx = "/maintenance/exam-ajax.htm";
	/**
	 * name of the jsp which corresponds to URL
	 */
	private static final String JSP = "/maintenance/examenes";
	private static final String JSPx = "/maintenance/exam-ajax";
	/**
	 * name of the form created in JSP
	 */
	private static final String FORM = "examenesMainForm";
	
	
	@Autowired
	private ExamenService examService;

	
	@RequestMapping(method = RequestMethod.GET, value = URL)
    public ModelAndView get(HttpServletRequest request) throws JsonGenerationException, JsonMappingException, IOException{
		
		List<Examen> exams = examService.findAll();
		
		ObjectMapper mapper = new ObjectMapper();
		request.setAttribute("examsList", mapper.writeValueAsString(exams));
		
		/// we have to set the view's title (text inserted on title html tag)
		request.setAttribute("title", "Examenes");
		
		return new ModelAndView(JSP,FORM,new examenesMainForm());
		
	}
	
}
