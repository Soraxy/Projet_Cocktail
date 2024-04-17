package com.cytech.application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import com.cytech.utilisateur.Client;

import javafx.event.ActionEvent;

public class MainSceneController {
	
	public static final String nomClient = null;
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	@FXML
	private TextField emailconn;
	
	@FXML
	private PasswordField mdpconn;
	
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
			 String nomClient = Client.getNomClient(email);
			 FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("com\\cytech\\interfaces\\ClientMenu.fxml"));	
				root = loader.load();
				
				ClientMenuController clientMenuController = loader.getController();
				clientMenuController.displayName(nomClient);
			 
			 //root = FXMLLoader.load(getClass().getClassLoader().getResource("com\\\\cytech\\\\interfaces\\\\ClientMenu.fxml"));
			 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			 scene = new Scene(root);
			 stage.setScene(scene);
			 stage.show();
		}
		else {
			errorconn.setText("Erreur de connexion");
		}
	}
}
