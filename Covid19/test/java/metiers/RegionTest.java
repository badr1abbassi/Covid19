package metiers;

import static org.junit.Assert.*;

import java.util.Vector;

import org.junit.Test;

public class RegionTest {

	@Test
	public void test() {
		Vector<Statistique> listStatistique=new Vector<Statistique>();
		Long var=new Long(1);
		Long varTests=new Long(100);
		Statistique s1=new Statistique(var,var,var,varTests,"21/06/2020");
		Statistique s2=new Statistique(var,var,var,varTests,"20/06/2020");
		Statistique s3=new Statistique(var,var,var,varTests,"19/06/2020");
		listStatistique.add(s1);
		listStatistique.add(s2);
		listStatistique.add(s3);
		Region region=new Region("Fès-Meknès", listStatistique);
		int expectedTests=100;
		int actualTests=region.getTotalTests_();
		assertEquals(expectedTests, actualTests);
	}

}
