package com.cytech.testsUnitaires;

import com.cytech.Ingredients.*;
import com.cytech.commandeboisson.*;
import com.cytech.utilisateur.Barman;

import java.util.*;

public class BarmanTest {

	public static void main(String[] args) {
		BoissonSimple test = new BoissonSimple("test", 10, 0, 0, 5);
		BoissonSimple test2 = new BoissonSimple("test2", 10, 0, 0, 5);
		BoissonSimple test3 = new BoissonSimple("test3", 10, 0, 0, 5);

		Map<BoissonSimple, Integer> listeBoisson = new HashMap<BoissonSimple, Integer>();

		listeBoisson.put(test, 1);
		listeBoisson.put(test2, 2);
		listeBoisson.put(test3, 3);

		// Barman.creerCocktail( ); //test création cocktail dans json

		Cocktail cocktail = new Cocktail(25, "Cuba Libre", 20, 10, listeBoisson, null);

		Map<Cocktail, Integer> listCocktail = new HashMap<Cocktail, Integer>();
		listCocktail.put(cocktail, 1);

		Commande testCommande = new Commande("test", listeBoisson, listCocktail, false, null, null);

		Barman.servirCommande(testCommande);

		System.out.println(testCommande);
		Barman.encaisser(testCommande);
		System.out.println(Barman.getProfit());

		Barman.reapprovisionner(test, 10);
		System.out.println("nouveau stock boisson1 : " + test.getStock());
	}

}
