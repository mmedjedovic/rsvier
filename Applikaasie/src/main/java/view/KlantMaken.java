package view;


import logic.Applikaasie;
import util.ExceptionIO;

import javafx.scene.control.*;

import java.util.ArrayList;

import javax.swing.event.ChangeListener;

import javafx.geometry.*;
import javafx.scene.layout.*;
import javafx.scene.*;
import javafx.scene.text.*;
import javafx.stage.*;
import javafx.beans.value.*;
import javafx.beans.property.*;
import javafx.collections.*;

@SuppressWarnings("restriction")
public class KlantMaken {
	
	
	public GridPane makeGridPane(Stage stage, Scene homeScene, Applikaasie applikaasie) {
		
		ArrayList<TextField> listTextFields = new ArrayList<TextField>();
		
		//crieeren velden for grid pane
		Button homeButton = getHomeButton(stage, homeScene);
		Label titel = new Label("Invullen formulier");
		Label voornaamLabel = new Label("voornaam:");
		TextField voornaamTextfield = new TextField();
		listTextFields.add(voornaamTextfield);
		Label achterNaamLabel = new Label("achternaam:");
		TextField achternaamTextField = new TextField();
		listTextFields.add(achternaamTextField);
		Label straatNaamLabel = new Label("straat naam:");
		TextField straatNaamTextField = new TextField();
		listTextFields.add(straatNaamTextField);
		Label huisnummerLabel = new Label("huisnummer:");
		TextField huisnummerTextField = new TextField();
		listTextFields.add(huisnummerTextField);
		Label toevoegingHuisnummerLabel = new Label("toevoegingsnummer:");
		TextField toevoegingHuisnummerTextField = new TextField();
		listTextFields.add(toevoegingHuisnummerTextField);
		Label postcodeLabel = new Label("postcode:");
		TextField postcodeTextField = new TextField();
		listTextFields.add(postcodeTextField);
		Label woonplaatsLabel = new Label("woonplaats:");
		TextField woonplaatsTextField = new TextField();
		listTextFields.add(woonplaatsTextField);
		Button registratieButton = new Button("registreer");
		
		//ListView<TextField> listVieuwTextField = new ListView<>(FXCollections.observableArrayList(listTextFields));
		
		//event handler voor registratie button
		registratieButton.setOnAction(e -> {
			try {
				applikaasie.maakNieuweKlantenAdres(voornaamTextfield.getText(), achternaamTextField.getText(), 
													straatNaamTextField.getText(), huisnummerTextField.getText(), 
														toevoegingHuisnummerTextField.getText(), 
															postcodeTextField.getText(), woonplaatsTextField.getText());
				voornaamTextfield.clear();
				achternaamTextField.clear();
				straatNaamTextField.clear();
				huisnummerTextField.clear();
				toevoegingHuisnummerTextField.clear();
				postcodeTextField.clear();
				woonplaatsTextField.clear();
			} catch (Exception e1) {
				
				e1.printStackTrace();
			}
			
		});
		//registratie button active maken
		BooleanProperty disableProperty = new SimpleBooleanProperty();
		disableProperty.set(true);
		registratieButton.disableProperty().bind(disableProperty);
		//change listener
		javafx.beans.value.ChangeListener<String> changeListener = (observable, oldValue, newValue) -> {
			boolean disable = false;
			for(TextField textField: listTextFields) {
				if(textField.getText().isEmpty()) {
					disable = true;
					break;
				}
			}
			disableProperty.set(disable);
		};
		//add listeners to text fields
		addListenerToTextFieldList(listTextFields, changeListener);
		
		
		
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
		
		return pane;
	}
	
	
	
	private Button getHomeButton(Stage stage, Scene homeScene) {
		Button homeButton = new Button("Home");
		homeButton.setOnAction(e -> {
			stage.setScene(homeScene);
		});
		return homeButton;
	}
	
	private void addListenerToTextFieldList(ArrayList<TextField> listTextFields, javafx.beans.value.ChangeListener<String> changeListener) {
		for(TextField textField: listTextFields) {
			textField.textProperty().addListener(changeListener);
		}
	}
	
}
