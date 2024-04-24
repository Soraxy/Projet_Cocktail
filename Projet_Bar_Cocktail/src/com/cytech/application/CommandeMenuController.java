package com.cytech.application;

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
import com.cytech.commandeboisson.Commande;
import com.cytech.gestionFichiers.GestionJSON;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;



public class CommandeMenuController implements Initializable {
	
	private Parent root;
	private Stage stage;
	private Scene scene;
	
	static double prixPanier = 0;
	static List<Boisson> panier;
	
	@FXML
	private Label prixTotal;
	@FXML
	private TextField boissonQuantite;
	
	@FXML
	private TableView<Boisson> panierTable = new TableView<>();
	@FXML
    private TableColumn<Boisson, String> nom = new TableColumn<>();
    @FXML
    private TableColumn<Boisson, String> type = new TableColumn<>();
    @FXML
    private TableColumn<Boisson, Integer> quantite = new TableColumn<>();
    @FXML
    private TableColumn<Boisson, Double> prix = new TableColumn<>();
    @FXML
    private TableColumn<Boisson, Double> degreAlcool = new TableColumn<>();
    @FXML
    private TableColumn<Boisson, Double> degreSucre = new TableColumn<>();
    
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
	private TableView<Cocktail> cocktailTable = new TableView<>();
	@FXML
    private TableColumn<Cocktail, String> cocktailTableNom = new TableColumn<>();
    @FXML
    private TableColumn<Cocktail, Double> cocktailTablePrix = new TableColumn<>();
    @FXML
    private TableColumn<Cocktail, Integer> cocktailTableAlcool = new TableColumn<>();
    @FXML
    private TableColumn<Cocktail, Integer> cocktailTableSucre = new TableColumn<>();
    @FXML
    private TableColumn<Cocktail, Boolean> cocktailTableStock = new TableColumn<>();
    
	@FXML
	public void retourclt(ActionEvent event) throws IOException {
		panier = null;
		prixPanier = 0;
		 FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("com\\cytech\\interfaces\\ClientMenu.fxml"));	
		root = loader.load();
			
			ClientMenuController clientMenuController = loader.getController();
			clientMenuController.displayName(MainSceneController.nomClient);
		 
		 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		 scene = new Scene(root);
		 stage.setScene(scene);
		 stage.show();
	}
	
	@FXML
	public void retourCommande(ActionEvent event) throws IOException {
		 root = FXMLLoader.load(getClass().getClassLoader().getResource("com\\\\cytech\\\\interfaces\\\\CommandeMenu.fxml"));
		 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		 scene = new Scene(root);
		 stage.setScene(scene);
		 stage.show();
		 
	}
	
	public void addboisson(ActionEvent event) throws IOException {
		 root = FXMLLoader.load(getClass().getClassLoader().getResource("com\\\\cytech\\\\interfaces\\\\listeBoissonCommande.fxml"));
		 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		 scene = new Scene(root);
		 stage.setScene(scene);
		 stage.show();
		 
	}
	
	public void addcocktail(ActionEvent event) throws IOException {
		 root = FXMLLoader.load(getClass().getClassLoader().getResource("com\\\\cytech\\\\interfaces\\\\listeCocktailCommande.fxml"));
		 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		 scene = new Scene(root);
		 stage.setScene(scene);
		 stage.show();
		 
	}
	
	public void createcocktail(ActionEvent event) throws IOException {
		 root = FXMLLoader.load(getClass().getClassLoader().getResource("com\\\\cytech\\\\interfaces\\\\CocktailCreate.fxml"));
		 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		 scene = new Scene(root);
		 stage.setScene(scene);
		 stage.show();
		 
	}
	
	public void addToCart(ActionEvent event) {
		BoissonSimple choix = boissonTable.getSelectionModel().getSelectedItem();
	    if (choix != null) {
	    	Boisson addPanier = new Boisson(choix.getPrix(),choix.getNom(),choix.getDegreAlcool(),choix.getDegreSucre(),Integer.parseInt(boissonQuantite.getText()),"Boisson");
	    	if(panier == null) {
	    		panier = new ArrayList<>();
	    	}
	    	panier.add(addPanier);
	    	prixPanier = prixPanier + (choix.getPrix()*Integer.parseInt(boissonQuantite.getText()));
	    	initialize(null, null);
	    }

	}
	
	public void addCocktailtoCart(ActionEvent event) {
		Cocktail choix = cocktailTable.getSelectionModel().getSelectedItem();
	    if (choix != null) {
	    	Boisson addPanier = new Boisson(choix.getPrix(),choix.getNom(),choix.getDegreAlcool(),choix.getDegreSucre(),Integer.parseInt(boissonQuantite.getText()),"Cocktail");
	    	if(panier == null) {
	    		panier = new ArrayList<>();
	    	}
	    	panier.add(addPanier);
	    	prixPanier = prixPanier + (choix.getPrix()*Integer.parseInt(boissonQuantite.getText()));
	    	prixTotal.setText(String.valueOf(prixPanier));
	    	initialize(null, null);
	    }
	}
	
	public void removeFromCart(ActionEvent event) {
		Boisson choix = panierTable.getSelectionModel().getSelectedItem();
	    if (choix != null) {
	    	panier.remove(choix);
	    	prixPanier = prixPanier - (choix.getPrix()*choix.getQuantite());
	    	initialize(null, null);
	    }
	}

	public void finishCommande(ActionEvent event) throws IOException {
		Date date = new Date();
		System.out.println(panier);
		Commande newCommande = new Commande(date.toString() + MainSceneController.nomClient, null, null, false, date.toString(), MainSceneController.nomClient);
		for (Boisson boisson : panier) {
			if (boisson.getType().equals("Boisson")) {
				newCommande.ajouterBoissonCommande(boisson.getNom(), boisson.getQuantite());
			}
			else {
				newCommande.ajouterCocktail(boisson.getNom(), boisson.getQuantite());
			}
			
		}

		List<Commande> lstCommande = GestionJSON.lireJSONCommande("src\\\\com\\\\cytech\\\\collections\\\\commandes.json");
		lstCommande.add(newCommande);
		GestionJSON.EcrireJsonCommande(lstCommande, "src\\\\com\\\\cytech\\\\collections\\\\commandes.json");
		
		panier = null;
		prixPanier = 0;
		
		 FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("com\\cytech\\interfaces\\ClientMenu.fxml"));	
		 root = loader.load();
			
			ClientMenuController clientMenuController = loader.getController();
			clientMenuController.displayName(MainSceneController.nomClient);
		 
		 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		 scene = new Scene(root);
		 stage.setScene(scene);
		 stage.show();

		
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		prixTotal.setText("Prix total : " + String.valueOf(prixPanier));
		
		Map<String, BoissonSimple> lstBoisson = GestionJSON.lireJSONBoisson("src\\\\com\\\\cytech\\\\collections\\\\boissonsimple.json");
		List<BoissonSimple> boissonList = new ArrayList<>(lstBoisson.values());
		ObservableList<BoissonSimple> obsBoisson = FXCollections.observableArrayList(boissonList);
		boissonTableNom.setCellValueFactory(new PropertyValueFactory<BoissonSimple, String>("nom"));
		boissonTablePrix.setCellValueFactory(new PropertyValueFactory<BoissonSimple, Double>("prix"));
		boissonTableAlcool.setCellValueFactory(new PropertyValueFactory<BoissonSimple, Integer>("degreAlcool"));
		boissonTableSucre.setCellValueFactory(new PropertyValueFactory<BoissonSimple, Integer>("degreSucre"));
		boissonTableStock.setCellValueFactory(new PropertyValueFactory<BoissonSimple, Integer>("stock"));
		
		boissonTable.setItems(obsBoisson);
		
		Map<String, Cocktail> lstCocktail = GestionJSON.lireJSONCocktail("src\\\\com\\\\cytech\\\\collections\\\\cocktail.json");
		List<Cocktail> cocktailList = new ArrayList<>(lstCocktail.values());
		ObservableList<Cocktail> obsCocktail = FXCollections.observableArrayList(cocktailList);
		cocktailTableNom.setCellValueFactory(new PropertyValueFactory<Cocktail, String>("nom"));
		cocktailTablePrix.setCellValueFactory(new PropertyValueFactory<Cocktail, Double>("prix"));
		cocktailTableAlcool.setCellValueFactory(new PropertyValueFactory<Cocktail, Integer>("degreAlcool"));
		cocktailTableSucre.setCellValueFactory(new PropertyValueFactory<Cocktail, Integer>("degreSucre"));
		cocktailTableStock.setCellValueFactory(cellData -> {
		    Cocktail cocktail = cellData.getValue();
		    boolean enStock = cocktail.estEnStock();
		    String affichage = enStock ? "Oui" : "Non";

		    return new SimpleObjectProperty(affichage);
		});
		
		cocktailTable.setItems(obsCocktail);
		
		if(panier != null) {
			ObservableList<Boisson> obspanier = FXCollections.observableArrayList(panier);
			nom.setCellValueFactory(new PropertyValueFactory<Boisson, String>("nom"));
			type.setCellValueFactory(new PropertyValueFactory<Boisson, String>("type"));
			quantite.setCellValueFactory(new PropertyValueFactory<Boisson, Integer>("quantite"));
			prix.setCellValueFactory(new PropertyValueFactory<Boisson, Double>("prix"));
			degreAlcool.setCellValueFactory(new PropertyValueFactory<Boisson, Double>("degreAlcool"));
			degreSucre.setCellValueFactory(new PropertyValueFactory<Boisson, Double>("degreSucre"));
			
			panierTable.setItems(obspanier);
		}
		
	}
}
