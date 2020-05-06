package it.polito.tdp.extflightdelays.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.extflightdelays.db.ExtFlightDelaysDAO;

public class Model {
	
	private Graph <Airport, DefaultWeightedEdge> grafo;
	private ExtFlightDelaysDAO dao;
	private List<Airport> aereoporti;
	private Map <Integer, Airport> idMapAirport;
	
	public Model() {
		dao = new ExtFlightDelaysDAO();
		idMapAirport = new HashMap<>();
	}
	
	public void creaGrafo(Integer minDistanza) {
		this.grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		
		//CREO VERTICI E ID MAP DI AEREOPORTI
		this.aereoporti = dao.loadAllAirports();
		Graphs.addAllVertices(grafo, aereoporti);
		for(Airport a : this.aereoporti)
			idMapAirport.put(a.getId(), a);
		
		//CREO ARCHI
		for(CoppiaAirport ca : dao.getCoppie(idMapAirport, minDistanza))
			Graphs.addEdge(this.grafo, ca.getA1(), ca.getA2(), ca.getAvgDistanza());
	}

	public Graph<Airport, DefaultWeightedEdge> getGrafo() {
		return grafo;
	}
	
}
