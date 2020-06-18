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

	private String toux;
	private String fievre;
	private String diffRespiratoire;
	private String pneumonie;
	private String fatigue;
	private String details;
	private String user;
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public Reclamation() {

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
		data.put("time",dtf.format(now));
		data.put("user",user);
		data.put("toux", toux);
		data.put("fievre",fievre);
		data.put("diffRespiratoire", diffRespiratoire);
		data.put("pneumonie",pneumonie);
		data.put("fatigue",fatigue);
		data.put("details",details);
		//asynchronously write data
	
		FirestoreClient.getFirestore().collection("reclamations").add(data);
		//ApiFuture<WriteResult> result = docRef.set(data);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
