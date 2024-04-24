package com.cytech.Ingredients;

import java.util.Objects;

public class IngredientBonus {

    private String nom;
    private int prix;
    private int quantite;
    
	@Override
	public String toString() {
		return "IngredientBonus [nom=" + nom + ", prix=" + prix + "]";
	}

	public IngredientBonus(String nom, int prix) {
		super();
		this.nom = nom;
		this.prix = prix;
	}
	
	public IngredientBonus(String nom, int prix, int quantite) {
		super();
		this.nom = nom;
		this.prix = prix;
		this.quantite = quantite;
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

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nom, prix);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IngredientBonus other = (IngredientBonus) obj;
		return Objects.equals(nom, other.nom) && prix == other.prix;
	}
	
	
}
   