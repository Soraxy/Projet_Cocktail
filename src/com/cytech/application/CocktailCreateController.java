package com.cytech.application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import com.cytech.Ingredients.Boisson;
import com.cytech.Ingredients.BoissonSimple;
import com.cytech.Ingredients.Cocktail;
import com.cytech.Ingredients.IngredientBonus;
import com.cytech.gestionFichiers.GestionJSON;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.TableView;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;

public class CocktailCreateController implements Initializable {
	private static Cocktail toCreate;
	private static List<Boisson> listeBoisson;
	private static List<IngredientBonus> listeIngredient;
	private static double prixcocktail = 0;
	private static double degreAlcool = 0;
	private static double degreSucre = 0;
	static boolean isBarman;
	
	private Parent root;
	private Stage stage;
	private Scene scene;
	
	@FXML
	private TableView<BoissonSimple> boissonTable = new TableView<>();
	@FXML
    private TableColumn<BoissonSimple, String> boissonTableNom = new TableColumn<>();
    @FXML
    private TableColumn<BoissonSimple, Double> boissonTablePrix = new TableColumn<>();
    @FXML
    private TableColumn<BoissonSimple, Integer> boissonTableAlcool = new TableColumn<>();
    @FXML
    private TableColumn<BoissonSimple, Integer> boissonTableSucre = new TableColumn<>();
    @FXML
    private TableColumn<BoissonSimple, Integer> boissonTableStock = new TableColumn<>();
    
    @FXML
	private TableView<IngredientBonus> ingredientTable = new TableView<>();
	@FXML
    private TableColumn<IngredientBonus, String> ingredientTableNom = new TableColumn<>();
    @FXML
    private TableColumn<IngredientBonus, Integer> ingredientTablePrix = new TableColumn<>();
		
	@FXML
	private TextField boissonQuantite;
	@FXML
	private Label prixCocktail;
	@FXML
	private Label CocktailRecapText;
	@FXML
	private TextField nomCocktail;
	
	@FXML
	private TableView<Boisson> boissonCocktailTable = new TableView<>();
	@FXML
	private TableColumn<Boisson, String> boissonCocktailNom = new TableColumn<>();
	@FXML
	private TableColumn<Boisson, String> boissonCocktailtype = new TableColumn<>();
	@FXML
	private TableColumn<Boisson, Integer> boissonCocktailquantite = new TableColumn<>();
	@FXML
	private TableColumn<Boisson, Double> boissonCocktailPrix = new TableColumn<>();
	@FXML
	private TableColumn<Boisson, Double> boissonCocktailAlcool = new TableColumn<>();
	@FXML
	private TableColumn<Boisson, Double> boissonCocktailSucre = new TableColumn<>();
	
	@FXML
	private TableView<IngredientBonus> ingredientCocktailTable = new TableView<>();
	@FXML
	private TableColumn<IngredientBonus, String> ingredientCocktailNom = new TableColumn<>();
	@FXML
	private TableColumn<IngredientBonus, Integer> ingredientCocktailquantite = new TableColumn<>();
	@FXML
	private TableColumn<IngredientBonus, Integer> ingredientCocktailPrix = new TableColumn<>();

	// Event Listener on Button.onAction
	@FXML
	public void retourCommande(ActionEvent event) throws IOException {
		 toCreate = null;
		 listeBoisson = null;
		 listeIngredient = null;
		 prixcocktail = 0;
		 degreAlcool = 0;
		 degreSucre = 0;
		 if (isBarman) {
			 root = FXMLLoader.load(getClass().getClassLoader().getResource("com\\\\cytech\\\\interfaces\\\\BarmanMenu.fxml"));
			 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			 scene = new Scene(root);
			 stage.setScene(scene);
			 stage.show();
		 }
		 else {
			 root = FXMLLoader.load(getClass().getClassLoader().getResource("com\\\\cytech\\\\interfaces\\\\CommandeMenu.fxml"));
			 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			 scene = new Scene(root);
			 stage.setScene(scene);
			 stage.show();
		 }
	}
	
	@FXML
	public void retourBoisson(ActionEvent event) throws IOException {
		 root = FXMLLoader.load(getClass().getClassLoader().getResource("com\\\\cytech\\\\interfaces\\\\CocktailCreate.fxml"));
		 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		 scene = new Scene(root);
		 stage.setScene(scene);
		 stage.show();
	}
	
	@FXML
	public void toIngredient(ActionEvent event) throws IOException {
		if(listeBoisson != null) {
		 root = FXMLLoader.load(getClass().getClassLoader().getResource("com\\\\cytech\\\\interfaces\\\\CocktailCreateIngredient.fxml"));
		 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		 scene = new Scene(root);
		 stage.setScene(scene);
		 stage.show();
		}
	}
	
	@FXML
	public void toRecap(ActionEvent event) throws IOException {
		if(isBarman) {
			 FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("com\\cytech\\interfaces\\CocktailRecapBarman.fxml"));	
				root = loader.load();
				
				CocktailCreateController cocktailCreateController = loader.getController();
				cocktailCreateController.displayCocktail(event);
			 
			 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			 scene = new Scene(root);
			 stage.setScene(scene);
			 stage.show();
		}
		else {
		
		 FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("com\\cytech\\interfaces\\CocktailRecap.fxml"));	
			root = loader.load();
			
			CocktailCreateController cocktailCreateController = loader.getController();
			cocktailCreateController.displayCocktail(event);
		 
		 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		 scene = new Scene(root);
		 stage.setScene(scene);
		 stage.show();
		}
	}
	
	@FXML
	public void displayCocktail(ActionEvent event) {
		CocktailRecapText.setText("Votre Cocktail : \nPrix : " + prixcocktail + "\nDegré Alcool : " + degreAlcool + "\nDegré Sucre : " + degreSucre );
	}
	// Event Listener on Button.onAction
	@FXML
	public void addToCocktail(ActionEvent event) {
		BoissonSimple choix = boissonTable.getSelectionModel().getSelectedItem();
	    if (choix != null) {
	    	Boisson addCocktail = new Boisson(choix.getPrix(),choix.getNom(),choix.getDegreAlcool(),choix.getDegreSucre(),Integer.parseInt(boissonQuantite.getText()),"Boisson");
	    	if(listeBoisson == null) {
	    		listeBoisson = new ArrayList<>();
	    	}
	    	if(toCreate == null) {
	    		toCreate = new Cocktail(0, null, 0, 0, null, null);
	    	}
	    	listeBoisson.add(addCocktail);
	    	prixcocktail = prixcocktail + (choix.getPrix()*Integer.parseInt(boissonQuantite.getText()));
	    	degreAlcool = degreAlcool + choix.getDegreAlcool();
	    	degreSucre = degreSucre + choix.getDegreSucre();
	    	prixCocktail.setText("Prix total : " + String.valueOf(prixcocktail));
	    	initialize(null, null);
	    }
	}
	
	public void confirmCocktailClient(ActionEvent event) throws IOException {
		Date date = new Date();
		toCreate.setPrix(prixcocktail*1.1);
		toCreate.setType("Cocktail");
		toCreate.setDegreAlcool(degreAlcool);
		toCreate.setDegreSucre(degreSucre);
		toCreate.setQuantite(1);
		toCreate.setNom(date.toString() + prixcocktail);
		for (Boisson boisson : listeBoisson) {
			toCreate.ajouterBoisson(boisson.getNom(), boisson.getQuantite());
		}
		for (IngredientBonus ingredient : listeIngredient) {
			toCreate.ajouterIngredient(ingredient.getNom(), ingredient.getQuantite());
		}

    	if(CommandeMenuController.panier == null) {
    		CommandeMenuController.panier = new ArrayList<>();
    	}
		CommandeMenuController.panier.add(toCreate);
		CommandeMenuController.prixPanier = CommandeMenuController.prixPanier + toCreate.getPrix();
		
		 toCreate = null;
		 listeBoisson = null;
		 listeIngredient = null;
		 prixcocktail = 0;
		 degreAlcool = 0;
		 degreSucre = 0;
		 root = FXMLLoader.load(getClass().getClassLoader().getResource("com\\\\cytech\\\\interfaces\\\\CommandeMenu.fxml"));
		 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		 scene = new Scene(root);
		 stage.setScene(scene);
		 stage.show();
	}
	
	public void confirmCocktailBarman(ActionEvent event) throws IOException {
		toCreate.setPrix(prixcocktail*1.1);
		toCreate.setType("Cocktail");
		toCreate.setDegreAlcool(degreAlcool);
		toCreate.setDegreSucre(degreSucre);
		toCreate.setQuantite(1);
		toCreate.setNom(nomCocktail.getText());
		for (Boisson boisson : listeBoisson) {
			toCreate.ajouterBoisson(boisson.getNom(), boisson.getQuantite());
		}
		for (IngredientBonus ingredient : listeIngredient) {
			toCreate.ajouterIngredient(ingredient.getNom(), ingredient.getQuantite());
		}
		
		Map <String, Cocktail> lstCocktail = GestionJSON.lireJSONCocktail("src\\\\com\\\\cytech\\\\collections\\\\cocktail.json");
		lstCocktail.put(toCreate.getNom(), toCreate);
		GestionJSON.EcrireJsonCocktail(lstCocktail, "src\\\\com\\\\cytech\\\\collections\\\\cocktail.json");
		
		 toCreate = null;
		 listeBoisson = null;
		 listeIngredient = null;
		 prixcocktail = 0;
		 degreAlcool = 0;
		 degreSucre = 0;
		 isBarman = false;
		 
		 root = FXMLLoader.load(getClass().getClassLoader().getResource("com\\\\cytech\\\\interfaces\\\\BarmanMenu.fxml"));
		 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		 scene = new Scene(root);
		 stage.setScene(scene);
		 stage.show();
	}
	
	@FXML
	public void addIngredientToCocktail(ActionEvent event) {
		IngredientBonus choix = ingredientTable.getSelectionModel().getSelectedItem();
		if (choix != null) {
	    	IngredientBonus addCocktail = new IngredientBonus(choix.getNom(),choix.getPrix()*Integer.parseInt(boissonQuantite.getText()),Integer.parseInt(boissonQuantite.getText()));
	    	if(listeIngredient == null) {
	    		listeIngredient = new ArrayList<>();
	    	}
	    	listeIngredient.add(addCocktail);
	    	prixcocktail = prixcocktail + (choix.getPrix()*Integer.parseInt(boissonQuantite.getText()));
	    	prixCocktail.setText("Prix total : " + String.valueOf(prixcocktail));
	    	initialize(null, null);
	    }
	}
	// Event Listener on Button.onAction
	@FXML
	public void removeFromCocktail(ActionEvent event) {
		Boisson choix = boissonCocktailTable.getSelectionModel().getSelectedItem();
	    if (choix != null) {
	    	listeBoisson.remove(choix);
	    	prixcocktail = prixcocktail - (choix.getPrix()*choix.getQuantite());
	    	degreAlcool = degreAlcool - choix.getDegreAlcool();
	    	degreSucre = degreSucre - choix.getDegreSucre();
	    	if (prixCocktail != null) {
	    		prixCocktail.setText("Prix total : " + String.valueOf(prixcocktail));
	    	}
	    	if (CocktailRecapText != null) {
	    		displayCocktail(event);
	    	}
	    	initialize(null, null);
	    }
	}
	
	public void removeIngredientFromCocktail(ActionEvent event) {
		IngredientBonus choix = ingredientCocktailTable.getSelectionModel().getSelectedItem();
	    if (choix != null) {
	    	listeIngredient.remove(choix);
	    	prixcocktail = prixcocktail - (choix.getPrix());
	    	if (prixCocktail != null) {
	    		prixCocktail.setText("Prix total : " + String.valueOf(prixcocktail));
	    	}
	    	if (CocktailRecapText != null) {
	    		displayCocktail(event);
	    	}
	    	initialize(null, null);
	    }
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		Map<String, BoissonSimple> lstBoisson = GestionJSON.lireJSONBoisson("src\\\\com\\\\cytech\\\\collections\\\\boissonsimple.json");
		List<BoissonSimple> boissonList = new ArrayList<>(lstBoisson.values());
		ObservableList<BoissonSimple> obsBoisson = FXCollections.observableArrayList(boissonList);
		boissonTableNom.setCellValueFactory(new PropertyValueFactory<BoissonSimple, String>("nom"));
		boissonTablePrix.setCellValueFactory(new PropertyValueFactory<BoissonSimple, Double>("prix"));
		boissonTableAlcool.setCellValueFactory(new PropertyValueFactory<BoissonSimple, Integer>("degreAlcool"));
		boissonTableSucre.setCellValueFactory(new PropertyValueFactory<BoissonSimple, Integer>("degreSucre"));
		boissonTableStock.setCellValueFactory(new PropertyValueFactory<BoissonSimple, Integer>("stock"));
		
		boissonTable.setItems(obsBoisson);
		
		Map<String, IngredientBonus> lstIngredient = GestionJSON.lireJSONIngredient("src\\com\\cytech\\collections\\ingredientbonus.json");
		List<IngredientBonus> ingredientList = new ArrayList<>(lstIngredient.values());
		ObservableList<IngredientBonus> obsIngredient = FXCollections.observableArrayList(ingredientList);
		ingredientTableNom.setCellValueFactory(new PropertyValueFactory<IngredientBonus, String>("nom"));
		ingredientTablePrix.setCellValueFactory(new PropertyValueFactory<IngredientBonus, Integer>("prix"));
		
		ingredientTable.setItems(obsIngredient);
		
		if(listeBoisson != null ) {
			ObservableList<Boisson>  obsCocktail = FXCollections.observableList(listeBoisson);
			boissonCocktailNom.setCellValueFactory(new PropertyValueFactory<Boisson, String>("nom"));
			boissonCocktailtype.setCellValueFactory(new PropertyValueFactory<Boisson, String>("type"));
			boissonCocktailquantite.setCellValueFactory(new PropertyValueFactory<Boisson, Integer>("quantite"));
			boissonCocktailPrix.setCellValueFactory(new PropertyValueFactory<Boisson, Double>("prix"));
			boissonCocktailAlcool.setCellValueFactory(new PropertyValueFactory<Boisson, Double>("degreAlcool"));
			boissonCocktailSucre.setCellValueFactory(new PropertyValueFactory<Boisson, Double>("degreSucre"));
			
			boissonCocktailTable.setItems(obsCocktail);
		}
		
		if(listeIngredient != null) {
			ObservableList<IngredientBonus> obsCocktailIngredient = FXCollections.observableList(listeIngredient);
			ingredientCocktailNom.setCellValueFactory(new PropertyValueFactory<IngredientBonus, String>("nom"));
			ingredientCocktailPrix.setCellValueFactory(new PropertyValueFactory<IngredientBonus, Integer>("prix"));
			ingredientCocktailquantite.setCellValueFactory(new PropertyValueFactory<IngredientBonus, Integer>("quantite"));
			
			ingredientCocktailTable.setItems(obsCocktailIngredient);
		}
	}
}
