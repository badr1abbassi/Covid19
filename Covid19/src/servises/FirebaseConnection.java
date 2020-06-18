package servises;

import java.io.FileInputStream;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

public class FirebaseConnection {

	public void initiaize() {
		try {
		FileInputStream serviceAccount =
				  new FileInputStream("path/to/serviceAccount.json");		
				FirebaseOptions options = new FirebaseOptions.Builder()
				  .setCredentials(GoogleCredentials.fromStream(serviceAccount))
				  .setDatabaseUrl("https://covid19-10abd.firebaseio.com")
				  .build();
			
				FirebaseApp.initializeApp(options);
				
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
