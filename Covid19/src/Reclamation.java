import java.io.FileInputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

@ManagedBean
public class Reclamation {

	private String ID="null";
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
	//pas encore traité
	private int status=0;
	private HashMap<String,String> listeRegions;
	

	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getReponse() {
		return reponse;
	}
	public void setReponse(String reponse) {
		this.reponse = reponse;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
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

	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Reclamation() {
		  //liste des regions
		String tableauRegions[] = { "Fès-Meknès", "Oriental", "Guelmim-Oued Noun", "Souss-Massa", "Drâa-Tafilalet",
				"Marrakech-Safi", "Casablanca-Settat", "Béni Mellal-Khénifra", "Tanger-Tétouan-Al Hoceïma",
				"Rabat-Salé-Kénitra", "Dakhla-Oued Ed Dahab", "Laâyoune-Sakia El Hamra" };
		listeRegions = new HashMap<String, String>();
		for(int i=0;i<12;i++) {
			listeRegions.put(tableauRegions[i],tableauRegions[i]);
		}
	}
	public String getToux() {
		return toux;
	}
	public void setToux(String toux) {
		this.toux = toux;
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
	public String action() {
		insertreclamation();
		return "Accueil";
	}
	
	
	public HashMap<String, String> getListeRegions() {
		return listeRegions;
	}
	public void setListeRegions(HashMap<String, String> listeRegions) {
		this.listeRegions = listeRegions;
	}
	public void insertreclamation() {
		try {
			FileInputStream serviceAccount = new FileInputStream(
					"C:/Users/badre/git/Covid19/Covid19/serviceAccount.json");
			FirebaseOptions options = new FirebaseOptions.Builder()
					.setCredentials(GoogleCredentials.fromStream(serviceAccount))
					.setDatabaseUrl("https://covid19-10abd.firebaseio.com").build();
			if (FirebaseApp.getApps().isEmpty()) { // <--- check with this line
				FirebaseApp.initializeApp(options);
			}
		// Add document data  with id "alovelace" using a hashmap
		Map<String, Object> data = new HashMap<>();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();
		time=dtf.format(now);
		data.put("region",region);
		data.put("time",time);
		data.put("toux", toux);
		data.put("fievre",fievre);
		data.put("diffRespiratoire", diffRespiratoire);
		data.put("pneumonie",pneumonie);
		data.put("fatigue",fatigue);
		data.put("details",details);
		data.put("status",status);
		data.put("numeroTel",numeroTel);
		data.put("reponse","rien");
		//asynchronously write data	
		FirestoreClient.getFirestore().collection("reclamations").add(data);
		//ApiFuture<WriteResult> result = docRef.set(data);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
