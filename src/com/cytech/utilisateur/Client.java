package com.cytech.utilisateur;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;

import com.cytech.Ingredients.BoissonSimple;
import com.cytech.Ingredients.Cocktail;
import com.cytech.commandeboisson.Commande;
import com.cytech.gestionFichiers.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;


public class Client {
	
	private String email;
    @Override
	public String toString() {
		return "Client [email=" + email + ", motDePasse=" + motDePasse + ", nom=" + nom + ", prenom=" + prenom
				+ ", dateNaissance=" + dateNaissance + ", adresse=" + adresse + ", phraseSecrete=" + phraseSecrete
				+ "]";
	}

	private String motDePasse;
    private String nom;
    private String prenom;
    private String dateNaissance;
    private String adresse;
    private String phraseSecrete;


	public Client(String email, String motDePasse, String nom, String prenom, String dateNaissance, String adresse,
			String phraseSecrete) {
		super();
		this.email = email;
		this.motDePasse = motDePasse;
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.adresse = adresse;
		this.phraseSecrete = phraseSecrete;
	}
    
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(String dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getPhraseSecrete() {
		return phraseSecrete;
	}

	public void setPhraseSecrete(String phraseSecrete) {
		this.phraseSecrete = phraseSecrete;
	}

	public static void inscrire() {
		
		
		Scanner info = new Scanner(System.in);
		System.out.println("Saisir votre nom");
		String newNom = info.next();
		System.out.println("Saisir votre prénom");
		String newPrenom = info.next();
		String newEmail = null;
		boolean emailUnique = false;
	    List<Client> lstClient = GestionJSON.lireJSON("src/com/cytech/collections/clients.json");

	    while (!emailUnique) {
	        System.out.println("Saisir votre email");
	        newEmail = info.next();
	        emailUnique = isEmailUnique(newEmail, lstClient);
	        if (!emailUnique) {
	            System.out.println("Cet email est déjà utilisé. Veuillez en choisir un autre.");
	        }
	    }
		System.out.println("Saisir votre date de naissance");   //Conditions sur String TODO
		String newNaissance = info.next();
		System.out.println("Saisir votre adresse");
		String newAdresse = info.next();
		System.out.println("Saisir votre mot de passe");
		String newPassword = info.next();
		System.out.println("Saisir votre phrase secrète");
		String newPhrase = info.next();
		//info.close();
		
		Client newClient = new Client(newEmail, newPassword, newNom, newPrenom, newNaissance, newAdresse, newPhrase);
		lstClient =  GestionJSON.lireJSON("src\\com\\cytech\\collections\\clients.json");
		if (lstClient == null) {
	        lstClient = new ArrayList<>();
	    }
		lstClient.add(newClient);
		GestionJSON.EcrireJsonDirecte(lstClient, "src\\com\\cytech\\collections\\clients.json");
    }
	
	private static boolean isEmailUnique(String email, List<Client> clients) {
		if (clients == null) {
			return true;
		}
		
	    for (Client client : clients) {
	        if (client.getEmail().equals(email)) {
	            return false;
	        }
	    }
	    return true;
	}

	public static boolean connexion(String email, String motDePasse) {
		
		List<Client> lstClient =  GestionJSON.lireJSON("src\\com\\cytech\\collections\\clients.json");
		if (lstClient == null) {
	        return false;
	    }
		for (Client client : lstClient) {
            // Récupérer les identifiants du client
            String mail = client.getEmail();
            String motdepasse = client.getMotDePasse();
            
            // Vérifier si les identifiants correspondent
            if (mail.equals(email) && motdepasse.equals(motDePasse)) {
            	System.out.println("Connexion réussie !");
                return true;
            }
        }
		System.out.println("Adresse email ou mot de passe incorrect !");
        return false;
    	
      
    }
	
	public static void passerCommande(String email) {
		Scanner sc = new Scanner(System.in);
		boolean boissonCreaCommande = true;
		boolean cocktailCreaCommande = true;
		
		Date date = new Date();
		String nomCommande = date.toString()+ email.toString();
		String choixCommande;
		Commande newCommande = new Commande(nomCommande, null, null, false, date.toString(), email);
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
						String toAdd = entry.getValue().getNom();
						
						if (nom.equals(choixCommande)) {
							System.out.println("Choisir une quantité");
							int quantité = sc.nextInt();
							if(entry.getValue().getStock()>=quantité) {
								boissonTrouvee = true;
								newCommande.ajouterBoissonCommande(toAdd, quantité);
							}
							else {
								System.out.println("Stock insuffisant il reste " + entry.getValue().getStock() + " de " + nom);
							}
						}
					}
					
					if (boissonTrouvee) {
						System.out.println("Liste des boissons de la commande après ajout :");
				        for (Entry<String, Integer> entry : newCommande.getListeBoisson().entrySet()) {
				            System.out.println(entry.getKey() + " - Quantité : " + entry.getValue());
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
			Map<String, Cocktail> lstCocktail = GestionJSON.lireJSONCocktail("src\\com\\cytech\\collections\\cocktail.json");
			if (lstCocktail != null) {
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				String jsonStr = gson.toJson(lstCocktail);
				System.out.println(jsonStr);
				System.out.println("Tapez le nom de votre cocktail pour l'ajouter sinon tapez FIN pour passer à la suite");
				
				
				choixCommande = sc.next();
				if (!choixCommande.equals("FIN")) {
					
						
						boolean cocktailTrouve = false;
						for (Map.Entry<String, Cocktail> entry : lstCocktail.entrySet()) {
							System.out.println(entry);
							String nom = entry.getKey();
							String toAdd = entry.getValue().getNom();
							
							if (nom.equals(choixCommande)) {
								System.out.println("Choisir une quantité");
								int quantité = sc.nextInt();
								
									cocktailTrouve = true;
									newCommande.ajouterCocktail(toAdd, quantité);
								
							}
						}
						
						if (cocktailTrouve) {
							System.out.println("Liste des cocktails de la commande après ajout :");
					        for (Entry<String, Integer> entry : newCommande.getListeBoisson().entrySet()) {
					            System.out.println(entry.getKey() + " - Quantité : " + entry.getValue());
					        }
					        System.out.println("\n");
						}
						
						else {
							System.out.println("Cocktail non trouvée ou stock insuffisant");
						}
				}
				else {
					cocktailCreaCommande = false;
					}
				}
			
			else {
				System.out.println("Pas de cocktails disponible");
				cocktailCreaCommande = false;
			}
			
			
			
		}
			
		}
		
	}
	
	public static String getNomClient(String email) {
		List<Client> lstClient = GestionJSON.lireJSON("src\\\\com\\\\cytech\\\\collections\\\\clients.json");
		for (Client entry : lstClient) {
			if (entry.getEmail().equals(email)) {
				return entry.getNom();
			}
			else {
				return null;
			}
		}
		return null;
	}
	
	public static List<Commande> voirCommande(String nom) {
		List<Commande> lstCommande = GestionJSON.lireJSONCommande("src\\\\com\\\\cytech\\\\collections\\\\commandes.json");
		List<Commande> commandesClients = new ArrayList();
		for (Commande entry : lstCommande) {
			if (entry.getNomClient().equals(nom)) {
				commandesClients.add(entry);
			}
		    
		}
		return commandesClients;
	}

}