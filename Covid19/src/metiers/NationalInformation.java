package metiers;

import java.util.Vector;

public class NationalInformation {
	
	private Vector<Region> listRegions;
	private Vector<String> numerosUrgence;
	
	
	public void addRegion(Region r) {
		listRegions.add(r);
	}
	public void addNumeroUrgence(String NU) {
		numerosUrgence.add(NU);
	}
	public NationalInformation() {
		listRegions=new Vector<Region>();
		numerosUrgence=new Vector<String>();
	}
	
	public NationalInformation(Vector<Region> listRegions, Vector<String> numerosUrgence) {
		super();
		this.listRegions = listRegions;
		this.numerosUrgence = numerosUrgence;
	}

	public Vector<Region> getListRegions() {
		return listRegions;
	}
	public void setListRegions(Vector<Region> listRegions) {
		this.listRegions = listRegions;
	}
	public Vector<String> getNumerosUrgence() {
		return numerosUrgence;
	}
	public void setNumerosUrgence(Vector<String> numerosUrgence) {
		this.numerosUrgence = numerosUrgence;
	}
	
}
