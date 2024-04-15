package com.cytech.Ingredients;

public class IngredientBonus {

    private String nom;
    private int prix;
    
	@Override
	public String toString() {
		return "IngredientBonus [nom=" + nom + ", prix=" + prix + "]";
	}

	public IngredientBonus(String nom, int prix) {
		super();
		this.nom = nom;
		this.prix = prix;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getPrix() {
		return prix;
	}

	public void setPrix(int prix) {
		this.prix = prix;
	}
	
	
}
   