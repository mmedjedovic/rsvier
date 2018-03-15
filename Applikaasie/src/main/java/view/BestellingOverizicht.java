package view;

import java.util.ArrayList;
import javafx.collections.*;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.util.*;
import javafx.stage.*;
import javafx.beans.binding.*;


import logic.Applikaasie;
import model.Bestelling;
import model.Klant;

@SuppressWarnings("restriction")
public class BestellingOverizicht {
	
	Stage homeStage;
	Scene homeScene;
	Scene bestellingOverzichtMakenScene;
	Applikaasie applikaasie;
	ArrayList<Bestelling> bestellingLijst;
	
	public BestellingOverizicht(Stage homeStage, Scene homeScene, Scene bestellingOverzichtMakenScene, Applikaasie applikaasie, ArrayList<Bestelling> bestellingLijst) {
		this.homeStage = homeStage;
		this.homeScene = homeScene;
		this.bestellingOverzichtMakenScene = bestellingOverzichtMakenScene;
		this.applikaasie =applikaasie;
		this.bestellingLijst = bestellingLijst;
	}
	
	public Scene getBestellingOverzicht() {
		
		BorderPane borderPane = new BorderPane();
		
		//Button voor home
		Button homeButton = new Button("home");
		homeButton.setOnAction(e -> {
			this.homeStage.setScene(homeScene);
		});
		//Cancel button maken
		Button cancelButton = new Button("cancel");
		cancelButton.setOnAction(e -> {
			homeStage.setScene(bestellingOverzichtMakenScene);
		});
		//hbox voor buttons
		HBox hBoxButton = new HBox();
		hBoxButton.getChildren().addAll(homeButton, cancelButton);
		hBoxButton.setPadding(new Insets(15, 15, 15, 15));
		hBoxButton.setSpacing(5);
		
		//list van bestellingen maken
		ListView<Bestelling> bestellingListView = new ListView<Bestelling>(FXCollections.observableArrayList(bestellingLijst));
		bestellingListView.setCellFactory(new Callback<ListView<Bestelling>, ListCell<Bestelling>>() {
			@Override
			public ListCell<Bestelling> call(ListView<Bestelling> list) {
				ListCell<Bestelling> cell = new ListCell<Bestelling>() {
					@Override 
					protected void updateItem(Bestelling bstl, boolean b) {
						if(bstl != null) {
							setText("datum van bestelling: " + bstl.getBestellingDate() + "   totaal prijs van bestelling: " + 
																		bstl.getTotaalPrijs() + "   status van bestelling " + bstl.getStatus());
			
						}
					}
				};
				return cell;
			}
		});
		bestellingListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		VBox vBoxBestellingLijst = new VBox();
		vBoxBestellingLijst.getChildren().addAll(bestellingListView);
		
		//alles bij ekaar brengen
		borderPane.setTop(hBoxButton);
		borderPane.setLeft(new ScrollPane(vBoxBestellingLijst));
		
		return new Scene(borderPane, 800, 400);
	}
	
}
