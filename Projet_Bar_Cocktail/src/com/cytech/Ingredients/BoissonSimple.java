package com.cytech.Ingredients;

public class BoissonSimple extends Boisson {
	
		private int stock;

	
	    public BoissonSimple(String nom, double prix, int degreAlcool, int degreSucre, int stock) {
	        super(prix, nom, degreAlcool, degreSucre);
	        this.stock = stock;
	    }


		public int getStock() {
	        return stock;
	    }

	    public void setStock(int stock) {
	        this.stock = stock;
	    }
	    
	    public static void miseAJourStock(BoissonSimple Ajour, int changestock) {
	        if (Ajour.getStock() !=0) {
	        	if ((Ajour.getStock() - changestock) >= 0) {
	        		Ajour.setStock(Ajour.getStock() - changestock);
	        	}
	        	else {
	        		Ajour.setStock(0);
	        	}
	        	
	        }
	    }


		@Override
		public String toString() {
			return "BoissonSimple [stock=" + stock + "]";
		}


		
}