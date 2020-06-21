import java.io.FileInputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import metiers.Reclamation;

@ManagedBean
public class GestionReclamationUser {

	private Reclamation reclamation=new Reclamation();
	private String numeroTel;
	private Reclamation detailsReclamation;
	// pas encore traité
	private HashMap<String, String> listeRegions;
	public GestionReclamationUser() {
		
		// liste des regions
		String tableauRegions[] = { "Fès-Meknès", "Oriental", "Guelmim-Oued Noun", "Souss-Massa", "Drâa-Tafilalet",
				"Marrakech-Safi", "Casablanca-Settat", "Béni Mellal-Khénifra", "Tanger-Tétouan-Al Hoceïma",
				"Rabat-Salé-Kénitra", "Dakhla-Oued Ed Dahab", "Laâyoune-Sakia El Hamra" };
		listeRegions = new HashMap<String, String>();
		for (int i = 0; i < 12; i++) {
			listeRegions.put(tableauRegions[i], tableauRegions[i]);
		}
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
			
			// asynchronously write data
			FirestoreClient.getFirestore().collection("reclamations").add(reclamation.getHashmap());
			// ApiFuture<WriteResult> result = docRef.set(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void myAction() {
		try {
			FileInputStream serviceAccount = new FileInputStream(
					"C:/Users/badre/git/Covid19/Covid19/serviceAccount.json");
			FirebaseOptions options = new FirebaseOptions.Builder()
					.setCredentials(GoogleCredentials.fromStream(serviceAccount))
					.setDatabaseUrl("https://covid19-10abd.firebaseio.com").build();
			if (FirebaseApp.getApps().isEmpty()) { // <--- check with this line
				FirebaseApp.initializeApp(options);
			}
			// asynchronously retrieve multiple documents
			ApiFuture<QuerySnapshot> future = FirestoreClient.getFirestore().collection("reclamations")
					.whereEqualTo("numeroTel", numeroTel).get();
			// future.get() blocks on response
			List<QueryDocumentSnapshot> documents = future.get().getDocuments();
			if (documents.size() == 0) {
				System.out.println("vous n'avez aucune reclamation");
			} else {
				for (DocumentSnapshot document : documents) {
					detailsReclamation=document.toObject(Reclamation.class);
					detailsReclamation.setID(document.getId());
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}



	public Reclamation getReclamation() {
		return reclamation;
	}



	public void setReclamation(Reclamation reclamation) {
		this.reclamation = reclamation;
	}



	public String getNumeroTel() {
		return numeroTel;
	}



	public void setNumeroTel(String numeroTel) {
		this.numeroTel = numeroTel;
	}



	public Reclamation getDetailsReclamation() {
		return detailsReclamation;
	}



	public void setDetailsReclamation(Reclamation detailsReclamation) {
		this.detailsReclamation = detailsReclamation;
	}
	

}
