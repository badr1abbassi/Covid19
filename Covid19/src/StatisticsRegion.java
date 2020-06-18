
public class StatisticsRegion {
	private String nom;
	private Long totalCases;
	private Long newCases;
	
	
	public StatisticsRegion(String nom, Long totalCases, Long newCases) {
		this.nom = nom;
		this.totalCases = totalCases;
		this.newCases = newCases;
	}
	public StatisticsRegion() {
	
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Long getTotalCases() {
		return totalCases;
	}
	public void setTotalCases(Long totalCases) {
		this.totalCases = totalCases;
	}
	public Long getNewCases() {
		return newCases;
	}
	public void setNewCases(Long newCases) {
		this.newCases = newCases;
	}
	
}
