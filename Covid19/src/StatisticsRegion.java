
public class StatisticsRegion {
	private String nom;
	private Long newDeaths;
	private Long newCases;
	private Long newRecovered;
	
	public StatisticsRegion(String nom, Long newDeaths, Long newCases, Long newRecovered) {
		super();
		this.nom = nom;
		this.newDeaths = newDeaths;
		this.newCases = newCases;
		this.newRecovered = newRecovered;
	}
	
	public StatisticsRegion() {

	}

	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
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
	
	

	
}
