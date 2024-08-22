package Tests;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.util.LinkedHashMap;
import Model.DatabaseModel;
import org.junit.jupiter.api.Test;
import Extra.MockDatabase;

class TestDatabaseModel {
	private MockDatabase mockDB = new MockDatabase();
	DatabaseModel db = new DatabaseModel();
	LinkedHashMap<String, String> resultatColors = new LinkedHashMap<String,String>();
	LinkedHashMap<String, String> resultatRanking = new LinkedHashMap<String,String>();
	LinkedHashMap<String, String> expectedColors = new LinkedHashMap<String,String>();	
	LinkedHashMap<String, String> expectedRanking = new LinkedHashMap<String,String>();
	
	// Test per comprovar la connexio a la nostra db
	@Test
	void testConnectBD() {
		mockDB.setDB(db);
		assertFalse(mockDB.connectBD(0));	
		assertFalse(mockDB.connectBD(6));	
		assertTrue(mockDB.connectBD(3));
	}
	
	// Test per comprovar que a la hora de insertar un nom, el nom es correcte o incorrecte
	@Test
	void testInsertBD() {
		// PARTICIONS EQUIVALENTS
		// FORMAT QUERY NO VÀLID
		// Nom ja registrat
		assertFalse(mockDB.insertBD("Pol, 5, 3"));
		
		//Format del nom incorrecte
		assertFalse(mockDB.insertBD("Pol22, 5, 3")); 
		
		//Format del nom incorrecte
		assertFalse(mockDB.insertBD("1111, 3, 7"));
		
		//Nom compost, no permès
		assertFalse(mockDB.insertBD("Eric Garcia, 4, 2"));
		
		//Format de la query incorrecte
		assertFalse(mockDB.insertBD("Alex")); 
		
		//Format de la query incorrecte
		assertFalse(mockDB.insertBD("Alex, 3")); 
		
		//Format de la query incorrecte
		assertFalse(mockDB.insertBD("Alex 3"));
		
		//Format de la query incorrecte
		assertFalse(mockDB.insertBD("Alex,, 5, 3"));
		
		//Format de la query incorrecte
		assertFalse(mockDB.insertBD("Alex, 5,, 3"));
		
		//Format de la query incorrecte
		assertFalse(mockDB.insertBD("Alex 5, 3"));
		
		//Format de la query incorrecte
		assertFalse(mockDB.insertBD("Alex, 5 3"));
		
		//Format de la query incorrecte
		assertFalse(mockDB.insertBD("Alex 5 3"));
		
		// VALORS LIMIT I PARTICIONS EQUIVALENTS
		//BOLES
		// 7 boles (frontera dreta)
		assertTrue(mockDB.insertBD("Quim, 7, 1"));
		
		// 6 boles (frontera interior dreta)
		assertTrue(mockDB.insertBD("Pau, 6, 1")); 
		
		// 8 boles (frontera exterior dreta)
		assertFalse(mockDB.insertBD("Quim, 8, 1")); 
		
		
		// 4 boles (frontera esquerre)
		assertTrue(mockDB.insertBD("Juan, 4, 6"));
		
		// 5 boles (frontera interior esquerre)
		assertTrue(mockDB.insertBD("Pepe, 5, 3")); 
		
		// 3 boles (frontera exterior esquerre)
		assertFalse(mockDB.insertBD("Juan, 3, 3")); 
		
		
		//INTENTS
		// 1 intent (frontera esquerre)
		assertTrue(mockDB.insertBD("Xavi, 4, 1"));
		
		// 2 intents (frontera interior esquerre)
		assertTrue(mockDB.insertBD("Javier, 4, 2"));
		
		// 0 intents (frontera exterior esquerre)
		assertFalse(mockDB.insertBD("Xavi, 4, 0"));
		
		// 6 intents (frontera dreta)
		assertTrue(mockDB.insertBD("Jose, 4, 6"));
		
		// Menys de 6 intents (frontera interior esquerre)
		assertTrue(mockDB.insertBD("Josep, 4, 5"));
		
		// Més de 6 intents (frontera exterior esquerre)
		assertFalse(mockDB.insertBD("Jose, 4, 7"));
		
		// PARWAISING
		// Esta permes 0 boles i 0 intents ja que l'afegim a la taula i despres
		// l'actualitzem amb la funcio update
		
		// 0 boles 0 intents es valid
		assertTrue(mockDB.insertBD("Lucia, 0, 0"));
		
		// 0 boles 1 intent
		assertFalse(mockDB.insertBD("Abelardo, 0, 1"));
		
		// 0 boles 2 intents
		assertFalse(mockDB.insertBD("Abelardo, 0, 2"));
		
		// 0 boles 3 intents
		assertFalse(mockDB.insertBD("Abelardo, 0, 3"));
		
		// 0 boles 4 intents
		assertFalse(mockDB.insertBD("Abelardo, 0, 4"));
		
		// 0 boles 5 intents
		assertFalse(mockDB.insertBD("Abelardo, 0, 5"));
		
		// 0 boles 6 intents
		assertFalse(mockDB.insertBD("Abelardo, 0, 6"));
		
		// 0 boles 7 intents
		assertFalse(mockDB.insertBD("Abelardo, 0, 7"));
		
		// 1 bola 0 intents
		assertFalse(mockDB.insertBD("Abelardo, 1, 0"));
		
		// 2 boles 0 intents
		assertFalse(mockDB.insertBD("Abelardo, 2, 0"));
		
		// 3 boles 0 intents
		assertFalse(mockDB.insertBD("Abelardo, 3, 0"));
		
		// 4 boles 0 intents
		assertFalse(mockDB.insertBD("Abelardo, 4, 0"));
	
		// 5 boles 0 intents
		assertFalse(mockDB.insertBD("Abelardo, 5, 0"));
		
		// 6 boles 0 intents
		assertFalse(mockDB.insertBD("Abelardo, 6, 0"));
		
		// 7 boles 0 intents
		assertFalse(mockDB.insertBD("Abelardo, 7, 0"));
		
	}
	
	// Test per comprovar que el ranking que ens retornen es l'esperat segons la query
	// que passem per parametre.
	@Test
	void testQueryBD() {
		mockDB.setDB(db);
		// PARTICIONS EQUIVALENTS
		// FORMAT QUERY NO VÀLID
		// Format de la query incorrecte
		assertTrue(mockDB.queryBD("boles").isEmpty());
		
		// Format de la query incorrecte
		assertTrue(mockDB.queryBD("nom").isEmpty());
		
		// Format de la query incorrecte
		assertTrue(mockDB.queryBD("boles,4").isEmpty());
		
		// Format de la query incorrecte
		assertTrue(mockDB.queryBD("nom,4").isEmpty());
		
		// Format de la query incorrecte
		assertTrue(mockDB.queryBD("4").isEmpty());
		
		// Format de la query incorrecte
		assertTrue(mockDB.queryBD("Juan").isEmpty());
		
		// Format de la query incorrecte
		assertTrue(mockDB.queryBD("aaa=4").isEmpty());

		// VALORS LIMIT I PARTICIONS EQUIVALENTS
		// BOLES
		// 7 boles (frontera dreta)
		expectedRanking.clear();
		expectedRanking.put("Judith", "7, 6");
		assertEquals(expectedRanking,mockDB.queryBD("boles=7"));
		
		// 6 boles (frontera interior dreta)
		expectedRanking.clear();
		expectedRanking.put("Joel", "6, 2");
		expectedRanking.put("Berta", "6, 2");
		expectedRanking.put("Daniel", "6, 3");
		assertEquals(expectedRanking,mockDB.queryBD("boles=6")); 
		
		// 8 boles (frontera exterior dreta)
		assertTrue(mockDB.queryBD("boles=8").isEmpty()); 
		
		// 4 boles (frontera esquerre)
		expectedRanking.clear();
		expectedRanking.put("Pol", "4, 1");
		expectedRanking.put("Marc", "4, 4");
		expectedRanking.put("Maria", "4, 5");
		assertEquals(expectedRanking,mockDB.queryBD("boles=4"));
		
		// 5 boles (frontera interior esquerre)
		expectedRanking.clear();
		expectedRanking.put("Ramon", "5, 3");
		expectedRanking.put("Fran", "5, 4");
		expectedRanking.put("Albert", "5, 5");
		expectedRanking.put("Arnau", "5, 5");
		assertEquals(expectedRanking,mockDB.queryBD("boles=5"));
		
		// 3 boles (frontera exterior esquerre)
		assertTrue(mockDB.queryBD("boles=3").isEmpty()); 
		
		// 0 boles
		expectedRanking.clear();
		expectedRanking.put("Alba", "0, 0");
		expectedRanking.put("Paco", "0, 0");
		expectedRanking.put("Raul", "0, 0");
		assertEquals(expectedRanking,mockDB.queryBD("boles=0"));
		
		
		//NOMS
		// Nom ja registrat
		expectedRanking.clear();
		expectedRanking.put("Ramon", "5, 3");
		assertEquals(expectedRanking,mockDB.queryBD("nom=Ramon")); 
		
		// Nom no registrat
		assertTrue(mockDB.queryBD("nom=Gustavo").isEmpty()); 
		
		// Nom invàlid
		assertTrue(mockDB.queryBD("nom=123").isEmpty()); 
		
		// Nom invàlid
		assertTrue(mockDB.queryBD("nom=").isEmpty()); 
	}
	
	// Test que serveix per comprovar que quan cridem la funció de update per actualitzar
	// els participants es realitzi de forma correcte
	@Test
	void testUpdateBD() { 
		mockDB.setDB(db);
		
		// INICI PARTICIONS EQUIVALENTS
		// VALORS NO-VÀLIDS (per part del fitxer)
		// Únic valor possible = "Ranking.txt" s'hauria d'actualitzar també o els Colors.txt o el Player.txt 
		// Sino s'hauria de treure el paràmetre

		// VÀLORS NO-VÀLIDS (per part de la query)
		
		// El nom no existeix
		assertFalse(mockDB.updateBD("Fernando, 4, 4"));
		
		// La query no té el format correcte
		assertFalse(mockDB.updateBD("Ramon 4 4"));
		
		// La query no té el format correcte
		assertFalse(mockDB.updateBD("Ramon 4,,,,4"));
		
		// El número de boles no és correcte
		assertFalse(mockDB.updateBD("Ramon, 10, 4"));
		
		// El número de boles no és correcte
		assertFalse(mockDB.updateBD("Ramon, -2, 4"));
		
		// El número d'intents no és correcte
		assertFalse(mockDB.updateBD("Ramon, 7, -1"));
		
		// El número d'intents no és correcte (supera el límit establert=6)
		assertFalse(mockDB.updateBD("Ramon, 7, 8"));
		
		// El número d'intents no és correcte
		assertFalse(mockDB.updateBD("Ramon, 5, 120"));
		
	
		// VALORS VÀLIDS
		// Els paràmetres són correctes completament
		assertTrue(mockDB.updateBD("Arnau, 5, 5"));
	
		assertTrue(mockDB.updateBD("Ramon, 5, 3"));
		
		assertTrue(mockDB.updateBD("Pol, 6, 4"));
		
		assertTrue(mockDB.updateBD("Judith, 7, 6"));
		
		
		// INICI VALORS LÍMIT 
		// VALORS LÍMITS PEL NÚMERO DE BOLES
		// Valors possibles [4,5,6,7]
		
		// frontera dreta
		assertTrue(mockDB.updateBD("Pol, 7, 3"));
		
		// frontera esquerra
		assertTrue(mockDB.updateBD("Pol, 4, 3")); 
		
		// frontera interior esquerra (valor interior)
		assertTrue(mockDB.updateBD("Pol, 5, 3")); 
		
		// frontera interior dreta (valor interior)
		assertTrue(mockDB.updateBD("Pol, 6, 3")); 
		
		// frontera exterior esquerra
		assertFalse(mockDB.updateBD("Pol, 8, 3")); 
		
		// frontera exterior dreta
		assertFalse(mockDB.updateBD("Pol, 3, 3")); 
		
		
		// VALORS LÍMITS PEL D'INTENTS
		// Valors possibles [1,2,3,4,5,6]
		// frontera dreta
		assertTrue(mockDB.updateBD("Pol, 7, 6")); 
		
		// frontera esquerra
		assertTrue(mockDB.updateBD("Pol, 4, 1")); 
		
		// frontera interior dreta
		assertTrue(mockDB.updateBD("Berta, 6, 2")); 
		
		// frontera interior esquerra
		assertTrue(mockDB.updateBD("Albert, 5, 5"));
		
		// valor interior esquerra
		assertTrue(mockDB.updateBD("Daniel, 6, 3")); 
		
		// valor interior dret
		assertTrue(mockDB.updateBD("Fran, 5, 4")); 

		// frontera exterior esquerra
		assertFalse(mockDB.updateBD("Pol, 8, 0")); 
		
		// frontera exterior dreta
		assertFalse(mockDB.updateBD("Maria, 3, 7")); 
	} 

	// Test per comprovar que quan sol·licitem el contingut d'una taula concreta
	// ens retorni el contingut esperat
	@Test
	void testReadTable() throws IOException {
		// Per validar fer array a ma i comprovar que genera el mateix
		
		mockDB.setDB(db);
		
		expectedColors.put("blau", "b");
		expectedColors.put("lila", "l");
		expectedColors.put("verd", "v");
		expectedColors.put("marro", "m");
		expectedColors.put("taronja", "t");
		expectedColors.put("rosa", "r");
		expectedColors.put("negre", "n");
		expectedColors.put("groc", "g");
		
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
		
		// PARTICIONS EQUIVALENTS
		// VALORS POSSIBLES: Colors.txt, Ranking.txt
		// La resta són valors incorrectes
		
		resultatColors = mockDB.readTable("Colors.txt");
		assertEquals(expectedColors, resultatColors);	
		resultatRanking = mockDB.readTable("Ranking.txt");
		assertEquals(expectedRanking, resultatRanking);	
		resultatRanking = mockDB.readTable("rrrrr.txt");
		assertTrue(resultatRanking.isEmpty());	
	}
	
}
