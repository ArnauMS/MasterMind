package Tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import Controller.PistesController;

class TestPistesController {
	PistesController pistes = new PistesController();
	
	// Test per comprovar que la comprovacio entre la solucio proposada per l'usuari i 
	// la solucio nostra funciona correctement i retorna un string amb les pistes esperades.
	@Test
	void testVerifyRow() {
		String expected;
		String userRow;
		String hintRow;
		String solutionRow;
		int numBoles;
		
		// PARTICIONS EQUIVALENTS
		// Totes les posicions correctes
		expected = "O O O O";
		userRow = "g m t v";
		hintRow = "- - - -";
		solutionRow = "g m t v";
		numBoles = 4;
		assertEquals(expected, pistes.VerifyRow(userRow, hintRow, solutionRow, numBoles));
		
		// Algunes posicions correctes i colors correctes pero mal posicionats
		expected = "O O X X";
		userRow = "g m v t";
		hintRow = "- - - -";
		solutionRow = "g m t v";
		numBoles = 4;
		assertEquals(expected, pistes.VerifyRow(userRow, hintRow, solutionRow, numBoles));
		
		// Colors correctes pero mal posicionats
		expected = "X X X X";
		userRow = "m g v t";
		hintRow = "- - - -";
		solutionRow = "g m t v";
		numBoles = 4;
		assertEquals(expected, pistes.VerifyRow(userRow, hintRow, solutionRow, numBoles));
		
		// Colors correctes pero mal posicionats i color malament
		expected = "X X X -";
		userRow = "m v g l";
		hintRow = "- - - -";
		solutionRow = "g m t v";
		numBoles = 4;
		assertEquals(expected, pistes.VerifyRow(userRow, hintRow, solutionRow, numBoles));
		
		// Colors ben posicionats i color malament
		expected = "O O O -";
		userRow = "g m t l";
		hintRow = "- - - -";
		solutionRow = "g m t v";
		numBoles = 4;
		assertEquals(expected, pistes.VerifyRow(userRow, hintRow, solutionRow, numBoles));
		
		// PATH COVERAGE
		// Color ben posicionat, colors correctes pero mal posicionats i color malament
		expected = "O X X -";
		userRow = "g v m l";
		hintRow = "- - - -";
		solutionRow = "g m t v";
		numBoles = 4;
		assertEquals(expected, pistes.VerifyRow(userRow, hintRow, solutionRow, numBoles));
	}
}
