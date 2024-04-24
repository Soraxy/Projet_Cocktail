package com.cytech.application;

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

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import com.cytech.Ingredients.BoissonSimple;
import com.cytech.commandeboisson.Commande;
import com.cytech.gestionFichiers.GestionJSON;
import com.cytech.utilisateur.Barman;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

public class BarmanMenuController implements Initializable {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	private static double profitTotal = 0;
	
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
	private TableView<Commande> CommandeTable = new TableView<>();
	@FXML
    private TableColumn<Commande, String> date = new TableColumn<>();
    @FXML
    private TableColumn<Commande, String> nomCommande = new TableColumn<>();
    @FXML
    private TableColumn<Commande, String> commandeBoisson = new TableColumn<>();
    @FXML
    private TableColumn<Commande, String> commandeCocktail = new TableColumn<>();
    @FXML
    private TableColumn<Commande, String> estServie = new TableColumn<>();
    
	@FXML
	private TextField boissonQuantite;
	@FXML
	private Label CommandeTitle;
	@FXML
	private Label profit;

	// Event Listener on Button.onAction
	@FXML
	public void servirCommande(ActionEvent event) throws IOException {
		 root = FXMLLoader.load(getClass().getClassLoader().getResource("com\\\\cytech\\\\interfaces\\\\CommandesBarman.fxml"));
		 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		 scene = new Scene(root);
		 stage.setScene(scene);
		 stage.show();
	}
	// Event Listener on Button.onAction
	@FXML
	public void gererStock(ActionEvent event) throws IOException {
		 root = FXMLLoader.load(getClass().getClassLoader().getResource("com\\\\cytech\\\\interfaces\\\\BarmanStock.fxml"));
		 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		 scene = new Scene(root);
		 stage.setScene(scene);
		 stage.show();
	}
	
	@FXML
	public void serveCommand(ActionEvent event) {
		Commande choix = CommandeTable.getSelectionModel().getSelectedItem();
		if (choix != null) {
			if(choix.isEstServie()) {
				CommandeTitle.setText("Commande déjà servie !");
			}
			else {
				if(Barman.servirCommande(choix)) {
					profitTotal = Barman.encaisser(choix);
					initialize(null, null);
				}
				else {
				CommandeTitle.setText("Stock insuffisant !");
				}
			}
			
		}
		
	}
	
	@FXML
	public void retourBarman(ActionEvent event) throws IOException {
		 root = FXMLLoader.load(getClass().getClassLoader().getResource("com\\\\cytech\\\\interfaces\\\\BarmanMenu.fxml"));
		 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		 scene = new Scene(root);
		 stage.setScene(scene);
		 stage.show();
	}
	
	public void addStock(ActionEvent event) throws IOException {
		BoissonSimple choix = boissonTable.getSelectionModel().getSelectedItem();
	    if (choix != null) {
	    	Map<String, BoissonSimple> boissonsMap = GestionJSON.lireJSONBoisson("src\\com\\cytech\\collections\\boissonsimple.json");
	    	Map<String, BoissonSimple> newMap = new HashMap<>();
	    	for (Map.Entry<String, BoissonSimple> entry : boissonsMap.entrySet()) {
					newMap.put(entry.getKey(), entry.getValue());
				if (entry.getKey().equals(choix.getNom())) {
					choix.setStock(choix.getStock() + Integer.parseInt(boissonQuantite.getText()));
					newMap.put(choix.getNom(), choix);
				}
	    	}
	    	GestionJSON.EcrireJsonBoisson(newMap,"src\\com\\cytech\\collections\\boissonsimple.json");
	    	root = FXMLLoader.load(getClass().getClassLoader().getResource("com\\\\cytech\\\\interfaces\\\\BarmanStock.fxml"));
			 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			 scene = new Scene(root);
			 stage.setScene(scene);
			 stage.show();
	    }
	}
	
	// Event Listener on Button.onAction
	@FXML
	public void creerCocktail(ActionEvent event) throws IOException {
		 FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("com\\cytech\\interfaces\\CocktailCreate.fxml"));	
			root = loader.load();
			
			CocktailCreateController.isBarman = true;
		 
		 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		 scene = new Scene(root);
		 stage.setScene(scene);
		 stage.show();
	}
	// Event Listener on Button.onAction
	@FXML
	public void retourMenu(ActionEvent event) throws IOException {
		 root = FXMLLoader.load(getClass().getClassLoader().getResource("com\\\\cytech\\\\interfaces\\\\MainScene.fxml"));
		 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		 scene = new Scene(root);
		 stage.setScene(scene);
		 stage.show();
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if (profit != null) {
			profit.setText("Profit Total : " + String.valueOf(profitTotal));
		}
		
		Map<String, BoissonSimple> lstBoisson = GestionJSON.lireJSONBoisson("src\\\\com\\\\cytech\\\\collections\\\\boissonsimple.json");
		List<BoissonSimple> boissonList = new ArrayList<>(lstBoisson.values());
		ObservableList<BoissonSimple> obsBoisson = FXCollections.observableArrayList(boissonList);
		boissonTableNom.setCellValueFactory(new PropertyValueFactory<BoissonSimple, String>("nom"));
		boissonTablePrix.setCellValueFactory(new PropertyValueFactory<BoissonSimple, Double>("prix"));
		boissonTableAlcool.setCellValueFactory(new PropertyValueFactory<BoissonSimple, Integer>("degreAlcool"));
		boissonTableSucre.setCellValueFactory(new PropertyValueFactory<BoissonSimple, Integer>("degreSucre"));
		boissonTableStock.setCellValueFactory(new PropertyValueFactory<BoissonSimple, Integer>("stock"));
		
		boissonTable.setItems(obsBoisson);
		
		List<Commande> lstCommande = GestionJSON.lireJSONCommande("src\\\\com\\\\cytech\\\\collections\\\\commandes.json");
		ObservableList<Commande> obsCommande = FXCollections.observableList(lstCommande);
		nomCommande.setCellValueFactory(new PropertyValueFactory<Commande, String>("nomCommande"));
		date.setCellValueFactory(new PropertyValueFactory<Commande, String>("date"));
		commandeBoisson.setCellValueFactory(new PropertyValueFactory<Commande, String>("listeBoisson"));
		commandeCocktail.setCellValueFactory(new PropertyValueFactory<Commande, String>("listeCocktail"));
		estServie.setCellValueFactory(cellData -> {
		    Commande commande = cellData.getValue();
		    boolean estServie = commande.isEstServie();
		    String affichage = estServie ? "Oui" : "Non";

		    return new SimpleStringProperty(affichage);
		});
		
		CommandeTable.setItems(obsCommande);
	}
}
