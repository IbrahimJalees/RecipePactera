package com.pactera.tech.assessment;

/**
 * This documents Unit.
 * 
 * @author Ibrahim
 * @version 1.0
 * @since 05-Mar-2015
 * */
public enum Unit {

	GRAMS("grams"), MILLILITRES("ml"), SLICES("slices"), KILOGRAM("KG");
	
	private String unitValue;
	
	private Unit(String _string){
		this.unitValue = _string;
	}
	
	public String getUnitValue(){
		return unitValue;
	}
	
}
