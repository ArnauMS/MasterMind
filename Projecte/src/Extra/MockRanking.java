package Extra;
import java.util.LinkedHashMap;
import Model.RankingModel;

public class MockRanking extends RankingModel {
	LinkedHashMap<String, String> expectedRanking = new LinkedHashMap<String,String>();
	
	// Funcio que simula el retorn d'un LinkedHashMap en funcio del parametre d'entrada
	// que ens passen.
	public LinkedHashMap<String, String> queryRanking(String query) {
		switch (query) {
			case "boles=0": 
				expectedRanking.clear();
				expectedRanking.put("Paco", "0, 0");
				expectedRanking.put("Raul", "0, 0");
				expectedRanking.put("Alba", "0, 0");
				return expectedRanking;
			case "boles=4": 
				expectedRanking.clear();
				expectedRanking.put("Pol", "4, 1");
				expectedRanking.put("Marc", "4, 4"); 
				expectedRanking.put("Maria", "4, 5");
				return expectedRanking;
			case "boles=5": 
				expectedRanking.clear();
				expectedRanking.put("Ramon", "5, 3");
				expectedRanking.put("Fran", "5, 4");
				expectedRanking.put("Albert", "5, 5");
				expectedRanking.put("Arnau", "5, 5");
				return expectedRanking;
			case "boles=6": 
				expectedRanking.clear();
				expectedRanking.put("Joel", "6, 2");
				expectedRanking.put("Berta", "6, 2");
				expectedRanking.put("Daniel", "6, 3");
				return expectedRanking;
			case "boles=7": 
				expectedRanking.clear();
				expectedRanking.put("Judith", "7, 6");
				return expectedRanking;
			case "nom=Ramon":
				expectedRanking.clear();
				expectedRanking.put("Ramon", "5, 3");
				return expectedRanking;
			default:
				expectedRanking.clear();
				return expectedRanking;
		}
	}
	
	// Funcio que simula l'actualització d'una query a la taula ranking de la nostra db
	public boolean updateRanking(String query) {
		switch (query) {
			case "Arnau, 5, 5":
			case "Ramon, 5, 3":
			case "Pol, 6, 4":
			case "Judith, 7, 6":
			case "Pol, 7, 3":
			case "Pol, 4, 3":
			case "Pol, 5, 3":
			case "Pol, 6, 3":
			case "Pol, 7, 6":
			case "Pol, 4, 1":
			case "Berta, 6, 2":
			case "Albert, 5, 5":
			case "Daniel, 6, 3":
			case "Fran, 5, 4":
				return true;
			default:
				return false;
		}
	}
}
