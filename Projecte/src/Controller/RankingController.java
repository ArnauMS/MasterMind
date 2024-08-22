package Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import Model.RankingModel;

public class RankingController {
	private RankingModel rModel = new RankingModel();
	private Map<String, String> contingut = new HashMap<String,String>();
	 
	// En aquesta funcio cridem al rankingModel per utilitzar la funcio de queryRanking
	// i ens retorna un LinkedHashMap que passem a una array i agafem aquells valors
	// que ens interessin segons el numero de boles.
	public List<String> rankingTable(int numBoles) throws IOException {
		if(numBoles < 4 || numBoles > 7) {
			return null;
		}
		
		int i = 1;
		contingut = rModel.queryRanking("boles="+numBoles);

		List<String> list = new ArrayList<>();
		List<String> rankingFinal = new ArrayList<>();
		for(Map.Entry player : contingut.entrySet()) {
			String clau = (String) player.getKey();
			String valor = (String) player.getValue();
			if (valor.length() == 1) {
				list.add(clau + "," + valor + "," + valor);
			} else {
				String[] valor2 = valor.split(", ");
				list.add(clau + "," + valor2[0] + "," + valor2[1]);
			}			 
		} 
		
		rankingFinal.add(list.get(0));
		for(int x = 1; x < list.size(); x++) {
			String[] listAux = list.get(x).split(",");
			for(int y = 0; y < rankingFinal.size(); y++) {
				String[] rankingAux = rankingFinal.get(y).split(",");
				if (Integer.parseInt(rankingAux[2]) > Integer.parseInt(listAux[2])) {
					if(!rankingFinal.contains(list.get(x))) {
						rankingFinal.add(y, list.get(x));
					}
				} else {
					if ((rankingAux[0].compareTo(listAux[0]) > 0) && (Integer.parseInt(rankingAux[2]) == Integer.parseInt(listAux[2]))) {
						if(!rankingFinal.contains(list.get(x))) {
							rankingFinal.add(y, list.get(x));
						}
					} else {
						if (y == rankingFinal.size()-1) {
							if(!rankingFinal.contains(list.get(x))) {
								rankingFinal.add(list.get(x));
							}
						}
					}
				}
			} 
		}

		System.out.println("Position\t" + "Name\t\t" + "Balls\t\t" + "Attempts");
		for(int y = 0; y < rankingFinal.size(); y++) {
			String[] rankingAux = rankingFinal.get(y).split(",");
			System.out.println("   " + i + "\t\t" + rankingAux[0] + "\t\t  " + rankingAux[1] + "\t\t   " + rankingAux[2]);
			i++;
		}
		return rankingFinal;
	} 
}
