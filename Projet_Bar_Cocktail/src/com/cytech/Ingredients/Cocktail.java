package com.cytech.Ingredients;

import java.util.*;
import java.util.Map.Entry;

import com.cytech.gestionFichiers.GestionJSON;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cocktail extends Boisson {

	@SerializedName("ListeBoissonSimple")
	@Expose
	private Map<String, Integer> ListeBoissonSimple;
	private Map<String, Integer> ListeIngredientBonus;
	
	@Override
	public String toString() {
		return "Cocktail [ListeBoissonSimple=" + ListeBoissonSimple + ", ListeIngredientBonus=" + ListeIngredientBonus
				+ "]";
	}
	
	public Cocktail( double prix, String nom, double degreAlcool, double degreSucre, Map<String, Integer> listeBoissonSimple, Map <String,Integer> listeIngredientBonus) {
		super(prix, nom, degreAlcool, degreSucre);
		ListeBoissonSimple = listeBoissonSimple;
    	ListeIngredientBonus = listeIngredientBonus;
		
		}
	
		
	public void ajouterBoisson(String boissonSimple, int contenance) {
		if (ListeBoissonSimple == null) {
            ListeBoissonSimple = new HashMap<>();
        }
        if (ListeBoissonSimple.containsKey(boissonSimple)) {
            int ancienneContenance = ListeBoissonSimple.get(boissonSimple);
            ListeBoissonSimple.put(boissonSimple, ancienneContenance + contenance);
        } else {
            ListeBoissonSimple.put(boissonSimple, contenance);
        }
    }
	
	public void ajouterIngredient(String ingredientBonus, int quantité) {
		if (ListeIngredientBonus == null) {
            ListeIngredientBonus = new HashMap<>();
        }
        if (ListeIngredientBonus.containsKey(ingredientBonus)) {
            int ancienneQuantite = ListeIngredientBonus.get(ingredientBonus);
            ListeIngredientBonus.put(ingredientBonus, ancienneQuantite + quantité);
        } else {
            ListeIngredientBonus.put(ingredientBonus, quantité);
        }
		
	}
    	

    public void retirerBoisson(String boissonSimple, int quantite) {
    	if (ListeBoissonSimple == null) {
            ListeBoissonSimple = new HashMap<>();
        }
        if (ListeBoissonSimple.containsKey(boissonSimple)) {
            int ancienneContenance = ListeBoissonSimple.get(boissonSimple);
            
            int nouvelleContenance = ancienneContenance - quantite;
            
            if (nouvelleContenance <= 0) {
                ListeBoissonSimple.remove(boissonSimple);
            } else {
                ListeBoissonSimple.put(boissonSimple, nouvelleContenance);
            }
        }
    }
    
    public boolean estEnStock() {
    	Map<String, BoissonSimple> lstBoisson = GestionJSON.lireJSONBoisson("src\\\\com\\\\cytech\\\\collections\\\\boissonsimple.json");
    	if (ListeBoissonSimple != null) {
	        for (Entry<String, Integer> entry : ListeBoissonSimple.entrySet()) {
	        	for (Entry<String, BoissonSimple> stockboisson : lstBoisson.entrySet()) {
	        		if (stockboisson.getValue().getStock() - entry.getValue() < 0) {
	                	
	                    return false;
	                }
	        	}
	        }
	        return true;
    	}
    	return true;
    }
    

	@Override
	public int hashCode() {
		return Objects.hash(ListeBoissonSimple, ListeIngredientBonus);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cocktail other = (Cocktail) obj;
		return Objects.equals(ListeBoissonSimple, other.ListeBoissonSimple)
				&& Objects.equals(ListeIngredientBonus, other.ListeIngredientBonus);
	}

	public Map<String, Integer> getListeBoissonSimple() {
		return ListeBoissonSimple;
	}

	public void setListeBoissonSimple(Map<String, Integer> listeBoissonSimple) {
		ListeBoissonSimple = listeBoissonSimple;
	}

	public Map<String, Integer> getListeIngredientBonus() {
		return ListeIngredientBonus;
	}

	public void setListeIngredientBonus(Map<String, Integer> listeIngredientBonus) {
		ListeIngredientBonus = listeIngredientBonus;
	}
	
}