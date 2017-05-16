package com.tesis.clinicapp.web.controller.maintenance;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tesis.clinicapp.model.CatalogoExamen;
import com.tesis.clinicapp.model.CatalogoItemsExamen;
import com.tesis.clinicapp.model.Examen;
import com.tesis.clinicapp.model.ItemsExamen;
import com.tesis.clinicapp.model.ItemsExamenId;
import com.tesis.clinicapp.model.ItemsValoresReferencia;
import com.tesis.clinicapp.model.Laboratorista;
import com.tesis.clinicapp.model.Paciente;
import com.tesis.clinicapp.service.CatExamenService;
import com.tesis.clinicapp.service.ExamenService;
import com.tesis.clinicapp.service.ItemsExamenService;
import com.tesis.clinicapp.service.ItemsValoresRefService;
import com.tesis.clinicapp.service.LaboratoristaService;
import com.tesis.clinicapp.service.PacienteService;
import com.tesis.clinicapp.util.TableData;
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
	
	@Autowired
	private CatExamenService catExamService;
	
	@Autowired
	private PacienteService pacientService;
	
	@Autowired
	private LaboratoristaService labService;
	
	@Autowired
	private ItemsExamenService itemService;

	@Autowired
	private ItemsValoresRefService valoresService;

	
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
	@RequestMapping(method = RequestMethod.POST, value = URLj, produces = "application/json")
	public @ResponseBody TableData dataTable(HttpServletRequest request, @RequestParam(value="order[0][column]") int col,
			@RequestParam(value="order[0][dir]") String dir, @RequestParam(required=false,name="search") String search){
		TableData json = new TableData();
		List<Map<String,String>> data = getExamsList(
					Integer.parseInt(request.getParameter("start")),
					Integer.parseInt(request.getParameter("length")),
					col,
					dir,
					search
				);
		
		json.setDraw(Integer.parseInt(request.getParameter("draw")));
		json.setRecordsTotal(examService.count());
		json.setRecordsFiltered(data.size());
		json.setData(data);
		
		return json;
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
		form.setEdad(ex.getPaciente().getEdad());
		form.setLaboratorista(ex.getLaboratorista().toString());
		form.setFecha(ex.transformDateForView());
		form.setObservaciones(ex.getObservaciones());
		
		List<ExamDetailFormItem> items = new ArrayList<>();
		ex.getItemsExamens().forEach(item->{
			ExamDetailFormItem formItem = new ExamDetailFormItem();
			ItemsValoresReferencia vn = valoresService.getSingle(item.getCatalogoItemsExamen().getId(), ex.getPaciente().getSexo(), ex.getPaciente().getEdad());
			String fullVn = (vn != null)?
					(vn.getTipoRango().equals("-")?
							vn.getValorRefMinimo()+" - "+vn.getValorRefMaximo():formatRange(vn.getTipoRango())+" "+vn.getValorRefMaximo())
					:"";
			formItem.setNombre(item.getCatalogoItemsExamen().getNombre());
			formItem.setValor(item.getValor());
			formItem.setUnidad(item.getCatalogoItemsExamen().getUnidad());
			formItem.setValorRef((!fullVn.isEmpty())?"V.N. "+fullVn+" "+item.getCatalogoItemsExamen().getUnidad():"");
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
			formItem.setId(item.getId());
			formItem.setNombre(item.getNombre());
			formItem.setValor("");
			formItem.setUnidad("");
			formItem.setValorRef("V.N. ");
			items.add(formItem);
		});
		
		form.setItems(items);
		form.setExamType(cat.getId());
		
		request.setAttribute("title", "Exámen de "+cat.getNombre());
		
		return new ModelAndView(JSPdet,FORMdet,form);
	}
	
	/**
	 * Insert or Update op
	 * 
	 * @param request
	 * @param form
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(method = RequestMethod.POST, value = URLops, params = "op=iou", produces = "text/plain")
	public @ResponseBody String modExamTable(HttpServletRequest request, HttpServletResponse response, ExamDetailForm form) throws ParseException{
		Long id = form.getExamId();
		Laboratorista lab = labService.getByExactName(form.getLaboratorista());
		Paciente pt = pacientService.getByExactName(form.getPaciente());
		String msj = "";
		
		msj = validate(pt, lab);
		
		if(!msj.isEmpty()){
			response.setStatus(500);
			return msj;
		}
		
		msj = "Registro guardado";
		
		if(id != null){ // this is an update because we have an exam id, so we'll read exam from db
			Examen ex = examService.findById(id);
			
			Set<ItemsExamen> exItems = ex.getItemsExamens();;
			
			form.getItems().forEach(formItem->{
				for(ItemsExamen exItem:exItems){
					if(exItem.getCatalogoItemsExamen().getNombre().equals(formItem.getNombre())){
						exItem.setValor(formItem.getValor());
						break;
					}
				}
			});
			
			ex.setItemsExamens(exItems);
			
			ex.setLaboratorista(lab);
			ex.setPaciente(pt);
			ex.setDateForModel(form.getFecha());
			ex.setObservaciones(form.getObservaciones());
			
			examService.saveOrUpdate(ex);
		} else{ // this is an insert 'cuz we don't have an exam id yet
			Examen ex = new Examen();
			CatalogoExamen cat = catExamService.findById(form.getExamType());
			
			ex.setCatalogoExamen(cat);
			ex.setLaboratorista(lab);
			ex.setPaciente(pt);
			ex.setDateForModel(form.getFecha());
			ex.setObservaciones(form.getObservaciones());
			
			examService.save(ex);
			
			try{
				form.getItems().forEach(formItem->{
					ItemsExamen itemOnEx = new ItemsExamen();
					itemOnEx.setExamen(ex);
					for(CatalogoItemsExamen item : cat.getCatalogoItemsExamens()){
						if(item.getNombre().equals(formItem.getNombre())){
							itemOnEx.setCatalogoItemsExamen(item);
							itemOnEx.setValor(formItem.getValor());
							itemOnEx.setId(new ItemsExamenId(Math.toIntExact(ex.getId()), Math.toIntExact(item.getId())));
							break;
						}
					}
					
					itemService.save(itemOnEx);
				});
			} catch(Exception e){
				examService.delete(ex);
				msj = e.getMessage();
				response.setStatus(500);
				return msj;
			}
		}
		
		response.setStatus(200);
		return msj;
	}
	
	private String validate(Paciente p, Laboratorista l) {
		String msj = "";
		
		if(p == null){
			msj = "Paciente ingresado no existe<br>";
		} 
		if(l == null){
			msj = msj+"Laboratorista ingresado no existe<br>";
		}
		
		return msj;
	}

	/**
	 * We only get the data we want to show in the view
	 * 
	 * @return a list filled with maps; each map holds specific values of a single exam
	 */
	@SuppressWarnings("unchecked")
	private List<Map<String,String>> getExamsList(int start,int length, int col, String order, String search){
		List<Map<String,String>> brief = new ArrayList<>();
		List<Examen> exams = examService.getFilteredList(start,length,col,order,search);
		
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
