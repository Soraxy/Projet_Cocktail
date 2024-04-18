package com.cytech.Ingredients;

import java.util.Objects;

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


		@Override
		public int hashCode() {
			return Objects.hash(stock);
		}


		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			BoissonSimple other = (BoissonSimple) obj;
			return stock == other.stock;
		}


		
}