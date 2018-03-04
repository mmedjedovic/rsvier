package view;

import util.*;
import java.math.*;
import java.util.ArrayList;

import javax.swing.plaf.basic.BasicTreeUI.SelectionModelPropertyChangeHandler;

import javafx.collections.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.scene.layout.*;
import javafx.scene.*;
import javafx.scene.text.*;
import javafx.stage.*;
import javafx.util.Callback;
import logic.Applikaasie;
import model.Kaas;
import javafx.application.*;
import javafx.util.*;



@SuppressWarnings("restriction")
public class ArtikelOverzicht {
	
	Integer kaasId;
	ListView<Kaas> kaasListView;
	ArrayList<Kaas> kaasLijst;
	Scene homeScene;
	Stage stage;
	
	public BorderPane getBorderPane(Stage stage, Scene homeScene, Applikaasie applikaasie, Home home) throws ExceptionIO {
		
		this.stage = stage;
		this.homeScene = homeScene;
		
		BorderPane pane = new BorderPane();
		
		//nodes van artikel overzicht maken
		//home button
		Button homeButton = new Button("home");
		homeButton.setOnAction(e -> {
			this.stage.setScene(homeScene);
		});
		
		//button van verwijderen een artikel uit asortimen
		Button deleteButton = new Button("delete");
		deleteButton.setOnAction(e -> {
			try {
				applikaasie.deleteKaas(kaasId);
				this.stage.setScene(homeScene);
			} catch (ExceptionIO e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		});
		
		ArrayList<Kaas> kaasLijst = applikaasie.getKaasLijst();
		kaasListView = new ListView<Kaas>(FXCollections.observableList(kaasLijst));
		kaasListView.setCellFactory(new Callback<ListView<Kaas>, ListCell<Kaas>>() {
			
			@Override
			public ListCell<Kaas> call(ListView<Kaas> list) {
				ListCell<Kaas> cell = new ListCell<Kaas>() {
					@Override
					protected void updateItem(Kaas k, boolean b) {
						if(k != null) {
							setText("Naam: " + k.getNaam() + "     prijs per kg: " + k.getPrijsInKg() + "     voorraad in kg: " + k.getVooraadInKg());
						}
					}
				};
				return cell;
			}
		});
		kaasListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		
		//implementatie van invalidation listener
		kaasListView.getSelectionModel().selectedItemProperty().addListener(ov -> {
			kaasId = kaasListView.getSelectionModel().getSelectedItem().getKaasId();
		});
		
		VBox kaasListViewVbox = new VBox();
		kaasListViewVbox.setPrefWidth(800);
		pane.setLeft(new ScrollPane(kaasListViewVbox));
		kaasListViewVbox.getChildren().addAll(kaasListView);
		
		HBox buttonHbox = new HBox();
		buttonHbox.getChildren().addAll(homeButton, deleteButton);
		buttonHbox.setPadding(new Insets(10, 10, 10, 10));
		buttonHbox.setSpacing(5);
		pane.setTop(buttonHbox);
		pane.setRight(null);
		pane.setCenter(null);
		return pane;
	}
	
}
