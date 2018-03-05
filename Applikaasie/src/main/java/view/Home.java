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
		HBox klantHBox = getKlantHBox();
		Label artikelLabel = new Label("Artikel");
		HBox artikelHBox = getArtikelHBox();
		Label bestellingLabel = new Label("Bestelling");
		HBox bestellingHBox = getBestellingHBox();
		
		//alle nodes in Vbox stoppen
		VBox vBox = new VBox();
		vBox.setPadding(new Insets(10, 10, 10, 10));
		vBox.getChildren().addAll(klantLabel, klantHBox, artikelLabel, artikelHBox, bestellingLabel, bestellingHBox);
		
		BorderPane borderPane = new BorderPane();
		borderPane.setLeft(vBox);
		
		homeScene = new Scene(borderPane);
		
		stage.setScene(homeScene);
		
		stage.show();
	}
	
	private HBox getKlantHBox() {
		Button buttonNewKlant = getButtonNewKlant();
		Button buttonOverzichtKlant = getButtonOverzichtKlant();
		HBox klantHBox = new HBox();
		klantHBox.setPadding(new Insets(10, 10, 20, 10));
		klantHBox.setSpacing(5);
		klantHBox.getChildren().addAll(buttonNewKlant, buttonOverzichtKlant);
		return klantHBox;
	}
	
	private Button getButtonNewKlant() {
		Button buttonNewKlant = new Button("nieuwe maken");
		buttonNewKlant.setOnAction(e -> {
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
				Scene klantOverzichtScene = klantenOverzicht.getBorderPaneScene(stage, homeScene, applikaasie, this);
				
				stage.setScene(klantOverzichtScene);
			} catch (ExceptionIO e1) {
				// TODO Auto-generated catch b
				e1.printStackTrace();
			}
		});
		return buttonOverzichtKlant;
	}
	
	private HBox getArtikelHBox() {
		Button buttonNewArtikel = getButtonNewArtikel();
		Button buttonOverzichtArtikel = getButtonOverzichtArtikel();
		HBox artikelHBox = new HBox();
		artikelHBox.setPadding(new Insets(10, 10, 20, 10));
		artikelHBox.setSpacing(5);
		artikelHBox.getChildren().addAll(buttonNewArtikel, buttonOverzichtArtikel);
		return artikelHBox;
	}
	
	private Button getButtonNewArtikel() {
		Button buttonNewArtikel = new Button("nieuw  maken");
		buttonNewArtikel.setOnAction(e -> {
			ArtikelMaken artikelMaken = new ArtikelMaken();
			GridPane artikelMakenGridPane = artikelMaken.makeGridPane(stage, homeScene, applikaasie);
			Scene scene = new Scene(artikelMakenGridPane, 400, 400);
			stage.setScene(scene);
		});
		return buttonNewArtikel;
	}
	
	private Button getButtonOverzichtArtikel() {
		Button buttonOverzichtArtikel = new Button("overzicht maken");
		buttonOverzichtArtikel.setOnAction(e -> {
			ArtikelOverzicht artikelOverzicht = new ArtikelOverzicht();
			try {
				BorderPane pane = artikelOverzicht.getBorderPane(stage, homeScene, applikaasie, this);
				Scene scene = new Scene(pane, 800, 300);
				stage.setScene(scene);
			} catch (ExceptionIO e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		return buttonOverzichtArtikel;
	}
	
	private HBox getBestellingHBox() {
		Button buttonNewBestelling = getButtonNewBestelling();
		Button buttonOverzichtBestelling = getButtonOverzichtBesteling();
		HBox bestellingFlowPane = new HBox();
		bestellingFlowPane.setPadding(new Insets(10, 10, 20, 10));
		bestellingFlowPane.setSpacing(5);
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
