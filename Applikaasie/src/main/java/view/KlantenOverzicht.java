package view;


import java.util.ArrayList;

import logic.Applikaasie;
import model.Klant;
import model.Adres;
import util.ExceptionIO;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.scene.layout.*;
import javafx.scene.*;
import javafx.util.*;
import javafx.collections.*;
import javafx.stage.*;
import javafx.beans.binding.*;



@SuppressWarnings("restriction")
public class KlantenOverzicht {
	
	Integer klantId;
	ArrayList<Klant> klantLijst;
	ListView<Klant> klantListView;
	Scene homeScene;
	Stage homeStage;
	
	Applikaasie applikaasie;
	
	public KlantenOverzicht(Stage homeStage, Scene homeScene, Applikaasie applikaasie) {
		this.homeStage = homeStage;
		this.homeScene = homeScene;
		this.applikaasie = applikaasie;
	}
	
	
	public Scene getBorderPaneScene() throws ExceptionIO {
		
		BorderPane borderPane = new BorderPane();
		
		
		//Pane voor adres details
		GridPane adresGridPane = new GridPane();
		adresGridPane.setPadding(new Insets(10, 10, 10, 10));
		adresGridPane.setVgap(5);
		adresGridPane.setHgap(5);
		
		//Vbox voor lijst van klanten namen 
		VBox klantenLijstVBox = new VBox();
		klantenLijstVBox.setPadding(new Insets(10, 10, 10, 10));
		
		
		//Button voor home
		Button homeButton = new Button("home");
		homeButton.setOnAction(e -> {
			this.homeStage.setScene(homeScene);
		});
		//Button voor verwijderen van een klant
		Button deleteButton = new Button("delete");
		deleteButton.setOnAction(e -> {
			try {
				applikaasie.deleteKlant(klantId);
				this.homeStage.setScene(homeScene);
			} catch (ExceptionIO e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	 
		});
		//Button voor maken nieuwe bestelling
		Button nieuweBestelling = new Button("nieuwe bestelling");
		nieuweBestelling.setOnAction(e -> {
			try {
				BestellingMaken bestellingMaken = new BestellingMaken(klantId, applikaasie, homeStage, homeScene, this.getBorderPaneScene());
				Scene bestellingMakenScene = bestellingMaken.getGridPaneScene();
				homeStage.setScene(bestellingMakenScene);
			} catch (ExceptionIO e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		
		//Hbox voor buttons
		HBox buttonHbox = new HBox();
		buttonHbox.setPadding(new Insets(10, 10, 10, 10));
		buttonHbox.setSpacing(5);
		buttonHbox.getChildren().addAll(homeButton, deleteButton, nieuweBestelling);
		
		//Stoppen nodes in Border pane 
		borderPane.setLeft(new ScrollPane(klantenLijstVBox));
		borderPane.setCenter(adresGridPane);
		borderPane.setTop(buttonHbox);
		
		
		//Lijst van klanten converteren naar List View
		klantLijst = applikaasie.getKlantenLijst();
		klantListView = new ListView<Klant>(FXCollections.observableArrayList(klantLijst));
		klantListView.setCellFactory(new Callback<ListView<Klant>, ListCell<Klant>>() {
			@Override
			public ListCell<Klant> call(ListView<Klant> list) {
				ListCell<Klant> cell = new ListCell<Klant>() {
					@Override
					protected void updateItem(Klant k, boolean b) {
						super.updateItem(k, b);
						if(k != null) {
							setText(k.getVoornaam() + "  " + k.getAchternaam());
						}
					}
				};
				return cell;
			}
		});
		klantListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		
		
		//implementation invalidation listener
		klantListView.getSelectionModel().selectedItemProperty().addListener(ov -> {
			klantId = klantListView.getSelectionModel().getSelectedItem().getKlantId();
			adresGridPane.getChildren().clear();
			try {
				Adres adres = applikaasie.getAdres(klantId);
				adresGridPane.add(new Label("Straat naam:"), 0, 0);
				adresGridPane.add(new Label(adres.getStraatNaam()), 1, 0);
				adresGridPane.add(new Label("Huisnummer:"), 0, 1);
				adresGridPane.add(new Label(adres.getHuisnummer()), 1, 1);
				adresGridPane.add(new Label("Toevoegingshuisnummer:"), 0, 2);
				adresGridPane.add(new Label(adres.getToevoegingHuisnummer()), 1, 2);
				adresGridPane.add(new Label("Postcode:"), 0, 3);
				adresGridPane.add(new Label(adres.getPostcode()), 1, 3);
				adresGridPane.add(new Label("Huisnummer:"), 0, 4);
				adresGridPane.add(new Label(adres.getWoonplaats()), 1, 4);
			} catch (ExceptionIO e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});	
		
		//button maken nactive totdat een klant is geselcteerd
		nieuweBestelling.disableProperty().bind(Bindings.isEmpty(klantListView.getSelectionModel().getSelectedItems()));
		deleteButton.disableProperty().bind(Bindings.isEmpty(klantListView.getSelectionModel().getSelectedItems()));
		
		//stoppen klantenlijst in Vbox
		klantenLijstVBox.getChildren().addAll(klantListView);	
		
		return new Scene(borderPane, 800, 400);	
	}
}
