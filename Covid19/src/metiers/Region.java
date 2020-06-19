package metiers;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class Region {
	private String nom;
	private int totalCases;
	private int totalDeaths;
	private int totalRecovered;
	private int totalTests;
	private Vector<Statistique> listStatistique;
	
	public Map<String, Object> getHashMap(){
		Map<String, Object> data = new HashMap<>();
		data.put("nom",nom);
		data.put("listStatistique",listStatistique);
		return data;
	}
	
	public void addStatistique(Statistique s) {
		listStatistique.add(s);
	}
	public int getTotalTests_() {
		int total=0;
		for(Statistique s : listStatistique) {
			total+=s.getTests();
		}
		return total;
	}
	public int getTotalCases_() {
		int total=0;
		for(Statistique s : listStatistique) {
			total+=s.getNewCases();
		}
		return total;
	}
	public int getTotalDeaths_() {
		int total=0;
		for(Statistique s : listStatistique) {
			total+=s.getNewDeaths();
		}
		return total;
	}
	public int getTotalRecovered_() {
		int total=0;
		for(Statistique s : listStatistique) {
			total+=s.getNewRecovered();
		}
		return total;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Vector<Statistique> getListStatistique() {
		return listStatistique;
	}
	public void setListStatistique(Vector<Statistique> listStatistique) {
		this.listStatistique = listStatistique;
	}
	public Region() {
		super();
		listStatistique=new Vector<Statistique>();
	}
	public Region(String nom, Vector<Statistique> listStatistique) {
		super();
		this.nom = nom;
		this.listStatistique = listStatistique;
	}
	public Region(String nom) {
		super();
		this.nom = nom;
		listStatistique=new Vector<Statistique>();
	}

	public int getTotalCases() {
		return totalCases;
	}

	public void setTotalCases(int totalCases) {
		this.totalCases = totalCases;
	}

	public int getTotalDeaths() {
		return totalDeaths;
	}

	public void setTotalDeaths(int totalDeaths) {
		this.totalDeaths = totalDeaths;
	}

	public int getTotalRecovered() {
		return totalRecovered;
	}

	public void setTotalRecovered(int totalRecovered) {
		this.totalRecovered = totalRecovered;
	}

	public int getTotalTests() {
		return totalTests;
	}

	public void setTotalTests(int totalTests) {
		this.totalTests = totalTests;
	}
	
	
}
