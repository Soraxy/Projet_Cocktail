package com.cytech.gestionFichiers;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.Map.Entry;

import com.cytech.Ingredients.BoissonSimple;
import com.cytech.Ingredients.Cocktail;
import com.cytech.Ingredients.IngredientBonus;
import com.cytech.collections.CocktailCollection;
import com.cytech.commandeboisson.Commande;
import com.cytech.utilisateur.Client;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

public class GestionJSON {

	// Recuperation de la liste de clients a partir du fichier JSON
	public static List<Client> lireJSON(String fichierJSON) {
		try {
			JsonReader reader = new JsonReader(new FileReader(fichierJSON));
			// On peut lire un seul objet ou un tableau avec []
			Client[] tabClient = new Gson().fromJson(reader, Client[].class);
			if (tabClient == null) {
				return null;
			}
			List<Client> listeClients = new ArrayList<>(Arrays.asList(tabClient));
			return listeClients;
		} catch (FileNotFoundException e) {
			System.out.println(e.getStackTrace() + " : File Not Found");
		} catch (JsonParseException e) {
			System.out.println(e.getStackTrace() + " : JsonParseException");
		}
		return null;
	}
	
	//Lecture Boisson Simple
	
	public static void lireJSONBoisson (String fichierJSON) {
		try (FileReader reader = new FileReader("src\\com\\cytech\\collections\\boissonsimple.json")) {
			Gson gson = new GsonBuilder().create();
			Map<String, BoissonSimple> boissonsMap = gson.fromJson(reader, new TypeToken<Map<String, BoissonSimple>>(){}.getType());
    
			for (Map.Entry<String, BoissonSimple> entry : boissonsMap.entrySet()) {
				String nom = entry.getKey();
				BoissonSimple boisson = entry.getValue();
				System.out.println("Nom de la boisson : " + nom);
				System.out.println("Prix : " + boisson.getPrix());
				System.out.println("Degré d'alcool : " + boisson.getDegreAlcool());
				System.out.println("Degré de sucre : " + boisson.getDegreSucre());
				System.out.println("Stock : " + boisson.getStock() + "\n");
			}
		}
		catch (IOException e) {
            e.printStackTrace();
		}
	}
	
	public static void lireJSONIngredient (String fichierJSON) {
		try (FileReader reader = new FileReader("src\\com\\cytech\\collections\\ingredientbonus.json")) {
			Gson gson = new GsonBuilder().create();
			Map<String, IngredientBonus> IngredientMap = gson.fromJson(reader, new TypeToken<Map<String, IngredientBonus>>(){}.getType());
    
			for (Map.Entry<String, IngredientBonus> entry : IngredientMap.entrySet()) {
				String nom = entry.getKey();
				IngredientBonus ingredient = entry.getValue();
				System.out.println("Nom de l'ingrédient : " + nom);
				System.out.println("Prix : " + ingredient.getPrix() + "\n");
			}
		}
		catch (IOException e) {
            e.printStackTrace();
		}
	}
	
	public static Map<String, Cocktail> lireJSONCocktail(String fichierJSON) {
	    try {
	        JsonReader reader = new JsonReader(new FileReader(fichierJSON));
	        Gson gson = new GsonBuilder().create();
	        Map<String, Cocktail> cocktail = gson.fromJson(reader, CocktailCollection.class);
	        return cocktail;
	    } catch (FileNotFoundException e) {
	        System.out.println(e.getStackTrace() + " : File Not Found");
	    } catch (JsonParseException e) {
	        System.out.println(e.getStackTrace() + " : JsonParseException");
	    }
	    return null;
	}

	
	/*public static void lireJSONCocktail(String fichierJSON) {
	    try (FileReader reader = new FileReader(fichierJSON)) {
	        Gson gson = new GsonBuilder().create();
	        Type listType = new TypeToken<List<Cocktail>>() {}.getType();
	        Cocktail [] tabCocktail = gson.fromJson(reader, listType);
	        List<Cocktail> cocktailList = new ArrayList<>(Arrays.asList(tabCocktail));
	        for (Cocktail cocktail : cocktailList) {
				System.out.println("Nom de la boisson : " + cocktail.getNom());
				System.out.println("Prix : " + cocktail.getPrix());
				System.out.println("Degré d'alcool : " + cocktail.getDegreAlcool());
				System.out.println("Degré de sucre : " + cocktail.getDegreSucre());
				System.out.println("Liste des boissons simples :");
	            for (Map.Entry<BoissonSimple, Integer> boissonEntry : cocktail.getListeBoissonSimple().entrySet()) {
	                BoissonSimple boisson = boissonEntry.getKey();
	                int quantite = boissonEntry.getValue();
	                System.out.println("- Nom : " + boisson.getNom());
	                System.out.println("  Prix : " + boisson.getPrix());
	                System.out.println("  Degré d'alcool : " + boisson.getDegreAlcool());
	                System.out.println("  Degré de sucre : " + boisson.getDegreSucre());
	                System.out.println("  Stock : " + boisson.getStock());
	                System.out.println("  Quantité : " + quantite);
	            }
	            for (Map.Entry<IngredientBonus, Integer> Ingrediententry : cocktail.getListeIngredientBonus().entrySet()) {
					IngredientBonus ingredient = Ingrediententry.getKey();
					int quantiteingredient = Ingrediententry.getValue();
					System.out.println("Nom de l'ingrédient : " + ingredient.getNom());
					System.out.println("Prix : " + ingredient.getPrix() + "\n");
					System.out.println("  Quantité : " + quantiteingredient);
				}
			}
	        
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}*/

	public static List<Commande> lireJSONCommande(String fichierJSON) {
		try {
			JsonReader reader = new JsonReader(new FileReader(fichierJSON));
			// On peut lire un seul objet ou un tableau avec []
			Commande[] tabCommande = new Gson().fromJson(reader, Commande[].class);
			if (tabCommande == null) {
				return null;
			}
			List<Commande> listeCommande = new ArrayList<>(Arrays.asList(tabCommande));
			return listeCommande;
		} catch (FileNotFoundException e) {
			System.out.println(e.getStackTrace() + " : File Not Found");
		} catch (JsonParseException e) {
			System.out.println(e.getStackTrace() + " : JsonParseException");
		}
		return null;
	}



	// Sauvegarde de la liste d dans un fichier JSON

	// Sauvegarde directe des attributs des objets grace a la methode toJson
	public static boolean EcrireJsonDirecte(List<Client> lstClient, String fichierJSON) {
		//System.out.println(lstClient);
		try {
			GsonBuilder gsonBuilder = new GsonBuilder();
			Gson gson = gsonBuilder.setPrettyPrinting().create();
			String jsonStr = gson.toJson(lstClient);
			BufferedWriter bw = new BufferedWriter(new FileWriter(fichierJSON));
			bw.write(jsonStr);
			bw.close();
		} catch (IOException e) {
			System.out.println(e.getStackTrace() + " : Probleme de fichier");
			return false;
		} catch (JsonParseException e) {
			System.out.println(e.getStackTrace() + " : JsonParseException");
			return false;
		}
		return true;
	}
	
	//Sauvegarde BoissonSimple
	
	public static boolean EcrireJsonBoisson(Map<BoissonSimple,Integer> lstBoisson, String fichierJSON) {
		try {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonObject jsonObject = new JsonObject();
            for (Map.Entry<BoissonSimple, Integer> entry : lstBoisson.entrySet()) {
                BoissonSimple boisson = entry.getKey();
                JsonObject boissonJson = new JsonObject();
                boissonJson.addProperty("nom", boisson.getNom());
                boissonJson.addProperty("prix", boisson.getPrix());
                boissonJson.addProperty("degreAlcool", boisson.getDegreAlcool());
                boissonJson.addProperty("degreSucre", boisson.getDegreSucre());
                boissonJson.addProperty("stock", boisson.getStock());
                
                jsonObject.add(boisson.getNom(), boissonJson);
			String jsonStr = gson.toJson(jsonObject);
			BufferedWriter bw = new BufferedWriter(new FileWriter(fichierJSON));
			bw.write(jsonStr);
			bw.close();
            }
		} catch (IOException e) {
			System.out.println(e.getStackTrace() + " : Probleme de fichier");
			return false;
		} catch (JsonParseException e) {
			System.out.println(e.getMessage() + " : JsonParseException");
			return false;
		}
		return true;
	}
	
	public static boolean EcrireJsonIngredient(Map<IngredientBonus,Integer> lstIngredient, String fichierJSON) {
		try {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonObject jsonObject = new JsonObject();
            for (Map.Entry<IngredientBonus, Integer> entry : lstIngredient.entrySet()) {
                IngredientBonus ingredient = entry.getKey();
                JsonObject ingredientJson = new JsonObject();
                ingredientJson.addProperty("nom", ingredient.getNom());
                ingredientJson.addProperty("prix", ingredient.getPrix());
                
                jsonObject.add(ingredient.getNom(), ingredientJson);
			String jsonStr = gson.toJson(jsonObject);
			BufferedWriter bw = new BufferedWriter(new FileWriter(fichierJSON));
			bw.write(jsonStr);
			bw.close();
            }
		} catch (IOException e) {
			System.out.println(e.getStackTrace() + " : Probleme de fichier");
			return false;
		} catch (JsonParseException e) {
			System.out.println(e.getMessage() + " : JsonParseException");
			return false;
		}
		return true;
	}
	
	/*public static boolean EcrireJsonCocktail(Map<String, Cocktail> listeCocktail, String fichierJSON) {
		//System.out.println(lstCocktail);
		try {
			GsonBuilder gsonBuilder = new GsonBuilder();
			Gson gson = gsonBuilder.setPrettyPrinting().create();
			String jsonStr = gson.toJson(listeCocktail);
			BufferedWriter bw = new BufferedWriter(new FileWriter(fichierJSON));
			bw.write(jsonStr);
			bw.close();
		} catch (IOException e) {
			System.out.println(e.getStackTrace() + " : Probleme de fichier");
			return false;
		} catch (JsonParseException e) {
			System.out.println(e.getStackTrace() + " : JsonParseException");
			return false;
		}
		return true;
	}*/
	
	public static boolean EcrireJsonCocktail(Map<String, Cocktail> lstCocktail, String fichierJSON) {
	    try {
	        Gson gson = new GsonBuilder().setPrettyPrinting().create();
	        JsonObject jsonObject = new JsonObject();
	        
	        for (Entry<String, Cocktail> entry : lstCocktail.entrySet()) {
	        	JsonArray listeBoissonJson = new JsonArray();
	        	JsonArray listeIngredientJson = new JsonArray();
	            Cocktail cocktail = entry.getValue();
	            JsonObject cocktailJson = new JsonObject();
	            cocktailJson.addProperty("nom", cocktail.getNom());
	            cocktailJson.addProperty("prix", cocktail.getPrix());
	            cocktailJson.addProperty("degreAlcool", cocktail.getDegreAlcool());
	            cocktailJson.addProperty("degreSucre", cocktail.getDegreSucre());
	            
	            if (cocktail.getListeBoissonSimple() != null) {
	            for (Map.Entry<BoissonSimple, Integer> boissonEntry : cocktail.getListeBoissonSimple().entrySet()) {
	            	
	                JsonObject boissonJson = new JsonObject();
	                BoissonSimple boisson = boissonEntry.getKey();
	                int quantite = boissonEntry.getValue();
	                boissonJson.addProperty("nom", boisson.getNom());
	                boissonJson.addProperty("prix", boisson.getPrix());
	                boissonJson.addProperty("degreAlcool", boisson.getDegreAlcool());
	                boissonJson.addProperty("degreSucre", boisson.getDegreSucre());
	                //boissonJson.addProperty("quantite", quantite);
	                listeBoissonJson.add(boissonJson);
	            }
	            cocktailJson.add("ListeBoissonSimple", listeBoissonJson);
	            }
	            
	            if(cocktail.getListeIngredientBonus() != null) {
	            for (Map.Entry<IngredientBonus, Integer> ingredientEntry : cocktail.getListeIngredientBonus().entrySet()) {
	                JsonObject ingredientJson = new JsonObject();
	                IngredientBonus ingredient = ingredientEntry.getKey();
	                int quantite = ingredientEntry.getValue();
	                ingredientJson.addProperty("nom", ingredient.getNom());
	                ingredientJson.addProperty("prix", ingredient.getPrix());
	                //ingredientJson.addProperty("quantite", quantite);
	                listeIngredientJson.add(ingredientJson);
	            }
	            cocktailJson.add("ListeIngredientBonus", listeIngredientJson);
	            }
	            
	            jsonObject.add(cocktail.getNom(), cocktailJson);
	        }
	        
	        
	        String jsonStr = gson.toJson(jsonObject);
	        BufferedWriter bw = new BufferedWriter(new FileWriter(fichierJSON));
	        bw.write(jsonStr);
	        bw.close();
	        
	        return true;
	    } catch (IOException e) {
	        System.out.println(e.getStackTrace() + " : Probleme de fichier");
	        return false;
	    }
	}
	
	public static boolean EcrireJsonCommande(List<Commande> listeCommande, String fichierJSON) {
	//System.out.println(lstCocktail);
	try {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.setPrettyPrinting().create();
		String jsonStr = gson.toJson(listeCommande);
		BufferedWriter bw = new BufferedWriter(new FileWriter(fichierJSON));
		bw.write(jsonStr);
		bw.close();
	} catch (IOException e) {
		System.out.println(e.getStackTrace() + " : Probleme de fichier");
		return false;
	} catch (JsonParseException e) {
		System.out.println(e.getStackTrace() + " : JsonParseException");
		return false;
	}
	return true;
}

	

}
