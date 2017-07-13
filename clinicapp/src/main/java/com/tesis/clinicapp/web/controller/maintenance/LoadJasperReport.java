package com.tesis.clinicapp.web.controller.maintenance;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.tesis.clinicapp.model.Clasificacion;
import com.tesis.clinicapp.model.Examen;
import com.tesis.clinicapp.model.Paciente;
import com.tesis.clinicapp.service.ClasificacionService;
import com.tesis.clinicapp.service.ExamenService;
import com.tesis.clinicapp.service.ItemsExamenService;
import com.tesis.clinicapp.service.PacienteService;
import com.tesis.clinicapp.util.TableData;
import com.tesis.clinicapp.web.form.maintenance.reportForm;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.export.SimpleHtmlReportConfiguration;

@Controller
public class LoadJasperReport {
	
	JasperReport jasperReport;
	JasperReport subJasperReport;
    JasperPrint jasperPrint;
    
    
	private static final String URL = "/maintenance/reportes.htm";
	private static final String URL2 = "/maintenance/rportes.htm";
	private static final String URLreport = "/maintenance/reportes.txt";
	private static final String URLj = "/maintenance/tipoExamen-ajax.json";
	
	
	private static final String JSP = "/maintenance/reportes";
	private static final String FORM = "reportForm";
	
	private ItemsExamenService itemService;
	
	@Autowired
	ServletContext servletContext;
	
	@Autowired
	private ClasificacionService clasService;
	
	@Autowired
	private ExamenService examService;
	
	@Autowired
	private PacienteService pacientService;
	
	@RequestMapping(method = RequestMethod.GET, value = URL)
    public ModelAndView get(HttpServletRequest request){
		request.setAttribute("title", "Reportes de Perfil Medico");
		return new ModelAndView(JSP,FORM,new reportForm());
	}
	
	@ModelAttribute("jasperRptFormats")
    public ArrayList getJasperRptFormats()
    {
        ArrayList < String> jasperRptFormats = new ArrayList<String>();
        jasperRptFormats.add("Html");
        jasperRptFormats.add("PDF");
        return jasperRptFormats;
    }   
	  
	  @RequestMapping(method = RequestMethod.GET, value = URL2)
	  public ModelAndView generateReport(HttpServletRequest request,HttpServletResponse response,reportForm form,ModelAndView modelAndView) throws ParseException {
		  System.out.println("entro");
		  String SubreportFileName;
		  String reportFileName;
		  int idpersona;
		  int idExamen;
		  HashMap<String,Object> dataSource=new HashMap<String,Object>(); //Parameters as Map to be passed to Jasper
		  System.out.println(form.getPaciente().toString());
          System.out.println(form.getExamen().toString());
	    
          
          if(form.getExamen().toString().equals("completo")){
        	  
        	  SubreportFileName = "p3";
        	  idpersona = Integer.parseInt(form.getPaciente());
        	  dataSource.put("id",idpersona);
        	  reportFileName = "reportFuncionando";
          }else{
        	  
        	  idpersona = Integer.parseInt(form.getPaciente());
        	  idExamen = Integer.parseInt(form.getExamen());
        	  SubreportFileName = "p3";
        	  dataSource.put("id",idpersona);
        	  dataSource.put("exId",idExamen);
        	  reportFileName = "examenEspecifico";
          }
          
          
          Connection conn = null;
	      try {
	          try {
	   
	               Class.forName("org.postgresql.Driver");
	              } catch (ClassNotFoundException e) {
	                  e.printStackTrace();
	              }  
	   
	           conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/clinica","postgres","postgres");
	           if (conn != null)
	       {
	           System.out.println("Database Connected");
	       }
	       else
	       {
	           System.out.println(" connection Failed ");
	       }
	   
	            String rptFormat = "pdf";  //form.getRptFmt();
	   
	             //Parameters as Map to be passed to Jasper
	           //  HashMap<String,Object> dataSource=new HashMap<String,Object>();
	   
	          //    dataSource.put("id",4);
	           //  dataSource.put("dataSource", conn);
	             
	   	   
	              JasperReport jasperReport = getCompiledFile(reportFileName, request);
	              //File subReportFile = new File("C:/Users/Byron/git/dasm83/tesisClinica/clinicapp/src/main/webapp/WEB-INF/reports/"+SubreportFileName+".jasper");
	              //JasperReport subJasperReport = (JasperReport) JRLoader.loadObjectFromFile(subReportFile.getPath());
	              dataSource.put("p3","C:/Users/Byron/git/dasm83/tesisClinica/clinicapp/src/main/webapp/WEB-INF/reports/");
	              if (rptFormat.equalsIgnoreCase("html") ) {
	   
	        //	  modelAndView = new ModelAndView("htmlReport", dataSource);
	        	  System.out.println("llega al antes de crearlo");
	             JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, dataSource, conn);
	              generateReportHtml(jasperPrint, request, response); // For HTML report
	   
	          }
	   
	          else if  (rptFormat.equalsIgnoreCase("pdf") )  {
	   
	        //	  modelAndView = new ModelAndView("pdfReport", dataSource)
	             generateReportPDF(response, dataSource, jasperReport, conn); // For PDF report
	   
	              }
	   
	         } catch (Exception e) {
	   
	             e.printStackTrace();
	   
	         } finally {
	   
	              try {
	   
	              if (conn != null) {
	                  conn.close();
	                  conn = null;
	              }
	   
	              } catch (SQLException expSQL) {
	   
	                  System.out.println("SQLExp::CLOSING::" + expSQL.toString());
	   
	              }
	   
	       } 
	      
	      return null;
	   //   return new ModelAndView();
	  }
	  
	  private void generateReportHtml( JasperPrint jasperPrint, HttpServletRequest req, HttpServletResponse resp) throws IOException, JRException {
	         System.out.print("llego");
		     HtmlExporter exporter=new HtmlExporter();
	         List<JasperPrint> jasperPrintList = new ArrayList<JasperPrint>();
	         jasperPrintList.add(jasperPrint);
	         exporter.setExporterInput(SimpleExporterInput.getInstance(jasperPrintList));
	         exporter.setExporterOutput( new SimpleHtmlExporterOutput(resp.getWriter()));
	         SimpleHtmlReportConfiguration configuration =new SimpleHtmlReportConfiguration();
	         exporter.setConfiguration(configuration);
	          exporter.exportReport();
	 
	    }
	 
	    private void generateReportPDF (HttpServletResponse resp, Map parameters, JasperReport jasperReport, Connection conn)throws JRException, NamingException, SQLException, IOException {
	        byte[] bytes = null;
	        bytes = JasperRunManager.runReportToPdf(jasperReport,parameters,conn);
	        resp.reset();
	        resp.resetBuffer();
	        resp.setContentType("application/pdf");
	        resp.setContentLength(bytes.length);
	        ServletOutputStream ouputStream = resp.getOutputStream();
	        ouputStream.write(bytes, 0, bytes.length);
	        ouputStream.flush();
	        ouputStream.close();
	    } 
	  
	  private JasperReport getCompiledFile(String fileName, HttpServletRequest request) throws JRException {
 
		  File reportFile = new File("C:/Users/Byron/git/dasm83/tesisClinica/clinicapp/src/main/webapp/WEB-INF/reports/"+fileName+".jasper");
		        JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(reportFile.getPath());		      
		       return jasperReport;
		    } 
	 
	  @ModelAttribute(value="examClasificacion")
		private List<Map<String,String>> getExamTypes(){
			List<Map<String,String>> brief = new ArrayList<>();
			List<Clasificacion> types = clasService.findAll();
			
			types.forEach(type->{
				Map<String,String> list = new HashMap<>();
				list.put("id", type.getId().toString());
				list.put("name", type.getCategoria());
				brief.add(list);
			});
			
			return brief;
		}
	  
	  @RequestMapping(method = RequestMethod.POST, value = URLj, produces = "application/json")
public @ResponseBody TableData dataTable(HttpServletRequest request, @RequestParam(value="order[0][column]") int col,@RequestParam(value="order[0][dir]") String dir, @RequestParam(required=false,name="search") String search){
			TableData json = new TableData();
			System.out.println("entro al metodo aunqyue sea");
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
	  
	  @SuppressWarnings("unchecked")
		private List<Map<String,String>> getExamsList(int start,int length, int col, String order, String search){
			List<Map<String,String>> brief = new ArrayList<>();
			//List<Examen> exams = examService.getFilteredList(start,length,col,order,search);
		//	List<Paciente> pacient = pacientService.getFilteredList(start,length,col,order,search);
			Paciente s= pacientService.getByExactName(search);
			
		    Set<Examen> set = s.getExamens();
			for(Examen e:set){
				Map<String,String> list = new HashMap<>();
				
				list.put("DT_RowId",e.getId().toString());
				list.put("tipo", e.getCatalogoExamen().getNombre().toString() );
			    list.put("lab",  e.getCatalogoExamen().getClasificacion().getCategoria().toString());
				list.put("date", e.getFecha().toString());
				list.put("personid", s.getId().toString());
				brief.add(list);
			}
			return brief;
		}
}
