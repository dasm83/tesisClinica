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
import org.springframework.web.servlet.ModelAndView;

import com.tesis.clinicapp.service.ItemsExamenService;
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
    String reportFileName = "reportFuncionando";
    
	private static final String URL = "/maintenance/reportes.htm";
	private static final String URL2 = "/maintenance/rportes.htm";
	private static final String URLreport = "/maintenance/reportes.txt";
	
	
	private static final String JSP = "/maintenance/reportes";
	private static final String FORM = "reportForm";
	
	private ItemsExamenService itemService;
	
	@Autowired
	ServletContext servletContext;
	
	@RequestMapping(method = RequestMethod.GET, value = URL)
    public ModelAndView get(HttpServletRequest request){
		request.setAttribute("title", "Programacion de Citas");
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
	  
	  //@RequestMapping(value = "/maintenance/reporteHtml.htm'", method = RequestMethod.POST)
	  @RequestMapping(method = RequestMethod.GET, value = URL2)
	  public ModelAndView generateReport(HttpServletRequest request,HttpServletResponse response,reportForm form,ModelAndView modelAndView) throws ParseException {
		  System.out.println("entro");
		  String SubreportFileName = "p3";
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
	   
	            String rptFormat = form.getRptFmt();
	            String noy = "3";
	   
	            System.out.println("rpt format " + rptFormat);
	            System.out.println("no of years " + noy);
	   
	             //Parameters as Map to be passed to Jasper
	             HashMap<String,Object> dataSource=new HashMap<String,Object>();
	   
	             dataSource.put("id",4);
	           //  dataSource.put("dataSource", conn);
	             
	   	   
	              JasperReport jasperReport = getCompiledFile(reportFileName, request);
	              File subReportFile = new File("C:/Users/Byron/git/dasm83/tesisClinica/clinicapp/src/main/webapp/WEB-INF/reports/"+SubreportFileName+".jasper");
	              JasperReport subJasperReport = (JasperReport) JRLoader.loadObjectFromFile(subReportFile.getPath());
	              dataSource.put("p3","C:/Users/Byron/git/dasm83/tesisClinica/clinicapp/src/main/webapp/WEB-INF/reports/");
	              if (rptFormat.equalsIgnoreCase("html") ) {
	   
	        //	  modelAndView = new ModelAndView("htmlReport", dataSource);
	        	  System.out.println("llega al antes de crearlo");
	             JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, dataSource, conn);
	              generateReportHtml(jasperPrint, request, response); // For HTML report
	   
	          }
	   
	          else if  (rptFormat.equalsIgnoreCase("pdf") )  {
	   
	        //	  modelAndView = new ModelAndView("pdfReport", dataSource);
	         //     generateReportPDF(response, hmParams, jasperReport, conn); // For PDF report
	   
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
	      
	      return  null;
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
		  System.out.println(servletContext.getRealPath("WEB-INF/reports/"+fileName+".jasper"));
		 // File reportFile = new File(servletContext.getRealPath("WEB-INF/reports/"+fileName+".jasper"));
		  File reportFile = new File("C:/Users/Byron/git/dasm83/tesisClinica/clinicapp/src/main/webapp/WEB-INF/reports/"+fileName+".jasper");
		  
		  
		  // If compiled file is not found, then compile XML template
		//    if (!reportFile.exists()) {
		 //              JasperCompileManager.compileReportToFile(request.getSession().getServletContext().getRealPath("/src/main/resources/"+fileName+ ".jrxml"),request.getSession().getServletContext().getRealPath("/src/main/resources/"+fileName+ ".jasper"));
		  //      }
		        JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(reportFile.getPath());
		      
		       return jasperReport;
		    } 
}
