import java.io.FileInputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.faces.bean.ManagedBean;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import metiers.*;

@ManagedBean
public class FormulaireStatistique {
	// Fès-Meknès
	private Statistique region1;
	// Oriental
	private Statistique region2;
	// Guelmim-Oued Noun
	private Statistique region3;
	// Souss-Massa
	private Statistique region4;
	// Drâa-Tafilalet
	private Statistique region5;
	// Marrakech-Safi
	private Statistique region6;
	// Casablanca-Settat
	private Statistique region7;
	// Béni Mellal-Khénifra
	private Statistique region8;
	// Tanger-Tétouan-Al Hoceïma
	private Statistique region9;
	// Rabat-Salé-Kénitra
	private Statistique region10;
	// Dakhla-Oued Ed Dahab
	private Statistique region11;
	// Laâyoune-Sakia El Hamra
	private Statistique region12;
	String tableauRegions[] = { "Fès-Meknès", "Oriental", "Guelmim-Oued Noun", "Souss-Massa", "Drâa-Tafilalet",
			"Marrakech-Safi", "Casablanca-Settat", "Béni Mellal-Khénifra", "Tanger-Tétouan-Al Hoceïma",
			"Rabat-Salé-Kénitra", "Dakhla-Oued Ed Dahab", "Laâyoune-Sakia El Hamra" };

	public FormulaireStatistique() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDateTime now = LocalDateTime.now();

		region1 = new Statistique(dtf.format(now));
		region2 = new Statistique(dtf.format(now));
		region3 = new Statistique(dtf.format(now));
		region4 = new Statistique(dtf.format(now));
		region5 = new Statistique(dtf.format(now));
		region6 = new Statistique(dtf.format(now));
		region7 = new Statistique(dtf.format(now));
		region8 = new Statistique(dtf.format(now));
		region9 = new Statistique(dtf.format(now));
		region10 = new Statistique(dtf.format(now));
		region11 = new Statistique(dtf.format(now));
		region12 = new Statistique(dtf.format(now));
	}

	public Vector<Statistique> getVector() {
		Vector<Statistique> list = new Vector<Statistique>();
		list.add(region1);
		list.add(region2);
		list.add(region3);
		list.add(region4);
		list.add(region5);
		list.add(region6);
		list.add(region7);
		list.add(region8);
		list.add(region9);
		list.add(region10);
		list.add(region11);
		list.add(region12);
		return list;
	}

	public String action() {
		insertStatistiques();
		return "Admin";
	}

	public void insertStatistiques() {
		try {
			FileInputStream serviceAccount = new FileInputStream(
					"C:/Users/XPS/git/Covid19/Covid19/serviceAccount.json");
			FirebaseOptions options = new FirebaseOptions.Builder()
					.setCredentials(GoogleCredentials.fromStream(serviceAccount))
					.setDatabaseUrl("https://covid19-10abd.firebaseio.com").build();
			if (FirebaseApp.getApps().isEmpty()) { // <--- check with this line
				FirebaseApp.initializeApp(options);
			}
			// Add document data with id "alovelace" using a hashmap
			Map<String, Object> data = new HashMap<>();
			Vector<Statistique> list = getVector();
			for (int i = 0; i < 12; i++) {
				FirestoreClient.getFirestore().collection("national").document("nationalStatistique")
						.collection("regions").document(tableauRegions[i]).collection("statistiques")
						.add(list.get(i).getHashMap());
			}
			DocumentReference docRef = FirestoreClient.getFirestore().collection("coivid19")
					.document("NpFudp8J0Rn6AnkBDCN5");
			// asynchronously retrieve the document
			ApiFuture<DocumentSnapshot> future = docRef.get();
			DocumentSnapshot document = future.get();
			if (document.exists()) {
				Map<String, Object> newData = getHashmapTotal(list);
				ApiFuture<WriteResult> result = docRef.set(newData);
				System.out.println("Update time : " + result.get().getUpdateTime());
			} else {
				System.out.println("No such document!");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Statistique getRegion1() {
		return region1;
	}

	public void setRegion1(Statistique region1) {
		this.region1 = region1;
	}

	public Statistique getRegion2() {
		return region2;
	}

	public void setRegion2(Statistique region2) {
		this.region2 = region2;
	}

	public Statistique getRegion3() {
		return region3;
	}

	public void setRegion3(Statistique region3) {
		this.region3 = region3;
	}

	public Statistique getRegion4() {
		return region4;
	}

	public void setRegion4(Statistique region4) {
		this.region4 = region4;
	}

	public Statistique getRegion5() {
		return region5;
	}

	public void setRegion5(Statistique region5) {
		this.region5 = region5;
	}

	public Statistique getRegion6() {
		return region6;
	}

	public void setRegion6(Statistique region6) {
		this.region6 = region6;
	}

	public Statistique getRegion7() {
		return region7;
	}

	public void setRegion7(Statistique region7) {
		this.region7 = region7;
	}

	public Statistique getRegion8() {
		return region8;
	}

	public void setRegion8(Statistique region8) {
		this.region8 = region8;
	}

	public Statistique getRegion9() {
		return region9;
	}

	public void setRegion9(Statistique region9) {
		this.region9 = region9;
	}

	public Statistique getRegion10() {
		return region10;
	}

	public void setRegion10(Statistique region10) {
		this.region10 = region10;
	}

	public Statistique getRegion11() {
		return region11;
	}

	public void setRegion11(Statistique region11) {
		this.region11 = region11;
	}

	public Statistique getRegion12() {
		return region12;
	}

	public void setRegion12(Statistique region12) {
		this.region12 = region12;
	}

	public Map<String, Object> getHashmapTotal(Vector<Statistique> list) {
		Map<String, Object> newData = new HashMap<>();
		try {
			DocumentReference docRef = FirestoreClient.getFirestore().collection("coivid19")
					.document("NpFudp8J0Rn6AnkBDCN5");
			// asynchronously retrieve the document
			ApiFuture<DocumentSnapshot> future = docRef.get();
			DocumentSnapshot document = future.get();
			if (document.exists()) {
				int totalCase = Math.toIntExact((Long) document.get("totalCase"));
				int totalRecovered = Math.toIntExact((Long) document.get("totalRecovered"));
				int TotalDeaths = Math.toIntExact((Long) document.get("totalDeaths"));
				int totalTests = Math.toIntExact((Long) document.get("totalTests"));
				int newCases = 0, newRecovered = 0, newDeaths = 0, newTests = 0,activecases;
				for (int i = 0; i < 12; i++) {
					newCases += list.get(i).getNewCases();
					newRecovered += list.get(i).getNewRecovered();
					newDeaths += list.get(i).getNewDeaths();
					newTests += list.get(i).getTests();
				}
				totalCase+=newCases;
				totalRecovered+=newRecovered;
				TotalDeaths+=newDeaths;
				totalTests+=newTests;
				activecases=calculateActiveCases(totalCase,totalRecovered,TotalDeaths);
				
				newData.put("totalCase", totalCase);
				newData.put("totalRecovered", totalRecovered);
				newData.put("totalDeaths", TotalDeaths);
				newData.put("totalTests",totalTests);
				newData.put("newCase", newCases);
				newData.put("newRecovered", newRecovered);
				newData.put("newDeaths", newDeaths);
				newData.put("newTests", newTests);
				newData.put("activeCases", activecases);

				
			} else {
				System.out.println("No such document!");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	
		return newData;

	}
	public int calculateActiveCases(int totalCase,int totalRecovered, int TotalDeaths) {
		return totalCase-totalRecovered-TotalDeaths;
	}

}
