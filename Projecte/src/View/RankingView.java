package View;
//Mostrarà el ranking segons les boles

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import Controller.RankingController;

public class RankingView {
	private RankingController ranking = new RankingController();
	List<String> rankingFinal = new ArrayList<>();
	
	// Funcio encarregada de mostrar per pantalla la taula del ranking
	public List<String> drawRanking(int numBoles) throws IOException {
		return ranking.rankingTable(numBoles); 
	}
}
