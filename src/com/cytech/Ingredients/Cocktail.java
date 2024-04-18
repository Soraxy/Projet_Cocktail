package com.cytech.Ingredients;

import java.util.*;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cocktail extends Boisson {

	@SerializedName("ListeBoissonSimple")
	@Expose
	private Map<BoissonSimple, Integer> ListeBoissonSimple;
	private Map<IngredientBonus, Integer> ListeIngredientBonus;
	
	@Override
	public String toString() {
		return "Cocktail [ListeBoissonSimple=" + ListeBoissonSimple + ", ListeIngredientBonus=" + ListeIngredientBonus
				+ "]";
	}
	
	public Cocktail( double prix, String nom, double degreAlcool, double degreSucre, Map<BoissonSimple, Integer> listeBoissonSimple, Map <IngredientBonus,Integer> listeIngredientBonus) {
		super(prix, nom, degreAlcool, degreSucre);
		ListeBoissonSimple = listeBoissonSimple;
    	ListeIngredientBonus = listeIngredientBonus;
		
		}
	
		
	public void ajouterBoisson(BoissonSimple boissonSimple, int contenance) {
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
	
	public void ajouterIngredient(IngredientBonus ingredientBonus, int quantité) {
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
    	

    public void retirerBoisson(BoissonSimple boissonSimple, int quantite) {
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
        for (Map.Entry<BoissonSimple, Integer> entry : ListeBoissonSimple.entrySet()) {
            if (entry.getKey().getStock() - entry.getValue() < 0) {
            	System.out.println("Il ne reste plus que " + entry.getKey().getStock() + "de" + entry.getKey());
                return false;
            }
        }
        return true;
    }
    
    public double calculerPrix() {
    	int prix = 0;
    	if (ListeBoissonSimple != null) {
    	for (Map.Entry<BoissonSimple, Integer> entry : ListeBoissonSimple.entrySet()) {
    		prix += entry.getKey().getPrix()*entry.getValue();
    	}
    	}
    	if (ListeIngredientBonus != null) {
    	for (Map.Entry<IngredientBonus, Integer> entry : ListeIngredientBonus.entrySet()) {
    		prix += entry.getKey().getPrix()*entry.getValue();
    	}
    	}
    	return prix+(10/100 * prix);
    	
    }
    
    public double calculerDegreAlcool() {
    	int degAlc = 0;
    	if (ListeBoissonSimple != null) {
    	for (Map.Entry<BoissonSimple, Integer> entry : ListeBoissonSimple.entrySet()) {
    		degAlc += entry.getKey().getDegreAlcool();
    	}
    	}
    	return degAlc;
    }
    
    public double calculerDegreSucre() {
    	int degSuc = 0;
    	if (ListeBoissonSimple != null) {
    	for (Map.Entry<BoissonSimple, Integer> entry : ListeBoissonSimple.entrySet()) {
    		degSuc += entry.getKey().getDegreSucre();
    	}
    	}
    	return degSuc;
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

	public Map<BoissonSimple, Integer> getListeBoissonSimple() {
		return ListeBoissonSimple;
	}

	public void setListeBoissonSimple(Map<BoissonSimple, Integer> listeBoissonSimple) {
		ListeBoissonSimple = listeBoissonSimple;
	}

	public Map<IngredientBonus, Integer> getListeIngredientBonus() {
		return ListeIngredientBonus;
	}

	public void setListeIngredientBonus(Map<IngredientBonus, Integer> listeIngredientBonus) {
		ListeIngredientBonus = listeIngredientBonus;
	}
	
}