package com.tesis.clinicapp.web.controller.maintenance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tesis.clinicapp.model.Examen;
import com.tesis.clinicapp.service.ExamenService;
import com.tesis.clinicapp.web.dataTable.DataToJSON;
import com.tesis.clinicapp.web.form.maintenance.ExamDetailForm;
import com.tesis.clinicapp.web.form.maintenance.ExamDetailFormItem;
import com.tesis.clinicapp.web.form.maintenance.ExamenesMainForm;

@Controller
public class ExamenMaintenanceController {
	
	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(ExamenMaintenanceController.class);

	/**
	 * where the request is coming from
	 */
	private static final String URL = "/maintenance/examenes.htm";
	private static final String URLj = "/maintenance/exam-ajax.json";
	private static final String URLops = "/maintenance/examOp.txt";
	private static final String URLdet = "/maintenance/detalleExamen.htm";
	
	/**
	 * name of the jsp which corresponds to URL
	 */
	private static final String JSP = "/maintenance/examenes";
	private static final String JSPdet = "/maintenance/detalleExamen";
	/**
	 * name of the form created in JSP
	 */
	private static final String FORM = "ExamenesMainForm";
	private static final String FORMdet = "ExamDetailForm";
	
	
	@Autowired
	private ExamenService examService;

	
	@RequestMapping(method = RequestMethod.GET, value = URL)
    public ModelAndView get(HttpServletRequest request){
		
		/// we have to set the view's title (text inserted on title html tag)
		request.setAttribute("title", "Exámenes");
		
		return new ModelAndView(JSP,FORM,new ExamenesMainForm());
		
	}
	
	@RequestMapping(method = RequestMethod.POST, value = URLj, params = "draw", produces = "application/json")
	public @ResponseBody DataToJSON dataTable(HttpServletRequest request){
		DataToJSON json = new DataToJSON();
		json.setDraw(Integer.parseInt(request.getParameter("draw")));
		json.setRecordsTotal(1);
		json.setRecordsFiltered(1);
		json.setData(getExamsList(Integer.parseInt(request.getParameter("draw")),
				Integer.parseInt(request.getParameter("start")),
				Integer.parseInt(request.getParameter("length"))));
		return json;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = URLops, params = "op=del", produces = "text/plain")
	public @ResponseBody String delExam(HttpServletRequest request){
		Long id = new Long(request.getParameter("id"));
		Examen ex = examService.findById(id);
		examService.delete(ex);
		return "Registro eliminado";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = URLdet, params = "id")
	public ModelAndView getOldDetail(HttpServletRequest request){
		Examen ex = examService.findById(new Long(request.getParameter("id")));
		
		ExamDetailForm form = new ExamDetailForm();
		form.setPaciente(ex.getPaciente().toString());
		form.setLaboratorista(ex.getLaboratorista().toString());
		form.setFecha(ex.transformDateForView());
		form.setObservaciones(ex.getObservaciones());
		
		List<ExamDetailFormItem> items = new ArrayList<>();
		ex.getItemsExamens().forEach(item->{
			ExamDetailFormItem formItem = new ExamDetailFormItem();
			formItem.setNombre(item.getCatalogoItemsExamen().getNombre());
			formItem.setValor(item.getValor());
			items.add(formItem);
		});
		
		form.setItems(items);
		
		request.setAttribute("title", "Exámen de "+ex.getCatalogoExamen().getNombre());
		
		return new ModelAndView(JSPdet,FORMdet,form);
	}
	
	/**
	 * We only get the data we want to show in the view
	 * 
	 * @return a list filled with maps; each map holds specific values of a single exam
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String,String>> getExamsList(int draw,int start,int length){
		List<Map<String,String>> brief = new ArrayList<>();
		List<Examen> exams = examService.getFilteredList(draw,start,length);
		
		for(Examen exam : exams){
			Map<String,String> list = new HashMap<>();
			list.put("DT_RowId", exam.getId().toString());
			list.put("tipo", exam.getCatalogoExamen().getNombre());
			list.put("lab", exam.getLaboratorista().toString());
			list.put("date", exam.transformDateForView());
			brief.add(list);
		}
		
		return brief;
	}
	
}
