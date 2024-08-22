package Model;
//retorna el ranking (nom, intents, boles)

import java.io.IOException;
import java.util.LinkedHashMap;
import Model.DatabaseModel;

public class RankingModel {
	private DatabaseModel db = new DatabaseModel();
	
	// Funcio encarregada de buscar en el ranking participants segons el parametre d'entrada
	public LinkedHashMap<String, String> queryRanking(String query) throws IOException {
		db.setDatabase(db);
		return db.queryBD(query);
	}
	
	// Funcio encarregada de actualitzar les puntuacions dels participants a la db
	public boolean updateRanking(String query) throws IOException {
		db.setDatabase(db);
		return db.updateBD(query);
	}
	
} 
 