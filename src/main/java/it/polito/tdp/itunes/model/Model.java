package it.polito.tdp.itunes.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graphs;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleGraph;
import it.polito.tdp.itunes.db.ItunesDAO;
import javafx.util.Pair;

public class Model {
	
	private SimpleGraph<Album, DefaultEdge> grafo;
	private List<Album> albumList;
	private ItunesDAO dao;
	private Map<Integer, Album>albumMapId;
	
	public void creaGrafo(double durata) {
		
		this.grafo = new SimpleGraph<>(DefaultEdge.class);
		dao = new ItunesDAO();
		albumList = dao.getVertici(durata);
		Graphs.addAllVertices(this.grafo, this.albumList);
		
		this.albumMapId = new HashMap<>();
		
		for (Album a : albumList) {
			this.albumMapId.put(a.getAlbumId(), a);
		}
		
		List<Pair<Integer,Integer>> archi = dao.getArchi();
		
		for(Pair<Integer, Integer> arco : archi) {
			if(this.albumMapId.containsKey(arco.getKey()) && this.albumMapId.containsKey(arco.getValue()) && !arco.getKey().equals(arco.getValue())) {
				this.grafo.addEdge(this.albumMapId.get(arco.getKey()), this.albumMapId.get(arco.getValue()));
				
			}
		}
		
	}
	
	public int getNVertici(){
		return this.grafo.vertexSet().size();
	}
	
	public List<Album> getVertices(){
		List<Album> allVertices = new ArrayList<>(this.grafo.vertexSet());
		Collections.sort(allVertices);
		return allVertices;
	}
	
	public int getNArchi(){
		return this.grafo.edgeSet().size();
	}
	
	public Set<Album> analizzaComponente(Album a) {
		
		// Trova componente connessa del singolo vertice (Connectivity Inspector)
		ConnectivityInspector<Album, DefaultEdge> inspector =
				new ConnectivityInspector<Album, DefaultEdge>(this.grafo);
		Set<Album> connessi = inspector.connectedSetOf(a);

		return connessi;
	}
}
