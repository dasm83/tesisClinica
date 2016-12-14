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
import com.tesis.clinicapp.model.CatalogoExamen;
import com.tesis.clinicapp.model.Examen;
import com.tesis.clinicapp.service.CatExamenService;
import com.tesis.clinicapp.util.TableData;
import com.tesis.clinicapp.web.form.maintenance.CatalogoExamenMainForm;


@Controller
public class CatalogoExamenesMaintenanceController {

	/**
	 * where the request is coming from
	 */
	private static final String URL = "/maintenance/catalogoExamenes.htm";
	private static final String URLj = "/maintenance/catalogoExam-ajax.json";
	private static final String URLops = "/maintenance/catalogoExam.txt";
	private static final String URLcatExam = "/maintenance/detalleExamen.htm";
	
	/**
	 * name of the jsp which corresponds to URL
	 */
	
	private static final String JSP = "/maintenance/catalogoExamenes";
	private static final String JSPdetCat = "/maintenance/nuevoExamenCatalogo";
	
	/**
	 * name of the form created in JSP
	 */
	private static final String FORM = "CatalogoExamenMainform";
	private static final String FORMdet = "ExamDetailForm";
	
	@Autowired
	private CatExamenService catExamService;
	
	
	@RequestMapping(method = RequestMethod.GET, value = URL)
    public ModelAndView get(HttpServletRequest request){
		
		/// we have to set the view's title (text inserted on title html tag)
		request.setAttribute("title", "Catalogo de Examenes");	
		return new ModelAndView(JSP,FORM,new CatalogoExamenMainForm());
		
	}
	
	@RequestMapping(method = RequestMethod.POST, value = URLj, params = "draw", produces = "application/json")
	public @ResponseBody TableData dataTable(HttpServletRequest request){
		TableData json = new TableData();
		json.setDraw(Integer.parseInt(request.getParameter("draw")));
		json.setRecordsTotal(1);
		json.setRecordsFiltered(1);
		json.setData(getCatExamList(Integer.parseInt(request.getParameter("draw")),
				Integer.parseInt(request.getParameter("start")),
				Integer.parseInt(request.getParameter("length"))));
		return json;
	}
	
	@SuppressWarnings("unchecked")
	private List<Map<String,String>>  getCatExamList(int draw, int start, int length) {
		List<Map<String,String>> brief = new ArrayList<>();
		List<CatalogoExamen> catexams = catExamService.getFilteredList(draw,start,length);
		for(CatalogoExamen exam : catexams){
			Map<String,String> list = new HashMap<>();
			list.put("DT_RowId", exam.getId().toString());
			list.put("nombre", exam.getNombre().toString());
			brief.add(list);
		}
		return brief;
		
	}
	
}
