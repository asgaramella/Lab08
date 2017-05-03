package it.polito.tdp.borders.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.ConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.borders.db.BordersDAO;

public class Model {
	
	UndirectedGraph<Country,DefaultEdge> grafo;
	List<Country> paesi;
	BordersDAO dao=new BordersDAO();
	

	public Model() {
		grafo= new SimpleGraph<Country,DefaultEdge>(DefaultEdge.class);
		paesi=new ArrayList<Country>(dao.loadAllCountries());
	
	}
	
	Country trovaPaese(int code){
		for(Country ctemp:paesi){
			if(ctemp.getCode()==code)
				return ctemp;
		}
		return null;
	}
	//ochhio a mettere public per le funzioni
	 public Set<Country> generaGrafo(int anno){
		
		for(Border btemp:dao.getCountryPairs(anno)){
			Country c1=this.trovaPaese(btemp.getCode1());
			Country c2=this.trovaPaese(btemp.getCode2());
			grafo.addVertex(c1);
			grafo.addVertex(c2);
			grafo.addEdge(c1,c2);
		}
		
		return grafo.vertexSet();
		
		
	}

	public int nrVicini(Country ctemp) {
		
		return grafo.degreeOf(ctemp);
	}
	
	public int nrComponentiConnesse(){
		ConnectivityInspector<Country,DefaultEdge> c= new ConnectivityInspector<Country,DefaultEdge>(grafo);
		
		return c.connectedSets().size();
	}
	
	

}
