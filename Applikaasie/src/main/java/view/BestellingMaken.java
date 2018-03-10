package view;

import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.scene.layout.*;
import javafx.scene.*;
import javafx.util.*;
import logic.Applikaasie;
import model.Kaas;
import util.ExceptionIO;

import java.util.*;
import java.math.*;
import javafx.collections.*;
import javafx.stage.*;



@SuppressWarnings("restriction")
public class BestellingMaken {
	
	private Stage homeStage;
	private Scene homeScene;
	private Scene klantOverzichtScene;
	private Applikaasie applikaasie;
	private Integer klantId;
	private Integer gekozenKaasId;
	private ArrayList<Kaas> kaasLijst;
	private ArrayList<Integer> kaasIdLijst;
	private ArrayList<String> kaasNaamLijst;
	private ArrayList<Double> kaasPrijsLijst;
	private ArrayList<Double> kaasVooraadLijst;
	private HashMap<Kaas, BigDecimal> besteldeKazenLijst;
	
	public BestellingMaken(Integer klantId, Applikaasie applikaasie, Stage homeStage, Scene homeScene, Scene klantOverzichtScene) {
		this.klantId = klantId;
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
		//listener voor bestelling button
		bestellenButton.setOnAction(e -> {
			BigDecimal besteldeHoeveelheid = new BigDecimal(bestelHoeveelheidTextField.getText());
			Kaas kaas = kaasLijst.get(gekozenKaasId - 1);
			besteldeKazenLijst.put(kaas, besteldeHoeveelheid);
			bestelHoeveelheidTextField.clear();
			comboKaasNamen.getSelectionModel().selectFirst();
		});
		//listener voor combo
		comboKaasNamen.setOnAction(e -> {
			gekozenKaasId = kaasNaamObesrvableLijst.indexOf(comboKaasNamen.getValue());
			prijsKaasTextField.setText(String.valueOf(kaasPrijsLijst.get(gekozenKaasId)));
			voorraadKaasTextField.setText(String.valueOf(kaasVooraadLijst.get(gekozenKaasId)));
		});
		gridPaneVoorCombo.add(comboLabel, 0, 0);
		gridPaneVoorCombo.add(comboKaasNamen, 1, 0);
		gridPaneVoorCombo.add(prijsKaasLabel, 0, 1);
		gridPaneVoorCombo.add(prijsKaasTextField, 1, 1);
		gridPaneVoorCombo.add(voorraadKaasLabel, 0, 2);
		gridPaneVoorCombo.add(voorraadKaasTextField, 1, 2);
		gridPaneVoorCombo.add(bestelHoeveelheidLabel, 0, 3);
		gridPaneVoorCombo.add(bestelHoeveelheidTextField, 1, 3);
		gridPaneVoorCombo.add(bestellenButton, 2, 3);
		
		//bestellings afsluiting deel
		HBox afsluitingHbox = new HBox();
		afsluitingHbox.setPadding(new Insets(15, 15, 15, 15));
		afsluitingHbox.setSpacing(5);
		//afsluiting button
		Label afsluitingLabel = new Label("Klaar met bestellen: ");
		Button afsluitingButton = new Button("afsluiten");
		afsluitingButton.setOnAction(e -> {
			try {
				applikaasie.bestellingMaken(besteldeKazenLijst, klantId);
				homeStage.setScene(homeScene);
			} catch (ExceptionIO e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		afsluitingHbox.getChildren().addAll(afsluitingLabel, afsluitingButton);
		
		//alles bij elkaar
		pane.setTop(hBoxButton);
		pane.setCenter(gridPaneVoorCombo);
		pane.setBottom(afsluitingHbox);
		
		return new Scene(pane, 600, 400);		
	}
	
}
