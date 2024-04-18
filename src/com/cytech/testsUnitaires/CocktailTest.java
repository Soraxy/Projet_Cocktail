package com.cytech.testsUnitaires;

import java.util.HashMap;
import java.util.Map;

import com.cytech.Ingredients.BoissonSimple;
import com.cytech.Ingredients.Cocktail;
import com.cytech.Ingredients.IngredientBonus;

public class CocktailTest {
    public static void main(String[] args) {
        // Cr�ation de quelques boissons simples
        BoissonSimple boisson1 = new BoissonSimple("Vodka", 10, 40, 2, 20);
        BoissonSimple boisson2 = new BoissonSimple("Rhum", 8, 30, 0, 15);
        
        // Cr�ation de quelques ingr�dients bonus
        IngredientBonus ingredient1 = new IngredientBonus("Citron", 2);
        IngredientBonus ingredient2 = new IngredientBonus("Sucre de canne", 1);
        
        // Cr�ation de la liste des boissons et des ingr�dients
        Map<BoissonSimple, Integer> boissons = new HashMap<>();
        boissons.put(boisson1, 2);
        boissons.put(boisson2, 1);
        
        Map<IngredientBonus, Integer> ingredients = new HashMap<>();
        ingredients.put(ingredient1, 3);
        ingredients.put(ingredient2, 2);
        
        // Cr�ation du cocktail
        Cocktail cocktail = new Cocktail(25, "Cuba Libre", 20, 10, boissons, ingredients);
        
        // Test de la m�thode ajouterBoisson()
        BoissonSimple nouvelleBoisson = new BoissonSimple("Gin", 12, 35, 0, 18);
        cocktail.ajouterBoisson(nouvelleBoisson, 1);
        
        // Affichage des boissons dans le cocktail apr�s ajout
        System.out.println("Liste des boissons du cocktail apr�s ajout :");
        for (Map.Entry<BoissonSimple, Integer> entry : cocktail.getListeBoissonSimple().entrySet()) {
            System.out.println(entry.getKey().getNom() + " - Quantit� : " + entry.getValue());
        }
        
        // Test de la m�thode retirerBoisson()
        cocktail.retirerBoisson(nouvelleBoisson, 1);
        
        // Affichage des boissons dans le cocktail apr�s retrait
        System.out.println("Liste des boissons du cocktail apr�s retrait :");
        for (Map.Entry<BoissonSimple, Integer> entry : cocktail.getListeBoissonSimple().entrySet()) {
            System.out.println(entry.getKey().getNom() + " - Quantit� : " + entry.getValue());
        }
        
        // Test de la m�thode estEnStock()
        boolean estEnStock = cocktail.estEnStock();
        System.out.println("Le cocktail est-il en stock ? " + estEnStock); // devrait afficher true
        for (Map.Entry<BoissonSimple, Integer> entry : cocktail.getListeBoissonSimple().entrySet()) {
            System.out.println(entry.getKey().getStock());
        }
        
        // Test des m�thodes de calcul
        double prixTotal = cocktail.calculerPrix();
        double degreAlcoolTotal = cocktail.calculerDegreAlcool();
        double degreSucreTotal = cocktail.calculerDegreSucre();
        
        System.out.println("Prix total du cocktail : " + prixTotal);
        System.out.println("Degr� d'alcool total du cocktail : " + degreAlcoolTotal);
        System.out.println("Degr� de sucre total du cocktail : " + degreSucreTotal);
    }
}

