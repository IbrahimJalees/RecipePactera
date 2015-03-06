package com.pactera.tech.assessment;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONTest {

	public static void main(String[] args) throws ParseException, FileNotFoundException, IOException, java.text.ParseException {
		// TODO Auto-generated method stub
		/*Recipes r = new Recipes();
		Scanner s = new Scanner(System.in);
		System.out.println("Enter a filename");
		String filename = s.nextLine();
		
		System.out.println("filename" + filename);
		JSONParser parser = new JSONParser();
		JSONArray jArray = (JSONArray) parser.parse(new FileReader(filename));
		for(Object o : jArray){
			JSONObject recipe = (JSONObject) o;
			
			String name = (String) recipe.get("name");
			System.out.println("name" + name);
			
			JSONArray array = (JSONArray) recipe.get("ingredients");
			for(Object oa : array){
				System.out.println(oa);
				r.setIngredient((String[]) oa);
				System.out.println(r.getIngredient());
				String ingredients[] = r.getIngredient();
				System.out.println("Array 1:" + ingredients[0]);
				
			}*/
		String _use_by_date = "25/12/2014";
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = formatter.parse(_use_by_date);
		System.out.println(date);
		System.out.println(formatter.format(date));
		
		}
		
		
		
		
		
		
		/*JSONParser parser = new JSONParser();
		//Object obj = (JSONObject) parser.parse(new FileReader(filename));
		JSONObject jsonObject = new JSONObject((parser.parse(filename)).toString());*/
		
		/*JSONArray jsonArray = (JSONArray) obj;
		for(Object object : jsonArray){
			JSONObject jsonObj = (JSONObject) object;
			JSONObject recipes = (JSONObject) jsonObj.get("0");
			String name = (String) recipes.get("name");
			System.out.println(name);	
		}*/
		
		//JSONObject  jsonObject = (JSONObject) obj;
		
		//String name = (String) obj.get("name");
		
		
		
	}


