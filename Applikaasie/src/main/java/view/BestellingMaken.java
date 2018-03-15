package view;

import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.scene.layout.*;
import javafx.scene.*;
import javafx.util.*;
import logic.Applikaasie;
import model.Kaas;
import model.Klant;
import util.ExceptionIO;
import java.util.*;

import com.mysql.cj.core.conf.StringPropertyDefinition;

import java.math.*;
import javafx.beans.binding.Bindings;
import javafx.collections.*;
import javafx.stage.*;
import javafx.beans.binding.*;
import javafx.beans.property.*;



@SuppressWarnings("restriction")
public class BestellingMaken {
	
	private Stage homeStage;
	private Scene homeScene;
	private Scene klantOverzichtScene;
	private Applikaasie applikaasie;
	private Klant klant;
	private Integer gekozenKaasId;
	private ArrayList<Kaas> kaasLijst;
	private ArrayList<Integer> kaasIdLijst;
	private ArrayList<String> kaasNaamLijst;
	private ArrayList<Double> kaasPrijsLijst;
	private ArrayList<Double> kaasVooraadLijst;
	private HashMap<Kaas, BigDecimal> besteldeKazenLijst;
	
	public BestellingMaken(Klant klant, Applikaasie applikaasie, Stage homeStage, Scene homeScene, Scene klantOverzichtScene) {
		this.klant = klant;
		this.applikaasie = applikaasie;
		this.homeStage = homeStage;
		this.homeScene = homeScene;
		this.klantOverzichtScene = klantOverzichtScene;
		kaasLijst = new ArrayList<Kaas>();
		kaasIdLijst = new ArrayList<Integer>();
		kaasIdLijst.add(0);
		kaasNaamLijst = new ArrayList<String>();
		kaasNaamLijst.add("");
		kaasPrijsLijst = new ArrayList<Double>();
		kaasPrijsLijst.add(0.0);
		kaasVooraadLijst = new ArrayList<Double>();
		kaasVooraadLijst.add(0.0);
		besteldeKazenLijst = new HashMap<Kaas, BigDecimal>();
	}
	
	public Scene getGridPaneScene() throws ExceptionIO {
		
		BorderPane pane = new BorderPane();
		
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
		
		//hbox voor buttons
		HBox hBoxButton = new HBox();
		hBoxButton.getChildren().addAll(homeButton, cancelButton);
		hBoxButton.setPadding(new Insets(15, 15, 15, 15));
		hBoxButton.setSpacing(5);
		//Ophalen kaas uit databse
		kaasLijst = applikaasie.getKaasLijst();
		
		if(!kaasLijst.isEmpty()) {
			for(Kaas kaas: kaasLijst) {
				kaasIdLijst.add(kaas.getKaasId());
				kaasNaamLijst.add(kaas.getNaam());
				kaasPrijsLijst.add(kaas.getPrijsInKg().doubleValue());
				kaasVooraadLijst.add(kaas.getVooraadInKg().doubleValue());
			}
		}
		//nodes maken waar combobox komt met kazen namen
		GridPane gridPaneVoorCombo = new GridPane();
		gridPaneVoorCombo.setPadding(new Insets(15, 15, 15, 15));
		gridPaneVoorCombo.setVgap(5);
		gridPaneVoorCombo.setHgap(5);
		Label klantNaamLabel = new Label("Nieuwe bestelling voor: " + klant.getVoornaam() + " " + klant.getAchternaam());
		Label comboLabel = new Label("kies artikel: ");
		ObservableList<String> kaasNaamObesrvableLijst = FXCollections.observableArrayList(kaasNaamLijst);
		ComboBox<String> comboKaasNamen = new ComboBox<>();
		comboKaasNamen.getItems().addAll(kaasNaamObesrvableLijst);
		comboKaasNamen.getSelectionModel().selectFirst();
		Label prijsKaasLabel = new Label("prijs van de kaas in euro: ");
		TextField prijsKaasTextField = new TextField();
		Label voorraadKaasLabel = new Label("vooraad in kg: ");
		TextField voorraadKaasTextField = new TextField();
		Label bestelHoeveelheidLabel = new Label("hoeveelheid in kg te bestellen: ");
		TextField bestelHoeveelheidTextField = new TextField();
		Button bestellenButton = new Button("toevoegen");
		
		//listener voor combo
		comboKaasNamen.setOnAction(e -> {
			gekozenKaasId = kaasNaamObesrvableLijst.indexOf(comboKaasNamen.getValue());
			prijsKaasTextField.setText(String.valueOf(kaasPrijsLijst.get(gekozenKaasId)));
			voorraadKaasTextField.setText(String.valueOf(kaasVooraadLijst.get(gekozenKaasId)));
		});
		gridPaneVoorCombo.add(klantNaamLabel, 0, 0);
		gridPaneVoorCombo.add(comboLabel, 0, 1);
		gridPaneVoorCombo.add(comboKaasNamen, 1, 1);
		gridPaneVoorCombo.add(prijsKaasLabel, 0, 2);
		gridPaneVoorCombo.add(prijsKaasTextField, 1, 2);
		gridPaneVoorCombo.add(voorraadKaasLabel, 0, 3);
		gridPaneVoorCombo.add(voorraadKaasTextField, 1, 3);
		gridPaneVoorCombo.add(bestelHoeveelheidLabel, 0, 4);
		gridPaneVoorCombo.add(bestelHoeveelheidTextField, 1, 4);
		gridPaneVoorCombo.add(bestellenButton, 2, 4);
		
		//bestellings afsluiting deel
		HBox afsluitingHbox = new HBox();
		afsluitingHbox.setPadding(new Insets(15, 15, 15, 15));
		afsluitingHbox.setSpacing(5);
		//afsluiting button
		Label afsluitingLabel = new Label("Klaar met bestellen: ");
		Button bestellingOpslaanButton = new Button("bestelling opslaan");
		//listener voor bestelling button
		bestellenButton.setOnAction(e -> {
			BigDecimal besteldeHoeveelheid = new BigDecimal(bestelHoeveelheidTextField.getText());
			Kaas kaas = kaasLijst.get(gekozenKaasId - 1);
			besteldeKazenLijst.put(kaas, besteldeHoeveelheid);
			bestelHoeveelheidTextField.clear();
			comboKaasNamen.getSelectionModel().selectFirst();
			//bestellingopslaan button active maken
			bestellingOpslaanButton.setDisable(false);
		});
		//listener voor bestellingopslaan button
		bestellingOpslaanButton.setOnAction(e -> {
			try {
				applikaasie.bestellingMaken(besteldeKazenLijst, klant);
				homeStage.setScene(homeScene);
			} catch (ExceptionIO e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		bestellingOpslaanButton.setDisable(true);
		afsluitingHbox.getChildren().addAll(afsluitingLabel, bestellingOpslaanButton);
		
		//bestellbutton inactive maken
		bestellenButton.disableProperty().bind(Bindings.isEmpty(bestelHoeveelheidTextField.textProperty()));
		
		//alles bij elkaar
		pane.setTop(hBoxButton);
		pane.setCenter(gridPaneVoorCombo);
		pane.setBottom(afsluitingHbox);
		
		return new Scene(pane, 600, 400);		
	}
	
}
