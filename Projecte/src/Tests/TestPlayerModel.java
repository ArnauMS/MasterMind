package Tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import Extra.MockPlayer;

class TestPlayerModel {

	private MockPlayer mockP = new MockPlayer();
	
	// Test per comprovar que a la hora de insertar un nom, el nom es correcte o incorrecte
	@Test
	void testInsertName() {
		// PARTICIONS EQUIVALENTS
		// Noms valids
		// Noms invalids

		//Nom ja registrat
		assertFalse(mockP.insertNom("Pol"));
		
		//Format del nom incorrecte
		assertFalse(mockP.insertNom("Pol22")); 
		
		//Format del nom incorrecte
		assertFalse(mockP.insertNom("1111"));
		
		//Format del nom incorrecte
		assertFalse(mockP.insertNom("Eric Garcia"));
		
		//Format del nom incorrecte
		assertFalse(mockP.insertNom(""));
		
		//Inserció correcte
		assertTrue(mockP.insertNom("Eric"));
		
	}
}