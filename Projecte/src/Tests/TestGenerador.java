package Tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import Extra.MockGenerador;
import java.util.LinkedHashMap;
import Extra.Generador;


class TestGenerador {
	MockGenerador mockGen = new MockGenerador();
	Generador gen;
	String[] resultat;
	int expected;
	 
	@Test
	void testGeneraColors() {
		// Tenim 8 colors
		// Colors min=5 max=8
		// Frontera (min=1, max=8)
		// Valors límit (2, 7)
		mockGen.setGenerador(gen);
		
		//INICI PARTICIÓ EQUIVALENT
		
		// VALORS INVÀLIDS
		resultat = mockGen.generaColors(1); // exterior frontera
		expected = 0;
		assertEquals(expected, resultat.length);
		
		resultat = mockGen.generaColors(-3); // exterior frontera
		expected = 0;
		assertEquals(expected, resultat.length);
		
		resultat = mockGen.generaColors(0);
		expected = 0;
		assertEquals(expected, resultat.length);
		
		resultat = mockGen.generaColors(10);
		expected = 0;
		assertEquals(expected, resultat.length);
		
		resultat = mockGen.generaColors(2300);
		expected = 0;
		assertEquals(expected, resultat.length);
		
		resultat = mockGen.generaColors(37);
		expected = 0;
		assertEquals(expected, resultat.length);
		
		
		// VALORS VÀLIDS
		resultat = mockGen.generaColors(5); 
		expected = 5;
		assertEquals(expected, resultat.length);
		
		resultat = mockGen.generaColors(6); 
		expected = 6;
		assertEquals(expected, resultat.length);
		
		resultat = mockGen.generaColors(7); 
		expected = 7;
		assertEquals(expected, resultat.length);
		
		resultat = mockGen.generaColors(8); 
		expected = 8;
		assertEquals(expected, resultat.length);
		
		// FINAL PARTICIÓ EQUIVALENT  
		
		// INICI VALORS LÍMITS 
		
		// REVISAR
		// Valor límit dreta 
		resultat = mockGen.generaColors(9); 
		expected = 0;
		assertEquals(expected, resultat.length);
		
		// Valor límit esquerra
		resultat = mockGen.generaColors(4); 
		expected = 0;
		assertEquals(expected, resultat.length);
		
		// Valors interiors
		resultat = mockGen.generaColors(6); 
		expected = 6;
		assertEquals(expected, resultat.length);
		
		// Valors interiors 
		resultat = mockGen.generaColors(7); 
		expected = 7;
		assertEquals(expected, resultat.length);
		
		// Valors frontera
		resultat = mockGen.generaColors(5); 
		expected = 5;
		assertEquals(expected, resultat.length);
		
		// Valors frontera
		resultat = mockGen.generaColors(8); 
		expected = 8;
		assertEquals(expected, resultat.length);
		
		// Valors exteriors a la frontera
		resultat = mockGen.generaColors(4); 
		expected = 0;
		assertEquals(expected, resultat.length);
		
		// Valors exteriors a la frontera
		resultat = mockGen.generaColors(9); 
		expected = 0;
		assertEquals(expected, resultat.length);

		// FINAL VALORS LÍMITS
		
		
		
		
		// DECISION COVERAGE
		// if (numColors < 5 || numColors > 8)
		
		// IF = TRUE
		resultat = mockGen.generaColors(6); 
		expected = 6;
		assertEquals(expected, resultat.length);
		
		// IF = FALSE
		resultat = mockGen.generaColors(3); 
		expected = 0;
		assertEquals(expected, resultat.length);
		
		
		// CONDITION COVERAGE
		// if (numColors < 5 || numColors > 8)
		
		// Predicat A = (numColors < 5)
		// Predicat B = (numColors > 8)
		
		// A = FALSE
		resultat = mockGen.generaColors(3); 
		expected = 0;
		assertEquals(expected, resultat.length);
		
		// A = TRUE
		resultat = mockGen.generaColors(6); 
		expected = 6;
		assertEquals(expected, resultat.length);
		
		// B=TRUE 
		resultat = mockGen.generaColors(9); 
		expected = 0;
		assertEquals(expected, resultat.length);
		
		// B=FALSE 
		resultat = mockGen.generaColors(7); 
		expected = 7;
		assertEquals(expected, resultat.length);
		
		
		
	}
	
	
	@Test
	void testGeneraSolucio() {
		LinkedHashMap<String, String> resultat;
		
		// INICI PARTICIONS EQUIVALENTS 
		
		// VALORS INVÀLIDS PEL NUM DE COLORS
		resultat = mockGen.generaSolucio(4,5); 
		expected = 0;
		assertEquals(expected, resultat.size());
		
		resultat = mockGen.generaSolucio(0,5); 
		expected = 0;
		assertEquals(expected, resultat.size());
		
		resultat = mockGen.generaSolucio(-10,5); 
		expected = 0;
		assertEquals(expected, resultat.size());
		
		resultat = mockGen.generaSolucio(10000,5); 
		expected = 0;
		assertEquals(expected, resultat.size());
		
		
		// VALORS INVÀLIDS PER LES BOLES
		resultat = mockGen.generaSolucio(6,0); 
		expected = 0;
		assertEquals(expected, resultat.size());
		
		resultat = mockGen.generaSolucio(6,-2); 
		expected = 0;
		assertEquals(expected, resultat.size());
		
		resultat = mockGen.generaSolucio(6,100); 
		expected = 0;
		assertEquals(expected, resultat.size());
		
		resultat = mockGen.generaSolucio(6,9); 
		expected = 0;
		assertEquals(expected, resultat.size());
		
		// FINAL PARTICIONS EQUIVALENTS 
		
		
		// INICI VALORS LÍMIT I FRONTERES
		
		// VALORS LÍMITS PELS COLORS
		resultat = mockGen.generaSolucio(8,5); // frontera dreta
		expected = 5;	
		assertEquals(expected, resultat.size());
		
		resultat = mockGen.generaSolucio(5,5); // frontera esquerra
		expected = 0;
		assertEquals(expected, resultat.size());
		
		resultat = mockGen.generaSolucio(7,5); // frontera interior / Valor interior
		expected = 5; 
		assertEquals(expected, resultat.size());
		
		resultat = mockGen.generaSolucio(6,5); // frontera interior / Valor interior
		expected = 5; 
		assertEquals(expected, resultat.size());
		
		resultat = mockGen.generaSolucio(9,5); // frontera exterior (invàlid)
		expected = 0; 
		assertEquals(expected, resultat.size());
		
		resultat = mockGen.generaSolucio(4,5); // frontera exterior (invàlid)
		expected = 0;
		assertEquals(expected, resultat.size());
		
		
		// VALORS LÍMITS PER LES BOLES
		resultat = mockGen.generaSolucio(6,7); // frontera dreta
		expected = 0;
		assertEquals(expected, resultat.size());
		
		resultat = mockGen.generaSolucio(6,4); // frontera esquerra
		expected = 4; 
		assertEquals(expected, resultat.size());
		
		resultat = mockGen.generaSolucio(6,6); // frontera interior / Valor interior
		expected = 0; 
		assertEquals(expected, resultat.size());
		
		resultat = mockGen.generaSolucio(6,5); // frontera interior / Valor interior
		expected = 5; 
		assertEquals(expected, resultat.size());
		
		resultat = mockGen.generaSolucio(6,8); // frontera exterior (invàlid)
		expected = 0; 
		assertEquals(expected, resultat.size());
		
		resultat = mockGen.generaSolucio(6,3); // frontera exterior (invàlid)
		expected = 0;
		assertEquals(expected, resultat.size());
		
		// FINAL VALORS LÍMIT I FRONTERES

		// INICI PAIRWISE TESTING 
		// colors, boles
		
		// 5 colors
		resultat = mockGen.generaSolucio(5,4); // correcte: mínim 1 bola més que els colors (5 > 4)
		expected = 4;
		assertEquals(expected, resultat.size());
		
		resultat = mockGen.generaSolucio(5,5); // error: no pot haver mateix número de colors i de boles
		expected = 0;
		assertEquals(expected, resultat.size());
		
		resultat = mockGen.generaSolucio(5,6); // error: més boles que colors
		expected = 0;
		assertEquals(expected, resultat.size());
		
		resultat = mockGen.generaSolucio(5,7); // error: més boles que colors
		expected = 0;
		assertEquals(expected, resultat.size());
		 
		// 6 colors
		resultat = mockGen.generaSolucio(6,4); // correcte: mínim 1 bola més que els colors (6 > 4)
		expected = 4;
		assertEquals(expected, resultat.size());
		
		resultat = mockGen.generaSolucio(6,5); // correcte: mínim 1 bola més que els colors (6 > 5)
		expected = 5;
		assertEquals(expected, resultat.size());
		
		resultat = mockGen.generaSolucio(6,6); // error: no pot haver mateix número de colors i de boles
		expected = 0;
		assertEquals(expected, resultat.size());
		
		resultat = mockGen.generaSolucio(6,7); // error: més boles que colors
		expected = 0;
		assertEquals(expected, resultat.size());
		 
		
		// 7 colors
		resultat = mockGen.generaSolucio(7,4); // correcte: mínim 1 bola més que els colors (7 > 4)
		expected = 4;
		assertEquals(expected, resultat.size());
		
		resultat = mockGen.generaSolucio(7,5); // correcte: mínim 1 bola més que els colors (7 > 5)
		expected = 5;
		assertEquals(expected, resultat.size());
		
		resultat = mockGen.generaSolucio(7,6); // correcte: mínim 1 bola més que els colors (7 > 6)
		expected = 6;
		assertEquals(expected, resultat.size());
		
		resultat = mockGen.generaSolucio(7,7); // error: no pot haver mateix número de colors i de boles
		expected = 0;
		assertEquals(expected, resultat.size());
		
		 
		// 8 colors
		resultat = mockGen.generaSolucio(8,4); // correcte: mínim 1 bola més que els colors (8 > 4)
		expected = 4;
		assertEquals(expected, resultat.size());
		
		resultat = mockGen.generaSolucio(8,5); // correcte: mínim 1 bola més que els colors (8 > 5)
		expected = 5;
		assertEquals(expected, resultat.size());
		
		resultat = mockGen.generaSolucio(8,6); // correcte: mínim 1 bola més que els colors (8 > 6)
		expected = 6;
		assertEquals(expected, resultat.size());
		
		resultat = mockGen.generaSolucio(8,7); // correcte: mínim 1 bola més que els colors (8 > 7)
		expected = 7;
		assertEquals(expected, resultat.size());
		
		// FINAL PARWISE TESTING
		
		
		
		
		// DECISION COVERAGE
		// Paràmetres (numColors, boles)
		// if (boles >= numColors) return this.getSolucio(); 
		
		// IF = FALSE
		resultat = mockGen.generaSolucio(5,7); 
		expected = 0;
		assertEquals(expected, resultat.size());
		
		// IF = TRUE
		resultat = mockGen.generaSolucio(8,7); 
		expected = 7;
		assertEquals(expected, resultat.size());
		
		
		// CONDITION COVERAGE
		// if (boles < 4 || boles > 7) return this.getSolucio(); 
		
		// Predicat A = (boles < 4)
		// Predicat B = (boles > 7)
		
		
		// A = FALSE
		resultat = mockGen.generaSolucio(8,3); 
		expected = 0;
		assertEquals(expected, resultat.size());
		
		// A = TRUE
		resultat = mockGen.generaSolucio(8,5); 
		expected = 5;
		assertEquals(expected, resultat.size());
		
		// B = FALSE
		resultat = mockGen.generaSolucio(8,6); 
		expected = 6;
		assertEquals(expected, resultat.size());
		
		// B = TRUE
		resultat = mockGen.generaSolucio(8,9); 
		expected = 0;
		assertEquals(expected, resultat.size());
		
		
		
	}
} 
