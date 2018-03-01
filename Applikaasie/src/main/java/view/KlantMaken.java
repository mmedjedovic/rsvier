package view;

import javafx.stage.*;
import javafx.application.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.scene.layout.*;
import javafx.scene.*;
import javafx.scene.text.*;

@SuppressWarnings("restriction")
public class KlantMaken extends Application{
	
	public void start(Stage stage) {
		
		stage.setTitle("Formulier");
		GridPane pane = makeGridPane(stage);
		Scene scene = new Scene(pane);
		stage.setScene(scene);
		stage.show();
		
	}
	
	private GridPane makeGridPane(Stage stage) {
		
		//crieeren velden for grid pane
		Button homeButton = getHomeButton(stage);
		Label titel = new Label("Invullen formulier");
		Label voornaamLabel = new Label("voornaam:");
		TextField voornaamTextfield = new TextField();
		Text voornaamSterretje = new Text("*");
		Label achterNaamLabel = new Label("achternaam:");
		TextField achternaamTextField = new TextField();
		Text achternaamSterretje = new Text("*");
		Label geboorteDatumLabel = new Label("geboorte datum:");
		DatePicker geboorteDatumPicker = new DatePicker();
		Label straatNaamLabel = new Label("straat naam:");
		TextField straatNaamTextField = new TextField();
		Label huisnummerLabel = new Label("huisnummer:");
		TextField huisnummerTextField = new TextField();
		Label toevoegingsnummerLabel = new Label("toevoegingsnummer:");
		TextField toevoegingsnummerTextField = new TextField();
		Label postcodeLabel = new Label("postcode:");
		TextField postcodeTextField = new TextField();
		Label woonplaatsLabel = new Label("woonplaats:");
		TextField woonplaatsTextField = new TextField();
		Button registratieButton = new Button("registreer");
		Text info = new Text("Velden met sterretjes zijn verplicht!");
		
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
		pane.add(voornaamSterretje, 2, 1);
		pane.add(achterNaamLabel, 0, 2);
		pane.add(achternaamTextField, 1, 2);
		pane.add(achternaamSterretje, 2, 2);
		pane.add(geboorteDatumLabel, 0, 3);
		pane.add(geboorteDatumPicker, 1, 3);
		pane.add(straatNaamLabel, 0, 4);
		pane.add(straatNaamTextField, 1, 4);
		pane.add(huisnummerLabel, 0, 5);
		pane.add(huisnummerTextField, 1, 5);
		pane.add(toevoegingsnummerLabel, 0, 6);
		pane.add(toevoegingsnummerTextField, 1, 6);
		pane.add(postcodeLabel, 0, 7);
		pane.add(postcodeTextField, 1, 7);
		pane.add(woonplaatsLabel, 0, 8);
		pane.add(woonplaatsTextField, 1, 8);
		pane.add(registratieButton, 1, 9);
		pane.add(info, 1, 10);
		
		return pane;
	}
	
	private Button getHomeButton(Stage stage) {
		Button homeButton = new Button("Home");
		homeButton.setOnAction(e -> {
			Home home = new Home();
			home.start(stage);
			
		});
		return homeButton;
	}
	
	
}
