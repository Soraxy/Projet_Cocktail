package com.cytech.application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.cytech.gestionFichiers.GestionJSON;
import com.cytech.utilisateur.Client;

import javafx.event.ActionEvent;

public class MainSceneController {
	
	public static String nomClient;
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	@FXML
	private TextField emailconn;
	
	@FXML
	private PasswordField mdpconn;
	
	@FXML
	private TextField nomInsc;
	@FXML
	private TextField prenomInscr;
	@FXML
	private TextField emailInscr;
	@FXML
	private DatePicker naissInscr;
	@FXML
	private TextField adresseInscr;
	@FXML
	private PasswordField passwordInscr;
	@FXML
	private TextField secretInscr;
	
	@FXML
	private Label errorconn;
	
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
	public void btnclickbarman(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getClassLoader().getResource("com\\\\cytech\\\\interfaces\\\\BarmanMenu.fxml"));
		 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		 scene = new Scene(root);
		 stage.setScene(scene);
		 stage.show();
	}
	
	public void retourmenu(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getClassLoader().getResource("com\\\\cytech\\\\interfaces\\\\MainScene.fxml"));
		 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		 scene = new Scene(root);
		 stage.setScene(scene);
		 stage.show();
	}
	
	@FXML
	public void cltmenuinscription(ActionEvent event) throws IOException {
		 root = FXMLLoader.load(getClass().getClassLoader().getResource("com\\\\cytech\\\\interfaces\\\\ClientInscriptionScene.fxml"));
		 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		 scene = new Scene(root);
		 stage.setScene(scene);
		 stage.show();
	}
	
	@FXML
	public void cltconn (ActionEvent event) throws IOException{
		String email = emailconn.getText();
		String mdp = mdpconn.getText();
		if (Client.connexion(email, mdp)) {
			 nomClient = Client.getNomClient(email);
			 FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("com\\cytech\\interfaces\\ClientMenu.fxml"));	
				root = loader.load();
				
				ClientMenuController clientMenuController = loader.getController();
				clientMenuController.displayName(nomClient);
			 
			 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			 scene = new Scene(root);
			 stage.setScene(scene);
			 stage.show();
		}
		else {
			errorconn.setText("Erreur de connexion");
		}
	}
	
	public void cltinscription (ActionEvent event) throws IOException {
		String nom = nomInsc.getText();
		String prenom = prenomInscr.getText();
		String email = emailInscr.getText();
		String date = naissInscr.getValue().toString();
		String adresse = adresseInscr.getText();
		String password = passwordInscr.getText();
		String secret = secretInscr.getText();
		
		List<Client> lstClient = GestionJSON.lireJSON("src/com/cytech/collections/clients.json");
		if (Client.isEmailUnique(email, lstClient)) {
			Client newClient = new Client(email, password, nom, prenom, date, adresse, secret);
			lstClient =  GestionJSON.lireJSON("src\\com\\cytech\\collections\\clients.json");
			if (lstClient == null) {
		        lstClient = new ArrayList<>();
		    }
			lstClient.add(newClient);
			GestionJSON.EcrireJsonDirecte(lstClient, "src\\com\\cytech\\collections\\clients.json");
		}
		
		 root = FXMLLoader.load(getClass().getClassLoader().getResource("com\\\\cytech\\\\interfaces\\\\ClientScene.fxml"));
		 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		 scene = new Scene(root);
		 stage.setScene(scene);
		 stage.show();
	}
}
