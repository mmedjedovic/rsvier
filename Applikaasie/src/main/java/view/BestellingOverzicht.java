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

import javax.swing.plaf.basic.BasicTreeUI.SelectionModelPropertyChangeHandler;

import logic.Applikaasie;
import model.Klant;
import util.ExceptionIO;

@SuppressWarnings("restriction")
public class BestellingOverzicht {
	
	
	Stage homeStage;
	Scene homeScene;
	Applikaasie applikaasie;
	ArrayList<Klant> klantLijst;
	
	public BestellingOverzicht(Stage homeStage, Scene homeScene, Applikaasie applikaasie) throws ExceptionIO {
		this.homeStage = homeStage;
		this.homeScene = homeScene;
		this.applikaasie =applikaasie;
		klantLijst = applikaasie.getKlantenLijst();
	}
	
	public Scene getBestellingOverzichtScene() throws ExceptionIO {
		
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
		statusCombo.getItems().addAll("open", "gesloten");
		
		//labels maken en button voor overzicht maken
		Label newInstructieLabel = new Label("Maak keuzes van onderstaande categories:");
		Label klantNaamLabel = new Label("Kies klant: ");
		Label statusBestellingLabel = new Label("Kies status van bestellingen: ");
		Button overzichtButton = new Button("overzicht maken");
		
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








