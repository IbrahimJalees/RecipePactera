package com.pactera.tech.assessment;


import java.util.LinkedHashMap;
import java.util.List;

/**
 * Java bean class to hold Recipe items.
 * 
 * @author Ibrahim
 * @version 1.0
 * @since 05-Mar-2015
 * 
 * */
public class Recipes {
	
	private String name;
	private List<LinkedHashMap<String, String>> ingredient ;
	
	/**
	 * @return the ingredient
	 */
	public List<LinkedHashMap<String, String>> getIngredient() {
		return ingredient;
	}
	/**
	 * @param ingredient the ingredient to set
	 */
	public void setIngredient(List<LinkedHashMap<String, String>> ingredient) {
		this.ingredient = ingredient;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	

}
