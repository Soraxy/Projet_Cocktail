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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.cytech.commandeboisson.Commande;
import com.cytech.utilisateur.Client;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

public class ClientMenuController implements Initializable{
	
	@FXML
	private Label TitleLabel;
	
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
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	// Event Listener on Button.onAction
	@FXML
	public void voirlisteCommande(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getClassLoader().getResource("com\\\\cytech\\\\interfaces\\\\ListCommandeScene.fxml"));
		 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		 scene = new Scene(root);
		 stage.setScene(scene);
		 stage.show();
	}
	// Event Listener on Button.onAction
	@FXML
	public void btnclickclient(ActionEvent event) throws IOException {
		 root = FXMLLoader.load(getClass().getClassLoader().getResource("com\\\\cytech\\\\interfaces\\\\ClientScene.fxml"));
		 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		 scene = new Scene(root);
		 stage.setScene(scene);
		 stage.show();
	}
	
	@FXML
	public void retourclt(ActionEvent event) throws IOException {
		 FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("com\\cytech\\interfaces\\ClientMenu.fxml"));	
			root = loader.load();
			
			ClientMenuController clientMenuController = loader.getController();
			clientMenuController.displayName(MainSceneController.nomClient);
		 
		 //root = FXMLLoader.load(getClass().getClassLoader().getResource("com\\\\cytech\\\\interfaces\\\\ClientMenu.fxml"));
		 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		 scene = new Scene(root);
		 stage.setScene(scene);
		 stage.show();
	}
	
	// Event Listener on Button.onAction
	@FXML
	public void passerCommande(ActionEvent event) throws IOException {
		 root = FXMLLoader.load(getClass().getClassLoader().getResource("com\\\\cytech\\\\interfaces\\\\CommandeMenu.fxml"));
		 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		 scene = new Scene(root);
		 stage.setScene(scene);
		 stage.show();
		 
	}
	
	@FXML
	public void displayName(String username) {
		TitleLabel.setText("Bonjour " + username);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		List<Commande> lstCommande = Client.voirCommande(MainSceneController.nomClient);
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
