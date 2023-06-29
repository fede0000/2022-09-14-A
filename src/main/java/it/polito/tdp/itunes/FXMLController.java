/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.itunes;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

import it.polito.tdp.itunes.model.Album;
import it.polito.tdp.itunes.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private Model model;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnComponente"
    private Button btnComponente; // Value injected by FXMLLoader

    @FXML // fx:id="btnCreaGrafo"
    private Button btnCreaGrafo; // Value injected by FXMLLoader

    @FXML // fx:id="btnSet"
    private Button btnSet; // Value injected by FXMLLoader

    @FXML // fx:id="cmbA1"
    private ComboBox<Album> cmbA1; // Value injected by FXMLLoader

    @FXML // fx:id="txtDurata"
    private TextField txtDurata; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML // fx:id="txtX"
    private TextField txtX; // Value injected by FXMLLoader

    @FXML
    void doComponente(ActionEvent event) {
    	
    	txtResult.clear();
    	Album a = cmbA1.getValue();
    	if(a==null) {
    		this.txtResult.setText("Selezionare un album");
    	}
    	Set<Album> connessi = model.analizzaComponente(a);
    	
    	double durata = 0;
    	
    	for(Album a1 : connessi) {
    		durata += a1.getDurata();
    	}
    	this.txtResult.clear();
    	this.txtResult.setText("Gli album connessi sono: "+ connessi.size()+ "\n con durata totale: "+ durata);
    	
    }

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	

    	String input = txtDurata.getText();
    	if(input == "") {
    		txtResult.setText("Album mancante");
    	}
    	
    	try {
    		double inputNum = Double.parseDouble(input);
    		model.creaGrafo(inputNum);
    		String stringa= "I vertici sono: " + model.getNVertici()+ "\nGli archi sono: "+ model.getNArchi();
    		txtResult.setText(stringa);
    		
    		cmbA1.getItems().clear();
    		cmbA1.getItems().setAll(model.getVertices());
    		
    	}catch(NumberFormatException e) {
    		txtResult.setText("Errore");
    	}
    	
    }

    @FXML
    void doEstraiSet(ActionEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnComponente != null : "fx:id=\"btnComponente\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSet != null : "fx:id=\"btnSet\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbA1 != null : "fx:id=\"cmbA1\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtDurata != null : "fx:id=\"txtDurata\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtX != null : "fx:id=\"txtX\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    	
    }

}
