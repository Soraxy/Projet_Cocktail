package com.cytech.testsUnitaires;

import java.util.List;
import java.util.Scanner;

import com.cytech.gestionFichiers.GestionJSON;
import com.cytech.utilisateur.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ClientTest {
	
	public static void main(String[] args) {
		/*try {
			 Client.inscrire();
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Client> lstCat = GestionJSON.lireJSON("src\\com\\cytech\\collections\\clients.json");

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String jsonStr = gson.toJson(lstCat);
		System.out.println(jsonStr);*/
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Connexion");
		System.out.println("Entrez votre email");
		String email = sc.next();
		System.out.println("Entrez votre mot de passe");
		String mdp = sc.next();
		
		Client.connexion(email, mdp);
		
		Client.passerCommande(email);
	}
}
