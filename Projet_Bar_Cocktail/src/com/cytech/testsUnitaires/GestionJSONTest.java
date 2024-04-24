package com.cytech.testsUnitaires;

import com.cytech.Ingredients.BoissonSimple;
import com.cytech.Ingredients.Cocktail;
import com.cytech.Ingredients.IngredientBonus;
import com.cytech.gestionFichiers.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.internal.LinkedTreeMap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class GestionJSONTest {

	public static void main(String[] args) {
		 //recuperation des donnees du fihier JSON clients.json
		//List<Client> lstClient = GestionJSON.lireJSON("src\\com\\cytech\\collections\\clients.json");

		/*List<Client> testliste = new ArrayList();
		Client test = new Client("hi", "hello", null, null, null, null, null);
		Client test2 = new Client("salut", "bonjour", null, null, null, null, null);
		testliste.add(test);
		testliste.add(test2);
		
		// Affichage par defaut sur une seule ligne
		//System.out.println(lstCat);
		// Affichage formatte (par defaut sur 1 ligne)
		/*Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String jsonStr = gson.toJson(lstClient);
		System.out.println(jsonStr);*/

		// Sauvegarde des donnees en JSON dans les fichiers

		//System.out.println("Sauvegarde directe : " + 
				//GestionJSON.EcrireJsonDirecte(testliste, "src\\com\\cytech\\collections\\clients.json"));
		
			
		BoissonSimple testb = new BoissonSimple("test",10, 0, 0,5);
		BoissonSimple testb2 = new BoissonSimple("test2",10, 0, 0,5);
		BoissonSimple testb3 = new BoissonSimple("test3",10, 0, 0,5);
		
		Map <String,Integer> listeBoisson = new HashMap<String, Integer>();
		
		listeBoisson.put(testb.getNom(),1);
		listeBoisson.put(testb2.getNom(),2);
		listeBoisson.put(testb3.getNom(),3);
		
		
		//System.out.println("Sauvegarde directe : " + 
				//GestionJSON.EcrireJsonBoisson(listeBoisson, "src\\com\\cytech\\collections\\boissonsimple.json"));
		
		//GestionJSON.lireJSONBoisson("src\\\\com\\\\cytech\\\\collections\\\\boissonsimple.json");*/

		IngredientBonus ing1 = new IngredientBonus("ing1", 1);
		IngredientBonus ing2 = new IngredientBonus("ing2", 1);
		
		Map <String, Integer> listeIngredient = new HashMap<String, Integer>();
		
		listeIngredient.put(ing1.getNom(), 1);
		listeIngredient.put(ing2.getNom(), 2);
		
		//System.out.println("Sauvegarde directe : " + 
				//GestionJSON.EcrireJsonIngredient(listeIngredient, "src\\com\\cytech\\collections\\ingredientbonus.json"));
		
		//GestionJSON.lireJSONIngredient("src\\\\com\\\\cytech\\\\collections\\\\ingredientbonus.json");
		
		Cocktail cocktail = new Cocktail(25, "Cuba Libre", 20, 10, listeBoisson, listeIngredient);
		
		Map <String, Cocktail> listeCocktail = new HashMap<>();
		
		listeCocktail.put(cocktail.getNom(), cocktail);
		
		GestionJSON.EcrireJsonCocktail(listeCocktail, "src\\\\com\\\\cytech\\\\collections\\\\cocktail.json");
		Map <String, Cocktail> printMap = GestionJSON.lireJSONCocktail("src\\\\com\\\\cytech\\\\collections\\\\cocktail.json");
		//printMap.forEach((key,value) -> System.out.println(key + value));
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String jsonStr = gson.toJson(printMap);
		//System.out.println(jsonStr);
		
		//Cocktail consta = printMap.get("Mojito");
		//System.out.println(consta);
		
		for (Map.Entry<String, Cocktail> entry : printMap.entrySet()) {
		    String nom = entry.getKey(); // Accéder à la clé (nom du cocktail)
		    //System.out.println(entry.getValue());
		    System.out.println(nom);
		    //System.out.println(entry.getValue());
		    for (Entry<String, Integer> entry2 : entry.getValue().getListeBoissonSimple().entrySet()) {
		    	System.out.println(entry2.getValue());
		    }
		    if (entry.getValue().getListeIngredientBonus()!=null) {
			    for (Entry<String, Integer> entry3 : entry.getValue().getListeIngredientBonus().entrySet()) {
			    	System.out.println(entry3.getKey());
			    }
		    }
		    
		}

		
		
		
	}

}

