package com.cytech.Ingredients;

public class Boisson {
	
    private double prix;
    private String nom;
    private double degreAlcool;
    private double degreSucre;

    public Boisson( double prix, String nom, double degreAlcool, double degreSucre) {
		this.prix = prix;
		this.nom = nom;
		this.degreAlcool = degreAlcool;
		this.degreSucre = degreSucre;
	}

	
	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public double getDegreAlcool() {
		return degreAlcool;
	}

	public void setDegreAlcool(double degreAlcool) {
		this.degreAlcool = degreAlcool;
	}

	public double getDegreSucre() {
		return degreSucre;
	}

	public void setDegreSucre(double degreSucre) {
		this.degreSucre = degreSucre;
	}


}