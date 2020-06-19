package metiers;

import java.util.HashMap;
import java.util.Map;

public class Statistique {
	private Long newDeaths;
	private Long newCases;
	private Long newRecovered;
	private Long tests;
	private String date;
	
	
	public Statistique() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Statistique(String date) {
		this.date = date;
	}
	public Statistique(Long newDeaths, Long newCases, Long newRecovered, Long tests, String date) {
		super();
		this.newDeaths = newDeaths;
		this.newCases = newCases;
		this.newRecovered = newRecovered;
		this.tests = tests;
		this.date = date;
	}
	public Statistique(Long newDeaths, Long newCases, Long newRecovered, Long tests) {
		super();
		this.newDeaths = newDeaths;
		this.newCases = newCases;
		this.newRecovered = newRecovered;
		this.tests = tests;
	}
	public Long getNewDeaths() {
		return newDeaths;
	}
	public void setNewDeaths(Long newDeaths) {
		this.newDeaths = newDeaths;
	}
	public Long getNewCases() {
		return newCases;
	}
	public void setNewCases(Long newCases) {
		this.newCases = newCases;
	}
	public Long getNewRecovered() {
		return newRecovered;
	}
	public void setNewRecovered(Long newRecovered) {
		this.newRecovered = newRecovered;
	}
	public Long getTests() {
		return tests;
	}
	public void setTests(Long tests) {
		this.tests = tests;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "Statistique [newDeaths=" + newDeaths + ", newCases=" + newCases + ", newRecovered=" + newRecovered
				+ ", tests=" + tests + ", date=" + date + "]";
	}
	public Map<String, Object> getHashMap(){
		Map<String, Object> data = new HashMap<>();
		data.put("date",date);
		data.put("newDeaths",newDeaths);
		data.put("newCases",newCases);
		data.put("newRecovered",newRecovered);
		data.put("tests",tests);
		return data;
	}
	
	
}
