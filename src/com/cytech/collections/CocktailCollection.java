package com.cytech.collections;

import java.util.HashMap;
import java.util.Map;

import com.cytech.Ingredients.Cocktail;

public class CocktailCollection extends HashMap<String, Cocktail> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7488838317839885900L;
	private Map<String, Cocktail> cocktails;

	    public CocktailCollection() {
	        cocktails = new HashMap<>();
	    }

	    public void ajouterCocktail(String nom, Cocktail cocktail) {
	        cocktails.put(nom, cocktail);
	    }

	    public Cocktail getCocktail(String nom) {
	        return cocktails.get(nom);
	    }

	    public void supprimerCocktail(String nom) {
	        cocktails.remove(nom);
	    }

	    public Map<String, Cocktail> getCocktails() {
	        return cocktails;
	    }

	    public void setCocktails(Map<String, Cocktail> cocktails) {
	        this.cocktails = cocktails;
	    }

}