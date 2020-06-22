import static org.junit.Assert.*;

import java.util.Vector;

import org.junit.Test;

import metiers.Statistique;

public class FormulaireStatistiqueTest {

	@Test
	public void test() {
		FormulaireStatistique formulaireStatistique=new FormulaireStatistique();
		int expected=10;
		int totalCase=20;
		int totalRecovered=5;
		int TotalDeaths=5;
		int actual=formulaireStatistique.calculateActiveCases(totalCase, totalRecovered, TotalDeaths);
		assertEquals(expected, actual);
	}

}
