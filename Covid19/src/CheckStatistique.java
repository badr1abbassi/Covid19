import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.faces.bean.ManagedBean;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import metiers.Region;
import metiers.Statistique;

@ManagedBean
public class CheckStatistique {

	private ArrayList<Region> listRegion = new ArrayList<Region>();
	private Region regionForDetails;
	private int size = 0;
	private String tableauRegions[] = { "Fès-Meknès", "Oriental", "Guelmim-Oued Noun", "Souss-Massa", "Drâa-Tafilalet",
			"Marrakech-Safi", "Casablanca-Settat", "Béni Mellal-Khénifra", "Tanger-Tétouan-Al Hoceïma",
			"Rabat-Salé-Kénitra", "Dakhla-Oued Ed Dahab", "Laâyoune-Sakia El Hamra" };

	public CheckStatistique() {

		try {
			FileInputStream serviceAccount = new FileInputStream(
					"C:/Users/badre/git/Covid19/Covid19/serviceAccount.json");
			FirebaseOptions options = new FirebaseOptions.Builder()
					.setCredentials(GoogleCredentials.fromStream(serviceAccount))
					.setDatabaseUrl("https://covid19-10abd.firebaseio.com").build();
			if (FirebaseApp.getApps().isEmpty()) { // <--- check with this line
				FirebaseApp.initializeApp(options);
			}
			for (int i = 0; i < 12; i++) {
				System.out.println("region " + tableauRegions[i]);
				Region region = new Region(tableauRegions[i]);
				ApiFuture<QuerySnapshot> statisticRef = FirestoreClient.getFirestore().collection("national")
						.document("nationalStatistique").collection("regions").document(tableauRegions[i])
						.collection("statistiques").get();
				List<QueryDocumentSnapshot> documents = statisticRef.get().getDocuments();

				for (QueryDocumentSnapshot doc : documents) {
					Statistique ss = doc.toObject(Statistique.class);
					region.addStatistique(doc.toObject(Statistique.class));
				}
				region.setTotalCases(region.getTotalCases_());
				region.setTotalDeaths(region.getTotalDeaths_());
				region.setTotalRecovered(region.getTotalRecovered_());
				region.setTotalTests(region.getTotalTests_());
				listRegion.add(region);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		size = listRegion.size();
		System.out.println("taille=" + listRegion.size() + "\nexemple = " + listRegion.get(0).getNom());

	}

	public Region getRegionForDetails() {
		return regionForDetails;
	}

	public void setRegionForDetails(Region regionForDetails) {
		this.regionForDetails = regionForDetails;
	}

	public void details(Region r) {
		regionForDetails=r;
	}

	public ArrayList<Region> getListRegion() {
		return listRegion;
	}

	public void setListRegion(ArrayList<Region> listRegion) {
		this.listRegion = listRegion;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String[] getTableauRegions() {
		return tableauRegions;
	}

	public void setTableauRegions(String[] tableauRegions) {
		this.tableauRegions = tableauRegions;
	}

}
