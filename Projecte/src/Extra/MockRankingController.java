package Extra;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import Controller.RankingController;

public class MockRankingController extends RankingController{
	private List<String> list = new ArrayList();
	public List<String> rankingTable(int numBoles) {
		switch (numBoles) {
		case 4: 
			list.clear();
			list.add("Pol,4,1");
			list.add("Marc,4,4");
			list.add("Maria,4,5");
			return list;
		case 5: 
			list.clear();
			list.add("Ramon,5,3");
			list.add("Fran,5,4");
			list.add("Albert,5,5");
			list.add("Arnau,5,5");
			return list;
		case 6: 
			list.clear();
			list.add("Berta,6,2");
			list.add("Joel,6,2");
			list.add("Daniel,6,3");
			return list;
		case 7: 
			list.clear();
			list.add("Judith,7,6");
			return list;
		default:
			return null;
	}
	}
}
