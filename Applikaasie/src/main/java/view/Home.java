package view;

import javafx.stage.*;
import logic.Applikaasie;
import util.ExceptionIO;
import javafx.application.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.scene.layout.*;
import javafx.scene.*;

@SuppressWarnings("restriction")
public class Home extends Application{
	
	Applikaasie applikaasie = new Applikaasie();
	Scene homeScene;
	Stage stage;
	
	public void start(Stage stage) {
		
		this.stage = stage;
		stage.setTitle("Applikassie");
		
		//Opzetten knoppen voor klant, bestelling en artikel
		Label klantLabel = new Label("Klant");
		FlowPane klantFlowPane = getKlantFlowPane(stage);
		Label artikelLabel = new Label("Artikel");
		FlowPane artikelFlowPane = getArtikelFlowPane();
		Label bestellingLabel = new Label("Bestelling");
		FlowPane bestellingFlowPane = getBestellingFlowPane();
		
		//verikal boxen voor elke kategorie appart
		VBox vBoxKlant = new VBox();
		vBoxKlant.setPadding(new Insets(10, 10, 10, 10));
		vBoxKlant.getChildren().addAll(klantLabel, klantFlowPane);
		
		VBox vBoxArtikel = new VBox();
		vBoxArtikel.setPadding(new Insets(10, 10, 10, 10));
		vBoxArtikel.getChildren().addAll(artikelLabel, artikelFlowPane);
		
		VBox vBoxBestelling = new VBox();
		vBoxBestelling.setPadding(new Insets(10, 10, 10, 10));
		vBoxBestelling.getChildren().addAll(bestellingLabel, bestellingFlowPane);
		
		
		VBox vBox = new VBox();
		vBox.setPadding(new Insets(10, 10, 10, 10));
		vBox.getChildren().addAll(vBoxKlant, vBoxBestelling, vBoxArtikel);
		
		BorderPane borderPane = new BorderPane();
		borderPane.setLeft(vBox);
		
		homeScene = new Scene(borderPane);
		
		stage.setScene(homeScene);
		
		stage.show();
	}
	
	private FlowPane getKlantFlowPane(Stage stage) {
		Button buttonNewKlant = getButtonNewKlant(stage);
		Button buttonOverzichtKlant = getButtonOverzichtKlant();
		FlowPane klantFlowPane = new FlowPane();
		klantFlowPane.setPadding(new Insets(10, 10, 20, 10));
		klantFlowPane.setVgap(5);
		klantFlowPane.setHgap(5);
		klantFlowPane.getChildren().addAll(buttonNewKlant, buttonOverzichtKlant);
		return klantFlowPane;
	}
	
	private Button getButtonNewKlant(Stage stage) {
		Button buttonNewKlant = new Button("nieuwe maken");
		buttonNewKlant.setOnAction(e -> {
			System.out.println("nieuwklant gedrukt");
			KlantMaken klantMaken = new KlantMaken();
			GridPane klantMakenGridPane = klantMaken.makeGridPane(stage, homeScene, applikaasie);
			Scene klantMakenScene = new Scene(klantMakenGridPane, 400, 400);
			stage.setScene(klantMakenScene);
		});
		return buttonNewKlant;
	}
	
	private Button getButtonOverzichtKlant() {
		Button buttonOverzichtKlant = new Button("overzicht maken");
		buttonOverzichtKlant.setOnAction(e -> {
			System.out.println("overzichtklant gedrukt");
			KlantenOverzicht klantenOverzicht = new KlantenOverzicht();
			try {
				BorderPane klantOverzichtPane = klantenOverzicht.getBorderPane(applikaasie);
				Scene klantOverzichtScene = new Scene(klantOverzichtPane, 800, 400);
				stage.setScene(klantOverzichtScene);
			} catch (ExceptionIO e1) {
				// TODO Auto-generated catch b
				e1.printStackTrace();
			}
		});
		return buttonOverzichtKlant;
	}
	
	private FlowPane getArtikelFlowPane() {
		Button buttonNewArtikel = getButtonNewArtikel();
		Button buttonOverzichtArtikel = getButtonOverzichtArtikel();
		FlowPane artikelFlowPane = new FlowPane();
		artikelFlowPane.setPadding(new Insets(10, 10, 20, 10));
		artikelFlowPane.setVgap(5);
		artikelFlowPane.setHgap(5);
		artikelFlowPane.getChildren().addAll(buttonNewArtikel, buttonOverzichtArtikel);
		return artikelFlowPane;
	}
	
	private Button getButtonNewArtikel() {
		Button buttonNewArtikel = new Button("nieuw maken");
		buttonNewArtikel.setOnAction(e -> {
			System.out.println("newartikel gedrukt");
		});
		return buttonNewArtikel;
	}
	
	private Button getButtonOverzichtArtikel() {
		Button buttonOverzichtArtikel = new Button("overzicht maken");
		buttonOverzichtArtikel.setOnAction(e -> {
			System.out.println("overzichartikel gedrukt");
		});
		return buttonOverzichtArtikel;
	}
	
	private FlowPane getBestellingFlowPane() {
		Button buttonNewBestelling = getButtonNewBestelling();
		Button buttonOverzichtBestelling = getButtonOverzichtBesteling();
		FlowPane bestellingFlowPane = new FlowPane();
		bestellingFlowPane.setPadding(new Insets(10, 10, 20, 10));
		bestellingFlowPane.setVgap(5);
		bestellingFlowPane.setHgap(5);
		bestellingFlowPane.getChildren().addAll(buttonNewBestelling, buttonOverzichtBestelling);
		return bestellingFlowPane;
	}
	
	private Button getButtonNewBestelling() {
		Button buttonNewBestelling = new Button("nieuwe maken");
		buttonNewBestelling.setOnAction(e -> {
			System.out.println("newbestelling gedrukt");
		});
		return buttonNewBestelling;
	}
	
	private Button getButtonOverzichtBesteling() {
		Button buttonOverzichtBestelling = new Button("overzicht maken");
		buttonOverzichtBestelling.setOnAction(e -> {
			System.out.println("overzichtbestelling gedrukt");
		});
		return buttonOverzichtBestelling;
	}
	
	public static void main(String[] args) {
		launch(args);
	} 
	
}
