package com.cytech.testsUnitaires;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cytech.Ingredients.BoissonSimple;
import com.cytech.Ingredients.Cocktail;
import com.cytech.commandeboisson.Commande;
import com.cytech.gestionFichiers.GestionJSON;

public class CommandeTest {

	public static void main(String[] args) {
	String date = new Date(1246764800000L).toString();
	Commande commandetest = new Commande("Commandetest", null, null, false, date, "a");
	
	BoissonSimple boisson1 = new BoissonSimple("Vodka", 10, 40, 2, 20);
	BoissonSimple boisson2 = new BoissonSimple("Rhum", 8, 30, 0, 15);
	
	Map<String, Integer> boissons = new HashMap<>();
    boissons.put(boisson1.getNom(), 2);
    boissons.put(boisson2.getNom(), 1);
	
	commandetest.ajouterBoissonCommande(boisson1.getNom(), 1);
	System.out.println(commandetest.getDate());
	
	Cocktail cocktail = new Cocktail(25, "Cuba Libre", 20, 10, boissons, null);
	
	commandetest.ajouterCocktail(cocktail.getNom(), 1);
	System.out.println(commandetest);
	
	
	/*List<Commande> lstCommande = new ArrayList();
	lstCommande.add(commandetest);
	System.out.println(commandetest.getListeCocktail());
	
	GestionJSON.EcrireJsonCommande(lstCommande, "src\\\\com\\\\cytech\\\\collections\\\\commandes.json");
	
	lstCommande = GestionJSON.lireJSONCommande("src\\\\com\\\\cytech\\\\collections\\\\commandes.json");
	System.out.println(lstCommande);
	*/
	}
}
