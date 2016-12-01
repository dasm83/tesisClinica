package com.tesis.clinicapp.web.controller.maintenance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tesis.clinicapp.model.CatalogoExamen;
import com.tesis.clinicapp.model.Examen;
import com.tesis.clinicapp.model.Laboratorista;
import com.tesis.clinicapp.model.Paciente;
import com.tesis.clinicapp.service.CatExamenService;
import com.tesis.clinicapp.service.ExamenService;
import com.tesis.clinicapp.service.LaboratoristaService;
import com.tesis.clinicapp.service.PacienteService;
import com.tesis.clinicapp.util.AutocompleteData;
import com.tesis.clinicapp.util.TableData;
import com.tesis.clinicapp.web.form.maintenance.ExamDetailForm;
import com.tesis.clinicapp.web.form.maintenance.ExamDetailFormItem;
import com.tesis.clinicapp.web.form.maintenance.ExamenesMainForm;

@Controller
public class ExamenMaintenanceController {
	
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
	
	@Autowired
	private CatExamenService catExamService;
	
	@Autowired
	private PacienteService pacientService;
	
	@Autowired
	private LaboratoristaService labService;

	
	/**
	 * Visualize exams view
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = URL)
    public ModelAndView get(HttpServletRequest request){
		
		/// we have to set the view's title (text inserted on title html tag)
		request.setAttribute("title", "Exámenes");
		
		return new ModelAndView(JSP,FORM,new ExamenesMainForm());
		
	}
	
	/**
	 * Read all exams in database for displaying on datatable
	 * 
	 * @param request
	 * @return {@link TableData} holds datatable params including all exams in db
	 */
	@RequestMapping(method = RequestMethod.POST, value = URLj, params = "draw", produces = "application/json")
	public @ResponseBody TableData dataTable(HttpServletRequest request){
		TableData json = new TableData();
		json.setDraw(Integer.parseInt(request.getParameter("draw")));
		json.setRecordsTotal(1);
		json.setRecordsFiltered(1);
		json.setData(getExamsList(Integer.parseInt(request.getParameter("draw")),
				Integer.parseInt(request.getParameter("start")),
				Integer.parseInt(request.getParameter("length"))));
		return json;
	}
	
	/**
	 * Read pacients in db whose name is like "query" param
	 * 
	 * @param request
	 * @return {@link AutocompleteData}
	 */
	@RequestMapping(method = RequestMethod.POST, value = URLj, params = "sug", produces = "application/json")
	public @ResponseBody AutocompleteData getPacientList(HttpServletRequest request){
		AutocompleteData suggestions = new AutocompleteData();
		List<Paciente> pacientList = pacientService.getByName(request.getParameter("query"));
		
		pacientList.forEach(p->{
			suggestions.addPair(p.toString(), p.getId().toString());
		});
		
		return suggestions;
	}
	
	/**
	 * Delete exam op
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = URLops, params = "op=del", produces = "text/plain")
	public @ResponseBody String delExam(HttpServletRequest request){
		Long id = new Long(request.getParameter("id"));
		Examen ex = examService.findById(id);
		examService.delete(ex);
		return "Registro eliminado";
	}
	
	/**
	 * Visualize exam details view for an update op
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = URLdet, params = "id")
	public ModelAndView getOldDetail(HttpServletRequest request){
		Examen ex = examService.findById(new Long(request.getParameter("id")));
		
		ExamDetailForm form = new ExamDetailForm();
		form.setExamId(ex.getId());
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
	 * Visualize exam detail view to create exam
	 * 
	 * @param request
	 * @return {@link ModelAndView}
	 */
	@RequestMapping(method = RequestMethod.POST, value = URLdet, params = "type")
	public ModelAndView getNewDetail(HttpServletRequest request){
		CatalogoExamen cat = catExamService.findById(new Long(request.getParameter("type")));
		
		ExamDetailForm form = new ExamDetailForm();
		
		List<ExamDetailFormItem> items = new ArrayList<>();
		cat.getCatalogoItemsExamens().forEach(item->{
			ExamDetailFormItem formItem = new ExamDetailFormItem();
			formItem.setNombre(item.getNombre());
			formItem.setValor("");
			items.add(formItem);
		});
		
		form.setItems(items);
		
		request.setAttribute("title", "Exámen de "+cat.getNombre());
		
		return new ModelAndView(JSPdet,FORMdet,form);
	}
	
	/**
	 * Insert or Update op
	 * 
	 * @param request
	 * @param form
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = URLops, params = "op=iou", produces = "text/plain")
	public @ResponseBody String modExamTable(HttpServletRequest request, HttpServletResponse response, ExamDetailForm form){
		Long id = form.getExamId();
		Examen ex = new Examen();
		Laboratorista lab = new Laboratorista();
		Paciente pt = new Paciente();
		String msj = "";
		
		msj = validate(form.getPaciente(), pt, form.getLaboratorista(), lab);
		
		if(!msj.isEmpty()){
			response.setStatus(500);
			return msj;
		}
		
		if(id != null){ // this is an update because we have an exam id, so we'll read exam from db
			ex = examService.findById(id);
			//ex.setLaboratorista();
		}
		
		response.setStatus(200);
		return msj;
	}
	
	private String validate(String pName, Paciente p, String lName, Laboratorista l) {
		String msj = "";
		
		p = pacientService.getByExactName(pName);
		l = labService.getByExactName(lName);
		
		if(p == null){
			msj = "Paciente ingresado no existe<br>";
		} 
		if(l == null){
			msj = msj+"Laboratorista ingresado no existe";
		}
		
		return msj;
	}

	/**
	 * We only get the data we want to show in the view
	 * 
	 * @return a list filled with maps; each map holds specific values of a single exam
	 */
	@SuppressWarnings("unchecked")
	private List<Map<String,String>> getExamsList(int draw,int start,int length){
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
	
	@ModelAttribute(value="examTypes")
	private List<Map<String,String>> getExamTypes(){
		List<Map<String,String>> brief = new ArrayList<>();
		List<CatalogoExamen> types = catExamService.findAll();
		
		types.forEach(type->{
			Map<String,String> list = new HashMap<>();
			list.put("id", type.getId().toString());
			list.put("name", type.getNombre());
			brief.add(list);
		});
		
		return brief;
	}
	
	
	
}
