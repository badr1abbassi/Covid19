import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import metiers.Region;
import metiers.Statistique;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.HorizontalBarChartModel;
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
	private Region regionForDetails;
	private ArrayList<Region> listRegion = new ArrayList<Region>();;
	private BarChartModel barModel;
	private String tableauRegions[] = { "Fès-Meknès", "Oriental", "Guelmim-Oued Noun", "Souss-Massa", "Drâa-Tafilalet",
			"Marrakech-Safi", "Casablanca-Settat", "Béni Mellal-Khénifra", "Tanger-Tétouan-Al Hoceïma",
			"Rabat-Salé-Kénitra", "Dakhla-Oued Ed Dahab", "Laâyoune-Sakia El Hamra" };
	public Accueil() {
		createBarModel();
		try {
			FileInputStream serviceAccount = new FileInputStream(
					"C:/Users/XPS/git/Covid19/Covid19/serviceAccount.json");
			FirebaseOptions options = new FirebaseOptions.Builder()
					.setCredentials(GoogleCredentials.fromStream(serviceAccount))
					.setDatabaseUrl("https://covid19-10abd.firebaseio.com").build();
			if (FirebaseApp.getApps().isEmpty()) { // <--- check with this line
				FirebaseApp.initializeApp(options);
			}

			// get values
			DocumentReference docRef = FirestoreClient.getFirestore().collection("coivid19")
					.document("NpFudp8J0Rn6AnkBDCN5");
			// asynchronously retrieve the document
			ApiFuture<DocumentSnapshot> future = docRef.get();
			DocumentSnapshot document = future.get();
			if (document.exists()) {
				totalCase = Math.toIntExact((Long) document.get("totalCase"));
				newCase = Math.toIntExact((Long) document.get("newCase"));
				newRecovered = Math.toIntExact((Long) document.get("newRecovered"));
				newDeaths = Math.toIntExact((Long) document.get("newDeaths"));
				newTests = Math.toIntExact((Long) document.get("newTests"));
				totalRecovered = Math.toIntExact((Long) document.get("totalRecovered"));
				TotalDeaths = Math.toIntExact((Long) document.get("TotalDeaths"));
				activeCases = Math.toIntExact((Long) document.get("activeCases"));
				totalTests = Math.toIntExact((Long) document.get("totalTests"));
			} else {
				System.out.println("No such document!");
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
					System.out.println(doc.getId() + " => " + doc.toObject(Statistique.class));
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
	}

	public void details(Region r) {
		regionForDetails=r;
	}

	public Region getRegionForDetails() {
		return regionForDetails;
	}

	public void setRegionForDetails(Region regionForDetails) {
		this.regionForDetails = regionForDetails;
	}

	public BarChartModel getBarModel() {
		return barModel;
	}

	public void setBarModel(BarChartModel barModel) {
		this.barModel = barModel;
	}



	public ArrayList<Region> getListRegion() {
		return listRegion;
	}

	public void setListRegion(ArrayList<Region> listRegion) {
		this.listRegion = listRegion;
	}

	public String[] getTableauRegions() {
		return tableauRegions;
	}

	public void setTableauRegions(String[] tableauRegions) {
		this.tableauRegions = tableauRegions;
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
			return "Admin";
		}
		message=null;
		return "Accueil";
	}

	public String consulter() {
			return "Reclamation";
	}
	
	  private HorizontalBarChartModel initBarModel() {
		  HorizontalBarChartModel model = new HorizontalBarChartModel();
	 
	        ChartSeries totalCaseGr = new ChartSeries();
	        totalCaseGr.setLabel("total Case");
	        totalCaseGr.set("aujourd huit", 152);

	 
	        ChartSeries TotalDeathsGr = new ChartSeries();
	        TotalDeathsGr.setLabel("Total Deaths");
	        TotalDeathsGr.set("aujourd huit", 20);
	       
	        
	        ChartSeries totalRecoveredGr = new ChartSeries();
	        totalRecoveredGr.setLabel("total Recovered");
	        totalRecoveredGr.set("aujourd huit", 52);

	        ChartSeries totalTestsGr = new ChartSeries();
	        totalTestsGr.setLabel("total Tests");
	        totalTestsGr.set("aujourd huit", 1052);

	        
	        
	        model.addSeries(totalCaseGr);
	        model.addSeries(TotalDeathsGr);
	        model.addSeries(totalRecoveredGr);
	        model.addSeries(totalTestsGr);

	        return model;
	    }
	
	  private void createBarModel() {
	        barModel = initBarModel();
	 
	        barModel.setTitle("Bar Chart");
	        barModel.setLegendPosition("ne");
	 
	        Axis xAxis = barModel.getAxis(AxisType.X);
	        xAxis.setLabel("jours");
	        Axis yAxis = barModel.getAxis(AxisType.Y);
	        yAxis.setLabel("cas");
	        yAxis.setMin(0);
	        yAxis.setMax(200);
	    }
}