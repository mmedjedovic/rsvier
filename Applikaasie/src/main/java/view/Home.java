package view;

import javafx.stage.*;
import javafx.application.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.scene.layout.*;
import javafx.scene.*;

@SuppressWarnings("restriction")
public class Home extends Application{
	
	
	public void start(Stage stage) {
		
		Label klantLabel = new Label("Klant");
		Button buttonNewKlant = new Button("nieuw klant");
		Button buttonOverzichtKlant = new Button("overzicht");
		FlowPane klantFlowPane = new FlowPane();
		klantFlowPane.setPadding(new Insets(10, 10, 20, 10));
		klantFlowPane.setVgap(5);
		klantFlowPane.setHgap(5);
		klantFlowPane.getChildren().addAll(buttonNewKlant, buttonOverzichtKlant);
		
		Label artikelLabel = new Label("Artikel");
		Button buttonNewArtikel = new Button("nieuw artikel");
		Button buttonOverzichtArtikel = new Button("overzicht");
		FlowPane artikelFlowPane = new FlowPane();
		artikelFlowPane.setPadding(new Insets(10, 10, 20, 10));
		artikelFlowPane.setVgap(5);
		artikelFlowPane.setHgap(5);
		artikelFlowPane.getChildren().addAll(buttonNewArtikel, buttonOverzichtArtikel);
		
		Label bestellingLabel = new Label("Bestelling");
		Button buttonNewBestelling = new Button("nieuw bestelling");
		Button buttonOverzichtBestelling = new Button("bestelling");
		FlowPane bestellingFlowPane = new FlowPane();
		bestellingFlowPane.setPadding(new Insets(10, 10, 20, 10));
		bestellingFlowPane.setVgap(5);
		bestellingFlowPane.setHgap(5);
		bestellingFlowPane.getChildren().addAll(buttonNewBestelling, buttonOverzichtBestelling);
		
		
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
		//borderPane.setTop(new HBox());
		//borderPane.setLeft(vBoxKlant);
		//borderPane.setCenter(vBoxArtikel);
		//borderPane.setRight(vBoxBestelling);
		borderPane.setLeft(vBox);
		
		Scene scene = new Scene(borderPane);
		
		stage.setScene(scene);
		
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	} 
	
}
