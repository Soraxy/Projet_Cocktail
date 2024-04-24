package com.cytech.utilisateur;
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

	public static double encaisser(Commande servie) {
    	for (Entry<String, Integer> ListeBoisson : servie.getListeBoisson().entrySet()) {
				Map<String, BoissonSimple> boissonsMap = GestionJSON.lireJSONBoisson("src\\\\com\\\\cytech\\\\collections\\\\boissonsimple.json");
				for (Map.Entry<String, BoissonSimple> entry : boissonsMap.entrySet()) {
					String nom = entry.getKey();
					if (nom.equals(ListeBoisson.getKey())) {
						double prix = entry.getValue().getPrix() * ListeBoisson.getValue(); 
						profit = profit + prix;
					}
				}
    		
    		
    	}
    	
    	for (Entry<String, Integer> ListeCocktail : servie.getListeCocktail().entrySet()) {
				Map<String, Cocktail> boissonsMap = GestionJSON.lireJSONCocktail("src\\\\com\\\\cytech\\\\collections\\\\cocktail.json");
				
				for (Entry<String, Cocktail> entry : boissonsMap.entrySet()) {
					String nom = entry.getKey();
					
					if (nom.equals(ListeCocktail.getKey())) {
						double prix = entry.getValue().getPrix() * ListeCocktail.getValue(); 
						profit = profit + prix;
					}
				}
    		
    	}
    	return profit;
    }
	
	public static void remplirStock() {
		Scanner sc = new Scanner(System.in);
		String choixboisson;
		try (FileReader reader = new FileReader("src\\com\\cytech\\collections\\boissonsimple.json")) {
			Gson gson = new GsonBuilder().create();
			Map<String, BoissonSimple> boissonsMap = gson.fromJson(reader, new TypeToken<Map<String, BoissonSimple>>(){}.getType());
	
            Map<String, BoissonSimple> newMap = new HashMap<>();
            System.out.println("Choisissez une boisson à remplir");
            choixboisson = sc.next();
            boolean boissonTrouvee = false;
			for (Map.Entry<String, BoissonSimple> entry : boissonsMap.entrySet()) {
				String nom = entry.getKey();
				
				newMap.put(nom, entry.getValue());
				if (nom.equals(choixboisson)) {
					boissonTrouvee = true;
					System.out.println("Rentrez une valeur de stock");
					int newStock = sc.nextInt();
					Barman.reapprovisionner(entry.getValue(), newStock);
					newMap.put(nom, entry.getValue());
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

    public static boolean servirCommande(Commande aServir) {
    		
			Map<String, BoissonSimple> boissonsMap = GestionJSON.lireJSONBoisson("src\\com\\cytech\\collections\\boissonsimple.json");
			Map<String, Cocktail> cocktailMap = GestionJSON.lireJSONCocktail("src\\com\\cytech\\collections\\cocktail.json");
			
            Map<String, BoissonSimple> newMap = new HashMap<>();
            for (Map.Entry<String, BoissonSimple> entry : boissonsMap.entrySet()) {
            	String nom = entry.getKey();
				for (Entry<String, Integer> ListeBoisson : aServir.getListeBoisson().entrySet()) {
					
					if (nom.equals(ListeBoisson.getKey())) {
						if(((entry.getValue().getStock() - ListeBoisson.getValue()))<0) {
							return false;
						}
						else {
							entry.getValue().setStock(entry.getValue().getStock() - ListeBoisson.getValue());
						}
						
					}
				}
				
				if (aServir.getListeCocktail()!=null) {
					for (Entry<String, Integer> ListeCocktail : aServir.getListeCocktail().entrySet()) {
						for (Map.Entry<String, Cocktail> entryCocktail : cocktailMap.entrySet()) {
							if(entryCocktail.getValue().estEnStock()) {
								if(ListeCocktail.getKey().equals(entryCocktail.getKey())) {
									for (Entry<String,Integer> boisson : entryCocktail.getValue().getListeBoissonSimple().entrySet()) {
										if(nom.equals(boisson.getKey())) {
											if(((entry.getValue().getStock() - boisson.getValue()))<0) {
												return false;
											}
											else {
												entry.getValue().setStock(entry.getValue().getStock() - boisson.getValue());
											}
										}
									}
								}
							}
						}
					}
				}
				newMap.put(nom, entry.getValue());
            }
            
            
			GestionJSON.EcrireJsonBoisson(newMap,"src\\com\\cytech\\collections\\boissonsimple.json");

			List<Commande> lstCommande = GestionJSON.lireJSONCommande("src\\\\com\\\\cytech\\\\collections\\\\commandes.json");
			lstCommande.remove(aServir);
			aServir.setEstServie(true);
			lstCommande.add(aServir);
			GestionJSON.EcrireJsonCommande(lstCommande, "src\\\\com\\\\cytech\\\\collections\\\\commandes.json");
			
			return true;
	} 
		
		
    		
    	
  }