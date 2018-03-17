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
import model.Kaas;
import model.Klant;
import util.ExceptionIO;

@SuppressWarnings("restriction")
public class BestellingOverizicht {
	
	Bestelling selectedBestelling;
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
						super.updateItem(bstl, b);
						if(bstl != null) {
							setText("datum van bestelling: " + bstl.getBestellingDate() + "   totaal prijs van bestelling: " + 
																		bstl.getTotaalPrijs() + "   status van bestelling " + bstl.getStatus());
			
						}
					}
				};
				return cell;
			}
		});
		bestellingListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		//Grid pane voor bestellingDetails maken

		
		//implementatie invalidation listener voor bestellinglijst
		bestellingListView.getSelectionModel().selectedItemProperty().addListener(ov -> {
			selectedBestelling = bestellingListView.getSelectionModel().getSelectedItem();
			ArrayList<ArrayList<String>> bestellingDetailsLijst = new ArrayList<>();
			try {
				bestellingDetailsLijst = applikaasie.getBestellingDetails(selectedBestelling);
				GridPane gridPaneDetails = maakDetailOverzicht(bestellingDetailsLijst, applikaasie);
				borderPane.setBottom(gridPaneDetails);
			} catch (ExceptionIO e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		//vbox voor bestellinglijst
		bestellingListView.setPrefWidth(600);
		VBox vBoxBestellingLijst = new VBox();
		vBoxBestellingLijst.setPrefWidth(600);
		ScrollPane scrollPaneBestellingLijst = new ScrollPane(bestellingListView);
		scrollPaneBestellingLijst.setPrefWidth(600);
		vBoxBestellingLijst.getChildren().addAll(scrollPaneBestellingLijst);
		
		//alles bij ekaar brengen
		borderPane.setTop(hBoxButton);
		borderPane.setLeft(new ScrollPane(vBoxBestellingLijst));
		borderPane.autosize();
		
		return new Scene(borderPane, 1000, 600);
	}
	
	private GridPane maakDetailOverzicht(ArrayList<ArrayList<String>> bestellingDetailsLijst, Applikaasie applikaasie) throws NumberFormatException, ExceptionIO {
		GridPane gridPaneDetails = new GridPane();
		for(int row = 0; row < bestellingDetailsLijst.size(); row++) {
			ArrayList<String> detailList = bestellingDetailsLijst.get(row);
			Kaas kaas = applikaasie.getKaas(Integer.parseInt(detailList.get(0)));
			//detailsoverzicht in elkaar maken
			gridPaneDetails.setPadding(new Insets(10, 10, 10, 10));
			gridPaneDetails.setVgap(5);
			gridPaneDetails.setHgap(5);
			gridPaneDetails.add(new Label("Kaas naam: "), 0, row);
			gridPaneDetails.add(new TextField(kaas.getNaam()), 1, row);
			gridPaneDetails.add(new Label("Bestelde hoeveelheid in kg: "), 2, row);
			gridPaneDetails.add(new TextField(detailList.get(1)), 3, row);
			gridPaneDetails.add(new Label("Prijs in euro: "), 4, row);
			gridPaneDetails.add(new TextField(detailList.get(2)), 5, row);
		}
		return gridPaneDetails;
	}

	
	
}
