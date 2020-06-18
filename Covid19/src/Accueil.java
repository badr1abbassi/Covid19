import java.io.FileInputStream;

import javax.faces.bean.ManagedBean;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

@ManagedBean
public class Accueil {
	private int totalCase;
	private int newCase;
	private int TotalDeaths;
	private int newDeaths;
	private int totalRecovered;
	private int newRecovered;
	private int activeCases;
	private int totalTests;
	private int newTests;
	private String message;
	private String login;
	private String mdp;

	public Accueil() {
		// totalCase=11;newCase=1;TotalDeaths=0;newDeaths=0;totalRecovered=10;newRecovered=2;activeCases=0;totalTests=0;newTests=0;

		try {
			FileInputStream serviceAccount = new FileInputStream("C:/JEEWorkspace/Covid19/serviceAccount.json");
			FirebaseOptions options = new FirebaseOptions.Builder()
					.setCredentials(GoogleCredentials.fromStream(serviceAccount))
					.setDatabaseUrl("https://covid19-10abd.firebaseio.com").build();

			FirebaseApp.initializeApp(options);
			DocumentReference docRef = FirestoreClient.getFirestore().collection("coivid19").document("NpFudp8J0Rn6AnkBDCN5");
			// asynchronously retrieve the document
			ApiFuture<DocumentSnapshot> future = docRef.get();
			DocumentSnapshot document = future.get();
			if (document.exists()) {
				totalCase=Math.toIntExact((Long)document.get("totalCase"));
				newCase=Math.toIntExact((Long)document.get("newCase"));
				newRecovered=Math.toIntExact((Long)document.get("newRecovered"));
				newDeaths=Math.toIntExact((Long)document.get("newDeaths"));
				newTests=Math.toIntExact((Long)document.get("newTests"));
				totalRecovered=Math.toIntExact((Long)document.get("totalRecovered"));
				TotalDeaths=Math.toIntExact((Long)document.get("TotalDeaths"));
				activeCases=Math.toIntExact((Long)document.get("activeCases"));
				totalTests=Math.toIntExact((Long)document.get("totalTests"));
			} else {
				System.out.println("No such document!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void initiaize() {

	}

	public int getNewRecovered() {
		return newRecovered;
	}

	public void setNewRecovered(int newRecovered) {
		this.newRecovered = newRecovered;
	}

	public int getTotalCase() {
		return totalCase;
	}

	public void setTotalCase(int totalCase) {
		this.totalCase = totalCase;
	}

	public int getNewCase() {
		return newCase;
	}

	public void setNewCase(int newCase) {
		this.newCase = newCase;
	}

	public int getTotalDeaths() {
		return TotalDeaths;
	}

	public void setTotalDeaths(int totalDeaths) {
		TotalDeaths = totalDeaths;
	}

	public int getNewDeaths() {
		return newDeaths;
	}

	public void setNewDeaths(int newDeaths) {
		this.newDeaths = newDeaths;
	}

	public int getTotalRecovered() {
		return totalRecovered;
	}

	public void setTotalRecovered(int totalRecovered) {
		this.totalRecovered = totalRecovered;
	}

	public int getActiveCases() {
		return activeCases;
	}

	public void setActiveCases(int activeCases) {
		this.activeCases = activeCases;
	}

	public int getTotalTests() {
		return totalTests;
	}

	public void setTotalTests(int totalTests) {
		this.totalTests = totalTests;
	}

	public int getNewTests() {
		return newTests;
	}

	public void setNewTests(int newTests) {
		this.newTests = newTests;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

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
		if (login.equals("badr") && mdp.equals("badr")) {
			this.message = "badr";
			return "Accueil";
		}
		return "Accueil";
	}

}
