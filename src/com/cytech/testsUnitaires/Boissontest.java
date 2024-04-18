package com.cytech.testsUnitaires;
import com.cytech.Ingredients.*;
public class Boissontest {

	public static void main(String[] args) {
		BoissonSimple test = new BoissonSimple("test", 10, 0, 0, 5 );
		BoissonSimple.miseAJourStock(test, 5);
		System.out.println(test.getStock());

	}

}
