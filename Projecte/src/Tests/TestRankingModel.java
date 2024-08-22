package Tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.LinkedHashMap;
import Extra.MockRanking;

class TestRankingModel {		
	private MockRanking mockR = new MockRanking();
	LinkedHashMap<String, String> resultatRanking = new LinkedHashMap<String,String>();
	LinkedHashMap<String, String> expectedRanking = new LinkedHashMap<String,String>();
	
	
	// Test per comprovar que el ranking que ens retornen es l'esperat segons la query
	// que passem per parametre.
	@Test 
	void testQueryRanking() {
		// PARTICIONS EQUIVALENTS
		// Format de la query invalid
		assertTrue(mockR.queryRanking("boles").isEmpty());
		
		// Format de la query invalid
		assertTrue(mockR.queryRanking("nom").isEmpty());
		
		// Format de la query invalid
		assertTrue(mockR.queryRanking("boles,4").isEmpty());
		
		// Format de la query invalid
		assertTrue(mockR.queryRanking("nom,4").isEmpty());
		
		// Format de la query invalid
		assertTrue(mockR.queryRanking("4").isEmpty());
		
		// Format de la query invalid
		assertTrue(mockR.queryRanking("Juan").isEmpty());
		
		// Format de la query invalid
		assertTrue(mockR.queryRanking("aaa=4").isEmpty());

		// VALORS LIMIT AMB PARTICIONS EQUIVALENTS
		// BOLES [4,7]
		// 7 boles (frontera dreta) i valor valid
		expectedRanking.clear();
		expectedRanking.put("Judith", "7, 6");
		assertEquals(expectedRanking,mockR.queryRanking("boles=7"));
		
		// Menys de 7 boles (frontera interior dreta) i valor valid
		expectedRanking.clear();
		expectedRanking.put("Joel", "6, 2");
		expectedRanking.put("Berta", "6, 2");
		expectedRanking.put("Daniel", "6, 3");
		assertEquals(expectedRanking,mockR.queryRanking("boles=6")); 
		
		// Més de 7 boles (frontera exterior dreta) i valor invalid
		assertTrue(mockR.queryRanking("boles=8").isEmpty()); 
		
		
		// 4 boles (frontera esquerre)
		expectedRanking.clear();
		expectedRanking.put("Pol", "4, 1");
		expectedRanking.put("Marc", "4, 4");
		expectedRanking.put("Maria", "4, 5");
		assertEquals(expectedRanking,mockR.queryRanking("boles=4"));
		
		// 5 boles (frontera interior esquerre)
		expectedRanking.clear();
		expectedRanking.put("Ramon", "5, 3");
		expectedRanking.put("Fran", "5, 4");
		expectedRanking.put("Albert", "5, 5");
		expectedRanking.put("Arnau", "5, 5");
		assertEquals(expectedRanking,mockR.queryRanking("boles=5"));
		
		
		// 0 boles
		expectedRanking.clear();
		expectedRanking.put("Alba", "0, 0");
		expectedRanking.put("Paco", "0, 0");
		expectedRanking.put("Raul", "0, 0");
		assertEquals(expectedRanking,mockR.queryRanking("boles=0"));
		
		
		//NOMS
		// Nom ja registrat,valor valid
		expectedRanking.clear();
		expectedRanking.put("Ramon", "5, 3");
		assertEquals(expectedRanking,mockR.queryRanking("nom=Ramon")); 
		
		// Nom no registrat però valor valid però
		assertTrue(mockR.queryRanking("nom=Gustavo").isEmpty()); 
		
		// Nom invàlid
		assertTrue(mockR.queryRanking("nom=123").isEmpty()); 
		
		// Nom invàlid
		assertTrue(mockR.queryRanking("nom=").isEmpty()); 
	}
	
	
	// Test que serveix per comprovar que quan cridem la funció de update per actualitzar
	// els participants es realitzi de forma correcte
	@Test
	void testUpdateRanking() {
		// PARTICIONS EQIVALENTS
		// VÀLORS NO-VÀLIDS (per part de la query)
		// El nom no existeix
		assertFalse(mockR.updateRanking("Fernando, 4, 4"));
		
		// La query no té el format correcte
		assertFalse(mockR.updateRanking("Ramon 4 4"));
		
		// La query no té el format correcte
		assertFalse(mockR.updateRanking("Ramon 4,,,,4"));
		
		// El número de boles no és correcte
		assertFalse(mockR.updateRanking("Ramon, 10, 4"));
		
		// El número de boles no és correcte
		assertFalse(mockR.updateRanking("Ramon, -2, 4"));
		
		// El número d'intents no és correcte
		assertFalse(mockR.updateRanking("Ramon, 7, -1"));
		
		// El número d'intents no és correcte (supera el límit establert=6)
		assertFalse(mockR.updateRanking("Ramon, 7, 8"));
		
		// El número d'intents no és correcte
		assertFalse(mockR.updateRanking("Ramon, 5, 120"));
		
	
		// VALORS VÀLIDS
		// Els paràmetres són correctes completament
		assertTrue(mockR.updateRanking("Arnau, 5, 5"));
	
		assertTrue(mockR.updateRanking("Ramon, 5, 3"));
		
		assertTrue(mockR.updateRanking("Pol, 6, 4"));
		
		assertTrue(mockR.updateRanking("Judith, 7, 6"));
		
		
		// VALORS LÍMITS PEL NÚMERO DE BOLES
		// Valors possibles [4,5,6,7]
		
		// frontera dreta
		assertTrue(mockR.updateRanking("Pol, 7, 3"));
		
		// frontera esquerra
		assertTrue(mockR.updateRanking("Pol, 4, 3")); 
		
		// frontera interior esquerra (valor interior)
		assertTrue(mockR.updateRanking("Pol, 5, 3")); 
		
		// frontera interior dreta (valor interior)
		assertTrue(mockR.updateRanking("Pol, 6, 3")); 
		
		// frontera exterior esquerra
		assertFalse(mockR.updateRanking("Pol, 8, 3")); 
		
		// frontera exterior dreta
		assertFalse(mockR.updateRanking("Pol, 3, 3")); 
		
		
		// VALORS LÍMITS PEL D'INTENTS
		// Valors possibles [1,2,3,4,5,6]
		// frontera dreta
		assertTrue(mockR.updateRanking("Pol, 7, 6")); 
		
		// frontera esquerra
		assertTrue(mockR.updateRanking("Pol, 4, 1")); 
		
		// frontera interior dreta
		assertTrue(mockR.updateRanking("Berta, 6, 2")); 
		
		// frontera interior esquerra
		assertTrue(mockR.updateRanking("Albert, 5, 5"));
		
		// valor interior esquerra
		assertTrue(mockR.updateRanking("Daniel, 6, 3")); 
		
		// valor interior dret
		assertTrue(mockR.updateRanking("Fran, 5, 4")); 

		// frontera exterior esquerra
		assertFalse(mockR.updateRanking("Pol, 8, 0")); 
		
		// frontera exterior dreta
		assertFalse(mockR.updateRanking("Maria, 3, 7")); 
	} 
}
