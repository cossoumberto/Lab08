/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.extflightdelays;

import java.net.URL;
import java.util.ResourceBundle;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;

import it.polito.tdp.extflightdelays.model.Airport;
import it.polito.tdp.extflightdelays.model.CoppiaAirport;
import it.polito.tdp.extflightdelays.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private Model model;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML // fx:id="distanzaMinima"
    private TextField distanzaMinima; // Value injected by FXMLLoader

    @FXML // fx:id="btnAnalizza"
    private Button btnAnalizza; // Value injected by FXMLLoader

    @FXML
    void doAnalizzaAeroporti(ActionEvent event) {
    	Integer d = -1;
    	try	{
    		d = Integer.parseInt(distanzaMinima.getText());
    	} catch (Exception e) {
    		txtResult.setText("Devi inserire un numero");
    	}
    	if(d<-1)
    		txtResult.setText("Devi inserire un numero positivo");
    	else if(d>-1) {
	    	model.creaGrafo(d);
	    	Graph <Airport, DefaultWeightedEdge> grafo = model.getGrafo();
	    	txtResult.setText("Numero vertici grafo: " + grafo.vertexSet().size() + "\n");
	    	txtResult.appendText("Numero archi grafo: " + grafo.edgeSet().size() + "\n");
	    	
	    	for(DefaultWeightedEdge de : grafo.edgeSet())
	    		txtResult.appendText(grafo.getEdgeSource(de) + " - " + grafo.getEdgeTarget(de) + " " +
	    				grafo.getEdgeWeight(de) + "\n");
       	}
    }
    	

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert distanzaMinima != null : "fx:id=\"distanzaMinima\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnAnalizza != null : "fx:id=\"btnAnalizza\" was not injected: check your FXML file 'Scene.fxml'.";
    }
    
    public void setModel(Model model) {
    	this.model = model;
    }
}
