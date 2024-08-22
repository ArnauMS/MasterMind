package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.LinkedHashMap;

import Model.GameModel;

import org.junit.jupiter.api.Test;

class TestGameModel {
	private GameModel gm = new GameModel();
	private int expected, res=0;

	@Test
	void testRandomColor() {
		// Cridem a mock genera colors
		// comprovem que el que retorna sigui correcte
		//fail("Not yet implemented");
	}

	@Test
	void testRandomRow() throws IOException {
		// Cridem a mock genera jugada guanyadora 
		// comprovem que el que retorna sigui correcte
		
		/** INICI PARTICIÓ EQUIVALENT **/	
		
		// VALORS VÀLIDS	
		expected = 4;
		res = gm.RandomRow(4).size();
		assertEquals(expected, res);
		
		expected = 5;
		res = gm.RandomRow(5).size();
		System.out.println("Res: " + res);
		assertEquals(expected, res);
		
		expected = 6;
		res = gm.RandomRow(6).size();
		assertEquals(expected, res);
		
		expected = 7;
		res = gm.RandomRow(7).size();
		assertEquals(expected, res);
		
		
		// VALORS NO-VÀLIDS
		assertEquals(gm.RandomRow(0), null);
		
		assertEquals(gm.RandomRow(10), null);
		
		assertEquals(gm.RandomRow(-1), null);
		
		assertEquals(gm.RandomRow(8), null);
		
		/** FINAL PARTICIÓ EQUIVALENT **/
		
		
		
		/** INICI VALORS LÍMIT I FRONTERA **/
		
		expected = 4;		
		res = gm.RandomRow(4).size();		// valor frontera
		assertEquals(expected, res);
		
		expected = 7;		
		res = gm.RandomRow(7).size(); 		// valor frontera
		assertEquals(expected, res);
		
		expected = 5;		
		res = gm.RandomRow(5).size(); 		// valors interiors + valor límit
		assertEquals(expected, res);
		
		expected = 6;		
		res = gm.RandomRow(6).size(); 		// valors interiors + valor límit
		assertEquals(expected, res);
		
		assertEquals(gm.RandomRow(3), null);   // valors límit exterior
		
		assertEquals(gm.RandomRow(8), null);			// valors límit exterior
		
		
		/** FINAL VALORS LÍMIT I FRONTERA **/
	}
	
	
	@Test
	public void testGetSolutioInitials() throws IOException 
	{
		GameModel gm = new GameModel();
		int exp, res;
		
		
		// PARTICIONS EQUIVALENTS + VALORS LÍMITS i FRONTERA
		
		// VALORS VÀLIDS
		gm.RandomRow(4);						// FRONTERA
		res = gm.getSolutioInitials().length();
		exp = 4;
		assertEquals(exp, res);
		
		gm.RandomRow(5);						// VALOR LÍMIT INTERIOR
		res = gm.getSolutioInitials().length();
		exp = 5;
		assertEquals(exp, res);
		
		gm.RandomRow(6);
		res = gm.getSolutioInitials().length(); // VALOR LÍMIT INTERIOR
		exp = 6;
		assertEquals(exp, res);
		
		gm.RandomRow(7);						// FRONTERA
		res = gm.getSolutioInitials().length();
		exp = 7;
		assertEquals(exp, res);
		
		
		// VALORS NO-VÀLIDS
		
		gm.RandomRow(3);						// VALOR LÍMIT EXTERIOR
		res = gm.getSolutioInitials().length();
		exp = 0;
		assertEquals(exp, res);
		
		gm.RandomRow(-6);
		res = gm.getSolutioInitials().length();
		exp = 0;
		assertEquals(exp, res);
		
		gm.RandomRow(0);
		res = gm.getSolutioInitials().length();
		exp = 0;
		assertEquals(exp, res);
		
		
		gm.RandomRow(8);						// VALOR LÍMIT EXTERIOR
		res = gm.getSolutioInitials().length();
		exp = 0;
		assertEquals(exp, res);
		
		
		
	}
	
	@Test
	public void testGetColorInitial() throws IOException
	{
		String colorToTest = "", expected = "", result="";
		
		// PARTICIONS EQUIVALENTS

		GameModel gm = new GameModel();
		
		// Valors vàlids
		colorToTest = "verd";
		result = gm.GetColorInitial(colorToTest);
		expected = "v";
		assertEquals(expected, result);
		
		colorToTest = "groc";
		result = gm.GetColorInitial(colorToTest);
		expected = "g";
		assertEquals(expected, result);
		
		colorToTest = "blau";
		result = gm.GetColorInitial(colorToTest);
		expected = "b";
		assertEquals(expected, result);
		
		colorToTest = "marro";
		result = gm.GetColorInitial(colorToTest);
		expected = "m";
		assertEquals(expected, result);
		
		
		// Valors no-vàlids	
		colorToTest = "silver";
		result = gm.GetColorInitial(colorToTest);
		expected = null;
		assertEquals(expected, result);
		
		colorToTest = "daurat";
		result = gm.GetColorInitial(colorToTest);
		expected = null;
		assertEquals(expected, result);
	}
	 
	@Test
	public void testShuffleColors() throws IOException 
	{
		int expected, result, boles;
		GameModel gm = new GameModel();
		
		// PARTICIÓ EQUIVALENT
		
		// VALORS VÀLIDS
		boles = 4;
		gm.RandomRow(boles);
		result = gm.ShuffleColors().size(); 
		expected = 5;
		assertEquals(expected, result);
		
		boles = 5;
		gm.RandomRow(boles);
		result = gm.ShuffleColors().size(); 
		expected = 6;
		assertEquals(expected, result);
		
		boles = 6;
		gm.RandomRow(boles);
		result = gm.ShuffleColors().size(); 
		expected = 7;
		assertEquals(expected, result);
		
		boles = 7;
		gm.RandomRow(boles);
		result = gm.ShuffleColors().size(); 
		expected = 8;
		assertEquals(expected, result);
		
		// VALORS NO-VÀLIDS
		boles = 8;
		gm.RandomRow(boles);
		result = gm.ShuffleColors().size(); 
		expected = 0;
		assertEquals(expected, result);
		
		boles = -3;
		gm.RandomRow(boles);
		result = gm.ShuffleColors().size(); 
		expected = 0;
		assertEquals(expected, result);
		
		
		boles = 0;
		gm.RandomRow(boles);
		result = gm.ShuffleColors().size(); 
		expected = 0;
		assertEquals(expected, result);
		
		
		boles = 1;
		gm.RandomRow(boles);
		result = gm.ShuffleColors().size(); 
		expected = 0;
		assertEquals(expected, result);
		 
		
		// VALORS LÍMITS + FRONTERES
		
		// Frontera esquerra
		boles = 4;
		gm.RandomRow(boles);
		result = gm.ShuffleColors().size(); 
		expected = 5;
		assertEquals(expected, result);
		
		// Frontera dreta
		boles = 7;
		gm.RandomRow(boles);
		result = gm.ShuffleColors().size(); 
		expected = 8;
		assertEquals(expected, result);
		
		
		// Valors límit exteriors
		boles = 3;
		gm.RandomRow(boles);
		result = gm.ShuffleColors().size(); 
		expected = 0;
		assertEquals(expected, result);
		
		boles = 8;
		gm.RandomRow(boles);
		result = gm.ShuffleColors().size(); 
		expected = 0;
		assertEquals(expected, result);
		
		
		// Valors límit interiors + Valors interiors
		boles = 5;					
		gm.RandomRow(boles);
		result = gm.ShuffleColors().size(); 
		expected = 6;
		assertEquals(expected, result);
		
		boles = 6;
		gm.RandomRow(boles);
		result = gm.ShuffleColors().size(); 
		expected = 7;
		assertEquals(expected, result);
	}

}
