package com.cytech.main;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

import com.cytech.Ingredients.BoissonSimple;
import com.cytech.Ingredients.Cocktail;
import com.cytech.Ingredients.IngredientBonus;
import com.cytech.commandeboisson.Commande;
import com.cytech.gestionFichiers.GestionJSON;
import com.cytech.utilisateur.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

public class MainOld {

	public static void main(String[] args) {
		boolean connect = true;
		Scanner sc = new Scanner(System.in);
		while (connect==true) {
			System.out.println("Bienvenue dans le bar à coktail ! \n Vous êtes...");
			System.out.println("1.Barman\n2.Client\n3.Quittez le programme\nVeuillez choisir une option");
			
			while (!sc.hasNextInt()) {
                System.out.println("Veuillez entrer un nombre entier !"); // vérifie si l'entrée n'est pas un entier
               if(sc.hasNextInt()) {
            	   System.out.println("Null");
               }
               int rsp = sc.nextInt();
               System.out.println(rsp);
            }
			int select = sc.nextInt();
			switch (select) {
				case 1:
					boolean barmanConnect = true;
					while (barmanConnect) {
						System.out.println("Menu Barman");
						System.out.println("1.Servir des commandes\n2.Gérer les coktails\n3.Gérer le stock des boissons\n4.Voir recettes\n5.Déconnexion");
						while (!sc.hasNextInt()) {
	                        System.out.println("Veuillez entrer un nombre entier !");
	                        sc.next();
	                    }
						int selectBarman = sc.nextInt();
						switch(selectBarman) {
						case 1:
							//ServirCommande
							break;
						case 2:
							boolean cocktailConnect = true;
							while (cocktailConnect) {
								System.out.println("Menu Cocktail");
								System.out.println("1.Créer un nouveau cocktail\n2.Voir les cocktails disponible\n3.Revenir en arrière");
								while (!sc.hasNextInt()) {
			                        System.out.println("Veuillez entrer un nombre entier !");
			                        sc.next();
			                    }
								int selectCocktail = sc.nextInt();
								switch (selectCocktail) {
								case 1:
									
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
							        for (Map.Entry<BoissonSimple, Integer> entry : newCocktail.getListeBoissonSimple().entrySet()) {
							            System.out.println(entry.getKey().getNom() + " - Quantité : " + entry.getValue());
							        }
							        System.out.println("\n");
							        System.out.println("Liste des ingrédients du cocktail après ajout :");
							        for (Map.Entry<IngredientBonus, Integer> entry : newCocktail.getListeIngredientBonus().entrySet()) {
							            System.out.println(entry.getKey().getNom() + " - Quantité : " + entry.getValue());
							        }
							        System.out.println("\n");
							        
							        double prixTotal = newCocktail.calculerPrix();
							        double degreAlcoolTotal = newCocktail.calculerDegreAlcool();
							        double degreSucreTotal = newCocktail.calculerDegreSucre();
							        
							        newCocktail.setPrix(prixTotal);
							        newCocktail.setDegreAlcool(degreAlcoolTotal);
							        
							        System.out.println("Prix total du cocktail : " + prixTotal);
							        System.out.println("Degré d'alcool total du cocktail : " + degreAlcoolTotal);
							        System.out.println("Degré de sucre total du cocktail : " + degreSucreTotal);
							        
							        System.out.println("Confirmer la création du cocktail ? \n O.Confirmer\n N.Annuler");
							        choixCocktail =sc.next();
							        switch (choixCocktail) {
							        case "O":
							        	Map<Cocktail, Integer> newMap = new HashMap<>();
							        	newMap.put(newCocktail, null);
							        	GestionJSON.EcrireJsonCocktail(newMap, "src\\\\com\\\\cytech\\\\collections\\\\cocktail.json");
							        	break;
							        case "N":
							        	break;
							        }
							        
									break;
								case 2:
									GestionJSON.lireJSONCocktail("src\\\\com\\\\cytech\\\\collections\\\\cocktail.json");
									break;
								case 3:
									selectCocktail = 0;
									cocktailConnect = false;
									break;
								}
							}
							
							break;
						case 3:
							boolean connectStock = true;
							while (connectStock) {
								System.out.println("Menu Gestion de stock");
								System.out.println("1.Voir le stock des boissons\n2.Remplir le stock des boissons\n3.Quitter");
								while (!sc.hasNextInt()) {
			                        System.out.println("Veuillez entrer un nombre entier !");
			                        sc.next();
			                    }
								int selectStock = sc.nextInt();
								switch (selectStock) {
								case 1: 
									GestionJSON.lireJSONBoisson("src\\\\com\\\\cytech\\\\collections\\\\boissonsimple.json");
									break;
								case 2:
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
									} 
									
									catch (IOException e) {
							            e.printStackTrace();
									}
									break;
								case 3:
									selectStock = 0;
									connectStock = false;
									break;
							}
							}
							break;
						case 4:
							//Voir recettes
							break;
						case 5:
							selectBarman = 0;
							barmanConnect = false;
							break;
						}
						
						
					}
					break;
				case 2:
					boolean clientConnect = true;
					while (clientConnect) {
						System.out.println("Menu Client");
						System.out.println("1.Inscription\n2.Connexion\n3.Retourner en arrière");
							while (!sc.hasNextInt()) {
		                        System.out.println("Veuillez entrer un nombre entier !");
		                        sc.next();
		                    }
						
							int selectClient = sc.nextInt();
							switch(selectClient) {
							case 1:
								Client.inscrire();
								break;
							case 2:
								boolean connexion = false;
									System.out.println("Veuillez rentrer un email");
									String email = sc.next();
									System.out.println("Veuillez rentrer un mot de passe");
									String mdp = sc.next();
									if(Client.connexion(email, mdp)) {
										connexion = true;
									}
								while(connexion == true) {
									System.out.println("Bienvenue \n1.Passer une nouvelle commande\n2.Voir la liste des commandes\n3.Se déconnecter");
									while (!sc.hasNextInt()) {
				                        System.out.println("Veuillez entrer un nombre entier !");
				                        sc.next();
				                    }
									
									int selectClientConn = sc.nextInt();
									switch(selectClientConn) {
									case 1:
										boolean boissonCreaCommande = true;
										boolean cocktailCreaCommande = true;
										
										Date date = new Date();
										String nomCommande = date.toString()+email.toString();
										String choixCommande;
										Commande newCommande = new Commande(nomCommande, null, null, false, date, email);
										while (boissonCreaCommande) {
											
											System.out.println("Ajoutez des boissons à votre commande, voici la liste des boissons disponibles ");
											GestionJSON.lireJSONBoisson("src\\\\com\\\\cytech\\\\collections\\\\boissonsimple.json");
											System.out.println("Tapez le nom de votre boisson pour l'ajouter sinon tapez FIN pour passer à la suite");
											
											choixCommande = sc.next();
											if (!choixCommande.equals("FIN")) {
												try (FileReader reader = new FileReader("src\\com\\cytech\\collections\\boissonsimple.json")) {
													Gson gson = new GsonBuilder().create();
													Map<String, BoissonSimple> boissonsMap = gson.fromJson(reader, new TypeToken<Map<String, BoissonSimple>>(){}.getType());
													
													boolean boissonTrouvee = false;
													for (Map.Entry<String, BoissonSimple> entry : boissonsMap.entrySet()) {
														String nom = entry.getKey();
														BoissonSimple toAdd = entry.getValue();
														
														if (nom.equals(choixCommande)) {
															System.out.println("Choisir une quantité");
															int quantité = sc.nextInt();
															if(toAdd.getStock()>=quantité) {
																boissonTrouvee = true;
																newCommande.ajouterBoissonCommande(toAdd, quantité);
															}
															else {
																System.out.println("Stock insuffisant il reste " + toAdd.getStock() + " de " + nom);
															}
														}
													}
													
													if (boissonTrouvee) {
														System.out.println("Liste des boissons de la commande après ajout :");
												        for (Map.Entry<BoissonSimple, Integer> entry : newCommande.getListeBoissonSimple().entrySet()) {
												            System.out.println(entry.getKey().getNom() + " - Quantité : " + entry.getValue());
												        }
												        System.out.println("\n");
													}
													
													else {
														System.out.println("Boisson non trouvée ou stock insuffisant");
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
												boissonCreaCommande = false;
											}
											
										while (cocktailCreaCommande) {
											System.out.println("Ajoutez des cocktails à votre commande, voici la liste des cocktails disponibles ");
											
											try (FileReader reader = new FileReader("src\\com\\cytech\\collections\\cocktail.json")) {
												Gson gson = new GsonBuilder().create();
												Map<String, Cocktail> cocktailMap = gson.fromJson(reader, new TypeToken<Map<String, Cocktail>>(){}.getType());
												//List<Cocktail> cocktailMap = gson.fromJson(reader, new TypeToken<Map<String, Cocktail>>(){}.getType());
												
												/*try (FileReader reader2 = new FileReader("src\\com\\cytech\\collections\\boissonsimple.json")) {
													Gson gson2 = new GsonBuilder().create();
													Map<String, BoissonSimple> boissonsMap = gson.fromJson(reader, new TypeToken<Map<String, BoissonSimple>>(){}.getType());
													Map<String, BoissonSimple> boissonsMap2 = gson2.fromJson(reader2, new TypeToken<Map<String, BoissonSimple>>(){}.getType());
													for (Map.Entry<String, Cocktail> entry : cocktailMap.entrySet()) {
														String nom = entry.getKey();
														Cocktail cocktail = entry.getValue();
										               
														for (Map.Entry<String, BoissonSimple> entry2 : boissonsMap.entrySet()) {
															String nomBoisson = entry2.getKey();
															BoissonSimple boisson = entry2.getValue();
															BoissonSimple boissonDisponible = boissonsMap2.get(nomBoisson);
															if (boissonDisponible != null && boissonDisponible.getStock() >= boisson.getStock()) {
											                    System.out.println("Nom du cocktail : " + nom);
											                    System.out.println("Nom de la boisson simple : " + nomBoisson);
											                    System.out.println("Stock de la boisson simple : " + boisson.getStock());
											                }
														}
														
													}
													
													System.out.println("Tapez le nom de votre boisson pour l'ajouter sinon tapez FIN pour passer à la suite");
												}*/
												
												
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
											
										}
										break;
									case 2:
										//voirListeCommande();
										break;
									case 3:
										connexion = false;
										selectClientConn = 0;
										break;
									}
								}
								
								break;
							case 3:
								clientConnect = false;
								selectClient = 0;
								break;
							}
					}
					break;
				case 3:
					System.out.println("Merci et à bientôt !");
					connect = false;
					break;
				default:
					break;
			}
			
		}
		
		//sc.close();
		}
		
	}

