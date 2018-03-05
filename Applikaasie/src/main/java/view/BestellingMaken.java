package view;

import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.scene.layout.*;
import javafx.scene.*;
import javafx.util.*;
import logic.Applikaasie;
import model.Kaas;
import util.ExceptionIO;

import java.util.ArrayList;

import javafx.collections.*;
import javafx.stage.*;



@SuppressWarnings("restriction")
public class BestellingMaken {
	
	Stage homeStage;
	Scene homeScene;
	Scene klantOverzichtScene;
	Applikaasie applikaasie;
	Integer klantId;
	Integer gekozenKaasId;
	ArrayList<Integer> kaasIdLijst;
	ArrayList<String> kaasNaamLijst;
	ArrayList<Double> kaasPrijsLijst;
	ArrayList<Double> kaasVooraadLijst;
	
	public BestellingMaken(Integer klantId, Applikaasie applikaasie, Stage homeStage, Scene homeScene, Scene klantOverzichtScene) {
		this.klantId = klantId;
		this.applikaasie = applikaasie;
		this.homeStage = homeStage;
		this.klantOverzichtScene = klantOverzichtScene;
	}
	
	public Scene getGridPaneScene() throws ExceptionIO {
		
		GridPane pane = new GridPane();
		
		//Home button maken
		Button homeButton = new Button("home");
		homeButton.setOnAction(e -> {
			homeStage.setScene(homeScene);
		});
		
		//Cancel button maken
		Button cancelButton = new Button("cancel");
		cancelButton.setOnAction(e -> {
			homeStage.setScene(klantOverzichtScene);
		});
		
		//Ophalen kaas uit databse
		ArrayList<Kaas> kaasLijst = applikaasie.getKaasLijst();
		//
		if(!kaasLijst.isEmpty()) {
			for(Kaas kaas: kaasLijst) {
				kaasIdLijst.add(kaas.getKaasId());
				kaasNaamLijst.add(kaas.getNaam());
				kaasPrijsLijst.add(kaas.getPrijsInKg().doubleValue());
				kaasVooraadLijst.add(kaas.getVooraadInKg().doubleValue());
			}
		}
		//nodes maken waar combobox komt met kazen namen
		HBox boxVoorCombo = new HBox();
		Label comboLabel = new Label("kies kaas: ");
		ObservableList<String> kaasNaamObesrvableLijst = FXCollections.observableArrayList(kaasNaamLijst);
		ComboBox<String> comboKaasNamen = new ComboBox<>();
		comboKaasNamen.getItems().addAll(kaasNaamObesrvableLijst);
		TextField prijsKaas = new TextField();
		TextField voorraadKaas = new TextField();
		//listener voor combo
		comboKaasNamen.setOnAction(e -> {
			gekozenKaasId = kaasNaamObesrvableLijst.indexOf(comboKaasNamen.getValue());
			prijsKaas.setText(String.valueOf(kaasPrijsLijst.get(gekozenKaasId)));
			voorraadKaas.setText(String.valueOf(kaasVooraadLijst.get(gekozenKaasId)));
		});
		boxVoorCombo.getChildren().addAll(comboLabel, comboKaasNamen, prijsKaas, voorraadKaas);
		
		
	}
}
