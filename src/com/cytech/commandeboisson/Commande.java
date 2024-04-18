package com.cytech.commandeboisson;

import java.util.*;

import com.cytech.Ingredients.BoissonSimple;
import com.cytech.Ingredients.Cocktail;

public class Commande {
	
    private String nomCommande;
    private Map <String,Integer> listeBoisson;
    private Map <String, Integer> listeCocktail;
    private boolean estServie;
    private String date;
    private String nomClient;
    
   
	public Commande(String nomCommande, Map<String, Integer> listeCommande, Map <String, Integer> listeCocktail,
			boolean estServie, String date, String nomClient) {
		super();
		this.nomCommande = nomCommande;
		this.listeBoisson = listeCommande;
		this.listeCocktail = listeCocktail;
		this.estServie = estServie;
		this.date = date;
		this.nomClient = nomClient;
	}

	public String getNomCommande() {
		return nomCommande;
	}

	public void setNomCommande(String nomCommande) {
		this.nomCommande = nomCommande;
	}

	public Map<String, Integer> getListeBoisson() {
		return listeBoisson;
	}

	public void setListeBoisson(Map<String, Integer> listeCommande) {
		this.listeBoisson = listeCommande;
	}

	public Map<String, Integer> getListeCocktail() {
		return listeCocktail;
	}

	public void setListeCocktail(Map<String, Integer> listeCocktail) {
		this.listeCocktail = listeCocktail;
	}

	public boolean isEstServie() {
		return estServie;
	}

	public void setEstServie(boolean estServie) {
		this.estServie = estServie;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getNomClient() {
		return nomClient;
	}

	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}

	@Override
	public String toString() {
		return "Commande [nomCommande=" + nomCommande + ", listeBoisson=" + listeBoisson + ", listeCocktail="
				+ listeCocktail + ", estServie=" + estServie + ", date=" + date + ", nomClient=" + nomClient + "]";
	}

	public void ajouterBoissonCommande(String toAdd, int quantité) {
		if (listeBoisson == null) {
            listeBoisson = new HashMap<>();
        }
        if (listeBoisson.containsKey(toAdd)) {
            int ancienneContenance = listeBoisson.get(toAdd);
            listeBoisson.put(toAdd, ancienneContenance + quantité);
        } else {
            listeBoisson.put(toAdd, quantité);
        }
		
	}
	
	public void ajouterCocktail(String addCocktail, int quantité) {
		if (listeCocktail == null) {
            listeCocktail = new HashMap<>();
        }
        if (listeCocktail.containsKey(addCocktail)) {
            int ancienneQuantite = listeCocktail.get(addCocktail);
            listeCocktail.put(addCocktail, ancienneQuantite + quantité);
        } else {
        	listeCocktail.put(addCocktail, quantité);
        }
		
	}

   

}