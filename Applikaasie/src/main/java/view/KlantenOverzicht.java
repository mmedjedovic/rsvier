package view;


import java.util.ArrayList;

import logic.Applikaasie;
import model.Klant;
import model.Adres;
import util.DateToString;
import util.ExceptionIO;
import javafx.application.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.scene.layout.*;
import javafx.scene.*;
import javafx.scene.text.*;
import javafx.util.*;
import javafx.collections.*;



public class KlantenOverzicht {
	
	
	
	
	@SuppressWarnings("restriction")
	public BorderPane getBorderPane(Applikaasie applikaasie) throws ExceptionIO {
		
		BorderPane borderPane = new BorderPane();
		
		GridPane adresGridPane = new GridPane();
		adresGridPane.setPadding(new Insets(10, 10, 10, 10));
		adresGridPane.setVgap(5);
		adresGridPane.setHgap(5);
		
		VBox klantenLijstVBox = new VBox();
		klantenLijstVBox.setPadding(new Insets(10, 10, 10, 10));
		
		borderPane.setLeft(new ScrollPane(klantenLijstVBox));
		borderPane.setCenter(adresGridPane);
		
		ArrayList<Klant> klantLijst = applikaasie.getKlantenLijst();
		ListView<Klant> klantListView = new ListView<Klant>(FXCollections.observableArrayList(klantLijst));
		klantListView.setCellFactory(new Callback<ListView<Klant>, ListCell<Klant>>() {
			@Override
			public ListCell<Klant> call(ListView<Klant> list) {
				ListCell<Klant> cell = new ListCell<Klant>() {
					@Override
					protected void updateItem(Klant k, boolean b) {
						super.updateItem(k, b);
						if(k != null) {
							setText(k.getVoornaam() + "  " + k.getAchternaam());
						}
					}
				};
				return cell;
			}
		});
		
		klantListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		
		//implementation listener invalidation listener
		klantListView.getSelectionModel().selectedItemProperty().addListener(ov -> {
			Integer klantId = klantListView.getSelectionModel().getSelectedItem().getKlantId();
			adresGridPane.getChildren().clear();
			try {
				Adres adres = applikaasie.getAdres(klantId);
				adresGridPane.add(new Label("Straat naam:"), 0, 0);
				adresGridPane.add(new Label(adres.getStraatNaam()), 1, 0);
				adresGridPane.add(new Label("Huisnummer:"), 0, 1);
				adresGridPane.add(new Label(adres.getStraatNaam()), 1, 1);
				adresGridPane.add(new Label("Toevoegingshuisnummer:"), 0, 2);
				adresGridPane.add(new Label(adres.getToevoegingHuisnummer()), 1, 2);
				adresGridPane.add(new Label("Postcode:"), 0, 3);
				adresGridPane.add(new Label(adres.getPostcode()), 1, 3);
				adresGridPane.add(new Label("Huisnummer:"), 0, 4);
				adresGridPane.add(new Label(adres.getWoonplaats()), 1, 4);
			} catch (ExceptionIO e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		//stoppen klantenlijst in Vbox
		klantenLijstVBox.getChildren().addAll(klantListView);
		return borderPane;	
	}
}
