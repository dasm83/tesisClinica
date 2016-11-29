package com.tesis.clinicapp.util;

import java.util.ArrayList;
import java.util.List;

public class AutocompleteData {
	
	private List<data> suggestions;
	
	
	public AutocompleteData() {
		this.suggestions = new ArrayList<>();
	}
	

	public List<data> getSuggestions() {
		return suggestions;
	}

	public void setSuggestions(List<data> suggestions) {
		this.suggestions = suggestions;
	}
	
	
	public void addPair(String value, String data){
		data pair = new data();
		pair.setValue(value);
		pair.setData(data);
		this.suggestions.add(pair);
	}


	class data{
		private String value;
		private String data;
		
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
		public String getData() {
			return data;
		}
		public void setData(String data) {
			this.data = data;
		}
	}
	
}
