import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean
public class Admin {
	private String actionValue;
	
	public String action() {
		 FacesContext fc = FacesContext.getCurrentInstance();
	      Map<String,String> params = 
	         fc.getExternalContext().getRequestParameterMap();
	      actionValue =  params.get("action");
	      switch(actionValue) 
	        { 
	            case "addStatistique": 
	                return "FormulaireStatistique";
	               
	            case "checkStatistique": 
	                return "CheckStatistique"; 
	                
	            case "checkReclamations": 
	                return "ListeReclaramation";
	            default: 
	                System.out.println("no match"); 
	        }
	      return "Accueil";
	}

}
