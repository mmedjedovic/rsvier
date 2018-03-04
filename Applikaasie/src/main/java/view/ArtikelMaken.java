package view;



import util.*;
import java.math.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.scene.layout.*;
import javafx.scene.*;
import javafx.scene.text.*;
import javafx.stage.*;
import logic.Applikaasie;

@SuppressWarnings("restriction")
public class ArtikelMaken {
	
	
	public GridPane makeGridPane(Stage stage, Scene homeScene, Applikaasie applikaasie) {
		
		GridPane pane = new GridPane();
		
		//crieeren velden for grid pane
		Button homeButton = getHomeButton(stage, homeScene);
		Label titel = new Label("Velden invullen");
		Label labelKaasNaam = new Label("kaas naam:");
		TextField textFieldKaasNaam = new TextField();
		Label labelPrijsInKg = new Label("prijs in kg:");
		TextField textFieldPrijsInKg = new TextField();
		Label labelvooraadInKg = new Label("vooraad in kg:");
		TextField textFieldVooraadInKg = new TextField();
		Button registratieButton = new Button("registreer");
		
		//event handler voor registratie button
		registratieButton.setOnAction(e -> {
			try {
				Double prijsInKg = isDouble(textFieldPrijsInKg);
				Double vooraadInKg = isDouble(textFieldVooraadInKg);
				applikaasie.artikelMaken(textFieldKaasNaam.getText(), prijsInKg, vooraadInKg);
				textFieldKaasNaam.clear();
				textFieldPrijsInKg.clear();
				textFieldVooraadInKg.clear();
			} catch (ExceptionFormatNumbers | ExceptionIO e1) {
				e1.printStackTrace();
			}
		});
		
		//Grid Pane maken
		pane.setAlignment(Pos.CENTER);
		pane.setPadding(new Insets(10, 10, 10, 10));
		pane.setVgap(5);
		pane.setHgap(5);
		
		pane.add(homeButton, 0, 0);
		pane.add(titel, 1, 0);
		pane.add(labelKaasNaam, 0, 1);
		pane.add(textFieldKaasNaam, 1, 1);
		pane.add(labelPrijsInKg, 0, 2);
		pane.add(textFieldPrijsInKg, 1, 2);
		pane.add(labelvooraadInKg, 0, 3);
		pane.add(textFieldVooraadInKg, 1, 3);
		pane.add(registratieButton, 1, 4);
		
		return pane;
	}
	
	
	
	private Button getHomeButton(Stage stage, Scene homeScene) {
		Button homeButton = new Button("Home");
		homeButton.setOnAction(e -> {
			stage.setScene(homeScene);
		});
		return homeButton;
	}
	
	public Double isDouble(TextField field) throws ExceptionFormatNumbers {	
		try {
			Double numberDouble = Double.parseDouble(field.getText());
			return numberDouble;	
		} catch(NumberFormatException e) {
			throw new ExceptionFormatNumbers(field.getText() + " is niet gepast nummer");
		}
	
	}
	
}

