package metiers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class Reclamation {
	private String ID = "null";
	private String toux;
	private String region;
	private String fievre;
	private String diffRespiratoire;
	private String pneumonie;
	private String fatigue;
	private String details;
	private String numeroTel;
	private String time;
	private String reponse;
	private int status = 0;
	public Reclamation() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getToux() {
		return toux;
	}
	public void setToux(String toux) {
		this.toux = toux;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getFievre() {
		return fievre;
	}
	public void setFievre(String fievre) {
		this.fievre = fievre;
	}
	public String getDiffRespiratoire() {
		return diffRespiratoire;
	}
	public void setDiffRespiratoire(String diffRespiratoire) {
		this.diffRespiratoire = diffRespiratoire;
	}
	public String getPneumonie() {
		return pneumonie;
	}
	public void setPneumonie(String pneumonie) {
		this.pneumonie = pneumonie;
	}
	public String getFatigue() {
		return fatigue;
	}
	public void setFatigue(String fatigue) {
		this.fatigue = fatigue;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public String getNumeroTel() {
		return numeroTel;
	}
	public void setNumeroTel(String numeroTel) {
		this.numeroTel = numeroTel;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getReponse() {
		return reponse;
	}
	public void setReponse(String reponse) {
		this.reponse = reponse;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Map<String, Object> getHashmap(){
		Map<String, Object> data = new HashMap<>();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		time = dtf.format(now);
		data.put("region", region);
		data.put("time", time);
		data.put("toux", toux);
		data.put("fievre", fievre);
		data.put("diffRespiratoire", diffRespiratoire);
		data.put("pneumonie", pneumonie);
		data.put("fatigue", fatigue);
		data.put("details", details);
		data.put("status", status);
		data.put("numeroTel", numeroTel);
		data.put("reponse", "Pas encore traité");
		return data;
	}
	@Override
	public String toString() {
		return "Reclamation [ID=" + ID + ", toux=" + toux + ", region=" + region + ", fievre=" + fievre
				+ ", diffRespiratoire=" + diffRespiratoire + ", pneumonie=" + pneumonie + ", fatigue=" + fatigue
				+ ", details=" + details + ", numeroTel=" + numeroTel + ", time=" + time + ", reponse=" + reponse
				+ ", status=" + status + "]";
	}
	
}
