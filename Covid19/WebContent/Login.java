import javax.faces.bean.ManagedBean;

@ManagedBean
public class Login {

	private String login;
	private String mdp;

	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getMdp() {
		return mdp;
	}
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	public String action() {
		if(login.equals("badr") && mdp.equals("badr")) {
			
			return "Accueil";
		}
		return "Accueil";
	}
	public String redirect() {
	return null;
	}
	
}
