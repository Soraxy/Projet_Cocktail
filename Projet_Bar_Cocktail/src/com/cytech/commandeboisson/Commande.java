package com.cytech.commandeboisson;

import java.util.*;

import com.cytech.Ingredients.BoissonSimple;
import com.cytech.Ingredients.Cocktail;

public class Commande {
	
    private String nomCommande;
    private Map <BoissonSimple,Integer> listeBoisson;
    private Map <Cocktail, Integer> listeCocktail;
    private boolean estServie;
    private Date date;
    private String nomClient;
    
   
	public Commande(String nomCommande, Map<BoissonSimple, Integer> listeCommande, Map <Cocktail, Integer> listeCocktail,
			boolean estServie, Date date, String nomClient) {
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

	public Map<BoissonSimple, Integer> getListeBoissonSimple() {
		return listeBoisson;
	}

	public void setListeBoissonSimple(Map<BoissonSimple, Integer> listeCommande) {
		this.listeBoisson = listeCommande;
	}

	public Map<Cocktail, Integer> getListeCocktail() {
		return listeCocktail;
	}

	public void setListeCocktail(Map<Cocktail, Integer> listeCocktail) {
		this.listeCocktail = listeCocktail;
	}

	public boolean isEstServie() {
		return estServie;
	}

	public void setEstServie(boolean estServie) {
		this.estServie = estServie;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
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

	public void ajouterBoissonCommande(BoissonSimple toAdd, int quantité) {
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
	
	public void ajouterCocktail(Cocktail addCocktail, int quantité) {
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