import java.io.FileInputStream;
import java.util.List;
import java.util.Vector;

import javax.faces.bean.ManagedBean;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import metiers.Region;
import metiers.Statistique;

@ManagedBean
public class GestionReclamations {
	
	Vector<Reclamation> listReclamations;
	private Reclamation detailsReclamation;
	private String reponse;
	
	public GestionReclamations() {
		listReclamations=new Vector<Reclamation>();
		initReclamation();
	}
	public String getReponse() {
		return reponse;
	}
	public void setReponse(String reponse) {
		this.reponse = reponse;
	}
	public void initReclamation() {
		try {
			FileInputStream serviceAccount = new FileInputStream(
					"C:/Users/XPS/git/Covid19/Covid19/serviceAccount.json");
			FirebaseOptions options = new FirebaseOptions.Builder()
					.setCredentials(GoogleCredentials.fromStream(serviceAccount))
					.setDatabaseUrl("https://covid19-10abd.firebaseio.com").build();
			if (FirebaseApp.getApps().isEmpty()) { // <--- check with this line
				FirebaseApp.initializeApp(options);
			}
		
				ApiFuture<QuerySnapshot> reclamationsRef = FirestoreClient.getFirestore().collection("reclamations").get();
				List<QueryDocumentSnapshot> documents = reclamationsRef.get().getDocuments();

				for (QueryDocumentSnapshot doc : documents) {
					Reclamation reclamation=doc.toObject(Reclamation.class);
					reclamation.setID(doc.getId());
					listReclamations.add(reclamation);
				}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String action(Reclamation r) {
			detailsReclamation=r;
			System.out.println("-------------------> "+detailsReclamation.getNumeroTel());
			return "DetailsReclamation";
	}
	public String go() {
		//System.out.println("-------------------> "+detailsReclamation.getID());
		try {
		DocumentReference docRef = FirestoreClient.getFirestore().collection("reclamations").document(detailsReclamation.getID());
		ApiFuture<WriteResult> future = docRef.update("reponse", reponse);
		WriteResult result = future.get();
		System.out.println("Write result: " + result);
		ApiFuture<WriteResult> future2 = docRef.update("status",1);
		WriteResult result2 = future2.get();
		System.out.println("Write result: " + result2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "ListeReclaramation";
	
	}
	public Reclamation getDetailsReclamation() {
		return detailsReclamation;
	}
	public void setDetailsReclamation(Reclamation detailsReclamation) {
		this.detailsReclamation = detailsReclamation;
	}
	public String details(Reclamation r) {
		detailsReclamation=r;
		return "detailsReclamation";
	}
	public Vector<Reclamation> getListReclamations() {
		return listReclamations;
	}
	public void setListReclamations(Vector<Reclamation> listReclamations) {
		this.listReclamations = listReclamations;
	}
	

}
