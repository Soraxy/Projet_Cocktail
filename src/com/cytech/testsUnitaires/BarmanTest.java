package com.cytech.testsUnitaires;

import com.cytech.Ingredients.*;
import com.cytech.commandeboisson.*;
import com.cytech.utilisateur.Barman;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class BarmanTest {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		BoissonSimple test = new BoissonSimple("test", 10, 0, 0, 5);
		BoissonSimple test2 = new BoissonSimple("test2", 10, 0, 0, 5);
		BoissonSimple test3 = new BoissonSimple("test3", 10, 0, 0, 5);

		Map<String, Integer> listeBoisson = new HashMap<String, Integer>();

		listeBoisson.put(test.getNom(), 1);
		listeBoisson.put(test2.getNom(), 2);
		listeBoisson.put(test3.getNom(), 3);

		// Barman.creerCocktail( ); //test création cocktail dans json

		Cocktail cocktail = new Cocktail(25, "Cuba Libre", 20, 10, listeBoisson, null);

		Map<String, Integer> listCocktail = new HashMap<String, Integer>();
		listCocktail.put(cocktail.getNom(), 1);
		
		Map<String,Integer> BoissonCommande = new HashMap<String,Integer>();
		BoissonCommande.put(test.getNom(), 10);
		BoissonCommande.put(test3.getNom(), 15);

		Commande testCommande = new Commande("test", BoissonCommande, listCocktail, false, null, null);

		Barman.servirCommande(testCommande);

		System.out.println(testCommande);
		Barman.encaisser(testCommande);
		System.out.println(Barman.getProfit());

		Barman.reapprovisionner(test, 10);
		System.out.println("nouveau stock boisson1 : " + test.getStock());
	}

}
