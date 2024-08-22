
package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import View.RankingView;

class TestRankingView {

	private RankingView vista = new RankingView();
	private List<String> list = new ArrayList();

	
	// Test per comprovar que el que ens mostrara per pantalla el rankingView
	// correspon a l'esperat
	@Test
	void testDrawRanking() throws IOException {
		// PARTICIONS EQUIVALENTS I VALORS LIMIT
		// Boles
		// 4 boles (frontera esquerre) i valor valid
		list.add("Pol,4,1");
		list.add("Marc,4,4");
		list.add("Maria,4,5");
		assertEquals(list, vista.drawRanking(4));
		
		// 3 boles (frontera exterior esquerre) i valor invalid
		assertNull(vista.drawRanking(3));
		
		// 5 boles (frontera interior esquerre) i valor valid
		list.clear();
		list.add("Ramon,5,3");
		list.add("Fran,5,4");
		list.add("Albert,5,5");
		list.add("Arnau,5,5");
		assertEquals(list, vista.drawRanking(5));
		
		// 7 boles (frontera dreta) i valor valid
		list.clear();
		list.add("Judith,7,6");
		assertEquals(list, vista.drawRanking(7));
		
		// 6 boles (frontera interior dreta) i valor valid
		list.clear();
		list.add("Berta,6,2");
		list.add("Joel,6,2");
		list.add("Daniel,6,3");
		assertEquals(list, vista.drawRanking(6));
		
		// 8 boles (frontera exterior dreta) i valor invalid
		assertNull(vista.drawRanking(8));
		
		// Altres valors invalids
		assertNull(vista.drawRanking(-2));
		
		assertNull(vista.drawRanking(10));
		
		assertNull(vista.drawRanking(100));	
	}
}

