package metiers;

public class Admin {
	private String identifiant;
	private String mdp;
	
	public Admin(String identifiant, String mdp) {
		super();
		this.identifiant = identifiant;
		this.mdp = mdp;
	}
	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getIdentifiant() {
		return identifiant;
	}
	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}
	public String getMdp() {
		return mdp;
	}
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	

}
