package it.polito.tdp.borders.model;

public class Country {
	private int code;
	private String name;
	private String stateAbb;
	
	
	public Country(int code, String name, String stateAbb) {
		super();
		this.code = code;
		this.name = name;
		this.stateAbb = stateAbb;
	}


	public int getCode() {
		return code;
	}


	public void setCode(int code) {
		this.code = code;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getStateAbb() {
		return stateAbb;
	}


	public void setStateAbb(String stateAbb) {
		this.stateAbb = stateAbb;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + code;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Country other = (Country) obj;
		if (code != other.code)
			return false;
		return true;
	}
	
	
	
	
	

}
