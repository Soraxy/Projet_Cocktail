package com.cytech.collections;

import java.util.ArrayList;
import java.util.List;

import com.cytech.commandeboisson.Commande;

public class CommandeCollection extends ArrayList<Commande> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Commande> commandes;
	
	public CommandeCollection() {
        commandes = new ArrayList<>();
    }
	
	public List<Commande> getCommandes() {
        return commandes;
    }

    public void setCommandes(List<Commande> commandes) {
        this.commandes = commandes;
    }
 
}
