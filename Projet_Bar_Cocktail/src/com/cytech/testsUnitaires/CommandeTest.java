package com.cytech.testsUnitaires;

import java.util.HashMap;
import java.util.Map;

import com.cytech.Ingredients.BoissonSimple;
import com.cytech.Ingredients.Cocktail;
import com.cytech.commandeboisson.Commande;

public class CommandeTest {

	public static void main(String[] args) {
	Commande commandetest = new Commande(null, null, null, false, null, null);
	
	BoissonSimple boisson1 = new BoissonSimple("Vodka", 10, 40, 2, 20);
	BoissonSimple boisson2 = new BoissonSimple("Rhum", 8, 30, 0, 15);
	
	Map<BoissonSimple, Integer> boissons = new HashMap<>();
    boissons.put(boisson1, 2);
    boissons.put(boisson2, 1);
	
	commandetest.ajouterBoissonCommande(boisson1, 1);
	System.out.println(commandetest);
	
	Cocktail cocktail = new Cocktail(25, "Cuba Libre", 20, 10, boissons, null);
	
	commandetest.ajouterCocktail(cocktail, 1);
	System.out.println(commandetest);
	
	
	commandetest.ajouterCocktail(null, 0);
	
	}
}
