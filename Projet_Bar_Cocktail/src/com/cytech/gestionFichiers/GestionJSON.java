package com.cytech.gestionFichiers;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.Map.Entry;

import com.cytech.Ingredients.BoissonSimple;
import com.cytech.Ingredients.Cocktail;
import com.cytech.Ingredients.IngredientBonus;
import com.cytech.collections.CocktailCollection;
import com.cytech.collections.CommandeCollection;
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
	
	public static Map<String, BoissonSimple> lireJSONBoisson (String fichierJSON) {
		try (FileReader reader = new FileReader("src\\com\\cytech\\collections\\boissonsimple.json")) {
			Gson gson = new GsonBuilder().create();
			Map<String, BoissonSimple> boissonsMap = gson.fromJson(reader, new TypeToken<Map<String, BoissonSimple>>(){}.getType());
    
			return boissonsMap;
		}
		catch (IOException e) {
            e.printStackTrace();
		} catch (JsonParseException e) {
	    	System.out.println(e);
	    }
	    return null;
	}
	
	public static Map<String, IngredientBonus> lireJSONIngredient (String fichierJSON) {
		try (FileReader reader = new FileReader("src\\com\\cytech\\collections\\ingredientbonus.json")) {
			Gson gson = new GsonBuilder().create();
			Map<String, IngredientBonus> IngredientMap = gson.fromJson(reader, new TypeToken<Map<String, IngredientBonus>>(){}.getType());
			return IngredientMap;
		}
		catch (IOException e) {
            e.printStackTrace();
		} catch (JsonParseException e) {
	    	System.out.println(e);
	    }
	    return null;
	}
	
	public static Map<String, Cocktail> lireJSONCocktail(String fichierJSON) {
	    try {
	        JsonReader reader = new JsonReader(new FileReader(fichierJSON));
	        Gson gson = new GsonBuilder().create();
	        Map<String,Cocktail> cocktail = gson.fromJson(reader, CocktailCollection.class);
	        return cocktail;
	    } catch (FileNotFoundException e) {
	        System.out.println(e.getStackTrace() + " : File Not Found");
	    } catch (JsonParseException e) {
	    	System.out.println(e);
	    }
	    return null;
	}

	public static List<Commande> lireJSONCommande(String fichierJSON) {
	    try {
	        JsonReader reader = new JsonReader(new FileReader(fichierJSON));
	        Gson gson = new GsonBuilder().create();
	        List<Commande> commande = gson.fromJson(reader, CommandeCollection.class);
	        return commande;
	    } catch (FileNotFoundException e) {
	        System.out.println(e.getStackTrace() + " : File Not Found");
	    } catch (JsonParseException e) {
	    	System.out.println(e);
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
	
	public static boolean EcrireJsonBoisson(Map<String, BoissonSimple> lstBoisson, String fichierJSON) {
		try {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonObject jsonObject = new JsonObject();
            for (Entry<String, BoissonSimple> entry : lstBoisson.entrySet()) {
                String boisson = entry.getKey();
                JsonObject boissonJson = new JsonObject();
                boissonJson.addProperty("nom", boisson);
                boissonJson.addProperty("prix", entry.getValue().getPrix());
                boissonJson.addProperty("degreAlcool", entry.getValue().getDegreAlcool());
                boissonJson.addProperty("degreSucre", entry.getValue().getDegreSucre());
                boissonJson.addProperty("stock", entry.getValue().getStock());
                
                jsonObject.add(boisson, boissonJson);
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
	
	public static boolean EcrireJsonCocktail(Map<String, Cocktail> lstCocktail, String fichierJSON) {
	    try {
	        Gson gson = new GsonBuilder().setPrettyPrinting().create();
	        JsonObject jsonObject = new JsonObject();
	        
	        for (Entry<String, Cocktail> entry : lstCocktail.entrySet()) {
	            Cocktail cocktail = entry.getValue();
	            JsonObject cocktailJson = new JsonObject();
	            cocktailJson.addProperty("nom", cocktail.getNom());
	            cocktailJson.addProperty("prix", cocktail.getPrix());
	            cocktailJson.addProperty("degreAlcool", cocktail.getDegreAlcool());
	            cocktailJson.addProperty("degreSucre", cocktail.getDegreSucre());
	            
	            if (cocktail.getListeBoissonSimple() != null) {
	            	JsonArray listeBoissonJson = new JsonArray();
	            for (Map.Entry<String, Integer> boissonEntry : cocktail.getListeBoissonSimple().entrySet()) {
	            	JsonArray Boisson = new JsonArray();
	                String boisson = boissonEntry.getKey();
	                int quantite = boissonEntry.getValue();
	                Boisson.add(boisson);
	                Boisson.add(quantite);
	                listeBoissonJson.add(Boisson);
	            }
	            cocktailJson.add("ListeBoissonSimple", listeBoissonJson);
	            }
	            
	            if(cocktail.getListeIngredientBonus() != null) {
	            	JsonArray listeIngredientJson = new JsonArray();
	            for (Map.Entry<String, Integer> ingredientEntry : cocktail.getListeIngredientBonus().entrySet()) {
	            	JsonArray Ingredient = new JsonArray();
	                String ingredient = ingredientEntry.getKey();
	                int quantite = ingredientEntry.getValue();
	                Ingredient.add(ingredient);
	                Ingredient.add(quantite);
	                listeIngredientJson.add(Ingredient);
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
	try {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.setPrettyPrinting().create();
		JsonArray jsonArray = new JsonArray();
		for (Commande entry : listeCommande) {
            JsonObject commandeJson = new JsonObject();
            commandeJson.addProperty("nomCommande", entry.getNomCommande());
            commandeJson.addProperty("estServie", entry.isEstServie());
            commandeJson.addProperty("date", entry.getDate());
            commandeJson.addProperty("nomClient", entry.getNomClient());
            
            if (entry.getListeBoisson()!= null) {
            	JsonArray listeBoissonJson = new JsonArray();
	            for (Entry<String, Integer> boissonEntry : entry.getListeBoisson().entrySet()) {
	            	JsonArray Boisson = new JsonArray();
	                String boisson = boissonEntry.getKey();
	                int quantite = boissonEntry.getValue();
	                Boisson.add(boisson);
	                Boisson.add(quantite);
	                listeBoissonJson.add(Boisson);
	            }
            commandeJson.add("listeBoisson", listeBoissonJson);
            }
            
            if(entry.getListeCocktail() != null) {
            	JsonArray arrayCocktail = new JsonArray();
            	for (Entry<String, Integer> entryCocktail : entry.getListeCocktail().entrySet()) {
            		JsonArray Cocktail = new JsonArray();
    	            String cocktail = entryCocktail.getKey();
    	            Cocktail.add(cocktail);
    	            Cocktail.add(entryCocktail.getValue());
    	            arrayCocktail.add(Cocktail);
    	            commandeJson.add("listeCocktail", arrayCocktail);
    	        }
            	
            }
            jsonArray.add(commandeJson);
		}
		String jsonStr = gson.toJson(jsonArray);
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
