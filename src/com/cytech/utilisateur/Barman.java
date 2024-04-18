package com.cytech.utilisateur;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

import com.cytech.Ingredients.*;
import com.cytech.commandeboisson.*;
import com.cytech.gestionFichiers.GestionJSON;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class Barman {
	
	private static double profit;

    public static double getProfit() {
		return profit;
	}

	public void setProfit(double profit) {
		Barman.profit = profit;
	}

	public static double encaisser(Commande servie) throws FileNotFoundException, IOException {
    	for (Entry<String, Integer> ListeBoisson : servie.getListeBoisson().entrySet()) {
    		try (FileReader reader = new FileReader("src\\com\\cytech\\collections\\boissonsimple.json")) {
				Gson gson = new GsonBuilder().create();
				Map<String, BoissonSimple> boissonsMap = gson.fromJson(reader, new TypeToken<Map<String, BoissonSimple>>(){}.getType());
				
				for (Map.Entry<String, BoissonSimple> entry : boissonsMap.entrySet()) {
					String nom = entry.getKey();
					BoissonSimple toAdd = entry.getValue();
					
					if (nom.equals(ListeBoisson.getKey())) {
						double prix = entry.getValue().getPrix() * ListeBoisson.getValue(); 
						profit = profit + prix;
					}
				}
    		}
    		
    		
    	}
    	
    	for (Entry<String, Integer> ListeCocktail : servie.getListeCocktail().entrySet()) {
    		try (FileReader reader = new FileReader("src\\com\\cytech\\collections\\cocktail.json")) {
				Gson gson = new GsonBuilder().create();
				Map<String, BoissonSimple> boissonsMap = gson.fromJson(reader, new TypeToken<Map<String, BoissonSimple>>(){}.getType());
				
				for (Map.Entry<String, BoissonSimple> entry : boissonsMap.entrySet()) {
					String nom = entry.getKey();
					BoissonSimple toAdd = entry.getValue();
					
					if (nom.equals(ListeCocktail.getKey())) {
						double prix = entry.getValue().getPrix() * ListeCocktail.getValue(); 
						profit = profit + prix;
					}
				}
    		}
    		
    	}
    	return profit;
    }

	public static void creerCocktail() {
		Scanner sc = new Scanner(System.in);
		boolean boissonCreaCocktail = true;
		boolean ingredientCreaCocktail = true;
		String choixCocktail;
		System.out.println("Donnez un nom à votre cocktail");
		choixCocktail = sc.next();
		
		Cocktail newCocktail = new Cocktail(0, choixCocktail, 0, 0, null, null);
		while (boissonCreaCocktail) {
			
			System.out.println("Ajoutez des boissons à votre cocktail, voici la liste des boissons disponibles ");
			GestionJSON.lireJSONBoisson("src\\\\com\\\\cytech\\\\collections\\\\boissonsimple.json");
			System.out.println("Tapez le nom de votre boisson pour l'ajouter sinon tapez FIN pour passer à la suite");
			
			choixCocktail = sc.next();
			if (!choixCocktail.equals("FIN")) {
				try (FileReader reader = new FileReader("src\\com\\cytech\\collections\\boissonsimple.json")) {
					Gson gson = new GsonBuilder().create();
					Map<String, BoissonSimple> boissonsMap = gson.fromJson(reader, new TypeToken<Map<String, BoissonSimple>>(){}.getType());
					
					boolean boissonTrouvee = false;
					for (Map.Entry<String, BoissonSimple> entry : boissonsMap.entrySet()) {
						String nom = entry.getKey();
						BoissonSimple toAdd = entry.getValue();
						
						if (nom.equals(choixCocktail)) {
							boissonTrouvee = true;
							System.out.println("Choisir une quantité");
							int quantité = sc.nextInt();
							newCocktail.ajouterBoisson(toAdd, quantité);
						}
					}
					
					if (boissonTrouvee) {
						System.out.println("Liste des boissons du cocktail après ajout :");
				        for (Map.Entry<BoissonSimple, Integer> entry : newCocktail.getListeBoissonSimple().entrySet()) {
				            System.out.println(entry.getKey().getNom() + " - Quantité : " + entry.getValue());
				        }
				        System.out.println("\n");
					}
					
					else {
						System.out.println("Boisson non trouvée !");
					}
			  } 
				
				catch (FileNotFoundException e) {
				  System.out.println(e.getStackTrace() + " : File Not Found");
				e.printStackTrace();
			  } 
				catch (IOException e) {
				System.out.println(e.getStackTrace() + " : JsonParseException");
				e.printStackTrace();
			  	}
			}
			
			else {
				boissonCreaCocktail = false;
			}
		}
		
		while (ingredientCreaCocktail) {
			
			System.out.println("Ajoutez des ingrédients à votre cocktail, voici la liste des ingrédients disponibles ");
			GestionJSON.lireJSONIngredient("src\\\\com\\\\cytech\\\\collections\\\\ingredientsbonus.json");
			System.out.println("Tapez le nom de votre ingrédient pour l'ajouter sinon tapez FIN pour passer à la suite");
			
			choixCocktail = sc.next();
			if (!choixCocktail.equals("FIN")) {
				try (FileReader reader = new FileReader("src\\com\\cytech\\collections\\ingredientbonus.json")) {
					Gson gson = new GsonBuilder().create();
					Map<String, IngredientBonus> ingredientMap = gson.fromJson(reader, new TypeToken<Map<String, IngredientBonus>>(){}.getType());
					
					boolean ingredientTrouve = false;
					for (Map.Entry<String, IngredientBonus> entry : ingredientMap.entrySet()) {
						String nom = entry.getKey();
						IngredientBonus toAdd = entry.getValue();
						
						if (nom.equals(choixCocktail)) {
							ingredientTrouve = true;
							System.out.println("Choisir une quantité");
							int quantité = sc.nextInt();
							newCocktail.ajouterIngredient(toAdd, quantité);
						}
					}
					
					if (ingredientTrouve) {
						System.out.println("Liste des ingrédients du cocktail après ajout :");
				        for (Map.Entry<IngredientBonus, Integer> entry : newCocktail.getListeIngredientBonus().entrySet()) {
				            System.out.println(entry.getKey().getNom() + " - Quantité : " + entry.getValue());
				        }
				        System.out.println("\n");
					}
					
					else {
						System.out.println("Ingrédient non trouvé !");
					}
			  } 
				
				catch (FileNotFoundException e) {
				  System.out.println(e.getStackTrace() + " : File Not Found");
				e.printStackTrace();
			  } 
				catch (IOException e) {
				System.out.println(e.getStackTrace() + " : JsonParseException");
				e.printStackTrace();
			  	}
			}
			
			else {
				ingredientCreaCocktail = false;
			}
		}
		
		System.out.println("Récapitulatif du cocktail");
		System.out.println("Liste des boissons du cocktail après ajout :");
		if (newCocktail.getListeBoissonSimple() != null) {
        for (Map.Entry<BoissonSimple, Integer> entry : newCocktail.getListeBoissonSimple().entrySet()) {
            System.out.println(entry.getKey().getNom() + " - Quantité : " + entry.getValue());
        }
		}
        System.out.println("\n");
        System.out.println("Liste des ingrédients du cocktail après ajout :");
        if (newCocktail.getListeIngredientBonus() != null) {
        for (Map.Entry<IngredientBonus, Integer> entry : newCocktail.getListeIngredientBonus().entrySet()) {
            System.out.println(entry.getKey().getNom() + " - Quantité : " + entry.getValue());
        }
        System.out.println("\n");
        }
        
        double prixTotal = newCocktail.calculerPrix();
        double degreAlcoolTotal = newCocktail.calculerDegreAlcool();
        double degreSucreTotal = newCocktail.calculerDegreSucre();
        
        newCocktail.setPrix(prixTotal);
        newCocktail.setDegreAlcool(degreAlcoolTotal);
        newCocktail.setDegreSucre(degreSucreTotal);
        
        System.out.println("Prix total du cocktail : " + prixTotal);
        System.out.println("Degré d'alcool total du cocktail : " + degreAlcoolTotal);
        System.out.println("Degré de sucre total du cocktail : " + degreSucreTotal);
        
        System.out.println("Confirmer la création du cocktail ? \n O.Confirmer\n N.Annuler");
        choixCocktail =sc.next();
        switch (choixCocktail) {
        case "O":
        	Map<String, Cocktail> lstCocktail = GestionJSON.lireJSONCocktail("src\\\\com\\\\cytech\\\\collections\\\\cocktail.json");
        	if (lstCocktail == null) {
        		lstCocktail = new HashMap<>();
        	}
        	lstCocktail.put(newCocktail.getNom(), newCocktail);
        	GestionJSON.EcrireJsonCocktail(lstCocktail, "src\\\\com\\\\cytech\\\\collections\\\\cocktail.json");
        	break;
        case "N":
        	break;
        }
	       //sc.close();
	}
	
	public static void remplirStock() {
		Scanner sc = new Scanner(System.in);
		String choixboisson;
		try (FileReader reader = new FileReader("src\\com\\cytech\\collections\\boissonsimple.json")) {
			Gson gson = new GsonBuilder().create();
			Map<String, BoissonSimple> boissonsMap = gson.fromJson(reader, new TypeToken<Map<String, BoissonSimple>>(){}.getType());
	
            Map<BoissonSimple, Integer> newMap = new HashMap<>();
            System.out.println("Choisissez une boisson à remplir");
            choixboisson = sc.next();
            boolean boissonTrouvee = false;
			for (Map.Entry<String, BoissonSimple> entry : boissonsMap.entrySet()) {
				String nom = entry.getKey();
				
				newMap.put(entry.getValue(), 1);
				if (nom.equals(choixboisson)) {
					boissonTrouvee = true;
					System.out.println("Rentrez une valeur de stock");
					int newStock = sc.nextInt();
					Barman.reapprovisionner(entry.getValue(), newStock);
					newMap.put(entry.getValue(), newStock);
				}
			}
			
			if(!boissonTrouvee) {
				System.out.println("Pas de boisson dans la liste");
			}
			else {
				GestionJSON.EcrireJsonBoisson(newMap,"src\\com\\cytech\\collections\\boissonsimple.json");
			}
			
			//sc.close();
		} 
		
		catch (IOException e) {
            e.printStackTrace();
		}
	}

    public static void reapprovisionner(BoissonSimple Astockplus, int stockplus) {
    	Astockplus.setStock(Astockplus.getStock() + stockplus);
    }

    public static void servirCommande(Commande aServir) {
    	try (FileReader reader = new FileReader("src\\com\\cytech\\collections\\boissonsimple.json")) {
			Gson gson = new GsonBuilder().create();
			Map<String, BoissonSimple> boissonsMap = gson.fromJson(reader, new TypeToken<Map<String, BoissonSimple>>(){}.getType());
			
            Map<BoissonSimple, Integer> newMap = new HashMap<>();
            for (Entry<String, Integer> ListeBoisson : aServir.getListeBoisson().entrySet()) {
				for (Map.Entry<String, BoissonSimple> entry : boissonsMap.entrySet()) {
					String nom = entry.getKey();
					
					if (nom.equals(ListeBoisson.getKey())) {
						entry.getValue().setStock(entry.getValue().getStock() - ListeBoisson.getValue());
						newMap.put(entry.getValue(), ListeBoisson.getValue());
					}
				}
            }
				GestionJSON.EcrireJsonBoisson(newMap,"src\\com\\cytech\\collections\\boissonsimple.json");
    	}
		catch (IOException e) {
           e.printStackTrace();
		}
    	aServir.setEstServie(true);
    	//sc.close();
	} 
		
		
    		
    	
  }