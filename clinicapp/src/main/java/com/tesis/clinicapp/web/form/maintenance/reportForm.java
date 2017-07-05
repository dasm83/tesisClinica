package com.tesis.clinicapp.web.form.maintenance;

public class reportForm {
	
	    private int noofYears;
	    private String rptFmt="Html";
	 
	    public String getRptFmt() {
	        return rptFmt;
	    }
	 
	    public void setRptFmt(String rptFmt) {
	        this.rptFmt = rptFmt;
	    }

		public int getNoofYears() {
			return noofYears;
		}

		public void setNoofYears(int noofYears) {
			this.noofYears = noofYears;
		}
}
