package view;

import javafx.stage.*;
import logic.Applikaasie;
import util.ExceptionIO;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javafx.application.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.scene.layout.*;
import javafx.scene.*;
import javafx.scene.text.*;

@SuppressWarnings("restriction")
public class KlantMaken {
	
	public GridPane makeGridPane(Stage stage, Scene homeScene, Applikaasie applikaasie) {
		
		//crieeren velden for grid pane
		Button homeButton = getHomeButton(stage, homeScene);
		Label titel = new Label("Invullen formulier");
		Label voornaamLabel = new Label("voornaam:");
		TextField voornaamTextfield = new TextField();
		Label achterNaamLabel = new Label("achternaam:");
		TextField achternaamTextField = new TextField();
		Label straatNaamLabel = new Label("straat naam:");
		TextField straatNaamTextField = new TextField();
		Label huisnummerLabel = new Label("huisnummer:");
		TextField huisnummerTextField = new TextField();
		Label toevoegingHuisnummerLabel = new Label("toevoegingsnummer:");
		TextField toevoegingHuisnummerTextField = new TextField();
		Label postcodeLabel = new Label("postcode:");
		TextField postcodeTextField = new TextField();
		Label woonplaatsLabel = new Label("woonplaats:");
		TextField woonplaatsTextField = new TextField();
		Button registratieButton = new Button("registreer");
		Text info = new Text("Velden met sterretjes zijn verplicht!");
		
		//event handler of registratie buton
		registratieButton.setOnAction(e -> {
			try {
				applikaasie.maakNieuweKlantenAdres(voornaamTextfield.getText(), achternaamTextField.getText(), 
												straatNaamTextField.getText(), huisnummerTextField.getText(), toevoegingHuisnummerTextField.getText(), 
																																postcodeTextField.getText(), woonplaatsTextField.getText());
				voornaamTextfield.clear();
				achternaamTextField.clear();
				straatNaamTextField.clear();
				huisnummerTextField.clear();
				toevoegingHuisnummerTextField.clear();
				postcodeTextField.clear();
				woonplaatsTextField.clear();
			} catch (ExceptionIO e1) {
				
				e1.printStackTrace();
			}
			
		});
		
		//opzetten grid pane
		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setPadding(new Insets(10, 10, 10, 10));
		pane.setVgap(5);
		pane.setHgap(5);
		
		//vullen grid pane met velden
		pane.add(homeButton, 0, 0);
		pane.add(titel, 1, 0);
		pane.add(voornaamLabel, 0, 1);
		pane.add(voornaamTextfield, 1, 1);
		pane.add(achterNaamLabel, 0, 2);
		pane.add(achternaamTextField, 1, 2);
		pane.add(straatNaamLabel, 0, 4);
		pane.add(straatNaamTextField, 1, 4);
		pane.add(huisnummerLabel, 0, 5);
		pane.add(huisnummerTextField, 1, 5);
		pane.add(toevoegingHuisnummerLabel, 0, 6);
		pane.add(toevoegingHuisnummerTextField, 1, 6);
		pane.add(postcodeLabel, 0, 7);
		pane.add(postcodeTextField, 1, 7);
		pane.add(woonplaatsLabel, 0, 8);
		pane.add(woonplaatsTextField, 1, 8);
		pane.add(registratieButton, 1, 9);
		pane.add(info, 1, 10);
		
		return pane;
	}
	
	private Button getHomeButton(Stage stage, Scene homeScene) {
		Button homeButton = new Button("Home");
		homeButton.setOnAction(e -> {
			stage.setScene(homeScene);
		});
		return homeButton;
	}
	
	
}
