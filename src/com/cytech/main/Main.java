package com.cytech.main;

import java.util.Scanner;

import com.cytech.utilisateur.Barman;
import com.cytech.utilisateur.Client;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		Barman.creerCocktail();
		Client.inscrire();
		
		String email = sc.next();
		String mdp = sc.next();
		Client.connexion(email, mdp);
		
		Client.passerCommande(email);
		
		//Barman.remplirStock();
		//Barman.servirCommande(email);
		

	}

}
