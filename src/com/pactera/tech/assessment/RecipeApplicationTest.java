package com.pactera.tech.assessment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * The RecipeApplicationTest program displays "Recipe" to the standard output.
 * 
 * @author Ibrahim
 * @version 1.0
 * @since 05-Mar-2015
 * 
 * */
public class RecipeApplicationTest {

	private List<Fridge> fridgeList = new ArrayList<>();

	/**
	 * This is the method to read Input files from commant line.
	 * 
	 * @param none
	 * @return String[]
	 * */
	private String[] readInputFiles() {
		String filenames[] = new String[2];

		Scanner scanner = new Scanner(System.in);

		System.out.println("Enter the Fridge input file");
		filenames[0] = scanner.nextLine();

		System.out.println("Enter the Json file");
		filenames[1] = scanner.nextLine();

		return filenames;
	}

	/**
	 * This is the processMenu method, process the menu based on the Input
	 * files.
	 * 
	 * @param List
	 *            <Recipes> _recipeList
	 * @return String
	 * */
	private String processMenu(List<Recipes> _recipeList) {
		String selectedRecipe = "";
		Date todaysDate = getSystemDate();
		LinkedHashMap<Date, String> finalMenu = new LinkedHashMap<>();

		for (Recipes recipe : _recipeList) {
			for (LinkedHashMap<String, String> ingredientMap : recipe
					.getIngredient()) {
				String item = ingredientMap.get("item");
				for (Fridge fridgeItems : fridgeList) {
					if (item.equalsIgnoreCase(fridgeItems.getItem())) {
						Date expiryDate = fridgeItems.getUse_by_date();

						if (expiryDate.before(todaysDate)) {
							ingredientMap.remove(item);
							ingredientMap.remove(ingredientMap.get("amount"));
							ingredientMap.remove(ingredientMap.get("Unit"));
						}

						if (expiryDate.equals(todaysDate)
								|| expiryDate.after(todaysDate))
							finalMenu.put(expiryDate, recipe.getName());
					}
				}
			}
		}

		if ((!finalMenu.isEmpty()) && finalMenu != null) {
			Iterator i = finalMenu.keySet().iterator();
			while (i.hasNext()) {
				Date date = (Date) i.next();

				if (date.equals(todaysDate)) {
					selectedRecipe = finalMenu.get(date);
					break;
				} else
					selectedRecipe = finalMenu.get(date);
			}
		} else
			selectedRecipe = "Order Takeout";

		return selectedRecipe;
	}

	/**
	 * This is getSystemDate method used to get current date from the system.
	 * 
	 * @param none
	 * @return Date
	 * */
	private Date getSystemDate() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");
		Date date = new Date();
		try {
			date = dateFormat.parse(dateFormat.format(date));
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}

		return date;
	}

	/**
	 * This is parseFiles method used to parse the input files.
	 * 
	 * @param String
	 *            [] files
	 * @return List<Recipes> recipeList
	 * */
	private List<Recipes> parseFiles(String[] files) {

		String line = null;
		List<Recipes> recipeList = new ArrayList<Recipes>();

		// First Parse the Ingredients.txt file.
		File csvFile = new File(files[0]);

		FileReader fReader = null;
		BufferedReader buffReader = null;
		String delimiter = ",";
		try {
			fReader = new FileReader(csvFile);
			buffReader = new BufferedReader(fReader);
			while ((line = buffReader.readLine()) != null) {
				Fridge fridge = new Fridge();
				String tokens[] = line.split(delimiter); // "," delimiter used
															// to read tokens
															// from the file.
				// System.out.println("token ::" + tokens.length);

				fridge.setItem(tokens[0]);
				fridge.setAmount(Integer.parseInt(tokens[1]));

				if (tokens[2].equalsIgnoreCase(Unit.GRAMS.getUnitValue()))
					fridge.setUnit(Unit.GRAMS.getUnitValue());
				else if (tokens[2].equalsIgnoreCase(Unit.MILLILITRES
						.getUnitValue()))
					fridge.setUnit(Unit.MILLILITRES.getUnitValue());
				else if (tokens[2].equalsIgnoreCase(Unit.SLICES.getUnitValue()))
					fridge.setUnit(Unit.SLICES.getUnitValue());
				else
					fridge.setUnit(Unit.MILLILITRES.getUnitValue());

				fridge.setUse_by_date(tokens[3]);

				fridgeList.add(fridge); // List used to contain the fridge
										// items.
			}

		} catch (IOException ioEx) {
			ioEx.printStackTrace();
		} finally {
			if (fReader != null)
				try {
					fReader.close();
					buffReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}// Ingredients.txt parsing complete.

		// Now parse the Recipes.json ingredients file.
		String fileName = files[1];

		try {

			JSONParser jParser = new JSONParser(); // Parsing using JSON Parser.
			JSONArray jArray = (JSONArray) jParser.parse(new FileReader(
					fileName));
			for (Object o : jArray) {
				Recipes recipe = new Recipes();

				JSONObject jObj = (JSONObject) o;
				String name = (String) jObj.get("name");
				recipe.setName(name);
				JSONArray array = (JSONArray) jObj.get("ingredients");

				List<LinkedHashMap<String, String>> mapList = new ArrayList<>();
				Iterator<?> i = array.iterator();
				while (i.hasNext()) {
					JSONObject innerObject = (JSONObject) i.next();
					LinkedHashMap<String, String> ingredientsMap = new LinkedHashMap<String, String>(); // Map
																										// to
																										// hold
																										// the
																										// ingredients
																										// array.
					ingredientsMap
							.put("item", (String) innerObject.get("item"));
					ingredientsMap.put("amount",
							(String) innerObject.get("amount"));
					ingredientsMap
							.put("unit", (String) innerObject.get("unit"));
					mapList.add(ingredientsMap); // Ingredient map added to
													// mapList.

				}
				recipe.setIngredient(mapList);// mapList set to recipe object.
				recipeList.add(recipe); // recipe object added to recipeList.
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ParseException pe) {
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		return recipeList;

	} // Recipes.json parsing compelted.

	/**
	 * This is main method. Starting point of the application.
	 * 
	 * @param args
	 *            unused
	 * @return nothing.
	 * */
	public static void main(String[] args) {

		RecipeApplicationTest recipeTest = new RecipeApplicationTest();

		// Reading the Input files.
		String files[] = recipeTest.readInputFiles();
		// Parsing the files.
		List<Recipes> recipeList = recipeTest.parseFiles(files);

		// Processing the Menu and display the recipe selected.
		String recipe = recipeTest.processMenu(recipeList);
		System.out.println("selected recipe is::" + recipe);
	}
}
