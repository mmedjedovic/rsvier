package view;

import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.scene.layout.*;
import javafx.scene.*;
import javafx.util.*;
import javafx.collections.*;
import javafx.stage.*;
import javafx.beans.binding.*;

import java.util.ArrayList;

import logic.Applikaasie;
import model.Bestelling;
import model.Klant;
import util.ExceptionIO;

@SuppressWarnings("restriction")
public class BestellingOverzichtMaken {
	
	
	Stage homeStage;
	Scene homeScene;
	Applikaasie applikaasie;
	ArrayList<Klant> klantLijst;
	
	public BestellingOverzichtMaken(Stage homeStage, Scene homeScene, Applikaasie applikaasie) throws Exception {
		this.homeStage = homeStage;
		this.homeScene = homeScene;
		this.applikaasie =applikaasie;
		klantLijst = applikaasie.getKlantenLijst();
	}
	
	public Scene getBestellingOverzichtMakenScene() throws ExceptionIO {
		
		//maken border pane
		BorderPane borderPane = new BorderPane();
		
		//Button voor home
		Button homeButton = new Button("home");
		homeButton.setOnAction(e -> {
			this.homeStage.setScene(homeScene);
		});
		
		//Hbox voor buttons
		HBox buttonHbox = new HBox();
		buttonHbox.setPadding(new Insets(10, 10, 10, 10));
		buttonHbox.setSpacing(5);
		buttonHbox.getChildren().addAll(homeButton);
		
		
		//maken grid pane
		GridPane gridPane = new GridPane();
		gridPane.setAlignment(Pos.TOP_CENTER);
		gridPane.setPadding(new Insets(10, 10, 10, 10));
		gridPane.setVgap(5);
		gridPane.setHgap(5);
		
		//grid pane maken voor comboboxen
		GridPane gridPaneVoorCombos = new GridPane();
		gridPaneVoorCombos.setPadding(new Insets(15, 15, 15, 15));
		gridPaneVoorCombos.setVgap(5);
		gridPaneVoorCombos.setHgap(5);
		
		
		//Combobox van klanten maken
		ComboBox<Klant> comboBoxKlant = new ComboBox<Klant>();
		comboBoxKlant.getItems().addAll(klantLijst);
		comboBoxKlant.setCellFactory(new Callback<ListView<Klant>, ListCell<Klant>>() {
			@Override
			public ListCell<Klant> call(ListView<Klant> list) {
				ListCell<Klant> cell = new ListCell<Klant>() {
					@Override
					protected void updateItem(Klant k, boolean b) {
						super.updateItem(k, b);
						if(k != null) {
							setText(k.getVoornaam() + " " + k.getAchternaam());
						}
					}
				};
				return cell;
			} 
		});
		comboBoxKlant.setButtonCell(new ListCell<Klant>() {
			@Override
			protected void updateItem(Klant k, boolean b) {
				super.updateItem(k, b);
				if(k != null) {
					setText(k.getVoornaam() + " " + k.getAchternaam());
				}
			}
		});
		
		//Combobox van status maken
		ComboBox<String> statusCombo = new ComboBox<String>();
		statusCombo.getItems().addAll("open", "gesloten", "alles");
		
		//labels maken en button voor overzicht maken
		Label newInstructieLabel = new Label("Maak keuzes van onderstaande categories:");
		Label klantNaamLabel = new Label("Kies klant: ");
		Label statusBestellingLabel = new Label("Kies status van bestellingen: ");
		Button overzichtButton = new Button("overzicht maken");
		overzichtButton.setOnAction(e -> {
			try {
				ArrayList<Bestelling> bestellinglijst = applikaasie.getBestellingen(comboBoxKlant.getSelectionModel().getSelectedItem(), 
																						statusCombo.getSelectionModel().getSelectedItem());
				BestellingOverizicht bestellingOverzicht = new BestellingOverizicht(homeStage, homeScene, this.getBestellingOverzichtMakenScene(), applikaasie, bestellinglijst);
				homeStage.setScene(bestellingOverzicht.getBestellingOverzicht());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		//nodes toevoegen aan gridPane
		gridPane.add(newInstructieLabel, 0, 0);
		gridPane.add(klantNaamLabel, 0, 1);
		gridPane.add(comboBoxKlant, 1, 1);
		gridPane.add(statusBestellingLabel, 0, 2);
		gridPane.add(statusCombo, 1, 2);
		gridPane.add(overzichtButton, 0, 3);
		
		//elementen aan borderpane toevoegen
		borderPane.setTop(buttonHbox);
		borderPane.setLeft(gridPane);
		
		Scene scene = new Scene(borderPane, 800, 400);
		return scene;
	}
	

}








