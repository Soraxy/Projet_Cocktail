package com.cytech.testsUnitaires;

import java.util.HashMap;
import java.util.Map;

import com.cytech.Ingredients.BoissonSimple;
import com.cytech.Ingredients.Cocktail;
import com.cytech.Ingredients.IngredientBonus;

public class CocktailTest {
    public static void main(String[] args) {
        // Création de quelques boissons simples
        BoissonSimple boisson1 = new BoissonSimple("Vodka", 10, 40, 2, 20);
        BoissonSimple boisson2 = new BoissonSimple("Rhum", 8, 30, 0, 15);
        
        // Création de quelques ingrédients bonus
        IngredientBonus ingredient1 = new IngredientBonus("Citron", 2);
        IngredientBonus ingredient2 = new IngredientBonus("Sucre de canne", 1);
        
        // Création de la liste des boissons et des ingrédients
        Map<BoissonSimple, Integer> boissons = new HashMap<>();
        boissons.put(boisson1, 2);
        boissons.put(boisson2, 1);
        
        Map<IngredientBonus, Integer> ingredients = new HashMap<>();
        ingredients.put(ingredient1, 3);
        ingredients.put(ingredient2, 2);
        
        // Création du cocktail
        Cocktail cocktail = new Cocktail(25, "Cuba Libre", 20, 10, boissons, ingredients);
        
        // Test de la méthode ajouterBoisson()
        BoissonSimple nouvelleBoisson = new BoissonSimple("Gin", 12, 35, 0, 18);
        cocktail.ajouterBoisson(nouvelleBoisson, 1);
        
        // Affichage des boissons dans le cocktail après ajout
        System.out.println("Liste des boissons du cocktail après ajout :");
        for (Map.Entry<BoissonSimple, Integer> entry : cocktail.getListeBoissonSimple().entrySet()) {
            System.out.println(entry.getKey().getNom() + " - Quantité : " + entry.getValue());
        }
        
        // Test de la méthode retirerBoisson()
        cocktail.retirerBoisson(nouvelleBoisson, 1);
        
        // Affichage des boissons dans le cocktail après retrait
        System.out.println("Liste des boissons du cocktail après retrait :");
        for (Map.Entry<BoissonSimple, Integer> entry : cocktail.getListeBoissonSimple().entrySet()) {
            System.out.println(entry.getKey().getNom() + " - Quantité : " + entry.getValue());
        }
        
        // Test de la méthode estEnStock()
        boolean estEnStock = cocktail.estEnStock();
        System.out.println("Le cocktail est-il en stock ? " + estEnStock); // devrait afficher true
        for (Map.Entry<BoissonSimple, Integer> entry : cocktail.getListeBoissonSimple().entrySet()) {
            System.out.println(entry.getKey().getStock());
        }
        
        // Test des méthodes de calcul
        double prixTotal = cocktail.calculerPrix();
        double degreAlcoolTotal = cocktail.calculerDegreAlcool();
        double degreSucreTotal = cocktail.calculerDegreSucre();
        
        System.out.println("Prix total du cocktail : " + prixTotal);
        System.out.println("Degré d'alcool total du cocktail : " + degreAlcoolTotal);
        System.out.println("Degré de sucre total du cocktail : " + degreSucreTotal);
    }
}

