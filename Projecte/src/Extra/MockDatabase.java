package Extra;
import java.io.IOException;
import java.util.LinkedHashMap;
import Model.DatabaseModel;

public class MockDatabase extends DatabaseModel{
	DatabaseModel db = new DatabaseModel();
	LinkedHashMap<String, String> expectedColors = new LinkedHashMap<String,String>();
	LinkedHashMap<String, String> expectedRanking = new LinkedHashMap<String,String>();
	LinkedHashMap<String, String> expectedRanking2 = new LinkedHashMap<String,String>();
	LinkedHashMap<String, String> mapBuit = new LinkedHashMap<String,String>();
	
	// Setter de la db
	public void setDB(DatabaseModel db) {
		this.db = db;
	}
	
	// Funcio que simula la connexio a la db
	public boolean connectBD(int val)
	{
		switch (val) {
			case 1:
			case 3:
			case 5: 
				return true;
			default: 
				return false;
		}
	}
	
	// Funcio que simula la insercio de querys a la taula ranking de la nostra db
	public boolean insertBD(String toInsert) {		
		switch (toInsert) {
			case "Quim, 7, 1":
			case "Pau, 6, 1":
			case "Juan, 4, 6":
			case "Pepe, 5, 3":
			case "Xavi, 4, 1":
			case "Javier, 4, 2":
			case "Jose, 4, 6":
			case "Josep, 4, 5":
			case "Lucia, 0, 0":
				return true;
			default:
				return false;
		}		
	}
	
	// Funcio que simula l'actualització d'una query a la taula ranking de la nostra db
	public boolean updateBD(String item) {
		switch (item) {
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
	
	// Funcio que simula el retorn d'un LinkedHashMap en funcio del parametre d'entrada
	// que ens passen.
	public LinkedHashMap<String, String> queryBD(String query) {		
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
	
	// Funcio que simula la lectura de la taula ranking de la nostra db
	public LinkedHashMap<String, String> readTable(String item) throws IOException {
		switch (item) {
			case "Colors.txt":
				expectedColors.put("blau", "b");
				expectedColors.put("lila", "l");
				expectedColors.put("verd", "v");
				expectedColors.put("marro", "m");
				expectedColors.put("taronja", "t");
				expectedColors.put("rosa", "r");
				expectedColors.put("negre", "n");
				expectedColors.put("groc", "g");
				return expectedColors;
			case "Ranking.txt":
				expectedRanking.put("Marc", "4, 4");
				expectedRanking.put("Arnau", "5, 5");
				expectedRanking.put("Joel", "6, 2");
				expectedRanking.put("Maria", "4, 5");
				expectedRanking.put("Pol", "4, 1");
				expectedRanking.put("Berta", "6, 2");
				expectedRanking.put("Ramon", "5, 3");
				expectedRanking.put("Albert", "5, 5");
				expectedRanking.put("Judith", "7, 6");
				expectedRanking.put("Paco", "0, 0");
				expectedRanking.put("Daniel", "6, 3");
				expectedRanking.put("Raul", "0, 0");
				expectedRanking.put("Fran", "5, 4");
				expectedRanking.put("Alba", "0, 0");
				return expectedRanking;
			default:
				return mapBuit;
		}
	}	
}
